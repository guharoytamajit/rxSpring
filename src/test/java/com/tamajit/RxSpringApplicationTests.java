package com.tamajit;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import rx.Observable;

import com.tamajit.bean.Employee;
import com.tamajit.service.EmployeeSvcReactive;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RxSpringApplication.class)
@WebAppConfiguration
public class RxSpringApplicationTests {

	@Autowired
	EmployeeSvcReactive es;
	@Test
	public void contextLoads() {
		Observable<List<Employee>> empByIds = es.empByIds(1l,2l,3l,4l,5l,6l,7l,8l);
		empByIds.subscribe(System.out::println);
		
	}

}
