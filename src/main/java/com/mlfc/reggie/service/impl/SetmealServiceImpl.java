package com.mlfc.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlfc.reggie.entity.Setmeal;
import com.mlfc.reggie.mapper.SetmealMapper;
import com.mlfc.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
