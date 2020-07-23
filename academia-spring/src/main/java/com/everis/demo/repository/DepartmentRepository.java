package com.everis.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.everis.demo.model.Department;

@Repository
public class DepartmentRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Department> listAll(){
		return entityManager.createQuery(
				"select d from Department d", Department.class).getResultList();
	}
	
	public Optional<Department> findById(Long Id){
		return Optional.ofNullable(entityManager.find(Department.class, Id, LockModeType.WRITE));
	}
}
