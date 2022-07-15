package com.littlebean.mall.model.dao;

import com.littlebean.mall.model.pojo.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("categoryMapper")
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Category selectByName(String name);

    List<Category> selectList();

    List<Category> selectCategoriesByParentId(Integer parentId);
}