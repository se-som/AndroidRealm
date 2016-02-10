package com.example.sesom1.androidrealm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sesom1.androidrealm.realmModule.RealmHelper;
import com.example.sesom1.androidrealm.realmModule.RealmUser;

import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getUser();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataToRealm();

            }
        });
    }

    private void addDataToRealm() {
        try {
            RealmUser user = new RealmUser();
            user.setId("3");
            user.setName("b");
            user.setPhone("098655");
            user.setEmail("ddafa@gmail.com");
            user.setFacebook("adfff.com");
            RealmHelper.getInstance(getBaseContext()).addUser(user);
            user.setId("4");
            user.setName("b4");
            user.setPhone("098655sss");
            user.setEmail("ddasdadffa@gmail.com");
            user.setFacebook("dfsfsf.com");
            user.setTwitter("dddd.twitter");

            RealmHelper.getInstance(getBaseContext()).addUser(user);

            user.setId("5");
            user.setName("bff4");
            user.setPhone("098655sss");
            user.setEmail("ddasdaddfffa@gmail.com");
            user.setFacebook("dfsfddsf.com");
            user.setTwitter("twitter.com");

            RealmHelper.getInstance(getBaseContext()).addUser(user);
            Log.i("On","dd");
        } catch (RealmMigrationNeededException e){

        }

    }

    private void getUser(){
        try {
            RealmResults<RealmUser> users = RealmHelper.getInstance(getBaseContext()).getUser();
            Log.i("On", "user=" + users.size());
            Log.i("On", "users="+users.toString());
        } catch (RealmMigrationNeededException e){

        }

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
