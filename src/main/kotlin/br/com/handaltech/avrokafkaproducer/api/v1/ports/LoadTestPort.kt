package br.com.handaltech.avrokafkaproducer.api.v1.ports

import br.com.handaltech.avrokafkaproducer.api.v1.models.LoadTestRequest

interface LoadTestPort {
    fun executeCustomerLoadTest(loadTestRequest: LoadTestRequest)
}