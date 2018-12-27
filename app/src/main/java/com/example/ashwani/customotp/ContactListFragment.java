package com.example.ashwani.customotp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;
import static android.content.ContentValues.TAG;
import static com.example.ashwani.customotp.R.layout.list_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {


    public ContactListFragment() {
        // Required empty public constructor
    }

    View view;
    ListView listView;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        context = view.getContext();
        listView = view.findViewById(R.id.contact_list);
        final ArrayList<String> contacts = new ArrayList<>();

        contacts.add("ashwani\n9911416637");
        contacts.add("home\n7982278251");
        contacts.add("UntroddenLab\n8510015577");

        ArrayAdapter adapter;
        adapter = new ArrayAdapter(context, simple_list_item_1, contacts);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onClick: " + contacts.get(position));

                Intent intent = new Intent(context, ComposeMsgActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("contact", contacts.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

}
