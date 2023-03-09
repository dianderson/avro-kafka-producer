package br.com.handaltech.avrokafkaproducer.infrastructure.kafka.producers

import br.com.handaltech.avrokafkaproducer.avros.CustomerAvro
import br.com.handaltech.avrokafkaproducer.infrastructure.kafka.config.KafkaConfigProperties
import br.com.handaltech.avrokafkaproducer.infrastructure.kafka.models.CustomerDataModel
import br.com.handaltech.avrokafkaproducer.infrastructure.kafka.resources.CustomerPublisher
import org.apache.logging.log4j.LogManager
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.concurrent.TimeUnit

@Component
class CustomerKafkaProducer(
    kafkaConfigProperties: KafkaConfigProperties,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) : CustomerPublisher {
    private val logger = LogManager.getLogger(CustomerKafkaProducer::class.java)
    private val topicConfig = kafkaConfigProperties.getTopicByName("customer")
    override fun publish(customerDataModel: CustomerDataModel) {
        customerDataModel.convertToAvro()
            .buildMessageWithPayload()
            .publicWithCallback()
    }

    private fun CustomerDataModel.convertToAvro(): CustomerAvro = CustomerAvro.newBuilder()
        .setCustomerCode(customerCode)
        .setCustomerName(customerName)
        .setCustomerDocument(customerDocument)
        .setCustomerMail(customerMail)
        .build()

    private fun CustomerAvro.buildMessageWithPayload() = MessageBuilder.withPayload(this)
        .setHeader("version", "1.0.0")
        .setHeader("endOfLife", LocalDate.now().plusDays(topicConfig.ttlInDays))
        .setHeader("hash", customerCode)
        .setHeader("cid", customerCode)
        .setHeader(KafkaHeaders.TOPIC, topicConfig.name)
        .setHeader(KafkaHeaders.KEY, customerCode)
        .build()

    private fun Message<CustomerAvro>.publicWithCallback() {
        try {
            kafkaTemplate.send(this).get(50, TimeUnit.MILLISECONDS)
            logger.debug("Message posted: $this")
        } catch (ex: Exception) {
            logger.error("The message $this generated error $ex")
        }
    }
}