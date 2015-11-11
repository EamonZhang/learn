package matcher;

public class Test {
//	验证手机号码:13[0-9]{9} 
//	实现手机号前带86或是+86的情况:^((\+86)|(86))?(13)\d{9}$ 
//	电话号码与手机号码同时验证:(^(\d{3,4}-)?\d{7,8})$|(13[0-9]{9})  
//	提取信息中的中国手机号码:(86)*0*13\d{9}     
//	提取信息中的中国固定电话号码:(\(\d{3,4}\)|\d{3,4}-|\s)?\d{8}     
//	提取信息中的中国电话号码（包括移动和固定电话）:(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}     
	 static String regPattern = "^13[0-9a-z]"; 
	public static void main(String[] args) {
		String str1 = "133";
		boolean matched = str1.matches(regPattern);
		System.out.println(matched);
	}
}