package com.tamajit.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import rx.Observable;

import com.tamajit.bean.Employee;

@Repository
public class EmployeeDao {

	public EmployeeDao() {
		super();
		emplist.add(new Employee(1, "jack", "germany"));
		emplist.add(new Employee(2, "robert", "uk"));
		emplist.add(new Employee(3, "jack", "spain"));
		emplist.add(new Employee(4, "robin", "australia"));
		emplist.add(new Employee(5, "alex", "uk"));
		emplist.add(new Employee(6, "jim", "poland"));
		emplist.add(new Employee(7, "mark", "mexico"));
		emplist.add(new Employee(8, "mark", "canada"));
		emplist.add(new Employee(9, "bill", "usa"));
		emplist.add(new Employee(10, "louis", "uk"));
		emplist.add(new Employee(11, "jack", "south africa"));
	}

	List<Employee> emplist = new ArrayList<>();

	public Employee getbyId(Long id) {
		log("EmployeeDao#getbyId()");
		sleep(500);
		for (Employee e : emplist) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public Observable<Employee> getbyIdRx(Long id) {
		return Observable.create(subscriber -> {
			subscriber.onNext(getbyId(id));
			subscriber.onCompleted();
		});
	}


	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getbyName(String name) {
		List<Employee> newEmplist = new ArrayList<>();
		for (Employee e : emplist) {
			if (e.getName().equals(name)) {
				newEmplist.add(e);
			}
		}
		return newEmplist;
	}

	public List<Employee> getAll() {
		return emplist;

	}
	 void log(Object label) {

		System.out.println(Thread.currentThread().getName() + "\t| " + label);
	}
}
