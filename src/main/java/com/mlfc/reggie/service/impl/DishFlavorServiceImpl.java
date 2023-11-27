package com.mlfc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlfc.reggie.dto.DishDto;
import com.mlfc.reggie.entity.DishFlavor;
import com.mlfc.reggie.mapper.DishFlavorMapper;
import com.mlfc.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {

}
