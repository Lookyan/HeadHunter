package com.example.alex.headhunter.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alex.headhunter.R;

/**
 * Created by nano on 26.12.15.
 */
public class SearchResultsFragment extends ListFragment {

    String data[] = new String[] { "Программист 1С",
            "Android-разработчик", "Программист С", "Java-программист",
            "Программист 1С",
            "Android-разработчик", "Программист С", "Java-программист",
            "Программист 1С",
            "Android-разработчик", "Программист С", "Java-программист",
            "Программист 1С",
            "Android-разработчик", "Программист С", "Java-программист"};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.result_item, data);

        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_results, null);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
    }
}
