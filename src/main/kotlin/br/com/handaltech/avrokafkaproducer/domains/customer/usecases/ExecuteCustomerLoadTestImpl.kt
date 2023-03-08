package br.com.handaltech.avrokafkaproducer.domains.customer.usecases

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
            repeat((messageQuantity / quantityPerSecond)) {
                executeAsync(quantityPerSecond, failurePercentage)
                Thread.sleep(1000L)
            }
        }
    }

    @Async
    fun executeAsync(quantityPerSecond: Int, failurePercentage: Int) {
        repeat(quantityPerSecond) {
            customerPort.publishMessage(
                buildRandomCustomer(getRandomFactor(it, quantityPerSecond, failurePercentage))
            )
        }
    }

    private fun buildRandomCustomer(correctCpf: Boolean) = CustomerModel(
        customerCode = UUID.randomUUID().toString(),
        customerName = "Customer Name Cpf $correctCpf",
        customerDocument = generateCpf().takeIf { correctCpf } ?: "99999999999",
        customerMail = "email_cpf_$correctCpf@email.com"
    )

    private fun getRandomFactor(current: Int, quantityPerSecond: Int, failurePercentage: Int): Boolean =
        when (failurePercentage) {
            0 -> true
            else -> true.takeUnless { current % (quantityPerSecond / failurePercentage) == 0 } ?: false
        }
}