package br.com.handaltech.avrokafkaproducer.main.adapters.domains

import br.com.handaltech.avrokafkaproducer.domains.customer.models.CustomerModel
import br.com.handaltech.avrokafkaproducer.domains.customer.ports.CustomerPort
import br.com.handaltech.avrokafkaproducer.infrastructure.kafka.resources.CustomerPublisher
import br.com.handaltech.avrokafkaproducer.main.converters.toCustomerDataModel
import org.springframework.stereotype.Component

@Component
class CustomerPortAdapter(
    private val customerPublisher: CustomerPublisher
) : CustomerPort {
    override fun publishMessage(customerModel: CustomerModel) {
        customerPublisher.publish(customerModel.toCustomerDataModel())
    }
}