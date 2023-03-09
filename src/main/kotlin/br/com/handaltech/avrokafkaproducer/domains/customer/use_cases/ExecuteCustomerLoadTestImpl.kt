package br.com.handaltech.avrokafkaproducer.domains.customer.use_cases

import br.com.handaltech.avrokafkaproducer.domains.customer.models.CustomerLoadTestModel
import br.com.handaltech.avrokafkaproducer.domains.customer.models.CustomerModel
import br.com.handaltech.avrokafkaproducer.domains.customer.ports.CustomerPort
import br.com.handaltech.avrokafkaproducer.domains.customer.resources.ExecuteCustomerLoadTest
import br.com.handaltech.avrokafkaproducer.domains.customer.rules.generateCpf
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExecuteCustomerLoadTestImpl(
    private val customerPort: CustomerPort
) : ExecuteCustomerLoadTest {
    override fun execute(customerLoadTestModel: CustomerLoadTestModel) {
        with(customerLoadTestModel) {
            repeat(messageQuantity / quantityPerSecond) {
                executeAsync(quantityPerSecond, failurePercentage)
                Thread.sleep(1000L)
            }
            executeAsync(messageQuantity % quantityPerSecond, failurePercentage)
        }
    }

    @Async
    fun executeAsync(quantityPerSecond: Int, failurePercentage: Int) {
        repeat(quantityPerSecond) {
            customerPort.publishMessage(
                customerModel = buildMessage(it, quantityPerSecond, failurePercentage)
            )
        }
    }

    private fun buildMessage(current: Int, quantityPerSecond: Int, failurePercentage: Int): CustomerModel =
        when (val factor = (failurePercentage / 100.0 * quantityPerSecond).toInt()) {
            0 -> buildRandomCustomer(true)

            else -> (current % (quantityPerSecond / factor)).takeIf { it == 0 }
                ?.let { buildRandomCustomer(false) }
                ?: buildRandomCustomer(true)
        }

    private fun buildRandomCustomer(correctCpf: Boolean) = CustomerModel(
        customerCode = UUID.randomUUID().toString(),
        customerName = "Customer Name Cpf $correctCpf",
        customerDocument = generateCpf().takeIf { correctCpf } ?: "99999999999",
        customerMail = "email_cpf_$correctCpf@email.com"
    )
}