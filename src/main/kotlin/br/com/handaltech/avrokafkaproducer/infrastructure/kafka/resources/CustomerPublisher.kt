package br.com.handaltech.avrokafkaproducer.infrastructure.kafka.resources

import br.com.handaltech.avrokafkaproducer.infrastructure.kafka.models.CustomerDataModel

interface CustomerPublisher {
    fun publish(customerDataModel: CustomerDataModel)
}