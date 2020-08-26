package com.jsrdxzw.shoppingmall.generator

import com.baomidou.mybatisplus.generator.AutoGenerator
import com.baomidou.mybatisplus.generator.config.*
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine


/**
 * @author  xuzhiwei
 * @date  2020/08/26
 */
fun main() {
    // 代码生成器
    val mpg = AutoGenerator()
    // 全局配置
    val gc = GlobalConfig().also {
        it.outputDir = "${System.getProperty("user.dir")}/src/main/kotlin"
        it.isOpen = false
        it.isFileOverride = true
        it.isKotlin = true
        it.author = "jsrdxzw"
    }
    // gc.setSwagger2(true); 实体属性 Swagger2 注解
    mpg.globalConfig = gc

    // 数据源配置
    val dsc = DataSourceConfig().also {
        it.url = "jdbc:mysql://localhost:3306/newbee_mall?useUnicode=true&autoReconnect=true&useSSL=false&allowMultiQueries=true"
        it.driverName = "com.mysql.cj.jdbc.Driver"
        it.username = "root"
        it.password = "root"
    }
    mpg.dataSource = dsc

    // 包配置
    mpg.packageInfo = PackageConfig().also {
        it.parent = "com.jsrdxzw.shoppingmall"
    }

    // 配置模板
    mpg.template = TemplateConfig().also {
        it.xml = null
        it.controller = null
        it.service = null
        it.serviceImpl = null
    }

    // 策略配置
    mpg.strategy = StrategyConfig().also {
        it.naming = NamingStrategy.underline_to_camel
        it.columnNaming = NamingStrategy.underline_to_camel
        it.setExclude("flyway_schema_history")
    }
    mpg.templateEngine = FreemarkerTemplateEngine()
    mpg.execute()
}
