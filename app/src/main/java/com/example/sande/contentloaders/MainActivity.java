package com.example.sande.contentloaders;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView listView;
    Uri uri;
    SimpleCursorAdapter adapter;
    int LOADER_ID = 007;
    Bundle additionalIns = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(LOADER_ID,additionalIns,this);

        listView = findViewById(R.id.listView);
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,//layout
                null,//data
                new String[]{"body","address"},//from db columns
                new int[]{android.R.id.text1,android.R.id.text2},//to which textviews of layout
                1);
        listView.setAdapter(adapter);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        uri = Uri.parse("content://sms/inbox"); //uri is my warehouse database

        return new CursorLoader(this,uri,null,null,null,null);
        //hiring a coolie
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
