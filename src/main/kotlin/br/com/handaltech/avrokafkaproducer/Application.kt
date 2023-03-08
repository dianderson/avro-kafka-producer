package br.com.handaltech.avrokafkaproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AvroKafkaProducerApplication

fun main(args: Array<String>) {
	runApplication<AvroKafkaProducerApplication>(*args)
}
