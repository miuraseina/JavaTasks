package javaCodingTaskA1;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductMasterCSV {

	public static void read(String productmasterPath,List<ProductMaster>productMasterList) {

		Path ProductMasterCSV = Paths.get(productmasterPath);


		if (!Files.exists(ProductMasterCSV)) {
			throw new RuntimeException("商品マスタCSV格納ディレクトリが存在しません：" + ProductMasterCSV);
		}

		try (BufferedReader br = Files.newBufferedReader(ProductMasterCSV, StandardCharsets.UTF_8)) {



			String line;

			while ((line = br.readLine()) != null) {

				String[] cols = line.split(",");
				String productid = cols[0];
				String productname = cols[1];
				String category = cols[2];
				String unitPrice = cols[3];
				String idnumPart = productid.substring(1, 4); // 2〜4文字目




				if( productid.length() < 4 ) {
					System.out.println("不正な商品IDです：" + ProductMasterCSV + productid);
					continue;
				}
				if( !productid.startsWith("I")  ) {
					System.out.println("不正な商品IDです：" + ProductMasterCSV + productid);
					continue;
				}
				if (!idnumPart.matches("[0-9]{3}")) {
					System.out.println("不正な商品IDです：" + ProductMasterCSV + productid);
					continue;
				}
				if( productname==null ) {
					System.out.println("商品名が未入力です：" +  ProductMasterCSV + productid);
					continue;
				}
				if( category==null ) {
					System.out.println("カテゴリが未入力です：" +  ProductMasterCSV + productid);
					continue;
				}
				if( unitPrice==null ) {
					System.out.println("単価が未入力です：" +  ProductMasterCSV + productid);
					continue;
				}
				
				if( !unitPrice.matches("[0-9]+") || unitPrice.startsWith("0") ) {
					System.out.println("単価の金額が不正です：" +  ProductMasterCSV + productid);
					continue;
				}

				


				ProductMaster productmaster = new ProductMaster();


				productmaster.productid = productid;
				productmaster.productname = productname;
				productmaster.category = category;
				productmaster.unitPrice = unitPrice;


				productMasterList.add(productmaster);



			}
			br.close();
			
		} catch (Exception e) {
			System.out.println("商品マスタCSVファイルの読み込み中にエラーが発生しました"+ e);
			return;
		}

	}


}
