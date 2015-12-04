package com.orvito.homevito.presencedentity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;



public class ServerConnect {

	private final String serverLoginName="lampp";
	private final String serverPasscode="Vito@123";

	public MODELResultSet requestServer(String url,List<NameValuePair> nameValuePairs){

		MODELResultSet modeljsonResponse=new MODELResultSet();
		InputStream is=null;

		try{
			DefaultHttpClient httpClient = new DefaultHttpClient();


			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(null, -1),
					new UsernamePasswordCredentials(serverLoginName, serverPasscode));


			if(nameValuePairs!=null){

				Log.v("URL CONTACTED", ""+url);
				Log.v("CREDENTIALS", ""+nameValuePairs);

				HttpPost httpPost = new HttpPost(url);				
				
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
			}else{
				HttpGet httpGet = new HttpGet(url);				
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
			}

			//convert response to string

			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			String result=sb.toString();
			
			JSONObject jObject = new JSONObject(result);
			Log.e("ModelJsonResponce",""+jObject);
			modeljsonResponse.setJsonObject(jObject);
		


		}catch(JSONException e){
			e.printStackTrace();
			modeljsonResponse.setError("error");
			modeljsonResponse.setMessage("Error parsing server data");
		}catch(Exception e){
			e.printStackTrace();
			modeljsonResponse.setError("error");
			modeljsonResponse.setMessage("Network Connection Error");
		}

		return modeljsonResponse;
	}
}
