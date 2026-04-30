package javaCodingTaskB1;

public class StartDateDerivationProcess {

	public int[] consistencyAdjustment(
			int  websiteManagementDays,
			int campaignImplementationDays,
			int[] visitNumList
			) {

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


		return new int[]{candidatePeriodNumber, campaignStartDate};
	}




}