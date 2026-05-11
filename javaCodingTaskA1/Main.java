package javaCodingTaskA1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		List<CustomerMaster> customerMasterList = new ArrayList<>();
		List<ProductMaster> productMasterList= new ArrayList<>();
		List<OrderInformation> orderInformationList= new ArrayList<>();
		Map<String, Integer> salesTotalAmountMapByOrder = new HashMap<>(); 
		


		String customermasterPath =
				LoadingPropertyFile.getCustomerMasterPath();
		String productmasterPath =
				LoadingPropertyFile.getProductMasterPath();
		String orderinformationpath =
				LoadingPropertyFile.getOrderInformationPath();



		CustomerMasterCSV.read(customermasterPath, customerMasterList);
		ProductMasterCSV.read(productmasterPath, productMasterList);
		OrderInformationCSV.read(orderinformationpath,orderInformationList);
		
		ReturnResult result = OrderPriceCalculatingProcess.calculate(
				customerMasterList,
				productMasterList,
				orderInformationList,
				salesTotalAmountMapByOrder
				);

		System.out.println(result.totalSales);
		System.out.println(result.salesTotalAmountMapByOrder);



	}



}
