package com.spring.jdbctemplate.repository;

import com.spring.jdbctemplate.model.Emp;

import java.util.List;

public interface EmpRepo {
	public int save(Emp p);
	public int update(Emp p);
	public int delete(int id);
	public Emp getEmpById(int id);
	public List<Emp> getEmployees();
}
