package br.com.handaltech.avrokafkaproducer.domains.customer.ports

import br.com.handaltech.avrokafkaproducer.domains.customer.models.CustomerModel

interface CustomerPort {
    fun publishMessage(customerModel: CustomerModel)
}