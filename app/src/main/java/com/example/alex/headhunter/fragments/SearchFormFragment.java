package com.example.alex.headhunter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.alex.headhunter.R;


public class SearchFormFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_form, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final Button btn1 = (Button) view.findViewById(R.id.search_btn);
    }

}
