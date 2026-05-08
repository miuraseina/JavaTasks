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
				if (column.length < 3) {
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
			e.printStackTrace();
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
				if (column.length < 3) {
					continue;
				}

				int capitalId = Integer.parseInt(column[0]);
				String capitalName = column[1];



				Capital capital =
						new Capital(capitalId, capitalName);

				capitalMap.put(capitalId, capital);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		Path path = Paths.get(outputCsv);

		try {
			Files.createDirectories(path.getParent());

			try (BufferedWriter writer =
					Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {

				// ヘッダー
				writer.write("ID,Prefecture,Capital_City");
				writer.newLine();//改行

				// データ行
				for (Map.Entry<Integer, String> entry : PrefectureMaster.entrySet()) {
		            // getKey() は Integer 型なので int に変換可能
		            int id = entry.getKey(); // オートアンボクシング

		        }
				

			}


		}


	}
	}