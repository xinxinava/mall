package com.littlebean.mall.controller;

import com.github.pagehelper.PageInfo;
import com.littlebean.mall.common.ApiRestResponse;
import com.littlebean.mall.common.Constant;
import com.littlebean.mall.exception.MallExceptionEnum;
import com.littlebean.mall.model.pojo.Category;
import com.littlebean.mall.model.pojo.User;
import com.littlebean.mall.model.request.AddCategoryReq;
import com.littlebean.mall.model.request.UpdateCategoryReq;
import com.littlebean.mall.model.vo.CategoryVO;
import com.littlebean.mall.service.CategoryService;
import com.littlebean.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class CategoryController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    /**
     * 后台添加目录
     */

    @ApiOperation("后台添加目录")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session,
                                       @Valid @RequestBody AddCategoryReq addCategoryReq){
        User currentUser= (User) session.getAttribute(Constant.MALL_USER);
        if(currentUser == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        boolean adminRole = userService.checkAdminRole(currentUser);
        if(adminRole){
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();
        }else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }

    /**
     * 后台添加目录
     */

    @ApiOperation("后台更新目录")
    @PostMapping("/admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(@Valid @RequestBody UpdateCategoryReq updateCategoryReq,
                                          HttpSession session){
        User currentUser= (User) session.getAttribute(Constant.MALL_USER);
        if(currentUser == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        boolean adminRole = userService.checkAdminRole(currentUser);
        if(adminRole){
            Category category=new Category();
            BeanUtils.copyProperties(updateCategoryReq, category);
            categoryService.update(category);
            return ApiRestResponse.success();
        }else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }

    @ApiOperation("后台删除目录")
    @PostMapping("/admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestParam Integer id){
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台目录列表")
    @GetMapping("/admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize){
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("前台目录列表")
    @PostMapping("/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForCustomer(){
        List<CategoryVO> categoryVOS = categoryService.listCategoryForCustomer(0);
        return ApiRestResponse.success(categoryVOS);
    }
}
