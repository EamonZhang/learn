package ly;

import java.security.MessageDigest;

public class ApiAuthUtil {
	private String module;
	private String category;
	private String methodName;
	private String accountId;
	private String password;
	public int allianceId;

	// / <summary>
	// / 请求时间
	// / </summary>
	private String requestTime;

	// / <summary>
	// / 数字签名
	// / </summary>
	private String digitalSign;

	/*
	 * 获取数字签名
	 */
	public String getDigitalSign() {
		return digitalSign;
	}

	/**
	 * 
	 * @param module
	 *            模块
	 * @param category
	 *            分类
	 * @param methodName
	 *            方法
	 * @param accountId
	 *            账号
	 * @param password
	 *            密码
	 * @param requestTime
	 *            请求时间字符串
	 * @param allianceId
	 *            联盟ID
	 */
	public ApiAuthUtil(String module, String category, String methodName,
			String accountId, String password, String requestTime,
			int allianceId) {
		this.requestTime = requestTime;
		this.module = module;
		this.category = category;
		this.methodName = methodName;
		this.accountId = accountId;
		this.password = password;
		this.allianceId = allianceId;
		EncryptDigitalSign();
	}

	/**
	 * 获取加入接口权限验证参数后的接口URL地址
	 * 
	 * @param apiUrl
	 *            原始接口地址
	 * @return
	 */
	public String getApiURL(String apiUrl) {
		return apiUrl + "?" + getAuthQeruyStringParams();
	}

	/**
	 * 获取权限验证参数字符串 用于GET方式提交到对应接口
	 * 
	 * @return
	 */
	public String getAuthQeruyStringParams() {
		return "allianceId=" + allianceId + "&digitalSign=" + digitalSign
				+ "&reqTime=" + requestTime;
	}

	/**
	 * 数字签名计算
	 */
	private void EncryptDigitalSign() {
		String fullActionName = module + "." + category + "." + methodName;
		String token = (fullActionName + "&" + accountId + "&" + password + "&" + requestTime)
				.toLowerCase();

		String digitalSign = SHA1Encrypt(token);
		this.digitalSign = digitalSign;
	}

	private String SHA1Encrypt(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

}