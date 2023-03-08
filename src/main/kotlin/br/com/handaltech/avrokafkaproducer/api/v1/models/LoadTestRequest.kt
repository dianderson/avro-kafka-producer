package br.com.handaltech.avrokafkaproducer.api.v1.models

data class LoadTestRequest(
    val messageQuantity: Int,
    val quantityPerSecond: Int,
    val failurePercentage: Int
)
