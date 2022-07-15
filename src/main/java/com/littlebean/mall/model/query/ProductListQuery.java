package com.littlebean.mall.model.query;

import com.littlebean.mall.model.pojo.Product;

import java.util.List;

public class ProductListQuery {

    private String keyword;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    private List<Integer> categoryIds;
}
