package com.orvito.homevito.presencedentity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PresenceIdentityActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {

//				sendAutoControl(getBaseContext(),"1");
			}
		});
    
    }
    
//    private void sendAutoControl(Context context,String controlCommand) {
//		MODELResultSet modelResultSet = new DFAutoControl().sendAutoControlMessage(context, controlCommand);
//		Toast.makeText(context, modelResultSet.getMessage(), 1).show();
//	}
}