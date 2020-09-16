package com.jsrdxzw.shoppingmall.config

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.Parameter
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration::class)
class SwaggerConfig {
    @Bean
    fun createDocket() = Docket(DocumentationType.SWAGGER_2).also {
        it.apiInfo(apiInfo())
                .groupName("C端API文档")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jsrdxzw.shoppingmall"))
                .paths(PathSelectors.any())
                .build()
        val tokenPar = ParameterBuilder()
        val pars = mutableListOf<Parameter>()
        tokenPar.name("Authorization").description("token令牌").modelRef(ModelRef("string")).parameterType("header").required(false).build()
        pars.add(tokenPar.build())
        it.globalOperationParameters(pars)
    }

    @Bean
    fun apiInfo(): ApiInfo = ApiInfoBuilder()
            .title("商城API文档")
            .description("商城C端API文档")
            .contact(Contact("XUZHIWEI", "www.github.com/jsrdxzw", "jsrdxzw@163.com"))
            .version("1.0")
            .build()
}
