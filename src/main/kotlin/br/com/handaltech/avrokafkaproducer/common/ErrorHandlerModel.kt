package br.com.handaltech.avrokafkaproducer.common

import java.time.Instant

data class ErrorHandlerModel(
    val timestamp: Instant,
    val httpStatus: Int,
    val errors: Set<ErrorMessage>,
    val path: String
)
