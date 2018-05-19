package com.example.krunal.chatapp;

import android.content.Context;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;


/**
 * Created by krunal on 8/2/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCM Service";
    DBHelper dbHelper;
    ChatBean chatBean;
    String simpleDateFormat;
    Context context;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.

        System.out.println("====================firebase service===================");
        Calendar c = Calendar.getInstance();
        dbHelper = new DBHelper(this);
        context = this;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());

        StringTokenizer tk = new StringTokenizer(simpleDateFormat);
        String date = tk.nextToken();
        String time = tk.nextToken();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
        Date dt = null;
        try {
            dt = sdf.parse(time);
            System.out.println("Time Display data: " + sdfs.format(dt)); // <-- I got result here
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String str = remoteMessage.getNotification().getBody();
        String jsonStr = str;


        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                if (Pref.getValue(context, Config.PREF_USERID, 0) != 0) {
                    chatBean = new ChatBean();
                    chatBean.message = jsonObj.getString("message");
                    chatBean.image = jsonObj.getString("image");
                    chatBean.time = sdfs.format(dt);
                    chatBean.is_read = "N";
                    chatBean.user_id = Pref.getValue(context, Config.PREF_USERID, 0);
                    dbHelper.insert(chatBean);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


}






