//プルリクエストの送信
package javaCodingTaskB3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {


	public List<Employee> readCsv() {


		Path path = Paths.get("resources/社員.csv");

		if (!Files.exists(path)) {
			throw new RuntimeException("CSVが見つかりません。");
		}




		List<Employee> employees = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

			String line;
			boolean isFirstLine = true; 

			while ((line = br.readLine()) != null) {



				if (isFirstLine) {
					isFirstLine = false;
					continue; // ヘッダを飛ばす
				}

				if (line.isEmpty()) {
					continue;
				}
				String[] column = line.split(",");
				if (column.length < 5) {
					continue;
				}

				Employee emp = new Employee(
						Integer.parseInt(column[0]),
						column[1],
						column[2],
						Integer.parseInt(column[3]),
						Integer.parseInt(column[4])
						);
				
				
				employees.add(emp);//格納

			}


		} catch (IOException e) {
			e.printStackTrace();
		}

		return employees;//戻り値


	}
}
























