package br.com.handaltech.avrokafkaproducer.main.converters

import br.com.handaltech.avrokafkaproducer.api.v1.models.LoadTestRequest
import br.com.handaltech.avrokafkaproducer.domains.customer.models.CustomerLoadTestModel

fun LoadTestRequest.toCustomerLoadTestModel() = CustomerLoadTestModel(
    messageQuantity = messageQuantity,
    quantityPerSecond = quantityPerSecond,
    failurePercentage = failurePercentage
)