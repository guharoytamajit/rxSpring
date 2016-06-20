package com.tamajit.bean;

public class Employee {
	long id;
	String name;
	String location;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Employee(long id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", location="
				+ location + "]";
	}

	public Employee() {
		super();
	}
}
