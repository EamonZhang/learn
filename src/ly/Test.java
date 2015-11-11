package ly;
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		ApiAuthUtil apiAuthUtil = new ApiAuthUtil("DiyTour", "Query",
				"GetGroupBuyResources", "xxxxxx",
				"xxxx", "2013-09-04 15:35:40.191",
				12);
		System.out.println(apiAuthUtil.getDigitalSign());
		System.out.println(apiAuthUtil.getAuthQeruyStringParams());
		System.out
				.println(apiAuthUtil
						.getApiURL("http://localhost/openapi/holiday/DiyTour/Query/GetGroupBuyResources"));
	}

}