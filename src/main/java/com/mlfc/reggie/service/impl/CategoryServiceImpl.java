package com.mlfc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlfc.reggie.entity.Category;
import com.mlfc.reggie.mapper.CategoryMapper;
import com.mlfc.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
