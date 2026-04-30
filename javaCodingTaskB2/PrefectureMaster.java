package javaCodingTaskB2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PrefectureMaster {


	private Map<Integer, Prefecture> prefectureMap;
	private Map<Integer, Capital> capitalMap;


	public PrefectureMaster() {
		prefectureMap = new HashMap<>();
		capitalMap    = new HashMap<>();
	}


	public void consistencyAdjustment(
			String prefectureCsv,
			String capitalsCsv,
			String outputCsv
			) {


		try (BufferedReader br =
				Files.newBufferedReader(
						Paths.get(prefectureCsv),
						StandardCharsets.UTF_8)) {

			String line;
			boolean isFirstLine = true;

			while ((line = br.readLine()) != null) {

				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				if (line.isEmpty()) {
					continue;
				}

				String[] column = line.split(",");
				if (column.length < 2) {
					continue;
				}

				int id = Integer.parseInt(column[0]);
				String name = column[1];
				int capitalCityId = Integer.parseInt(column[2]);

				// Prefectureを作ってMapへ
				Prefecture prefecture =
						new Prefecture(id, name, capitalCityId);

				prefectureMap.put(id, prefecture);
			}

		} catch (Exception e) {
			System.out.println("prefectureCsvを読み込む際にエラーが発生しました");
		}

		try (BufferedReader br =
				Files.newBufferedReader(
						Paths.get(capitalsCsv),
						StandardCharsets.UTF_8)) {

			String line;
			boolean isFirstLine = true;

			while ((line = br.readLine()) != null) {

				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				if (line.isEmpty()) {
					continue;
				}

				String[] column = line.split(",");
				if (column.length < 2) {
					continue;
				}

				int capitalId = Integer.parseInt(column[0]);
				String capitalName = column[1];



				Capital capital =
						new Capital(capitalId, capitalName);

				capitalMap.put(capitalId, capital);
			}

		} catch (Exception e) {
			System.out.println("capitalsCsvを読み込む際にエラーが発生しました");
		}


		Path path = Paths.get(outputCsv);

		try {
			Files.createDirectories(path.getParent());

			try (BufferedWriter writer =
					Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {

				// ヘッダー
				writer.write("ID,Prefecture,Capital_City");
				writer.newLine();

				// PrefectureMap を1件ずつ処理
				for (Map.Entry<Integer, Prefecture> entry : prefectureMap.entrySet()) {

					int id = entry.getKey();
					Prefecture prefecture = entry.getValue();

					// 県庁所在地を取得
					Capital capital =
							capitalMap.get(prefecture.getCapitalCityId());

					if (capital != null) {

						String line =
								id + "," +
										prefecture.getName() + "," +
										capital.getCapitalName() + "\n";

						writer.write(line);
					}
				}


			} 
		}catch (Exception e) {
			System.out.println("都道府県情報CSVの出力でエラーが発生しました");
			e.printStackTrace();
			return;

		}

		System.out.println("都道府県情報.csv ファイルが作成されました");



	}
}