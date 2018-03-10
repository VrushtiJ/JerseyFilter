package com.jersey.services.imp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.*;

import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ADA {

	public static String idToken;
	public static String expiresOn;
	public static String notBefore;
	public static final String httpBodyPart1="resource=https://graph.microsoft.com&client_id=d63bd49b-91a4-4dab-9155-c54882aec3a4&grant_type=password";
	public static final String httpBodyPart2="scope=openid&client_secret=MmES4oQxotXjJnS1wXeZ0V2/rOONvTWdwGnsKEkQhr8=";
	private final String USER_AGENT = "Mozilla/5.0";
	@Autowired
	static AzureAdProperties azureAdProperties;

	public void setAzureAdProperties(AzureAdProperties azureAdProperties) {
		ADA.azureAdProperties = azureAdProperties;
	}

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "http://www.google.com/search?q=mkyong";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	public static void sendPost(String userEmail, String userPassword) throws Exception {
		String url =azureAdProperties.getAzureRequestURL();
		String httpBody ="resource="+azureAdProperties.getAzureRequestResponse()+"&client_id="+azureAdProperties.getAzureRequestClientId()+"&grant_type="+azureAdProperties.getAzureRequestGrantType()+"&username="+userEmail+"&password="+userPassword+"&scope="+azureAdProperties.getAzureRequestScope()+"&client_secret="+azureAdProperties.getAzureRequestClientSecret()+"";

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Accept", "application/json");
		try {
			StringEntity stringEntity = new StringEntity(httpBody);
			httpPost.getRequestLine();
			httpPost.setEntity(stringEntity);

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			System.out.println(content);
			content = content.replace("{", "");
			content = content.replace("}", "");
			content = content.replaceAll("\"", "");

			String values[] = content.split(",");
			for (String value : values) {

				if (value.contains("id_token")) {
					String tokens[] = value.split(":");
					ADA.idToken = tokens[1];

				}
				if (value.contains("not_before")) {
					String tokens[] = value.split(":");
					ADA.notBefore = tokens[1];
				}
				if (value.contains("expires_on")) {
					String tokens[] = value.split(":");
					ADA.expiresOn = tokens[1];
				}

			}
			//System.out.println(response);
			if(idToken == null)
			{
				
				throw new Exception("ID token is not received from Azure AD: Security Exception");
			}
			
		}
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		/*
		 * StringEntity stringEntity = new StringEntity(httpBody);
		 * 
		 * httppost.setEntity(stringEntity);
		 * 
		 * HttpResponse response = httpclient.execute(httppost);
		 */ // System.out.println( response ) ;

		// HttpEntity resEntity = response.getEntity();
		/*
		 * System.out.println( resEntity ) ; System.out.println(
		 * EntityUtils.toString(resEntity) );
		 */

		// EntityUtils.consume(resEntity);
		// httpclient.getConnectionManager().shutdown();
	}


}