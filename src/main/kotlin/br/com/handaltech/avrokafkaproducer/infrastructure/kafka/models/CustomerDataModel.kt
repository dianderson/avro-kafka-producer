package br.com.handaltech.avrokafkaproducer.infrastructure.kafka.models

data class CustomerDataModel(
    val customerCode: String,
    val customerName: String,
    val customerDocument: String,
    val customerMail: String
)