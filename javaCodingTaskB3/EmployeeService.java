//PRの送信
package javaCodingTaskB3;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

	public List<Employee> salaryComparison(List<Employee> employees, int standard) {

		List<Employee> highSalaryEmployees = new ArrayList<>();


		for (Employee emp : employees) {

			if (emp.getSalary() >= standard) {
				highSalaryEmployees.add(emp);
			}
		}

		return highSalaryEmployees;
	}
}



