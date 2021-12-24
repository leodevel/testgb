package br.com.leodevel.testgb.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignClientConfig(
    @Value("\${api.cashback.token}") private val token: String
) {

    @Bean
    fun requestInterceptor(): RequestInterceptor? {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header("Content-Type", "application/json")
            requestTemplate.header("token", token)
        }
    }

}