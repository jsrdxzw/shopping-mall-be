package com.jsrdxzw.shoppingmall.web.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.jsrdxzw.shoppingmall.annotation.TokenToMallUser
import com.jsrdxzw.shoppingmall.entity.MallOrder
import com.jsrdxzw.shoppingmall.service.MallOrderService
import com.jsrdxzw.shoppingmall.util.ResultData
import com.jsrdxzw.shoppingmall.web.bo.PageRequest
import com.jsrdxzw.shoppingmall.web.bo.SaveOrderBo
import com.jsrdxzw.shoppingmall.web.vo.MallOrderDetailVo
import com.jsrdxzw.shoppingmall.web.vo.MallOrderListVo
import com.jsrdxzw.shoppingmall.web.vo.MallUserLogin
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
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

    @GetMapping
    @ApiOperation(value = "订单列表接口", notes = "传参为页码")
    fun orderList(pageRequest: PageRequest<MallOrder>,
                  @ApiParam(value = "订单状态:0.待支付 1.待确认 2.待发货 3:已发货 4.交易成功") @RequestParam(required = false) status: Int?,
                  @TokenToMallUser loginMallUser: MallUserLogin
    ): ResultData<IPage<MallOrderListVo>> {
        return ResultData.success(mallOrderService.orderList(pageRequest, status, loginMallUser))
    }

    @PutMapping("/cancel/{orderNumber}")
    @ApiOperation(value = "订单取消接口", notes = "传参为订单号")
    fun cancelOrder(@ApiParam(value = "订单号") @PathVariable("orderNumber") orderNumber: String, @TokenToMallUser loginMallUser: MallUserLogin): ResultData<*> {
        mallOrderService.cancelOrder(orderNumber, loginMallUser)
        return ResultData.SUCCESS
    }

    @PutMapping("/finish/{orderNumber}")
    @ApiOperation(value = "确认收货接口", notes = "传参为订单号")
    fun finishOrder(@ApiParam(value = "订单号") @PathVariable("orderNumber") orderNumber: String, @TokenToMallUser loginMallUser: MallUserLogin): ResultData<*> {
        mallOrderService.finishOrder(orderNumber, loginMallUser)
        return ResultData.SUCCESS
    }

    @GetMapping("/paySuccess")
    @ApiOperation(value = "模拟支付成功回调的接口", notes = "传参为订单号和支付方式")
    fun paySuccess(@ApiParam(value = "订单号") @RequestParam("orderNumber") orderNumber: String, @ApiParam(value = "支付方式") @RequestParam("payType") payType: Int): ResultData<*> {
        mallOrderService.paySuccess(orderNumber, payType)
        return ResultData.SUCCESS
    }

}
