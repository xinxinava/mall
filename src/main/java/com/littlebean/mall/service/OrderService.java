package com.littlebean.mall.service;

import com.github.pagehelper.PageInfo;
import com.littlebean.mall.model.request.CreateOrderReq;
import com.littlebean.mall.model.vo.OrderVO;

public interface OrderService {
    String create(CreateOrderReq createOrderReq);

    OrderVO detail(String orderNo);

    PageInfo listForCustomer(Integer pageNum, Integer pageSize);

    void cancel(String orderNo);

    String qrcode(String orderNo);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    void pay(String orderNo);

    //发货
    void deliver(String orderNo);

    void finish(String orderNo);
}
