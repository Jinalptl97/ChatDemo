package com.example.krunal.chatapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;

import java.io.File;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static Context context;
    public static SQLiteDatabase db = null;
    private static final String DB_PATH_SUFFIX = "/databases/";
    private static final String DATABASE_NAME = "chat.db";
    NotificationManager mNotifyManager;
    NotificationCompat.InboxStyle inboxStyle;
    ArrayList<ChatBean> chatBeanArrayList;
    ArrayList<ChatBean> chatBeanArrayList1;
    ChatBean chatBean;
    ViewPager mViewPager;

    NotificationCompat.Builder builder;

    public DBHelper(Context context) {
        super(context, "chat.db", null, 33);
        this.context = context;
        chatBeanArrayList = new ArrayList<ChatBean>();
        chatBeanArrayList1 = new ArrayList<ChatBean>();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void copyDataBase() {

        try {
//            // Open your local db as the input stream
//            InputStream myInput = context.getAssets().open(DATABASE_NAME);
//            // Path to the just created empty db
//            String outFileName = getDatabasePath();
//
//            // if the path doesn't exist first, create it
//            File f = new File(context.getApplicationInfo().dataDir
//                    + DB_PATH_SUFFIX);
//            if (!f.exists())
//                f.mkdir();
//
//            // Open the empty db as the output stream
//            OutputStream myOutput = new FileOutputStream(outFileName);
//
//            // transfer bytes from the inputfile to the outputfile
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = myInput.read(buffer)) > 0) {
//                myOutput.write(buffer, 0, length);
//            }
//
//            // Close the streams
//            myOutput.flush();
//            myOutput.close();
//            myInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkDatabase() {
        SQLiteDatabase checkDB = null;

        checkDB = null;
        try {
            try {
                String myPath = getDatabasePath();
                File file = new File(myPath);
                if (file.exists() && !file.isDirectory()) {
                    checkDB = SQLiteDatabase.openDatabase(myPath, null,
                            SQLiteDatabase.OPEN_READONLY);
                    checkDB.close();
                }
            } catch (Exception e) {
            }
        } catch (Throwable ex) {
        }
        return checkDB != null ? true : false;
    }

    private static String getDatabasePath() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    public void execute(String statment) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            System.out.println("===statment===" + statment);
            db.execSQL(statment);
        } catch (Exception e) {
           /* FirebaseCrash.report(new Exception(e.toString()));*/
            System.out.println(e);
        } finally {
            db.close();
            db = null;
        }
    }

    public Cursor query(String statment) {
        Cursor cur = null;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            System.out.println("===statment===" + statment);
            cur = db.rawQuery(statment, null);
            cur.moveToPosition(-1);
        } catch (Exception e) {
           /* FirebaseCrash.report(new Exception(e.toString()));*/
            System.out.println(e);
        } finally {

            db.close();
            db = null;
        }

        return cur;
    }


    public static String getDBStr(String str) {

        str = str != null ? str.replaceAll("'", "''") : null;
        str = str != null ? str.replaceAll("&#039;", "''") : null;
        str = str != null ? str.replaceAll("&amp;", "&") : null;

        return str;

    }

    public void upgrade() {

        doUpdate1();
    }

    private void doUpdate1() {

        this.execute("CREATE TABLE message (id INTEGER PRIMARY KEY AUTOINCREMENT,message TEXT NOT NULL,time TEXT NOT NULL,is_read TEXT NOT NULL,image TEXT NOT NULL,user_id INTEGER NOT NULL)");
    }


    public void insert(ChatBean chatBean) {
        String sql = null;

        try {
            sql = "INSERT INTO message (message,time,is_read,image,user_id) VALUES ('" + DBHelper.getDBStr(chatBean.message) + "','" + DBHelper.getDBStr(chatBean.time) + "','" + chatBean.is_read + "', '" + chatBean.image + "', " + chatBean.user_id + ")";

            execute(sql);
            CreateNotification();


        } catch (Exception e) {
            // Log.error(this.getClass() + " :: insert()", e);

        } finally {

            sql = null;

            System.gc();
        }

    }

    public void update(ChatBean chatBean) {

        String sql = null;
        try {

            sql = "UPDATE message SET is_read = '" + chatBean.is_read + "' WHERE id = " + chatBean.id;
            execute(sql);

        } catch (Exception e) {
            // Log.error(this.getClass() + " :: update()", e);

        } finally {

            // release

            sql = null;
            System.gc();
        }


    }


    public void CreateNotification() {

        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        chatBeanArrayList = getListdata();
        Intent intent = new Intent(context, MainActivity.class);
        System.out.println("inside new task");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("unread message").setSmallIcon(R.drawable.ic_fab_add);
        inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Conversation:");
        inboxStyle.setSummaryText("unread message" + chatBeanArrayList.size());

        if (chatBeanArrayList != null && chatBeanArrayList.size() > 0) {
            for (int i = (chatBeanArrayList.size() - 1); i > Math.max(chatBeanArrayList.size() - 6, 0); i--) {
                inboxStyle.addLine(chatBeanArrayList.get(i - 1).message);
                System.out.println("**********notificationBeanArrayList.get(i).message******" + chatBeanArrayList.get(i).message);
            }

            builder.setStyle(inboxStyle);
            mNotifyManager.notify(1, builder.build());


            BadgeUtils.setBadge(context, chatBeanArrayList.size());


        }

    }


    public ArrayList<ChatBean> getList() {

        String sql = null;
        Cursor cursor = null;
        ArrayList<ChatBean> chatBeanArrayList = new ArrayList<ChatBean>();
        ChatBean chatBean = null;

        try {
            sql = "SELECT * FROM message where user_id = " + Pref.getValue(context, Config.PREF_USERID, 0) + " OR user_id = 67 order by id DESC limit 1 ";
            System.out.println("===========notificationBean====" + sql);


            cursor = query(sql);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    chatBean = new ChatBean();
                    chatBean.id = cursor.getInt(0);
                    chatBean.message = cursor.getString(1);
                    chatBean.time = cursor.getString(2);
                    chatBean.is_read = cursor.getString(3);
                    chatBean.image = cursor.getString(4);
                    chatBean.user_id = cursor.getInt(5);

                    chatBeanArrayList.add(chatBean);


                }
            }
        } catch (Exception e) {
            //  Log.error(this.getClass() + " :: getCategories()", e);

        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();

            sql = null;
            cursor = null;
            chatBean = null;
            System.gc();
        }
        return chatBeanArrayList;
    }

    public ArrayList<ChatBean> getList1() {

        String sql = null;
        Cursor cursor = null;
        ArrayList<ChatBean> chatBeanArrayList = new ArrayList<ChatBean>();
        ChatBean chatBean = null;

        try {
            sql = "SELECT * FROM message where user_id = " + Pref.getValue(context, Config.PREF_USERID, 0) + " OR user_id = 67  ";
            System.out.println("===========notificationBean====" + sql);


            cursor = query(sql);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    chatBean = new ChatBean();
                    chatBean.id = cursor.getInt(0);
                    chatBean.message = cursor.getString(1);
                    chatBean.time = cursor.getString(2);
                    chatBean.is_read = cursor.getString(3);
                    chatBean.image = cursor.getString(4);
                    chatBean.user_id = cursor.getInt(5);

                    chatBeanArrayList.add(chatBean);


                }
            }
        } catch (Exception e) {
            //  Log.error(this.getClass() + " :: getCategories()", e);

        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();

            sql = null;
            cursor = null;
            chatBean = null;
            System.gc();
        }
        return chatBeanArrayList;
    }


    public ArrayList<ChatBean> getListdata() {

        String sql = null;
        Cursor cursor = null;
        ChatBean chatBean = null;
        ArrayList<ChatBean> chatBeanArrayList = new ArrayList<ChatBean>();

        try {
            sql = "SELECT * FROM message where is_read = 'N' ";
            System.out.println("===========notificationBean====" + sql);


            cursor = query(sql);


            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    chatBean = new ChatBean();
                    chatBean.id = cursor.getInt(0);
                    chatBean.message = cursor.getString(1);
                    chatBean.time = cursor.getString(2);
                    chatBean.is_read = cursor.getString(3);
                    chatBean.image = cursor.getString(4);
                    chatBean.user_id = cursor.getInt(5);

                    chatBeanArrayList.add(chatBean);
                }
            }
            System.out.println("the num of count is" + cursor.getCount());
        } catch (Exception e) {
            //  Log.error(this.getClass() + " :: getCategories()", e);

        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();

            sql = null;
            cursor = null;
            chatBean = null;
            System.gc();
        }
        System.out.println("the size of" + chatBeanArrayList.size());
        return chatBeanArrayList;
    }


    public void delete(String str) {


        Cursor cursor = null;
        String sql = null;


        try {
            String strdelete1 = "delete from message where id='" + str + " '";

            String strdelete = "delete from message where Id in (" + str + ")";
            System.out.println("=============query=============" + strdelete);

            cursor = query(strdelete);

        } catch (Exception e) {
            System.out.println("==========Exception=====" + e.toString());

        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();

            // release

            sql = null;
            cursor = null;

            System.gc();
        }


    }
}

