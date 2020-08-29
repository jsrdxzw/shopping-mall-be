package com.jsrdxzw.shoppingmall

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.jsrdxzw.shoppingmall.mapper")
class ShoppingMallApplication

fun main(args: Array<String>) {
    runApplication<ShoppingMallApplication>(*args)
}
