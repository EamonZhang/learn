package httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;

public class HttpGetTest {
	public static void main(String[] args) throws Exception {
		JSONObject pagingParameterJson = new JSONObject();
		pagingParameterJson.put("PageIndex", 1);
		pagingParameterJson.put("PageSize", 100);
		
		JSONObject requstBodyJson = new JSONObject();
		requstBodyJson.put("DistributionChannel", 9);
		requstBodyJson.put("SearchParameter", new JSONObject());
		requstBodyJson.put("PagingParameter", pagingParameterJson);
		
		JSONObject paramsJson = new JSONObject();
		paramsJson.put("TimeStamp", "1442972570");
		paramsJson.put("Signature", "7E64F7FAD8D08E2D7133C18B7E29AFDE");
		paramsJson.put("RequestBody", requstBodyJson.toString());
		paramsJson.put("ResponseBody", "");
		paramsJson.put("AllianceID", "4304");
		paramsJson.put("SID", "451013");
		paramsJson.put("Channel", "Vacations"); 
		paramsJson.put("Interface", "TicketSenicSpotSearch");
		paramsJson.put("ProtocolType", 1);
		paramsJson.put("Channel", "Vacations");
		paramsJson.put("ErrorMessage", "");
		paramsJson.put("IsError", false);

		JSONObject requestJson = new JSONObject();
		requestJson.put("RequestJson", paramsJson);
		System.out.println(requestJson);
		HttpClient client = new HttpClient();
		//GET 方式
//		PostMethod method = new PostMethod(
//				"http://openapi.ctrip.com/vacations/OpenServer.ashx?RequestJson="+URLEncoder.encode(paramsJson.toString(),"GB2312"));

		//POST 方式
		PostMethod method = new PostMethod(
				"http://openapi.ctrip.com/vacations/OpenServer.ashx");
		NameValuePair mvp = new NameValuePair("RequestJson",paramsJson.toString());
		method.setRequestBody(new NameValuePair[]{mvp});
		
//		RequestEntity requestEntity = new StringRequestEntity(requestJson.toString(), "application/json", "GB2312");
//		method.setRequestEntity(requestEntity);
		
//		method.addParameter("RequestJson",paramsJson.toString());
		
		int status = client.executeMethod(method);
		if (status == 200) {
			String result = method.getResponseBodyAsString();
			System.out.println(result);
		}
		method.releaseConnection();
	}
}
