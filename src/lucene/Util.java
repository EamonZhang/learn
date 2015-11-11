package lucene;

public class Util {
	
	private static final double EARTH_RADIUS = 6378137;
	/**
	 * 
	 * return m（米）球面距离
	 */
	public static double twoPointDistance(double lat1,double lon1,double lat2,double lon2) {
		// 转单位为弧度
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		
		double radLng1 = rad(lon1);
		double radLng2 = rad(lon2);
		double b = radLng1 - radLng2;

//		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
//				+ Math.cos(radLat1) * Math.cos(radLat2)
//				* Math.pow(Math.sin(b / 2), 2)));
//		s = s * EARTH_RADIUS;
		//一下为球面距离公式，与以上计算结果进行比较，比较结果为误差非常小.
		double angle = Math.sin(radLat1)*Math.sin(radLat2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.cos(b);
		if(angle > 1){
			angle = 1;
		}
		double s = EARTH_RADIUS*Math.acos(angle) ;
		return s;
	}
	
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	public static void main(String[] args) {
//		String str1 = "内蒙古自治区;乌兰察布市;集宁区;工农大街(佳食面包北50米路西);;;;;;;;";
//		String str2 = "(";
//		int c = countStr(str1, str2);
//		System.out.println(c);
//		Date date = new Date();
//		Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
//        System.out.println(w);
		//获取新的地址和adminCode信息
//		String[] s = getCodeAndAddress("");
//		System.out.println(s[0]+"	"+s[1]);
//		System.out.println(nameAbbr("国家税务局"));
		
//		System.out.println(stringContains("中华人民共国和", "国中"));
//		System.out.println("".contains(null));
//		System.out.println(getCodeAndAddress(""));
	}
}
