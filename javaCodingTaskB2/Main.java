//プルリクエストを送信
package javaCodingTaskB2;

public class Main {

	public static void main(String[] args) {


		String prefectureCsv = "resources/都道府県.csv";
		String capitalsCsv   = "resources/県庁所在地.csv";
		String outputCsv     = "resources/都道府県情報.csv";

		PrefectureMaster master = new PrefectureMaster();


		master.consistencyAdjustment(
				prefectureCsv,
				capitalsCsv,
				outputCsv
				);
		
	}
}



