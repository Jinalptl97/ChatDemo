package com.example.krunal.chatapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Chatfregment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Chatfregment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Chatfregment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<ChatBean> chat;
    ChatAdapter chatAdapter;
    DBHelper dbHelper;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Chatfregment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Chatfregment.
     */
    // TODO: Rename and change types and number of parameters
    public static Chatfregment newInstance(String param1, String param2) {
        Chatfregment fragment = new Chatfregment();
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
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_chatfregment, container, false);
        setupRecyclerView(rv);


        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        dbHelper=new DBHelper(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        chat = new ArrayList<ChatBean>();
        chat.clear();
        chat=dbHelper.getList();
        System.out.println("the size of"+chat.size());
       /* chat.add(new ChatBean(1,"ankita","Hello","10:24 AM"));
        chat.add(new ChatBean(2,"manish","Hii","10:26 AM"));
        chat.add(new ChatBean(3,"jinal","How r u","10:30 AM"));
        chat.add(new ChatBean(4,"krunal","i m fine","10:35 AM"));
        chat.add(new ChatBean(5,"sejal","Hello","10:27 AM"));*/
        chatAdapter=new ChatAdapter(getActivity(),chat);
        chatAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(chatAdapter);






    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




}
