package com.example.krunal.chatapp;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
public class Utils {

    public static Dialog dialog;

    /**
     * System Upgrade for Database.
     */
  /*  public static void systemUpgrade(Context context) {
        DBHelper dbHelper = null;
        try {
            dbHelper = new DBHelper(context);
            dbHelper.upgrade();
        } catch (Exception e) {
            Log.print(e);
        } finally {
            if (dbHelper != null)
                dbHelper.close();
            dbHelper = null;
        }
    }*/

   /* public static String getDeviceID(Context context) {
        String udid = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        Pref.setValue(context, Config.PREF_UDID, udid);
        return udid;
    }*/

    public static Uri getPlayStorUrlUri(Context context) {
        return Uri.parse("https://play.google.com/store/apps/details?id=" + context.getApplicationContext().getPackageName());
    }

  /*  public static void addActivities(Activity _activity, String name) {
        if (Config.screen_al == null)
            Config.screen_al = new ArrayList<Activity>();
        if (_activity != null)
            Config.screen_al.add(_activity);
    }

    public static void closeAllScreens() {
        if (Config.screen_al != null && Config.screen_al.size() > 0) {

            for (int i = 0; i < Config.screen_al.size(); i++) {
                Activity _activity = Config.screen_al.get(i);

                if (_activity != null) {
                    _activity.finish();
                }
            }
        }
    }*/

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);

        }
    }


    public static int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }


    public static Bitmap rotateBitmap(Bitmap b, int degrees) {

        Matrix m = new Matrix();
        if (degrees != 0) {
            // clockwise
            m.postRotate(degrees, (float) b.getWidth() / 2,
                    (float) b.getHeight() / 2);
        }
        try {
            Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
                    b.getHeight(), m, true);
            if (b != b2) {
                b.recycle();
                b = b2;
            }
        } catch (OutOfMemoryError ex) {
            // We have no memory to rotate. Return the original bitmap.
        }
        return b;
    }

    public static void ConvertImage(Context context, Bitmap bitmap, String name) {
        try {
            File imageFile = new File(context.getApplicationInfo().dataDir, name);
            OutputStream os;
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
        }
    }

   /* public static Retrofit getRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(Config.TIMEOUT_CONNECTION, TimeUnit.MILLISECONDS);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Config.HOST).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit;
    }
*/
    public static Dialog dialog(Context context) {
        if (dialog != null)
            return dialog;
        else
            dialog = new Dialog(context);

        return dialog;
    }


  /*  public void SendFirebaseerror(Context context, String error) {
        FirebaseCrash.report(new Exception(error));
        Log.print(context.getClass() + " :: Exception :: ", error);
    }*/

    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern
            .compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$");


    /**
     * Check Connectivity of network.
     */
    // Check Network Status
    public static boolean isOnline(Context context) {

        try {

            ConnectivityManager conMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = conMgr.getActiveNetworkInfo();
            if (info != null && info.isConnected())
                return true;
            else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            // Log.error("Utils :: isonline() ", e);
            return false;
        }
    }

    public static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static Typeface getFont(Context context, int tag) {

        if (tag == 100) {
            return Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        } else if (tag == 200) {
            return Typeface.createFromAsset(context.getAssets(), "Roboto-Medium.ttf");
        } else if (tag == 300) {
            return Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        } else if (tag == 400) {
            return Typeface.createFromAsset(context.getAssets(), "Roboto-Thin.ttf");
        } else if (tag == 700) {
            return Typeface.createFromAsset(context.getAssets(), "Roboto_Bold.ttf");
        } else if (tag == 800) {
            return Typeface.createFromAsset(context.getAssets(), "Satisfy-Regular.ttf");
        } else if (tag == 1100) {
            return Typeface.createFromAsset(context.getAssets(), "MaterialIconsRegular.ttf");
        } else if (tag == 1200) {
            return Typeface.createFromAsset(context.getAssets(), "FontAwesomeWebfont.ttf");
        } else if (tag == 1300) {
            return Typeface.createFromAsset(context.getAssets(), "MaterialDesignIconsWebfont.ttf");
        }
        return Typeface.DEFAULT;
    }

    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    public static String getUDID(Context objContext) {
        return Secure.getString(objContext.getContentResolver(),
                Secure.ANDROID_ID);

    }


    public static Date convertStringToDate(String strDate, String parseFormat) {
        try {
            if (strDate == null || strDate.equals(""))
                return null;
            return new SimpleDateFormat(parseFormat).parse(strDate);

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertDateToString(Date strDate, String parseFormat) {
        try {
            if (strDate == null || strDate.equals(""))
                return null;
            return new SimpleDateFormat(parseFormat).format(strDate);

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convert Current String to String formate with apply new date formate
     * Function
     */
    public static String convertDateStringToString(String strDate, String currentFormat, String parseFormat) {
        try {
            if (strDate == null || strDate.equals(""))
                return null;
            return convertDateToString(
                    convertStringToDate(strDate, currentFormat), parseFormat);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * for spinner
     */
    public static int indexOfStringArray(String[] strArray, String strFind) {
        int index;

        for (index = 0; index < strArray.length; index++)
            if (strArray[index].equals(strFind))
                break;

        return index;
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;

        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    /*public static void copyAssets(Context pContext, String pAssetFilePath, String pDestDirPath) {
        AssetManager assetManager = pContext.getAssets();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(pAssetFilePath);
            File outFile = new File(pDestDirPath, pAssetFilePath);
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (IOException e) {
            FirebaseCrash.report(new Exception(e.toString()));
        }
    }*/

    private static void copyFile(InputStream in, OutputStream out)
            throws IOException {
        byte[] buffer = new byte[1024 * 16];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public static void deleteCache(Context context) {
        System.gc();
        File dir = null;
        try {
            dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }


    public static void DeleteTempFileInData(Context context) {
        File dir = new File(context.getApplicationInfo().dataDir);
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File child = new File(dir, children[i]);
                System.out.println("==delete file name==" + child.getAbsolutePath());
                if (child.isFile() && child.getName().contains("temp_")) {
                    child.delete();
                }
            }
        }
    }

    public static void DeleteFileFormDATA(Context context, String name) {
        File dir = new File(context.getApplicationInfo().dataDir);
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File child = new File(dir, children[i]);
                System.out.println("==delete file name==" + child.getAbsolutePath());
                if (child.isFile() && child.getName().contains(name)) {
                    child.delete();
                    break;
                }
            }
        }
    }

    public static boolean checkFileSize(String path, int size) {
        File file = new File(path);
        if (file != null && file.exists()) {
            return file.length() <= (size * 1024 * 1024);
        }
        return false;
    }

    public static String getMimeType(Context context, Uri uri) {
        String mimeType = null;
        if (uri.getScheme().equalsIgnoreCase(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }


        String noOfMimeType[] = "application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.openxmlformats-officedocument.wordprocessingml.template,text/plain,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.presentationml.presentation,application/vnd.ms-powerpointtd,application/vnd.openxmlformats-officedocument.presentationml.slideshow,application/postscript,application/pdf,application/zip,application/x-rar-compressed,text/csv,text/html,text/xml".split(",");
        for (int i = 0; i < noOfMimeType.length; i++) {
            if (noOfMimeType[i].equalsIgnoreCase(mimeType)) {
                return mimeType;
            }
        }

        return null;
    }


    public static boolean isGotoCrop(Context context, Uri imgDestination, int minWidth, int minHeight) {
        boolean isGoto = false;
        try {
            Bitmap bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imgDestination);
            if (bm.getWidth() > minWidth && bm.getHeight() > minHeight) {
                isGoto = true;

            } else {
                Toast.makeText(context, "Minimum image dimension must be " + minWidth + " X " + minHeight, Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("inside camear" + e.toString());
        }
        return isGoto;
    }

    public static String milliSecondsToTimer(long milliseconds) {

        int timeSec = (int) milliseconds / 1000;
        int Minutes = timeSec / 60;
        int Seconds = timeSec % 60;

        String minString = Integer.toString(Minutes);
        String secString = Integer.toString(Seconds);

        if (Minutes < 10) {
            minString = "0" + Minutes;
        }
        if (Seconds < 10) {
            secString = "0" + Seconds;
        }

        return minString + ":" + secString;
    }

    public static int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        return percentage.intValue();
    }

    public static int progressToTimer(int progress, long totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double) progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }
    public static boolean isMyServiceRunning(Context context,Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {

                return true;
            }
        }

        return false;
    }



}
