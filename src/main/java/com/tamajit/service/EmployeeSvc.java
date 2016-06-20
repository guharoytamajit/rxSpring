package com.tamajit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rx.Observable;

import com.tamajit.bean.Employee;
import com.tamajit.dao.EmployeeDao;
import com.tamajit.exception.EmployeeNotFound;

@Service
public class EmployeeSvc {

	private EmployeeDao employeeDao;

	@Autowired
	public EmployeeSvc(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Employee empById(long id) {
		Employee employee = employeeDao.getbyId(id);
		if (employee == null) {
			throw new EmployeeNotFound("with ID:" + id);
		}
		return employee;
	}

	public List<Employee> empByName(String name) {
		return employeeDao.getbyName(name);
	}

	public List<Employee> empList() {
		return employeeDao.getAll();
	}

	public List<Employee> empByIds(Long... ids) {
		List<Employee> employees = new ArrayList<Employee>();
		for (Long id : ids) {
			Employee employee = employeeDao.getbyId(id);
			if (employee != null) {
				employees.add(employee);
			}
		}
		return employees;
	}

}
