package httpclient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;


public class FirstClient {
public static void main(String[] args) {
	List<NameValuePair> list = new ArrayList<NameValuePair>();
	list.add(new BasicNameValuePair("", ""));
	String uri = URLEncodedUtils.format(list, "utf-8");
//	URI uri = URIUtils.createURI(scheme, host, port, path, query, fragment)
//	UrlEncodedFormEntity 
	ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager();
	SingleClientConnManager s = new SingleClientConnManager(null);
}
}
