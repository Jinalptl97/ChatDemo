package com.example.krunal.chatapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by krunal on 16/2/17.
 */

public class MyService extends Service {

    ChatBean chatBean;
    Context context;
    DBHelper dbHelper;
    ArrayList<ChatBean> chatBeanArrayList;

    ArrayList<ChatBean> chatdatalist;
    NotificationCompat.InboxStyle inboxStyle;
    String simpleDateFormat;
    private TimerTask timerTask;
    private Timer timer;
    Firebase ref;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        context = this;

        Firebase.setAndroidContext(context);


        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        dbHelper = new DBHelper(this);
        chatBean = new ChatBean();
        chatBeanArrayList = new ArrayList<ChatBean>();
        chatdatalist = new ArrayList<>();
        ref = new Firebase("https://chat-b8a9e.firebaseio.com/");
        System.out.println("=====inside service=======");


        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {


                InsertData();

            }
        };


        timer.schedule(timerTask, 60000, 60000);

    }

    private void InsertData() {
        System.out.println("==========InsertData()=========");


        System.out.println("==========chatBean.user_id=========" + chatBean.user_id);
        System.out.println("==========InsertData() PREF_USERID=========");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {


                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    for (DataSnapshot person : postSnapshot.getChildren()) {

                        ChatBean person1 = person.getValue(ChatBean.class);


                        chatBean.message = person1.message;
                        chatBean.time = person1.time;
                        chatBean.is_read = "N";
                        chatBean.image = "";
                        chatBean.user_id = person1.user_id;

                        System.out.println("the cheatbean id"+chatBean.id);
                        System.out.println("the person1 id"+person1.id);

                        if(chatBean.user_id!=79) {

                            dbHelper.insert(chatBean);

                        }
                       /* if (person1.id!=chatBean.id) {


                        }*/
                        /*chatdatalist.add(person1);*/


                    }
                }


             /*   for (int i = 0; i < chatdatalist.size(); i++) {

                    chatBean.message = chatdatalist.get(i).message;
                    chatBean.time = chatdatalist.get(i).time;
                    chatBean.is_read = "N";
                    chatBean.image = "";
                    chatBean.user_id = chatdatalist.get(i).user_id;
                    dbHelper.insert(chatBean);

                }

*/

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });











          /*  Calendar c = Calendar.getInstance();

            simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());

            StringTokenizer tk = new StringTokenizer(simpleDateFormat);
            String date = tk.nextToken();
            String time = tk.nextToken();

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm:ss");
            java.text.SimpleDateFormat sdfs = new java.text.SimpleDateFormat("hh:mm a");
            Date dt = null;
            try {
                dt = sdf.parse(time);
                System.out.println("Time Display: " + sdfs.format(dt)); // <-- I got result here
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            int timeOfDay = c.get(Calendar.HOUR_OF_DAY);*/

          /*  if (timeOfDay >= 0 && timeOfDay < 12) {
                chatBean.message = "Hello " + Pref.getValue(context, Config.PREF_USERNAME, "") + " Good Morinng ";

                System.out.println("the message is" + chatBean.message);
            } else if (timeOfDay >= 12 && timeOfDay < 16) {
                chatBean.message ="Hello " + Pref.getValue(context, Config.PREF_USERNAME, "") +" Good Afternoon";

            } else if (timeOfDay >= 16 && timeOfDay < 21) {
                chatBean.message = " Hello " + Pref.getValue(context, Config.PREF_USERNAME, "") +" Good evening ";

            } else if (timeOfDay >= 21 && timeOfDay < 24) {
                chatBean.message = " Hello " + Pref.getValue(context, Config.PREF_USERNAME, "") + " Good Night ";

            }*/


    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }


}



