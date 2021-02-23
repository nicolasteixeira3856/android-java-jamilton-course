package com.nicolas.zenos.fragments.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicolas.zenos.fragments.R;

public class ContactsFragment extends Fragment {

    private TextView textContact;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        textContact = view.findViewById(R.id.textContact);
        textContact.setText("Contato alterado");

        return view;
    }
}