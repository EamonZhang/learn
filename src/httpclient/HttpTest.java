package httpclient;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpTest {
	public static void main(String[] args) throws Exception {
//		HttpClientParams params = new HttpClientParams();
//		params.setConnectionManagerTimeout(50 * 1000);
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(
				"http://openapi.ctrip.com/vacations/OpenServer.ashx");
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

		System.out.println(requestJson.toString());
		method.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GB2312");
//		method.addRequestHeader("Content-Type", "application/json; charset=GB2312");
		NameValuePair mvp = new NameValuePair("RequestJson",paramsJson.toString());
		method.setRequestBody(new NameValuePair[]{mvp});
		int status = client.executeMethod(method);
		if (status == 200) {
			String result = method.getResponseBodyAsString();
			System.out.println(result);
		}
		method.releaseConnection();
	}
	
	 public static JSONObject doPost(String url,JSONObject json){
		    DefaultHttpClient client = new DefaultHttpClient();
		    HttpPost post = new HttpPost(url);
		    JSONObject response = null;
		    try {
		      StringEntity s = new StringEntity(json.toString());
		      s.setContentEncoding("GB2312");
		      s.setContentType("application/json");//发送json数据需要设置contentType
		      post.setEntity(s);
		      HttpResponse res = client.execute(post);
		      if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
		        HttpEntity entity = res.getEntity();
		        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
		        System.out.println(result);
//		        response = JSONObject.fromObject(result);
		      }
		    } catch (Exception e) {
		      throw new RuntimeException(e);
		    }
		    return response;
		  }
}
