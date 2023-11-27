package com.mlfc.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mlfc.reggie.dto.DishDto;
import com.mlfc.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
}
