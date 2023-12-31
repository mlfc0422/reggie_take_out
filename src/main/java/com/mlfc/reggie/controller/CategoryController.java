package com.mlfc.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlfc.reggie.common.Rest;
import com.mlfc.reggie.entity.Category;
import com.mlfc.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Rest<String> save(@RequestBody Category category){
        log.info("新增分类:{}",category);
        categoryService.save(category);
        return Rest.success("新增成功");
    }

    @GetMapping("/page")
    public Rest<Page> page(int page,int pageSize){
        log.info("查询分类列表:page={},pageSize={}",page,pageSize);
        Page<Category> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return Rest.success(pageInfo);
    }

    @DeleteMapping
    public Rest<String> delete(Long id){//@RequestParam("ids")未尝试的方法，现在是修改了category的js
        log.info("删除分类id为:{}",id);
        categoryService.remove(id);
//        categoryService.removeById(id);
        return Rest.success("删除成功");
    }

    @PutMapping
    public Rest<String> update(@RequestBody Category category){
        log.info("修改分类:{}",category);
        categoryService.updateById(category);
        return Rest.success("修改成功");
    }

    @GetMapping("/list")
    public Rest<List<Category>> list(Category category){
        log.info("查询菜品分类下拉表单");
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(queryWrapper);
        return Rest.success(list);
    }
}
