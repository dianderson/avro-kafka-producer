package br.com.handaltech.avrokafkaproducer.common

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@EnableAsync
@Configuration
class ThreadsSettings : AsyncConfigurer {
    @Bean
    fun taskExecutor(): Executor = ThreadPoolTaskExecutor()
        .apply { corePoolSize = 50 }
        .apply { maxPoolSize = 100 }
        .apply { initialize() }
}