package com.jsrdxzw.shoppingmall.config

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
@Configuration
class MybatisConfig {
    @Bean
    fun paginationInterceptor() = PaginationInnerInterceptor()
}
