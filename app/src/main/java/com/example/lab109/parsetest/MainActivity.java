package com.example.lab109.parsetest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;

import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ParseObject testObject = new ParseObject("Member");
//        //testObject.put("name", "Jusnit");
//        String s  = testObject.getObjectId();
//       // Log.i("ParseTest", s);
//        testObject.setObjectId("yYq1c85QtH");
//        testObject.put("age", "25");
//        testObject.saveInBackground();
        ParseObject testObject = new ParseObject("Event");
        //testObject.put("name", "Jusnit");
        String s  = testObject.getObjectId();
       // Log.i("Event Object Id:", s);

        //testObject.setObjectId("yYq1c85QtH");
        testObject.put("Limit", "10");
        testObject.saveInBackground();
        ParsePush.subscribeInBackground("Eating");
        ParsePush push = new ParsePush();
        push.setChannel("Eating");
        push.setMessage("Eating Push test");
       // push.sendInBackground();
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> markers, ParseException e) {
                if (e == null) {
                    // your logic here
                    for(ParseObject p : markers){
                        Log.i("query from cloud","limit = "+p.getString("Limit"));
                    }
                } else {
                    // handle Parse Exception here
                    Log.i("query from cloud","cant read from cloud");

                }
            }
        });

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
