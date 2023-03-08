package br.com.handaltech.avrokafkaproducer.infrastructure.kafka.config

import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.CreateTopicsResult
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicsConfig(
    private val kafkaConfigProperties: KafkaConfigProperties
) {
    @Bean
    fun createTopics(): CreateTopicsResult = kafkaConfigProperties.topics
        .buildNewTopics()
        .createTopics()

    private fun buildKafkaAdmin() = hashMapOf<String, Any>()
        .apply { this[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaConfigProperties.bootstrapServers }
        .let { KafkaAdmin(it) }

    private fun List<KafkaTopicsConfigModel>.buildNewTopics(): List<NewTopic> = this
        .map { NewTopic(it.name, it.numPartitions, it.replicationFactor) }

    private fun List<NewTopic>.createTopics() = AdminClient
        .create(buildKafkaAdmin().configurationProperties)
        .createTopics(this)
}