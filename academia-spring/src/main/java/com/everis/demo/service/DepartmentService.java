package com.everis.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.demo.model.Department;
import com.everis.demo.repository.DepartmentRepository;

@Transactional
@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> listAll() {
		return departmentRepository.listAll();
	}
	
	public Optional<Department> findByID(Long Id) {
		return departmentRepository.findById(Id);
	}
}
