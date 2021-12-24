package br.com.leodevel.testgb.client

import br.com.leodevel.testgb.config.FeignClientConfig
import br.com.leodevel.testgb.dto.response.CashbackResponseDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "cashback-client",
    value = "cashback-client",
    url = "\${api.cashback}",
    configuration = [FeignClientConfig::class]
)
interface CashbackClient {

    @GetMapping("/v1/cashback")
    fun getAccumulated(@RequestParam cpf: String): CashbackResponseDTO

}