package com.everis.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.demo.model.Department;
import com.everis.demo.service.DepartmentService;

@RestController
@RequestMapping("api/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping
	public List<Department> getAllDepatment() {
		return departmentService.listAll();
	}
	
	@GetMapping(value = "/{id}")
	public Department getDepatment(@PathVariable("id") Long Id) {
		return departmentService.findByID(Id).get();
	}
}
