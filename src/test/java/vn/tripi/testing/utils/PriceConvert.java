package vn.tripi.testing.utils;

public class PriceConvert {
	public static int getPrice(String price) {
		String priceStr = price.replaceAll("\\D+", "");
		return Integer.parseInt(priceStr);
	}
}
