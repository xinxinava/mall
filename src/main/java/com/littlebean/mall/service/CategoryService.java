package com.littlebean.mall.service;

import com.github.pagehelper.PageInfo;
import com.littlebean.mall.model.pojo.Category;
import com.littlebean.mall.model.request.AddCategoryReq;
import com.littlebean.mall.model.vo.CategoryVO;

import java.util.List;

public interface CategoryService {

    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
