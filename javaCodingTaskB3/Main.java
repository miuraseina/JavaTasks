//プルリクエストの送信
package javaCodingTaskB3;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("給与基準額を入力してください");
		try (Scanner sc = new Scanner(System.in)) {
			int payrollStandardAmount = sc.nextInt();


			CsvReader reader = new CsvReader();
			List<Employee> employees = reader.readCsv();

			EmployeeService service = new EmployeeService();

			List<Employee> result = service.salaryComparison(employees, payrollStandardAmount);

			for (Employee emp : result) {
				System.out.println(emp);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

}
