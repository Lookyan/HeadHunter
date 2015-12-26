package com.example.alex.headhunter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by nano on 26.12.15.
 */
public class SearchResultFragment extends Fragment {
    private String[] data = {"1","2","3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_results, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ListView listResult = (ListView) view.findViewById(R.id.listResult);

        int[] toViews = {android.R.id.text1};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
                R.layout.result_item, null,
                data, toViews, 0);


        listResult.setAdapter(adapter);

    }



}
