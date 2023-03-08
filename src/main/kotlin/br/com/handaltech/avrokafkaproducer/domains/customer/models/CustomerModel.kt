package br.com.handaltech.avrokafkaproducer.domains.customer.models

data class CustomerModel(
    val customerCode: String,
    val customerName: String,
    val customerDocument: String,
    val customerMail: String
)
