package br.com.handaltech.avrokafkaproducer.api.v1.resources

import br.com.handaltech.avrokafkaproducer.api.v1.models.LoadTestRequest
import br.com.handaltech.avrokafkaproducer.api.v1.ports.LoadTestPort
import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/v1/load-tests")
class LoadTestResource(
    private val loadTestPort: LoadTestPort
) {
    @PostMapping("/customers")
    fun customerLoadTest(
        @Valid @NotNull @Positive @RequestParam("message-quantity") messageQuantity: Int,
        @Valid @NotNull @Positive @RequestParam("quantity-per-second") quantityPerSecond: Int,
        @Valid @NotNull @Max(50) @RequestParam("failure-percentage-per-second") failurePercentage: Int,
    ) {
        LoadTestRequest(
            messageQuantity = messageQuantity,
            quantityPerSecond = quantityPerSecond,
            failurePercentage = failurePercentage
        ).also { loadTestPort.executeCustomerLoadTest(it) }
    }
}