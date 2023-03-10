package br.com.handaltech.avrokafkaproducer.api.v1.resources

import br.com.handaltech.avrokafkaproducer.api.v1.models.LoadTestRequest
import br.com.handaltech.avrokafkaproducer.api.v1.ports.LoadTestPort
import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/v1/load-tests")
class LoadTestResource(
    private val loadTestPort: LoadTestPort
) {
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun customerLoadTest(
        @Valid @NotNull @Positive @RequestParam("message-quantity") messageQuantity: Int,
        @Valid @NotNull @Positive @RequestParam("quantity-per-second") quantityPerSecond: Int,
        @Valid @NotNull @Max(50) @RequestParam("failure-percentage-per-second") failurePercentagePerSecond: Int,
    ) {
        LoadTestRequest(
            messageQuantity = messageQuantity,
            quantityPerSecond = quantityPerSecond,
            failurePercentage = failurePercentagePerSecond
        ).also { loadTestPort.executeCustomerLoadTest(it) }
    }
}