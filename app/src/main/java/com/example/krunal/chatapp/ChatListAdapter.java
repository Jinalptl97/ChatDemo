package com.example.krunal.chatapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by krunal on 17/2/17.
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder> {
    Context context;
    ArrayList<ChatBean> chatdatalist;
   ;


    public boolean isMultiselected = false;
    public MyInterface myInterface;

    public ChatListAdapter(Context context, ArrayList<ChatBean> chatdatalist, MyInterface myInterface) {
        this.context = context;
        this.chatdatalist = chatdatalist;
        this.myInterface = myInterface;

    }


    @Override
    public ChatListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        System.out.println("In side view");
        return new ChatListAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ChatListAdapter.MyViewHolder holder, final int position) {
        if (chatdatalist.get(position).user_id != 79) {
            holder.lin_left.setVisibility(View.VISIBLE);
            holder.lin_right.setVisibility(View.GONE);
            holder.txt_message_left.setText(chatdatalist.get(position).message);
            holder.txt_time_left.setText(String.valueOf(chatdatalist.get(position).time));


        } else {
            holder.lin_left.setVisibility(View.GONE);
            holder.lin_right.setVisibility(View.VISIBLE);
            holder.txt_message_right.setText(chatdatalist.get(position).message);
            holder.txt_time_right.setText(String.valueOf(chatdatalist.get(position).time));


        }


        if (chatdatalist.get(position).ischecked == true) {
            holder.itemView.setBackgroundColor(R.color.white);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.list_item);
        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                isMultiselected = true;
                myInterface.UpdateMyText(position, chatdatalist.get(position).ischecked);

                return true;

            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isMultiselected == true) {
                    myInterface.UpdateMyText(position, chatdatalist.get(position).ischecked);

                }
            }


        });
    }

    @Override
    public int getItemCount() {
        return chatdatalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_message_left, txt_time_left, txt_message_right, txt_time_right;
        LinearLayout lin_left, lin_right, lin_1;
        TextView imageView;


        public MyViewHolder(View v) {
            super(v);
            txt_message_left = (TextView) v.findViewById(R.id.txt_message_left);
            txt_time_left = (TextView) v.findViewById(R.id.txt_time_left);
            txt_message_right = (TextView) v.findViewById(R.id.txt_message_right);
            txt_time_right = (TextView) v.findViewById(R.id.txt_time_right);
            lin_left = (LinearLayout) v.findViewById(R.id.lin_left);
            lin_right = (LinearLayout) v.findViewById(R.id.lin_right);
            lin_1 = (LinearLayout) v.findViewById(R.id.lin_1);


        }


    }
}
