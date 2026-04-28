
package javaCodingTaskB2;

public class Main {

	public static void main(String[] args) {

		// ①〜③ CSVパス
		String prefectureCsv = "resources/都道府県.csv";
		String capitalsCsv   = "resources/県庁所在地.csv";
		String outputCsv     = "resources/都道府県情報.csv";

		// ④ PrefectureMasterをインスタンス化
		PrefectureMaster master = new PrefectureMaster();

		// ⑤ 処理実行
		master.consistencyAdjustment(
				prefectureCsv,
				capitalsCsv,
				outputCsv
				);
	}
}



