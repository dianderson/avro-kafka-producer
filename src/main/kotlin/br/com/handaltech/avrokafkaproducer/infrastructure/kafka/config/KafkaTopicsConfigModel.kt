package br.com.handaltech.avrokafkaproducer.infrastructure.kafka.config

data class KafkaTopicsConfigModel(
    val name: String,
    val numPartitions: Int,
    val replicationFactor: Short,
    val ttlInDays: Long
)