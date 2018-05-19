package com.example.krunal.chatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

public class ChatActivity extends AppCompatActivity implements MyInterface {
    RecyclerView list_chat;
    LinearLayoutManager linearLayoutManager;
    ChatListAdapter chatListAdapter;
    Context context;
    DBHelper dbHelper;
    EditText edit_msg;
    ArrayList<ChatBean> chatdatalist;
    Intent intent;
    TextView img_send;
    ChatBean chatBean;
    String simpleDateFormat;
    public TextView txt_count;
    public CheckBox ch1;
    String time1;
    MyInterface listner;
    public int count = 0;
    public LinearLayout lin;
    String checkbox = "";
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    Firebase ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Firebase.setAndroidContext(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edit_msg = (EditText) findViewById(R.id.edit_msg);
        img_send = (TextView) findViewById(R.id.img_send);
        txt_count = (TextView) findViewById(R.id.txt_count);
        lin = (LinearLayout) findViewById(R.id.lin1);
        ch1 = (CheckBox) findViewById(R.id.checkBox);


        context = this;
        dbHelper = new DBHelper(this);
        chatdatalist = new ArrayList<ChatBean>();
        chatdatalist = dbHelper.getList1();
        Calendar c = Calendar.getInstance();

        ref = new Firebase("https://chat-b8a9e.firebaseio.com/");

       /* mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("chat");

        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });*/


        list_chat = (RecyclerView) findViewById(R.id.list_chat);
        linearLayoutManager = new LinearLayoutManager(ChatActivity.this);
        list_chat.setLayoutManager(linearLayoutManager);


        chatListAdapter = new ChatListAdapter(context, chatdatalist, ChatActivity.this);
        list_chat.setAdapter(chatListAdapter);
        list_chat.scrollToPosition(chatdatalist.size() - 1);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());


        StringTokenizer tk = new StringTokenizer(simpleDateFormat);
        String date = tk.nextToken();
        String time = tk.nextToken();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
        Date dt = null;
        try {
            dt = sdf.parse(time);
            time1 = sdfs.format(dt);
            System.out.println("Time Display data: " + sdfs.format(dt)); // <-- I got result here
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

      /*  txt_count.setText(listner.foo());*/
        edit_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (edit_msg.getText().toString().trim().length() >= 0) {
                    img_send.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ch1.isChecked()) {
                    count = chatdatalist.size();
                    UnselectRow(true);
                    chatListAdapter.notifyDataSetChanged();
                } else {
                    count = 0;
                    UnselectRow(false);
                    chatListAdapter.notifyDataSetChanged();
                }
            }

        });


        img_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edit_msg.getText().toString().trim().equals("")) {
                    chatBean = new ChatBean();
                    chatBean.message = edit_msg.getText().toString();
                    chatBean.image = "";
                    chatBean.time = time1;
                    chatBean.is_read = "N";
                    chatBean.user_id = Pref.getValue(context, Config.PREF_USERID, 0);
                    dbHelper.insert(chatBean);

                   /* String message=edit_msg.getText().toString();
                    String image="";
                    String time=time1;
                    String is_read="N";
                    int user_id = Pref.getValue(context, Config.PREF_USERID, 0);

                    ChatBean chatBean = new ChatBean(message,image,time,is_read, user_id);
*/
                    System.out.println("==========inside fcm==============");


                    Firebase newRef = ref.child("ChatBean").push();
                    newRef.setValue(chatBean);

                    chatdatalist.add(chatBean);
                    chatListAdapter.notifyDataSetChanged();
                    list_chat.scrollToPosition(chatdatalist.size() - 1);
                    edit_msg.setText("");





                } else {
                    Toast.makeText(context, "please enter message", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }


    public void UnselectRow(boolean isCheck) {
        for (ChatBean chatBean : chatdatalist) {
            chatBean.ischecked = isCheck;
        }
        txt_count.setText("" + count);
    }

    @Override
    public void onBackPressed() {
        if (chatListAdapter != null) {
            chatListAdapter.notifyDataSetChanged();
            System.out.println("=======Inside backpressed=================");

            if (chatListAdapter.isMultiselected == true) {
                chatListAdapter.isMultiselected = false;
                System.out.println("=======Inside backpressed data================");
                lin.setVisibility(View.GONE);
                count = 0;
                UnselectRow(false);
                txt_count.setVisibility(View.GONE);
                ch1.setVisibility(View.GONE);
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                chatListAdapter.notifyDataSetChanged();
            } else {
                finish();
            }
        } else {
            System.out.println("inside else");
            finish();
        }

    }

    @Override
    public void UpdateMyText(final int pos, boolean isCheck) {
        System.out.println("=====isCheck=====" + isCheck + "===pos===" + pos);
        if (chatListAdapter != null) {
            chatListAdapter.isMultiselected = true;
            lin.setVisibility(View.VISIBLE);
            txt_count.setVisibility(View.VISIBLE);
            ch1.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            System.out.println("the position is" + chatdatalist.get(pos).id);

        }
        if (!isCheck) {
            count++;
            chatdatalist.get(pos).ischecked = true;
            txt_count.setText("" + count);
            System.out.println("number of selected data" + count);
            System.out.println("position get" + chatdatalist.get(pos).id);
            lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    System.out.println("inside for loop");

                    for (int i = 0; i < chatdatalist.size(); i++) {
                        checkbox = checkbox + (chatdatalist.get(i).ischecked == true ? (chatdatalist.get(i).id) + "," : "");


                    }
                    if (checkbox.endsWith(",")) {
                        checkbox = checkbox.substring(0, checkbox.length() - 1);
                        System.out.println("===========selected data==========" + checkbox);
                    }
                    dbHelper.delete(checkbox);
                    Iterator<ChatBean> iterator = chatdatalist.iterator();
                    while (iterator.hasNext()) {
                        ChatBean value = iterator.next();
                        if (value.ischecked == true) {
                            System.out.println("inside if satatment");
                            iterator.remove();


                        }
                    }

                    chatListAdapter.notifyDataSetChanged();

                  /*  chatdatalist.remove(checkbox);*/
                    checkbox = "";
                   /* chatdatalist = dbHelper.getList1();
                    chatListAdapter = new ChatListAdapter(context, chatdatalist, ChatActivity.this);*/
                    count = 0;





                    lin.setVisibility(View.GONE);
                    txt_count.setVisibility(View.GONE);
                    ch1.setVisibility(View.GONE);
                    getSupportActionBar().setDisplayShowTitleEnabled(true);

                }
            });
            chatListAdapter.notifyDataSetChanged();
        } else {
            count--;
            chatdatalist.get(pos).ischecked = false;
            txt_count.setText("" + count);
            System.out.println("number of unselected data" + count);
            chatListAdapter.notifyDataSetChanged();
        }
    }


}




