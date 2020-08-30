package com.jsrdxzw.shoppingmall.config

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
@Configuration
class MybatisConfig {
    @Bean
    fun paginationInterceptor() = PaginationInterceptor().also {
        it.setCountSqlParser(JsqlParserCountOptimize(true))
    }
}
