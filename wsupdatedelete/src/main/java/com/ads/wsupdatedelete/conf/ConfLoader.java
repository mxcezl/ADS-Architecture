package com.ads.wsupdatedelete.conf;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ConfLoader {

	private static final String CONF_GIT_URL = "https://raw.githubusercontent.com/mxcezl/config-pg-java/main/postgres.conf";

	public static Properties getConfGit() throws Exception {
		HttpGet request = new HttpGet(CONF_GIT_URL);
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity);
		
		Properties prop = null;

		prop = new Properties();
		prop.load(new ByteArrayInputStream(result.getBytes()));

		return prop;
	}
}
