package com.project.dev_ops.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun eDesignApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).enable(true).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().pathMapping("/").directModelSubstitute(LocalDate::class.java, String::class.java)
                .genericModelSubstitutes(ResponseEntity::class.java).useDefaultResponseMessages(false)
                //     .globalOperationParameters(builder())
                .enableUrlTemplating(false)
    }

    private fun apiInfo(): ApiInfo {
        val title = "DevOps API"
        val description = "Sample Swagger implementation for the `DevOps` service, leveraging annotations at the controller-method level."
        return ApiInfoBuilder()
                .title(title)
                .description(description)
                .license("By freeway core")
                .version("1.0").build()
    }

    /* fun builder(): List<Parameter?> {
         val tokenValue = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJ1c2VybmFtZVwiOlwiRlJFRVNVUFwifSJ9.dQ0AI4p02NuNWcTrbxtrvTi2O9bZ0VmB--aRgFJKe7-OKkf7v46VHgSWkrDwOV3m8xbUC7n5yFnLjVuwbjic2A"
         val tokenKey = "X-AUTH-TOKEN"
         val parameterBuilder = ParameterBuilder()
         parameterBuilder.name(tokenKey)
                 .modelRef(ModelRef("string"))
                 .parameterType("header")
                 .defaultValue(tokenValue)
                 .required(true)
                 .build()
         return ArrayList<Any?>(setOf(parameterBuilder.build()))
     }*/
}