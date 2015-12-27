package com.example.alex.headhunter.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alex.headhunter.R;
import com.example.alex.headhunter.content.HHContentProvider;
import com.example.alex.headhunter.content.contracts.SearchResultContract;

import java.net.URI;

public class SearchResultsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

//
//    String data[] = new String[] { "Программист 1С",
//            "Android-разработчик", "Программист С", "Java-программист",
//            "Программист 1С",
//            "Android-разработчик", "Программист С", "Java-программист",
//            "Программист 1С",
//            "Android-разработчик", "Программист С", "Java-программист",
//            "Программист 1С",
//            "Android-разработчик", "Программист С", "Java-программист"};

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
//                R.layout.result_item, data);
//
//        setListAdapter(adapter);
//    }

    private final int LOADER_ID = 0;
    private final Uri CONTENT_URI = Uri.parse("content://com.example.alex.headhunter.provider/search_result");

    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_results, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] from = new String[] {
                SearchResultContract.SearchResultEntry.COLUMN_NAME_NAME,
                SearchResultContract.SearchResultEntry.COLUMN_NAME_EMPLOYER_NAME
        };

        int[] to = new int[] {
                R.id.vacancy_name,
                R.id.vacancy_employer_name
        };

        ListView listView = (ListView) view.findViewById(R.id.list);
        simpleCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.result_item,
                null, from, to, LOADER_ID
        );
        listView.setAdapter(simpleCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
            }
        });

        getActivity().getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), CONTENT_URI, new String[] {
                SearchResultContract.SearchResultEntry.COLUMN_NAME_VACANCY_ID,
                SearchResultContract.SearchResultEntry.COLUMN_NAME_NAME,
                SearchResultContract.SearchResultEntry.COLUMN_NAME_EMPLOYER_NAME
        }, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case LOADER_ID:
                simpleCursorAdapter.swapCursor(data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        simpleCursorAdapter.swapCursor(null);
    }

//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
//    }
}
