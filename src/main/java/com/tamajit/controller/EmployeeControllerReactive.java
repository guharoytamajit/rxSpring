package com.tamajit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import rx.Observable;

import com.tamajit.bean.Employee;
import com.tamajit.service.EmployeeSvcReactive;

@RestController
@RequestMapping("rx")
public class EmployeeControllerReactive {
	@Autowired
	private EmployeeSvcReactive empSvc;

	@RequestMapping(value = "emp/{id}", method = RequestMethod.GET)
	public DeferredResult<Employee> empById(@PathVariable long id) {
		Observable<Employee> o = empSvc.empById(id);
		return createDeferredResult(o);
	}

	private <E> DeferredResult<E> createDeferredResult(Observable<E> o) {
		DeferredResult<E> deffered = new DeferredResult<E>(90000l);
		o.subscribe(m -> deffered.setResult(m), e -> deffered.setErrorResult(e));
		return deffered;
	}

	@RequestMapping(value = "emp/name/{name}", method = RequestMethod.GET)
	public DeferredResult<List<Employee>> empById(@PathVariable String name) {
		Observable<List<Employee>> empByName = empSvc.empByName(name);
		return createDeferredResult(empByName);
	}

	@RequestMapping(value = "emp", method = RequestMethod.GET)
	public DeferredResult<List<Employee>> empList() {
		Observable<List<Employee>> empList = empSvc.empList();
		return createDeferredResult(empList);
	}
	
	@RequestMapping(value = "emp/ids", method = RequestMethod.GET)
	public DeferredResult<List<Employee>> empList(@RequestParam Long... ids) {
		Observable<List<Employee>> empList = empSvc.empByIds(ids);
		return createDeferredResult(empList);
	}
}
