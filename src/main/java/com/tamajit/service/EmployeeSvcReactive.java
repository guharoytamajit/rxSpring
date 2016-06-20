package com.tamajit.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rx.Observable;
import rx.Scheduler;

import com.tamajit.bean.Employee;
import com.tamajit.dao.EmployeeDao;
import com.tamajit.exception.EmployeeNotFound;

@Service
public class EmployeeSvcReactive {
	private EmployeeDao employeeDao;
	@Autowired
	Scheduler employeeScheculer;

	@Autowired
	public EmployeeSvcReactive(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Observable<Employee> empById(long id) {
		return Observable.<Employee> create(s -> {
			Employee employee = employeeDao.getbyId(id);
			if (employee != null) {
				s.onNext(employee);
				s.onCompleted();
			} else {
				s.onError(new EmployeeNotFound("with ID:" + id));
			}
		}).subscribeOn(employeeScheculer);

	}

	public Observable<List<Employee>> empByName(String name) {
		return Observable.<List<Employee>> just(employeeDao.getbyName(name))
				.subscribeOn(employeeScheculer);
	}

	public Observable<List<Employee>> empList() {
		return Observable.<List<Employee>> just(employeeDao.getAll());
	}

	public Observable<List<Employee>> empByIds(Long... ids) {
		return Observable
				.from(ids)
				.flatMap(
						id -> employeeDao.getbyIdRx(id).subscribeOn(
								employeeScheculer)).toList();
	}
}
