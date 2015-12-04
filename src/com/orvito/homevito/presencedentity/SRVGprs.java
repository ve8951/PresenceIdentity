package com.orvito.homevito.presencedentity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class SRVGprs extends Service{
	static int i=0;
	BroadcastReceiver brGprs;

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		brGprs = new BRGprs();
		registerReceiver(brGprs,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}

	public class BRGprs extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {

			TelephonyManager mgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			if(mgr.getDataState() == TelephonyManager.DATA_CONNECTED)
			sendAutoControl(context, "0");

		}



		private void sendAutoControl(Context context,String controlCommand) {
			if(UTILSharedPreference.getBooleanPreference(context, UTILConstants.AUTOSTATE)){ 
				MODELResultSet modelResultSet = new DFAutoControl().sendAutoControlMessage(context, controlCommand);
				Toast.makeText(context, "CONNECTED GPRS", Toast.LENGTH_SHORT).show();
			}
			stopService(new Intent(context,SRVGprs.class));

		}

	}


	@Override
	public void onDestroy() {
		super.onDestroy();

		unregisterReceiver(brGprs);
		Log.e("Model MEssage", "OnDestroy");
	}

}
