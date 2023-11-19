package com.mlfc.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlfc.reggie.common.CustomException;
import com.mlfc.reggie.entity.Category;
import com.mlfc.reggie.entity.Dish;
import com.mlfc.reggie.entity.Setmeal;
import com.mlfc.reggie.mapper.CategoryMapper;
import com.mlfc.reggie.service.CategoryService;
import com.mlfc.reggie.service.DishService;
import com.mlfc.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        long count1 = dishService.count(dishLambdaQueryWrapper);
        if (count1>0){
            throw new CustomException("该分类下有菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        long count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2>0){
            throw new CustomException("该分类下有套餐，不能删除");
        }

        super.removeById(id);
    }
}
