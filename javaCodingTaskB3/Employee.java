//PRの送信
package javaCodingTaskB3;



public class Employee {


	private int id;// ←変数を定義

	private String name;

	private String department;

	private int age;

	private int salary;


	public Employee(int id, String name, String department, int age, int salary) {
		this.id = id;// ←「値を代入している」フィールド   引数

		this.name = name;

		this.department = department;

		this.age = age;

		this.salary = salary;
	}



	public int getSalary() {
		return salary;
	}
//オブジェクトの中身を人が読める形で表示するためのメソッド 
// toString()オブジェクトを文字列に変換 return表示したい内容
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Age: " + age + ", Salary: "+ salary;
	}





}
