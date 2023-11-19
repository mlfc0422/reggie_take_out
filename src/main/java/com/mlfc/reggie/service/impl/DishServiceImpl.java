package com.mlfc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlfc.reggie.entity.Dish;
import com.mlfc.reggie.mapper.DishMapper;
import com.mlfc.reggie.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
