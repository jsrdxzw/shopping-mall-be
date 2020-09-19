package com.jsrdxzw.shoppingmall.web.bo

import com.baomidou.mybatisplus.extension.plugins.pagination.Page

/**
 * @author  xuzhiwei
 * @date  2020/09/19
 */
data class PageRequest<T>(val page: Long = 1, val pageSize: Long = 10) : Page<T>(page, pageSize)
