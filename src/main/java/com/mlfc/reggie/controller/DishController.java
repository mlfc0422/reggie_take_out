package com.mlfc.reggie.controller;

import com.mlfc.reggie.common.Rest;
import com.mlfc.reggie.dto.DishDto;
import com.mlfc.reggie.entity.Dish;
import com.mlfc.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;
    @PostMapping()
    public Rest<String> save(@RequestBody DishDto dishDto){
        log.info("新增菜品:{}",dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return Rest.success("新增菜品成功");
    }
}
