//入力のwebサイト運営日数、キャンペーン実施日数、訪問者リストをもとに、
//平均訪問者数が最大となる連続する日数がいくつ存在するかと、
//その候補期間の中で最も早く始まる日を導出する。
package javaCodingTaskB1;

public class Main {

	public static void main(String[] args) {



		int websiteManagementDays = Integer.parseInt(args[0]);			//webサイト運営日数
		int campaignImplementationDays = Integer.parseInt(args[1]);		//キャンペーン実施日数

		int[] visitNumList = new int[websiteManagementDays];//webサイト運営日数分だけ入る int型の配列を作る
		for (int i = 0; i < websiteManagementDays; i++) {
			visitNumList[i] = Integer.parseInt(args[i + 2]);			//訪問者数リスト i[ + args[2] ]
		}


		/*
		 * 
		引数の並び：java Main 7 3 5 8 4 6 3 7 2

		webサイト運営日数
		キャンペーン実施日数
		以降：日ごとの訪問者数（運営日数分）

		 * */





		int maxVisitorCountSum = 0;		//最大訪問者数合計
		int candidatePeriodNumber = 1;	//候補期間数
		int campaignStartDate = 1;		//キャンペーン開始日

		int visitorCountSum = 0;		//訪問者数合計


		for (int i = 0; i < campaignImplementationDays; i++) {
			visitorCountSum += visitNumList[i];
		}
		maxVisitorCountSum = visitorCountSum;


		for (int i = 1; i <= websiteManagementDays - campaignImplementationDays; i++) {

			// 左端を引いて右端を足す
			visitorCountSum
			= visitorCountSum
			- visitNumList[i - 1]
					+ visitNumList[i + campaignImplementationDays - 1];

			if (visitorCountSum > maxVisitorCountSum) {
				maxVisitorCountSum = visitorCountSum;
				candidatePeriodNumber = 1;
				campaignStartDate = i + 1; // 開始日は1日目基準
			} else if (visitorCountSum == maxVisitorCountSum) {
				candidatePeriodNumber++;
			}
		}


		System.out.println(candidatePeriodNumber);
		System.out.println(campaignStartDate);
	}
}




