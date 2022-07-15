package com.littlebean.mall.service;

import com.github.pagehelper.PageInfo;
import com.littlebean.mall.model.pojo.Product;
import com.littlebean.mall.model.request.AddProductReq;
import com.littlebean.mall.model.request.ProductListReq;

public interface ProductService {
    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    Product detail(Integer id);

    PageInfo list(ProductListReq productListReq);
}
