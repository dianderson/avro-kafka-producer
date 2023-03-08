package br.com.handaltech.avrokafkaproducer.main.adapters.api.v1

import br.com.handaltech.avrokafkaproducer.api.v1.models.LoadTestRequest
import br.com.handaltech.avrokafkaproducer.api.v1.ports.LoadTestPort
import br.com.handaltech.avrokafkaproducer.domains.customer.resources.ExecuteCustomerLoadTest
import br.com.handaltech.avrokafkaproducer.main.converters.toCustomerLoadTestModel
import org.springframework.stereotype.Component

@Component
class LoadTestPortAdapter(
    private val executeCustomerLoadTest: ExecuteCustomerLoadTest
) : LoadTestPort {
    override fun executeCustomerLoadTest(loadTestRequest: LoadTestRequest) {
        executeCustomerLoadTest.execute(loadTestRequest.toCustomerLoadTestModel())
    }
}