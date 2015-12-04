package com.orvito.homevito.presencedentity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class BRWifi extends BroadcastReceiver{
	static int i=0;
	String officeSSID="Arien";

	@Override
	public void onReceive(Context context, Intent intent) {
		WifiManager  manager=(WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = manager.getConnectionInfo();

		String SSID = info.getSSID();

		if(manager == null || info == null || SSID == null){

			String tempSSID = UTILSharedPreference.getPreference(context, "ssid");

			if(tempSSID != null){

				if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
					NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
					if(tempSSID.equals(officeSSID) && networkInfo.isConnected()) {
						// Wifi is connected
						Toast.makeText(context, "CONNECTED ssid:ARIEN", Toast.LENGTH_SHORT).show();
					}else if(!tempSSID.equals(officeSSID)){
						if(networkInfo.isConnected())
							Toast.makeText(context, "CONNECTED ssid:NEW", Toast.LENGTH_SHORT).show();
					}

				} else if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
					NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
					if(tempSSID.equals(officeSSID) && networkInfo.getType() == ConnectivityManager.TYPE_WIFI && ! networkInfo.isConnected()) {
						// Wifi is disconnected
						Toast.makeText(context, "DISCONNECTED ssid:NEW", Toast.LENGTH_SHORT).show();
						context.startService(new Intent(context,SRVGprs.class));
					}
				}

			}
			return;
		}

		UTILSharedPreference.setPreference(context, "ssid", SSID);

		if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
			NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if(SSID.equals(officeSSID)&& networkInfo.isConnected()) {
				// Wifi is connected
				sendAutoControl(context, "1");
				Toast.makeText(context, "CONNECTED ssid:ARIEN2", Toast.LENGTH_SHORT).show();

			}else if(!SSID.equals(officeSSID)){
				if(networkInfo.isConnected()){
					Toast.makeText(context, "CONNECTED ssid:NEW2", Toast.LENGTH_SHORT).show();
					sendAutoControl(context, "0");
				}
			}

		} else if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
			if(SSID.equals(officeSSID) && networkInfo.getType() == ConnectivityManager.TYPE_WIFI && ! networkInfo.isConnected()) {
				// Wifi is disconnected
				context.startService(new Intent(context,SRVGprs.class));
			}
		}

	}

	private void sendAutoControl(Context context,String controlCommand) {
		MODELResultSet modelResultSet = new DFAutoControl().sendAutoControlMessage(context, controlCommand);
		Toast.makeText(context, modelResultSet.getMessage(), Toast.LENGTH_SHORT).show();
	}


}