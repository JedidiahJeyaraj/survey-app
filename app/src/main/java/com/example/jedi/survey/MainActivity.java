package com.example.jedi.survey;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handler = new DatabaseHandler(this);
        FILL_DATA();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewSurvey.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void FILL_DATA() {
        Cursor REMINDER_CURSOR = handler.GET_ALL_DATA();
        startManagingCursor(REMINDER_CURSOR);
        String [] from = new String[]{handler.NAME,handler.ORGANIZATION_NAME};
        int[] to = new int[]{R.id.first,R.id.second};
        ListView view =(ListView)findViewById(android.R.id.list);
        SimpleCursorAdapter details = new SimpleCursorAdapter(getApplicationContext()
                ,R.layout.surveylist,REMINDER_CURSOR,from,to);
        view.setAdapter(details);
        registerForContextMenu(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FILL_DATA();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivityForResult(intent, 0);
        }

        return super.onOptionsItemSelected(item);
    }
}
