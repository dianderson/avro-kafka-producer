package br.com.handaltech.avrokafkaproducer.main.converters

import br.com.handaltech.avrokafkaproducer.domains.customer.models.CustomerModel
import br.com.handaltech.avrokafkaproducer.infrastructure.kafka.models.CustomerDataModel

fun CustomerModel.toCustomerDataModel() = CustomerDataModel(
    customerCode = customerCode,
    customerName = customerName,
    customerDocument = customerDocument,
    customerMail = customerMail
)