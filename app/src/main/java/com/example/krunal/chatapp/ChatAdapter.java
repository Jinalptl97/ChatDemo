package com.example.krunal.chatapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;

/**
 * Created by krunal on 8/2/17.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{

    Context context;
    ArrayList<ChatBean> chat;
    OnItemClickListener listener;
    Dialog dialog;
    DBHelper dbHelper;
    ImageView img_1;
    TextView txt_info;
    public static String LIVE = "http://192.168.1.42/work/fancase";
    public static String IMAGE_SHOWCASE_DOWNLOAD_URL = LIVE + "/public/media/userdata/";


    public interface OnItemClickListener {
        void onItemClick(ChatBean item);

    }


    public ChatAdapter(Context context, ArrayList<ChatBean> chat) {
        this.context = context;
        this.chat = chat;


    }

    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        System.out.println("inside row");
        return new ChatAdapter.MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.txt_name.setText(chat.get(position).message);
        holder.txt_type.setText(chat.get(position).time);
        if (chat.get(position).is_read.equals("N")) {
            holder.txt_name.setTextColor(Color.BLACK);
        } else {
            holder.txt_name.setTextColor(Color.BLUE);
        }

        Glide.with(context).load(IMAGE_SHOWCASE_DOWNLOAD_URL + chat.get(position).user_id + "/" + chat.get(position).image).asBitmap().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar).into(new BitmapImageViewTarget(holder.imgUser) {

            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                holder.imgUser.setImageDrawable(roundedBitmapDrawable);


            }
        });

        holder.imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_layout);
                dialog.show();
                img_1 = (ImageView) dialog.findViewById(R.id.img_1);
                txt_info=(TextView)dialog.findViewById(R.id.txt_info);
                Glide.with(context).load(IMAGE_SHOWCASE_DOWNLOAD_URL + chat.get(position).user_id + "/" + chat.get(position).image).asBitmap().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar).into(img_1);
                txt_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context,ViewProfileActiviry.class);
                        context.startActivity(intent);
                    }
                });

            }
        });

        holder.bind(chat.get(position), listener);
    }



    @Override
    public int getItemCount() {
        return chat.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_number, txt_type;
        ImageView imgUser;
        RelativeLayout rel_main;


        public MyViewHolder(View view) {
            super(view);

            txt_name = (TextView) view.findViewById(R.id.txt_name);
            imgUser = (ImageView) view.findViewById(R.id.img_1);
            txt_number = (TextView) view.findViewById(R.id.txt_number);
            txt_type = (TextView) view.findViewById(R.id.txt_type);
            rel_main = (RelativeLayout) view.findViewById(R.id.rel_main);
            dbHelper=new DBHelper(context);

        }

        public void bind(final ChatBean item, final OnItemClickListener listener) {


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(item.is_read.equals("N"))
                    {
                        item.is_read="Y";
                        dbHelper.update(item);
                        notifyDataSetChanged();

                    }

                    Intent intent=new Intent(context,ChatActivity.class);
                    intent.putExtra("ChatBean", item.image);
                    context.startActivity(intent);
                }

            });


        }
    }
}





