package com.mlfc.reggie.controller;

import com.mlfc.reggie.common.Rest;
import com.mlfc.reggie.entity.Category;
import com.mlfc.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
