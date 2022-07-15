package com.littlebean.mall.model.dao;

import com.littlebean.mall.model.pojo.OrderItem;
import com.littlebean.mall.model.vo.OrderVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("orderItemMapper")
public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> selectByOrderNo(String orderNo);
}