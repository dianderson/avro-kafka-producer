package br.com.handaltech.avrokafkaproducer.infrastructure.kafka.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "configs.kafka")
data class KafkaConfigProperties(
    var bootstrapServers: String = "",
    var schemaRegistryUrl: String = "",
    var topics: List<KafkaTopicsConfigModel> = listOf()
) {
    fun getTopicByName(topicName: String): KafkaTopicsConfigModel =
        topics.firstOrNull { it.name.contains(topicName) }
            ?: throw ClassNotFoundException("Properties to topic name $topicName not found")
}