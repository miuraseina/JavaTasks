package javaCodingTaskC2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		// 1. 日付入力チェック
		Scanner sc = new Scanner(System.in);
		System.out.println("日付を指定してください");
		String input = sc.nextLine();

		DateTimeFormatter inputFormatter =
				DateTimeFormatter.ofPattern("yyyyMMdd");



		LocalDate today = LocalDate.now();
		LocalDate inputDate;

		try {
			inputDate = LocalDate.parse(input, inputFormatter);//入力（yyyyMMdd）→ LocalDate に変換 

			if (inputDate.isAfter(today)) {
				System.out.println("未来の日付は指定できません");
				return;
			}
		} catch (DateTimeParseException e) {
			System.out.println("その日付は無効です");
			return;
		}

		// 2. CSV 読み込み
		InputStream is = Main.class.getClassLoader()
				.getResourceAsStream("javaCodingTaskC2/resources/購入履歴.csv");


		if (is == null) {
			throw new RuntimeException("CSVが見つかりません");
		}

		List<String> lines = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(is, StandardCharsets.UTF_8))) {
			/*
linesにはlines = ["2024/07/01,[4567, 1234, 987, 5432, 6789]","2024/07/11,[8765, 3456, 1234, 9876, 2345]",
  ...
];
,区切りで一行でなっている

			 */
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}


		// 3. CSV解析
		DateTimeFormatter csvFormatter =
				DateTimeFormatter.ofPattern("yyyy/MM/dd");


		LocalDate targetDay =  null;
		List<Integer> amounts = null;
		boolean found = false;
		boolean isFirstLine = true;

		/*
		for (String line : lines)一行ずつ処理
		ヘッダーがある一行目を飛ばす→これをしないとDateTimeParseExceptionになった
		1回目line = "2024/07/01,[4567, 1234, 987, 5432, 6789]"
		 */


		for (String line : lines) {
			if (isFirstLine) {
				isFirstLine = false;
				continue; // ヘッダを飛ばす
			}



			// 「day,amount」を2つに分ける
			/*
		split(",", 2) を使うと
		parts[0] = "2024/07/01"
		parts[1] = "[4567, 1234, 987, 5432, 6789]"
			 */
			String[] parts = line.split(",", 2);
			LocalDate day = LocalDate.parse(parts[0], csvFormatter);

			if (day.equals(inputDate)) {
				found = true;
				targetDay = day;

				//amount 部分から [ ] を消す
				String amountPart = parts[1]
						.replace("[", "")
						.replace("]", "");

				amounts = Arrays.stream(amountPart.split(","))//"4567, 1234, 987" →["4567", " 1234", " 987"]
						.map(String::trim)//" 1234" → "1234"
						.map(Integer::parseInt)//"1234" → 1234文字列 → 数値
						.collect(Collectors.toList());//List<Integer> = [4567, 1234, 987, 5432, 6789]
				break;
			}
		}

		if (!found) {
			System.out.println("購入履歴が存在しません");
			return;
		}

		// 4. 合計計算
		int totalAmount = amounts.stream()
				.mapToInt(Integer::intValue)
				.sum();

		String dayStr = String.valueOf(targetDay.getDayOfMonth());

		int point;
		if (dayStr.contains("3")) {
			point = (int)(totalAmount * 0.03);
		} else if (dayStr.contains("5")) {
			point = (int)(totalAmount * 0.05);
		} else {
			point = (int)(totalAmount * 0.01);
		}
		System.out.println(point);






	}

}

