package javaCodingTaskB3;



public class Employee {


	private int id;

	private String name;

	private String department;

	private int age;

	private int salary;


	public Employee(int id, String name, String department, int age, int salary) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.age = age;
		this.salary = salary;
	}



	public int getSalary() {
		return salary;
	}

	public String toString() {
		return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Age: " + age + ", Salary: "+ salary;
	}





}
