package com.example.krunal.chatapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by krunal on 7/2/17.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    Context context;

    ArrayList<ContactBean> storeContacts;


    public ContactAdapter(Context context, ArrayList<ContactBean> storeContacts) {
        this.context=context;

        this.storeContacts=storeContacts;

        System.out.println("inside constructer");
    }

    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        System.out.println("inside row");
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ContactAdapter.MyViewHolder holder, int i) {
        holder.txt_name.setText(storeContacts.get(i).name);
        holder.txt_number.setText(storeContacts.get(i).number);
        holder.txt_type.setText(storeContacts.get(i).type);





    }

    @Override
    public int getItemCount() {
        System.out.println("the size of"+storeContacts.size());
        return storeContacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_number,txt_type;
        ImageView imgUser;



        public MyViewHolder(View view) {
            super(view);

            txt_name = (TextView) view.findViewById(R.id.txt_name);
            imgUser = (ImageView) view.findViewById(R.id.img_1);
            txt_number = (TextView) view.findViewById(R.id.txt_number);
            txt_number.setVisibility(View.VISIBLE);
            txt_type=(TextView)view.findViewById(R.id.txt_type);



        }
    }
}
