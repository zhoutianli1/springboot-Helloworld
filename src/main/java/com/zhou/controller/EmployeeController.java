package com.zhou.controller;

import com.zhou.dao.DepartmentDao;
import com.zhou.dao.EmployeeDao;
import com.zhou.dao.pojo.Department;
import com.zhou.dao.pojo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
//显示员工列表 跳转到emp/list.html
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/emps")
    public String list(Model model)
    {
        Collection<Employee> employees=employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }
    @GetMapping("/emp")
    public String toAddpage(Model model)
    {
        //要在添加之前拿到所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
    //添加员工业务逻辑 ： 点击添加员工，跳转到"emp/add.html"页面。在add页面输入数据，点击添加，将数据保存到数据库
    @PostMapping("/emp")
    public String addEmp(Employee employee)
    {
        //将添加的员工保存数据到数据库，
        System.out.println("save=>employee");
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}