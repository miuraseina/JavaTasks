//修正済み
package javaCodingTaskC3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		
		if (args.length== 0) {
			System.out.println("「迷惑メールを判別するワードを入力してください:」");
			return;
		}

		String strargs = args[0];

		List<String> rejectedmails = new ArrayList<>();
		List<String> remainingMails = new ArrayList<>();
		int count = 0;

		Path path = Paths.get("resources/受信メール.csv");

		if (!Files.exists(path)) {
			throw new RuntimeException("CSVが見つかりません");
		}

		List<String> lines = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 3. CSV解析
		boolean isFirstLine = true;


		for (String line : lines) {
			if (isFirstLine) {
				isFirstLine = false;
				continue; // ヘッダを飛ばす
			}




			String[] parts = line.split(",");


			String title = parts[0]
					.replace("[", "")
					.replace("]", "");

			String text = parts[1]
					.replace("[", "")
					.replace("]", "");





			if (title.contains(strargs)||text.contains(strargs)) {
				rejectedmails.add(line);
				count ++;
			}else {
				remainingMails.add(line);}
		}


		String fileName = "迷惑メール.csv";
		Path paths = Paths.get("output", fileName);

		try {
			// outputディレクトリが無ければ作成
			Files.createDirectories(paths.getParent());

			try (BufferedWriter writer =
					Files.newBufferedWriter(paths, StandardCharsets.UTF_8)) {

				// ヘッダー
				writer.write("{title},{text}");
				writer.newLine();//改行

				// データ行
				for (String str : rejectedmails) {
					writer.write(str);
					writer.newLine();//繰り返し改行しながら要素を書き出す
				}
			}


			Path receivePath = Paths.get("output", "受信メール.csv");

			Files.createDirectories(receivePath.getParent());

			try (BufferedWriter writer =
					Files.newBufferedWriter(receivePath, StandardCharsets.UTF_8)) {

				writer.write("{title},{text}");
				writer.newLine();

				for (String str : remainingMails) {
					writer.write(str);
					writer.newLine();


				}
				if(count == 0) {
					System.out.println("「対象の[入力]{ワード}を含むメールは検出されませんでした」");

				}
			}

			System.out.println("対象の単語:[入力]ワードを含むメールを"+ count +"件検出しました。迷惑メールにメールを移動します」");

		} catch (IOException e) {
			throw new RuntimeException("ファイル処理中にエラーが発生しました", e);
		}




	}

}
