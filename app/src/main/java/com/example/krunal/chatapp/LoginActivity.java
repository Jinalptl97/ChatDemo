package com.example.krunal.chatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText edt1, edt2;
    Button btn1;
    Context context;
    Intent intent;
    DBHelper dbHelper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edt1 = (EditText) findViewById(R.id.edt_1);
        edt2 = (EditText) findViewById(R.id.edt_2);
        btn1 = (Button) findViewById(R.id.btn_1);
        dbHelper = new DBHelper(this);
        dbHelper.upgrade();
        context = this;


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int USerId = Integer.parseInt(edt1.getText().toString());
                String username = edt2.getText().toString();
                Pref.setValue(context, Config.PREF_USERID, USerId);
                Pref.setValue(context, Config.PREF_USERNAME, username);
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });


    }


}
