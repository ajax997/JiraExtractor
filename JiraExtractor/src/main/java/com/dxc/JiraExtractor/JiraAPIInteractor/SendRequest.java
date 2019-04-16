package com.dxc.JiraExtractor.JiraAPIInteractor;

import java.io.IOException;
import java.util.Base64;

import com.dxc.JiraExtractor.ConfigStuffs;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class SendRequest {

	static CookieStore cookieStore = new BasicCookieStore();
	static BasicHttpContext httpContext = new BasicHttpContext();
	static HttpClient httpClient = HttpClientBuilder.create().build();
	static {
		httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
	}
	public static String sendRequest(String url, RequestType requestType) {
		System.out.print("url >>> "+url);
		HttpGet requestGet = new HttpGet(url);

		HttpResponse response;
		try {
			response = httpClient.execute(requestGet, httpContext);
			String jsonReString = EntityUtils.toString(response.getEntity());
			System.out.println("GET >>> "+jsonReString);
			return jsonReString;
		} catch (IOException e) {

			e.printStackTrace();
		}

		return "[]";
	}

	public static String sendPOSTRequest(String url, JSONObject body) {
		String payload = body.toString();
		System.out.print("body >>>" + body.toString());
		StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);

		HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		try {
			HttpResponse response = httpClient.execute(request, httpContext);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String sendAuthorizeRequest(String url, String email, String token) {
		String originalInput = email + ":" + token;
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

		HttpClient httpClient = HttpClientBuilder.create().build();

		String encoding = encodedString;
		HttpGet httpPost = new HttpGet(url);
		httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);

		return getString(httpClient, httpPost);
	}

	public static String sendRequestToken(String url, RequestType requestType)
	{
		HttpClient httpClient = HttpClientBuilder.create().build();


		HttpGet httpPost = new HttpGet(url);
		httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + ConfigStuffs.tokenBase64);

		return getString(httpClient, httpPost);
	}

	private static String getString(HttpClient httpClient, HttpGet httpPost) {
		System.out.println("executing request " + httpPost.getRequestLine());
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		try {
			return (EntityUtils.toString(entity));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
