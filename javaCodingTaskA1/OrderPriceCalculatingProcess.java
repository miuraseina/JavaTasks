package javaCodingTaskA1;

import java.util.List;
import java.util.Map;

public class OrderPriceCalculatingProcess {


	private static CustomerMaster findCustomer(
			List<CustomerMaster> customerMasterList,
			String customerId) {

		for (CustomerMaster c : customerMasterList) {
			if (c.customerid.equals(customerId)) {
				return c;
			}
		}
		return null; // 見つからない場合
	}



	private static ProductMaster findProduct(
			List<ProductMaster> productMasterList,
			String productId) {

		for (ProductMaster c : productMasterList) {
			if (c.productid.equals(productId)) {
				return c;
			}
		}
		return null; 
	}


	public static ReturnResult calculate(
			List<CustomerMaster> customerMasterList,
			List<ProductMaster> productMasterList,
			List<OrderInformation> orderInformationList,
			Map<String, Integer> salesTotalAmountMapByOrder
			) {




		for (OrderInformation orderinfo : orderInformationList) {

			CustomerMaster customermaster =
					findCustomer(customerMasterList, orderinfo.customerId);

			if (customermaster == null) {
				System.out.println(
						"顧客マスタに存在しない顧客IDです：" + orderinfo.customerId
						);
				continue;
			}

			int totalAmountEachOrder = 0;

			for (OrderingProduct product : orderinfo.orderProductsList) {

				ProductMaster productmaster =
						findProduct(productMasterList, product.orderingproductid);

				if (productmaster == null) {
					System.out.println(
							"商品マスタに存在しない商品IDです：" + product.orderingproductid
							);
					continue;
				}

				int totalAmountEachProduct =
						Integer.parseInt(productmaster.unitPrice) * product.quantity;

				double priceAfterDiscount = totalAmountEachProduct;

				// カテゴリ割引
				if ("家電".equals(productmaster.category)) {
					priceAfterDiscount *= 0.95;
				} else if ("衣服".equals(productmaster.category)) {
					priceAfterDiscount *= 0.9;
				}

				// 会員割引
				if ("ゴールド".equals(customermaster.membersLevel)) {
					priceAfterDiscount *= 0.95;
				} else if ("プラチナ".equals(customermaster.membersLevel)) {
					priceAfterDiscount *= 0.8;
				}

				totalAmountEachOrder += (int) priceAfterDiscount;
			}

			totalAmountEachOrder = (int)(totalAmountEachOrder * 1.1);


			salesTotalAmountMapByOrder.put(orderinfo.orderId, totalAmountEachOrder);
		}


		int totalSales = 0;
		for (int amount : salesTotalAmountMapByOrder.values()) {
			totalSales += amount;
		}

		// 7 返却
		ReturnResult result = new ReturnResult();
		result.totalSales = totalSales;
		result.salesTotalAmountMapByOrder = salesTotalAmountMapByOrder;

		return result;
	}
}
