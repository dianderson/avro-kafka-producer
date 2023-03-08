package br.com.handaltech.avrokafkaproducer.domains.customer.models

data class CustomerLoadTestModel(
    val messageQuantity: Int,
    val quantityPerSecond: Int,
    val failurePercentage: Int
)
