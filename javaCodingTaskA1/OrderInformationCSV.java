package javaCodingTaskA1;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OrderInformationCSV {

	public static void read(String orderinformationpath,List<OrderInformation>orderInformationList) {

		Path OrderInformationCSV = Paths.get(orderinformationpath);

		try (BufferedReader br = Files.newBufferedReader(OrderInformationCSV, StandardCharsets.UTF_8)) {
/*4-1：注文単位（OrderInformation）
4-1-3：注文商品のループ（OrderingProduct）
注文情報は 1回作る
注文商品は リストにして中に持たせる
最後に 注文情報リストに add
			 */
			String line;

			while ((line = br.readLine()) != null) {

				String[] cols = line.split(",");

				if (cols.length != 4) {
					System.out.println("注文情報が不足しています：" + line);
					continue;
				}

				String orderId = cols[0];
				String customerId = cols[1];
				String orderDate = cols[2];
				String orderProductsListStr = cols[3];

				OrderInformation orderInformation = new OrderInformation();
				orderInformation.orderId = orderId;
				orderInformation.customerId = customerId;
				orderInformation.orderDate = orderDate;


				String[] orderProducts = orderProductsListStr.split("\\|");
				
				
				List<OrderingProduct> orderProductList = new ArrayList<>();
				

				for (String order : orderProducts) {

					String[] parts = order.split(":");

					if (parts.length != 2) {
						System.out.println("注文商品情報が不正です：" + order);
						continue;
					}

					OrderingProduct product = new OrderingProduct();
					product.orderingproductid = parts[0];

					try {
						product.quantity = Integer.parseInt(parts[1]);
					} catch (NumberFormatException e) {
						System.out.println("注文商品数量が不正です：" + order);
						continue;
					}

					orderProductList.add(product);
				}



				orderInformation.orderProductsList = orderProductList;
				orderInformationList.add(orderInformation);


			}
			br.close();
			
		} catch (Exception e) {
			System.out.println(
					"注文情報CSVファイルの読み込み中にエラーが発生しました：" + e
					);
		}


	}
}




