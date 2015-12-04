package com.orvito.homevito.presencedentity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class DFAutoControl {

	final String dataInvalidMsg="Invalid input data";	

	public MODELResultSet sendAutoControlMessage(Context context, String state){		

		String url=UTILConstants.SERVERURL+"autocontrol";
		
		MODELResultSet modeljsonResponseToReturn=new MODELResultSet();

		String oneTimeKey = UTILSharedPreference.getPreference(context, UTILConstants.ONETIMEKEY);
		
		if(url.length()<1 && oneTimeKey.length()<1  && state.length()<1){//--------------------------------data input check

			modeljsonResponseToReturn.setError("Error");
			modeljsonResponseToReturn.setMessage(dataInvalidMsg);
			return modeljsonResponseToReturn;
		}

		JSONObject jsonObject=null;		

		try {

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("oneTimeKey", oneTimeKey));
			nameValuePairs.add(new BasicNameValuePair("state", state));

			MODELResultSet jsonResponseFromServer = new ServerConnect().requestServer(url,nameValuePairs);

			if(jsonResponseFromServer.getError()==null){//----------------------------------------------case if no error occurred during server connect process
				jsonObject=jsonResponseFromServer.getJsonObject();

				if(jsonObject.get("error").toString().length()>0){//---------------------------------case if server response data has an error

					modeljsonResponseToReturn.setError(jsonObject.get("error").toString());
					modeljsonResponseToReturn.setMessage(jsonObject.get("message").toString());
					return modeljsonResponseToReturn;
				}else{

					modeljsonResponseToReturn.setMessage(jsonObject.get("message").toString());
					return modeljsonResponseToReturn;
				}		

			}else{
				modeljsonResponseToReturn.setError(jsonResponseFromServer.getError());
				modeljsonResponseToReturn.setMessage(jsonResponseFromServer.getMessage());
				return modeljsonResponseToReturn;
			}

		} catch (Exception e) {
			modeljsonResponseToReturn.setError("Error");
			modeljsonResponseToReturn.setMessage(e.toString());
			return modeljsonResponseToReturn;
		}

	}

}
