package com.tamajit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tamajit.bean.Employee;
import com.tamajit.service.EmployeeSvc;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeSvc empSvc;

	@RequestMapping(value = "emp/{id}", method = RequestMethod.GET)
	public Employee empById(@PathVariable long id) {
		return empSvc.empById(id);
	}

	@RequestMapping(value = "emp/name/{name}", method = RequestMethod.GET)
	public List<Employee> empById(@PathVariable String name) {
		return empSvc.empByName(name);
	}

	@RequestMapping(value = "emp", method = RequestMethod.GET)
	public List<Employee> empList() {
		return empSvc.empList();
	}
	
	@RequestMapping(value = "emp/ids", method = RequestMethod.GET)
	public List<Employee> empList(@RequestParam Long... ids) {
		return empSvc.empByIds(ids);
	}
}
