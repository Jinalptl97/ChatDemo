package com.example.krunal.chatapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;



import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Contactfregment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Contactfregment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contactfregment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView list_contact;
    ArrayList<ContactBean> StoreContacts;
    public static final int RequestPermissionCode = 1;
    Cursor cursor;
    String name, phonenumber;
    ContactAdapter contactAdapter;
    ContactBean contactBean;
     TextView txtTime;
     String simpleDateFormat;


    private AdapterView.OnItemSelectedListener listener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Contactfregment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Contactfregment.
     */
    // TODO: Rename and change types and number of parameters
    public static Contactfregment newInstance(String param1, String param2) {
        Contactfregment fragment = new Contactfregment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contactfregment, container, false);

        list_contact = (RecyclerView) view.findViewById(R.id.contact_list);


        list_contact.setLayoutManager(new LinearLayoutManager(list_contact.getContext()));



        StoreContacts = new ArrayList<ContactBean>();

       /* update();
*/
      /*  EnableRuntimePermission();*/
        GetContactsIntoArrayList();
        System.out.println("the size of" + StoreContacts.size());
        contactAdapter = new ContactAdapter(getActivity(), StoreContacts);
        list_contact.setAdapter(contactAdapter);





        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }



    public void GetContactsIntoArrayList() {


        StoreContacts.clear();
        cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {

            contactBean = new ContactBean();


            contactBean.name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            contactBean.number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

              int type = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));


            switch (type) {
                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                    Log.i("TYPE_HOME", "" +  contactBean.number);
                    contactBean.type="HOME";
                    break;
                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                    Log.i("TYPE_MOBILE", "" +contactBean.number);
                    contactBean.type="MOBILE";
                    break;
                case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                    Log.i("TYPE_WORK", "" + contactBean.number);
                    contactBean.type="WORK";
                    break;

                case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
                    Log.i("TYPE_OTHER", "" + contactBean.number);
                    contactBean.type="OTHER";
                    break;
            }


            StoreContacts.add(contactBean);
        }


        cursor.close();

    }

   /* public  void update()
    {
        Calendar c = Calendar.getInstance();

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());

        StringTokenizer tk = new StringTokenizer(simpleDateFormat);
        String date = tk.nextToken();
        String time = tk.nextToken();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
        Date dt = null;
        try {
            dt = sdf.parse(time);
            System.out.println("Time Display: " + sdfs.format(dt)); // <-- I got result here
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        txtTime.setText(sdfs.format(dt));

    }
*/
  /*  public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                getActivity(),
                Manifest.permission.READ_CONTACTS)) {

            Toast.makeText(getActivity(), "CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getActivity(), "Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getActivity(), "Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
*/

}


