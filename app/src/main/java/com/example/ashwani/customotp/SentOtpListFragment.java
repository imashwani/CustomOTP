package com.example.ashwani.customotp;


import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;
import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class SentOtpListFragment extends Fragment {


    public SentOtpListFragment() {
        // Required empty public constructor
    }

    View view;
    Context context;
    ListView listView;
    ContactRoomDatabase contactRoomDatabase;
    List<Contact> contactList;

    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sent_otp_list, container, false);
        context = view.getContext();
        listView = view.findViewById(R.id.otp_listview);


        contactRoomDatabase = Room.databaseBuilder(getContext().getApplicationContext(),
                ContactRoomDatabase.class, "contact-database").build();

        new FetchDataAsync().execute();


        return view;
    }

    @Override
    public void onResume() {
        new FetchDataAsync().execute();
        super.onResume();
    }

    private class FetchDataAsync extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: starting");
            contactList = contactRoomDatabase.contactDao().getList();
            ArrayList<String> contacts = new ArrayList<>();
            for (Contact c : contactList) {
                contacts.add(c.nameAndPhone + "\t OTP:" + c.otp + "\n\nDate Time:" + c.time);
            }

            return contacts;
        }

        @Override
        protected void onPostExecute(List<String> s) {
            super.onPostExecute(s);

            initListView(s);

        }
    }

    private void initListView(List<String> s) {
        adapter = new ArrayAdapter(context, simple_list_item_1, s);
        listView.setAdapter(adapter);
    }

}
