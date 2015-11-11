package httpclient;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class DaZhongDianPingDealDataHandler {
	/**
	 * Create one, use global
	 */
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private static final String appKey = "4986383667";
	private static final String secret = "636eb914dcb543d396b5cbf244bf1da3";
	// 大众城市列表
	public static List<String> requestCityList() throws JsonProcessingException, IOException{
		String apiUrl = "http://api.dianping.com/v1/metadata/get_cities_with_deals";

		String jsonResult = DemoApiTool.requestApi(apiUrl, appKey, secret, new HashMap<String, String>());
		JsonNode tree = JSON_MAPPER.readTree(jsonResult);
		JsonNode citiesNode = tree.get("cities");
		List<String> cities = new ArrayList<String>();
		for (Iterator<JsonNode> iterator = citiesNode.getElements(); iterator.hasNext();) {
			JsonNode node = iterator.next();
			if(node.getTextValue().equals("全国")){
				continue;
			}
			cities.add(node.getTextValue());
		}
		return cities;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	public static void main(String[] args) throws Exception {
				startDownLoad();
	}
	
	public static void startDownLoad(){
			try {
				// 大众城市列表
				List<String> daZhCitys = requestCityList();
				for (String cityName : daZhCitys) {
					System.out.println(cityName+"_"+new Date());
					convertToXml(cityName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}

	public static void convertToXml(String cityName) throws JsonProcessingException, IOException{
		List<String> dealIds = new ArrayList<String>();
		String apiUrl = "http://api.dianping.com/v1/deal/get_all_id_list";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("city", cityName);
		// paramMap.put("category", "美食");
		String jsonResult = DemoApiTool.requestApi(apiUrl, appKey, secret, paramMap);
		JsonNode tree = JSON_MAPPER.readTree(jsonResult);
		JsonNode idsNode = tree.get("id_list");
		for (Iterator<JsonNode> iterator = idsNode.getElements(); iterator.hasNext();) {
			JsonNode node = iterator.next();
			dealIds.add(node.getTextValue());
		}
		System.out.println("size : " +dealIds.size());
		List<List<String>> batchIdsList = splitList(dealIds, 40);
		List<Map<String, Object>> dealDataList = new ArrayList<Map<String, Object>>();
		for (List<String> batchIds : batchIdsList) {
			List<Map<String, Object>> batchDataList = requestBatchDeals(appKey, secret, batchIds);
			if (batchDataList != null && !batchDataList.isEmpty()) {
				dealDataList.addAll(batchDataList);
			} else {
				// System.err.println("发生错误城市："+city);
			}
		}
		
		for (int i = 0; i < dealDataList.size(); i++) {
			Map<String, Object> o = (Map<String, Object>)dealDataList.get(i);
			String dealId = new Date().getTime()+"";
			for (String s :o.keySet()) {
				if(s.equals("deal_id")){
					dealId = o.get(s)+"";
					break;
				}
			}
			File file= new File("/home/zhangjin/data/dazhong/"+dealId);
			if(file.exists()){
				file= new File("/home/zhangjin/data/dazhong/"+dealId+"_"+new Date().getTime());
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new BufferedOutputStream(new FileOutputStream(file))));
			bw.write(o.toString());
			bw.flush();
			bw.close();
		}
	}
	public static List<Map<String, Object>> requestBatchDeals(String appKey, String secret, List<String> batchIdsList) throws JsonParseException,
			JsonMappingException, IOException {
		String batchIdsParam = StringUtils.join(batchIdsList, ',');
		String apiUrl = "http://api.dianping.com/v1/deal/get_batch_deals_by_id";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("deal_ids", batchIdsParam);
		String jsonResult = DemoApiTool.requestApi(apiUrl, appKey, secret, paramMap);
		List<Map<String, Object>> deals = new ArrayList<Map<String,Object>>();
		try {
			Map<?, ?> resultMap = JSON_MAPPER.readValue(jsonResult, Map.class);
			deals = (List<Map<String, Object>>) resultMap.get("deals");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(jsonResult);
		}
		return deals;
	}
	
	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		int listSize = list.size(); // list的大小

		List<List<T>> listArray = new ArrayList<List<T>>(); // 创建list数组
															// ,用来保存分割后的list
		for (int index = 0; index < listSize; index = index + pageSize) {
			List<T> subList = new ArrayList<T>(pageSize);
			if (index + pageSize < listSize) {
				subList.addAll(list.subList(index, index + pageSize));
			} else {
				subList.addAll(list.subList(index, list.size()));
			}
			listArray.add(subList);
			//测试
//			break;
		}
		return listArray;
	}
}
