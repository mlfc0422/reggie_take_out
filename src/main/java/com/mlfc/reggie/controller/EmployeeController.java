package com.mlfc.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlfc.reggie.common.Rest;
import com.mlfc.reggie.entity.Employee;
import com.mlfc.reggie.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Rest<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        if(emp==null)
        {
            return Rest.error("登陆失败");
        }
        if(!emp.getPassword().equals(password))
        {
            return Rest.error("密码错误");
        }
        if(emp.getStatus()==0)
        {
            return Rest.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());

        return Rest.success(emp);
    }

    @PostMapping("/logout")
    public Rest<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return Rest.success("退出成功");
    }

    @PostMapping
    public Rest<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("新增员工:{}",employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);
        return Rest.success("新增员工成功");
    }

    @GetMapping("/page")
    public Rest<Page> page(int page,int pageSize,String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);
        return Rest.success(pageInfo);
    }

    @PutMapping
    public Rest<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info("员工信息:{}",employee.toString());
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return Rest.success("修改员工信息成功");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Rest<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息,id={}",id);
        Employee employee = employeeService.getById(id);
        if (employee!=null){
            return Rest.success(employee);
        }
        return Rest.error("未找到员工信息");
    }
}
