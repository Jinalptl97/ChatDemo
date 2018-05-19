package com.example.krunal.chatapp;

import android.app.Activity;
import android.os.Environment;

import java.util.ArrayList;

public class Config {


    public static String TAG = "FanCase";
    public static String DB_NAME = "fancase.db";

    // preference file name
    public static final String PREF_FILE = TAG + "_PREF";
    public static String APP_HOME = Environment.getExternalStorageDirectory().getPath() + "/" + TAG;
    public static String DIR_LOG = APP_HOME + "/log";
    public static String DIR_USERDATA = APP_HOME + "/userdata";


    public static final String API_KEY = "FanCase@#2017!#";
    public static final String API_HEADER = "AUTH-KEY:" + API_KEY;
    public static final String API_VERSION = "v1/";


    public static String LIVE = "http://192.168.1.42/work/fancase";
    //LIVE

    public static String HOST = LIVE + "/api/" + API_VERSION;


    public static String IMAGE_SHOWCASE_DOWNLOAD_URL = LIVE + "/public/media/userdata/";
    public static String WALLPAPER_DOWNLOAD_URL = LIVE + "/public/media/genre/";

    // Handle Open Screen Stack
    public static ArrayList<Activity> screen_al;

    public static int API_SUCCESS = 0;
    public static int API_FAIL = 1;

    // connection timeout is set to 20 seconds
    public static int TIMEOUT_CONNECTION = 20000;

    public static final int CAMARA_PERMISSION_CODE = 150;
    public static final int GALLERY_PERMISSION_CODE = 160;
    public static final int OPEN_GALLERY_IMAGE_CODE = 170;
    public static final int OPEN_CAMARA_IMAGE_CODE = 180;
    public static final int OPEN_GALLERY_VIDEO_CODE = 190;
    public static final int OPEN_CAMARA_VIDEO_CODE = 200;
    public static final int OPEN_AUDIO_CODE = 210;
    public static final int LOCATION_PERMISSION_CODE = 220;
    public static final int OPEN_DOCUMENT_CODE = 230;

    public static final String TEMP_UPLOAD_IMAGE_NAME = "temp_image.jpeg";
    public static final String TEMP_UPLOAD_PROFILE_NAME = "avatar.jpg";

    //API
    public static final String API_SYNC = "init/sync";
    public static String TAG_SYNC = "TAG_SYNC";
    public static final String API_LOGIN = "user/login";
    public static String TAG_LOGIN = "TAG_LOGIN";
    public static final String API_REGISTER = "user/register";
    public static String TAG_REGISTER = "TAG_REGISTER";
    public static String TAG_BASICINFO = "TAG_BASICINFO";
    public static final String API_BASICINFO = "user/basicinfo";


    public static final String API_LOGINWITHFACEBOOK = "user/registerwithfacebook";
    public static String TAG_LOGINWITHFACEBOOK = "TAG_LOGINWITHFACEBOOK";
    public static final String API_REPORT_PROBLEM = "misc/reportproblem";
    public static String TAG_REPORT_PROBLEM = "TAG_REPORT_PROBLEM";
    public static final String API_CHANGEPASSWORD = "user/changepassword";
    public static String TAG_CHANGEPASSWORD = "TAG_CHANGEPASSWORD";
    public static final String API_CHANGEEMAIL = "user/changeemail";
    public static String TAG_ChANGEEMAIL = "TAG_ChANGEEMAIL";
    public static final String API_PRIVATEACCOUNT = "user/privateaccount";
    public static String TAG_PRIVATEACCOUNT = "TAG_PRIVATEACCOUNT";
    public static final String API_PUSHNOTIFICATION = "user/pushnotification";
    public static String TAG_PUSHNOTIFICATION = "TAG_PUSHNOTIFICATION";
    public static final String API_EMAILNOTIFICATION = "user/emailnotification";
    public static String TAG_EMAILNOTIFICATION = "TAG_EMAILNOTIFICATION";
    public static final String API_FORGOTPASSWORD = "user/forgotpassword";
    public static String TAG_FORGOTPASSWORD = "TAG_FORGOTPASSWORD";
    public static final String API_CHANGEAVATAR = "user/changeavatar";
    public static String TAG_CHANGEAVATAR = "TAG_CHANGEAVATAR";
    public static final String API_CHECKVERIFICATION = "user/checkverification";
    public static String TAG_CHECKVERIFICATION = "TAG_CHECKVERIFICATION";

    public static final String API_CHANGEAUTOGRAPH = "user/changeautograph";
    public static String TAG_CHANGEAUTOGRAPH = "TAG_CHANGEAUTOGRAPH";

    /*POST*/
    public static final String API_CREATE_POST = "showcase/createpost";
    public static String TAG_CREATE_POST = "TAG_CREATE_POST";

    public static String TAG_POST_LIST = "TAG_POST_LIST";
    public static final String API_POST_LIST = "showcase/list";

    public static String TAG_COMMENT_LIST = "TAG_COMMENT_LIST";
    public static final String API_COMMENT_LIST = "showcase/comments";

    public static String TAG_ADD_COMMENT = "TAG_ADD_COMMENT";
    public static final String API_ADD_COMMENT = "showcase/commentadd";

    public static String TAG_POST_LIKE = "TAG_POST_LIKE";
    public static final String API_POST_LIKE = "showcase/like";

    public static String TAG_POST_DETAIL = "TAG_POST_DETAIL";
    public static final String API_POST_DETAIL = "showcase/detail";


    /*profile*/
    public static String TAG_VIEW_PROFILE = "TAG_VIEW_PROFILE";
    public static final String API_VIEW_PROFILE = "profile/view";


    public static String TAG_WORKEXP_ADD = "TAG_WORKEXP_ADD";
    public static final String API_WORKEXP_ADD = "profile/experienceadd";
    public static String TAG_WORKEXP_DELETE = "TAG_WORKEXP_DELETE";
    public static final String API_WORKEXP_DELETE = "profile/experiencedelete";
    public static String TAG_EDIT_WORK = "TAG_EDIT_WORK";
    public static final String API_EDIT_WORK = "profile/experienceupdate";

    public static String TAG_ACHIVE_ADD = "TAG_ACHIVE_ADD";
    public static final String API_ACHIVE_ADD = "profile/achievementadd";
    public static String TAG_ACHIVE_DELETE = "TAG_ACHIVE_DELETE";
    public static final String API_ACHIVE_DELETE = "profile/achievementdelete";
    public static String TAG_ACHIVE_EDIT = "TAG_ACHIVE_EDIT";
    public static final String API_ACHIVE_EDIT = "profile/achievementupdate";
    public static String TAG_EVENT_ADD = "TAG_EVENT_ADD";
    public static final String API_EVENT_ADD = "profile/eventadd";
    public static String TAG_EVENT_DELETE = "TAG_EVENT_DELETE";
    public static final String API_EVENT_DELETE = "profile/eventdelete";
    public static String TAG_EVENT_EDIT = "TAG_EVENT_EDIT";
    public static final String API_EVENT_EDIT = "profile/eventupdate";
    public static String TAG_ACCOMPLISH_ADD = "TAG_ACCOMPLISH_ADD";
    public static final String API_ACCOMPLISH_ADD = "profile/accomplishmentadd";
    public static String TAG_ACCOMPLISH_DELETE = "TAG_ACCOMPLISH_DELETE";
    public static final String API_ACCOMPLISH_DELETE = "profile/accomplishmentdelete";
    public static String TAG_ACCOMPLISH_EDIT = "TAG_ACCOMPLISH_EDIT";
    public static final String API_ACCOMPLISH_EDIT = "profile/accomplishmentupdate";

    public static String TAG_SKILL_ADD = "TAG_SKILL_ADD";
    public static final String API_SKILL_ADD = "profile/skilladd.json";
    public static String TAG_SKILL_DELETE = "TAG_SKILL_DELETE";
    public static final String API_SKILL_DELETE = "profile/skilldelete";
    public static String TAG_SKILL_EDIT = "TAG_SKILL_EDIT";
    public static final String API_SKILL_EDIT = "profile/skillupdate";

    public static String TAG_PRODUCT_ADD = "TAG_PRODUCT_ADD";
    public static final String API_PRODUCT_ADD = "profile/productadd";
    public static String TAG_PRODUCT_DELETE = "TAG_PRODUCT_DELETE";
    public static final String API_PRODUCT_DELETE = "profile/productdelete";
    public static String TAG_PRODUCT_EDIT = "TAG_PRODUCT_EDIT";
    public static final String API_PRODUCT_EDIT = "profile/productupdate";

    public static String TAG_EDUCATION_ADD = "TAG_EDUCATION_ADD";
    public static final String API_EDUCATION_ADD = "profile/educationadd";
    public static String TAG_EDUCATION_DELETE = "TAG_EDUCATION_DELETE";
    public static final String API_EDUCATION_DELETE = "profile/educationdelete";
    public static String TAG_EDUCATION_EDIT = "TAG_EDUCATION_EDIT";
    public static final String API_EDUCATION_EDIT = "profile/educationupdate";

    public static final String API_SEARCHSUGGESTION = "profile/searchsuggestion";
    public static String TAG_SEARCHSUGGESTION = "TAG_SEARCHSUGGESTION";

    public static final String API_SEARCH = "profile/search";
    public static String TAG_SEARCH = "TAG_SEARCH";

    public static final String API_BLOCKEDUSER = "profile/blocked";
    public static String TAG_BLOCKEDUSER = "TAG_BLOCKEDUSER";

    public static final String API_FOLLOWERS = "profile/followers";
    public static String TAG_FOLLOWERS = "TAG_FOLLOWERS";

    public static final String API_FOLLOWING = "profile/following";
    public static String TAG_FOLLOWING = "TAG_FOLLOWING";

    public static final String API_AUTOGRAPH = "profile/autograph";
    public static String TAG_AUTOGRAPH = "TAG_AUTOGRAPH";

    public static final String API_BlOCK = "profile/block";
    public static String TAG_BLOCK = "TAG_BLOCK";

    public static final String API_FOLLOW = "profile/follow";
    public static String TAG_FOLLOW = "TAG_FOLLOW";






    /* PREFERENCE VARIABLES */

    public static String PREF_DB_VERSION = "PREF_DB_VERSION";
    public static String PREF_LAST_UPDATED = "PREF_LAST_UPDATED";
    public static String PREF_USERID = "PREF_USERID";
    public static String PREF_NOTIFICATION = "PREF_NOTIFICATION";
    public static String PREF_UDID = "PREF_UDID";
    public static String PREF_PUSH_ID = "PREF_PUSH_ID";
    public static String PREF_DEVICE_TYPE = "1";

    /*LOGIN*/
    public static String PREF_FACEBOOK_ID = "PREF_FACEBOOK_ID";
    public static String PREF_EMAIL = "PREF_EMAIL";
    public static String PREF_IMAGE = "PREF_IMAGE";
    public static String PREF_AUTOGRAPH = "PREF_AUTOGRAPH";
    public static String PREF_ROLE = "PREF_ROLE";
    public static String PREF_USERNAME = "PREF_USERNAME";
    public static String PREF_LASTNAME = "PREF_LASTNAME";
    public static String PREF_COMPANY_NAME = "PREF_COMPANY_NAME";
    public static String PREF_GENDER = "PREF_GENDER";
    public static String PREF_BIRTHDATE = "PREF_BIRTHDATE";
    public static String PREF_LATITUDE = "PREF_LATITUDE";
    public static String PREF_LONGITUDE = "PREF_LONGITUDE";
    public static String PREF_ISPRIVATE = "PREF_ISPRIVATE";
    public static String PREF_ISVERIFIED = "PREF_ISVERIFIED";
    public static String PREF_PUSH_NOTIFICATION = "PREF_PUSH_NOTIFICATION";
    public static String PREF_EMAIL_NOTIFICATION = "PREF_EMAIL_NOTIFICATION";
    public static String PREF_CREATED = "PREF_CREATED";
    public static String PREF_GENRE = "PREF_GENRE";
    public static String PREF_WALLPAPER = "PREF_WALLPAPER";
    public static String PREF_LAYOUT = "PREF_LAYOUT";
    public static String PREF_LOCATION = "PREF_LOCATION";
    /* setting*/
    public static String PREF_FOLLOWERS_REQUEST = "PREF_FOLLOWERS_REQUEST";
    public static String PREF_FOLLOWERS = "PREF_FOLLOWERS";
    public static String PREF_FOLLOWING = "PREF_FOLLOWING";
    public static String PREF_RATING = "PREF_RATING";
    public static String PREF_POSTS = "PREF_POSTS";
    public static String PREF_COMPETITION = "PREF_COMPETITION";
    public static String PREF_JOB = "PREF_JOB";


    public static String TAG_IMAGE = "TAG_IMAGE";
    /*
     * Cookie and SESSION
     */
    public static String PREF_SESSION_COOKIE = "sessionid";
    public static String SET_COOKIE_KEY = "Set-Cookie";
    public static String COOKIE_KEY = "Cookie";
    public static String SESSION_COOKIE = "sessionid";

    public static int RESULT_OK = 0;
    public static int RESULT_FAIL = 1;


    public static String FACEBOOK_LOGIN_TAG = "FACEBOOK_LOGIN_TAG";
    public static String FACEBOOK_PROFILE_TAG = "FACEBOOK_PROFILE_TAG";
    public static int FACEBOOK_REQUEST = 130;

}
