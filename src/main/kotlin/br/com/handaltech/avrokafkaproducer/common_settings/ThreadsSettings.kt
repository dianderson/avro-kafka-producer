package br.com.handaltech.avrokafkaproducer.common_settings

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@EnableAsync
@Configuration
class ThreadsSettings {
    @Bean
    fun taskExecutor(): Executor = ThreadPoolTaskExecutor()
        .apply { corePoolSize = 20 }
        .apply { maxPoolSize = 50 }
        .apply { initialize() }
}