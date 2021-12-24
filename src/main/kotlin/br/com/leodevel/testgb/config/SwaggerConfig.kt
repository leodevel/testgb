package br.com.leodevel.testgb.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun docketBean(): Docket {
        val defaultErrors: MutableList<ResponseMessage> = mutableListOf()
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.leodevel.testgb.controller"))
            .build()
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, defaultErrors)
            .globalResponseMessage(RequestMethod.POST, defaultErrors)
            .globalResponseMessage(RequestMethod.PUT, defaultErrors)
            .globalResponseMessage(RequestMethod.DELETE, defaultErrors)
    }

    fun apiInfo(): ApiInfo {
        return ApiInfo(
            "API - Teste Grupo Boticário - Cashback",
            "Sistema para os revendedores(as) cadastrarem suas compras e acompanhar o retorno de seu cashback",
            "1.0",
            "Terms of service",
            Contact("Leonardo Barbosa", "www.linkedin.com/in/leonardo-dos-santos-barbosa-4845bbba", "leobar1995@gmail.com"),
            "License of API",
            "API license URL",
            Collections.emptyList()
        )
    }

}