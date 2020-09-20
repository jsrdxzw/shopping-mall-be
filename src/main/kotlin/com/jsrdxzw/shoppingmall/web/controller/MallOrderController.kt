package com.jsrdxzw.shoppingmall.web.controller

import com.jsrdxzw.shoppingmall.annotation.TokenToMallUser
import com.jsrdxzw.shoppingmall.service.MallOrderService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.bo.SaveOrderBo
import com.jsrdxzw.shoppingmall.web.vo.MallOrderDetailVo
import com.jsrdxzw.shoppingmall.web.vo.MallUserLogin
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author  xuzhiwei
 * @date  2020/09/20
 */
@Validated
@RestController
@Api(value = "v1", tags = ["新蜂商城订单操作相关接口"])
@RequestMapping("/order")
class MallOrderController {
    @Autowired
    private lateinit var mallOrderService: MallOrderService

    @PostMapping("/saveOrder")
    @ApiOperation(value = "生成订单接口", notes = "传参为地址id和待结算的购物项id数组")
    fun saveOrder(@Valid @RequestBody saveOrderBo: SaveOrderBo, @TokenToMallUser loginMallUser: MallUserLogin): ResultData<String> {
        return ResultData.success(mallOrderService.saveOrder(saveOrderBo, loginMallUser))
    }

    @GetMapping("/{orderNumber}")
    @ApiOperation(value = "订单详情接口")
    fun orderDetail(@PathVariable("orderNumber") orderNumber: String, @TokenToMallUser loginMallUser: MallUserLogin): ResultData<MallOrderDetailVo> {
        return ResultData.success(mallOrderService.getOrderDetail(orderNumber, loginMallUser))
    }
}
