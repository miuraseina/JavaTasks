package javaCodingTaskA1;

public class LoadingPropertyFile {

	public static String getCustomerMasterPath() {

		String customermasterpath = System.getProperty("customers.dir");
		if (customermasterpath == null) {
			throw new RuntimeException("顧客マスタ、または商品マスタが取得できません。");
		}

		return customermasterpath;
	}
	public static String getProductMasterPath() {


		String productmasterpath = System.getProperty("items.dir");
		if (productmasterpath==null) {
			throw new RuntimeException("顧客マスタ、または商品マスタが取得できません。");
		}


		return productmasterpath;


	}
	public static String getOrderInformationPath() {


		String orderinformationpath = System.getProperty("order.dir");
		if (orderinformationpath==null) {
			throw new RuntimeException("注文情報が取得できません。");
		}


		return orderinformationpath;


	}



}
