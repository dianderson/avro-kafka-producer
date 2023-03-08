package br.com.handaltech.avrokafkaproducer.domains.customer.resources

import br.com.handaltech.avrokafkaproducer.domains.customer.models.CustomerLoadTestModel

interface ExecuteCustomerLoadTest {
    fun execute(customerLoadTestModel: CustomerLoadTestModel)
}