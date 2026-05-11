package javaCodingTaskA1;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CustomerMasterCSV {

	public static void read(String customermasterPath,List<CustomerMaster> customerMasterList) {

		Path CustomerMasterCSV = Paths.get(customermasterPath);


		if (!Files.exists(CustomerMasterCSV)) {
			throw new RuntimeException("顧客マスタCSV格納ディレクトリが存在しません：" + CustomerMasterCSV);
		}

		try (BufferedReader br = Files.newBufferedReader(CustomerMasterCSV, StandardCharsets.UTF_8)) {



			String line;

			while ((line = br.readLine()) != null) {

				String[] cols = line.split(",");
				String customerid = cols[0];
				String customername = cols[1];
				String membersLevel = cols[2];
				String numPart = customerid.substring(1, 4); // 2〜4文字目



				if( customerid.length() < 4 ) {
					System.out.println("不正な顧客IDです：" + CustomerMasterCSV + customerid);
					continue;
				}
				if( !customerid.startsWith("C")  ) {
					System.out.println("不正な顧客IDです：" + CustomerMasterCSV + customerid);
					continue;
				}
				if (!numPart.matches("[0-9]{3}")) {
					System.out.println("不正な顧客IDです：" + CustomerMasterCSV + customerid);
					continue;
				}
				if( customername==null ) {
					System.out.println("顧客名が未入力です：" + CustomerMasterCSV + customerid);
					continue;
				}
				if( membersLevel==null ) {
					System.out.println("会員レベルが未入力です：" + CustomerMasterCSV + customerid);
					continue;
				}

				
				
				CustomerMaster customermaster = new CustomerMaster();


				customermaster.customerid = customerid;
				customermaster.customername = customername;
				customermaster.membersLevel = membersLevel;

				customerMasterList.add(customermaster);






			}
			br.close();
			
		} catch (Exception e) {
			System.out.println("顧客マスタCSVファイルの読み込み中にエラーが発生しました"+ e);
			return;
		}
		
		

	}

}