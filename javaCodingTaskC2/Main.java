//プルリクエストを送る
package javaCodingTaskC2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

		sc.close();



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


		// 2. CSV 読み込み ＋ 3. CSV解析（逐次処理）
		InputStream is = Main.class.getClassLoader()
				.getResourceAsStream("javaCodingTaskC2/resources/購入履歴.csv");

		if (is == null) {
			throw new RuntimeException("CSVが見つかりません");
		}

		DateTimeFormatter csvFormatter =
				DateTimeFormatter.ofPattern("yyyy/MM/dd");

		LocalDate targetDay = null;
		List<Integer> amounts = null;
		boolean found = false;

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(is, StandardCharsets.UTF_8))) {

			String line;
			boolean isFirstLine = true; // ヘッダ対策

			while ((line = br.readLine()) != null) {

				/*
				for (String line : lines)一行ずつ処理
				ヘッダーがある一行目を飛ばす→これをしないとDateTimeParseExceptionになった
				1回目line = "2024/07/01,[4567, 1234, 987, 5432, 6789]"
				 */


				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				// 空行対策
				if (line.isEmpty()) {
					continue;
				}


				// 「day,amount」を2つに分ける
				/*
					split(",", 2) を使うと
					parts[0] = "2024/07/01"
					parts[1] = "[4567, 1234, 987, 5432, 6789]"
				 */

				String[] parts = line.split(",", 2);
				if (parts.length < 2) {
					continue;
				}

				LocalDate day = LocalDate.parse(parts[0], csvFormatter);

				if (day.equals(inputDate)) {
					found = true;
					targetDay = day;

					// amount 部分の解析
					String amountPart = parts[1]
							.replace("[", "")
							.replace("]", "");

					amounts = Arrays.stream(amountPart.split(","))
							.map(String::trim)
							.map(Integer::parseInt)
							.collect(Collectors.toList());

					break; // 見つかったら即終了
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return;
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

		final double RATE_POINT_THREE = 0.03; 
		final double RATE_POINT_FIVE = 0.05; 
		final double RATE_POINT_ONE = 0.01;

		int point;
		if (dayStr.contains("3")) {
			point = (int)(totalAmount *  RATE_POINT_THREE);
		} else if (dayStr.contains("5")) {
			point = (int)(totalAmount * RATE_POINT_FIVE);
		} else {
			point = (int)(totalAmount * RATE_POINT_ONE);
		}
		System.out.println(point);


	}

}
