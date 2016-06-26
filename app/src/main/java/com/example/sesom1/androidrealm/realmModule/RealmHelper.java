package com.example.sesom1.androidrealm.realmModule;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by sesom1 on 2/9/2016.
 */
public class RealmHelper {
    private static RealmHelper INSTANCE;

    private Context context;

    RealmConfiguration realm;

    /**
     * @param context
     */
    private RealmHelper(Context context) {
        setContext(context);
        setConfig();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    private void setConfig() {
        realm = new RealmConfiguration.Builder(getContext()).name("AndroidRealm.realm").schemaVersion(1).deleteRealmIfMigrationNeeded().migration(new Migration()).build();
    }

    public static RealmHelper getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new RealmHelper(context);
        return INSTANCE;
    }

    public RealmConfiguration getConfig() {
        return realm;
    }

    public void addUser(RealmUser user){
        try {
            Realm realm = Realm.getInstance(getConfig());
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();
            Log.i("On", "add success");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public RealmResults<RealmUser> getUser(){
        Realm realm = Realm.getInstance(getConfig());
        RealmResults<RealmUser> users = null;
        realm.refresh();
        realm.beginTransaction();
        try {
          users = realm.where(RealmUser.class).findAll();Log.i("On", "user===="+users.size());
        } catch (Exception e) {
            Log.i("On", "error" + e.toString());
        }
        realm.commitTransaction();
        return users;
    }

    public void saveToRealmData(final RealmList<MusicObjModel> dataSource) {
        Log.i("On", "size=" + dataSource.size());
        Realm realm = Realm.getInstance(getConfig());
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(dataSource);
        realm.commitTransaction();
    }

    public void saveToRealmDatabase(final RealmList<MusicObjModel> dataSource){
        Log.i("On", "size="+dataSource.size());
        try {
            new AsyncTask<JSONArray, Void, Void>() {
                @Override
                protected Void doInBackground(JSONArray... arg0) {
                    Realm realm = Realm.getInstance(getConfig());
                    realm.beginTransaction();
                    for (int i=0; i<dataSource.size(); i++ ) {
                        Log.i("On", "i="+i+"data="+dataSource.get(i).getId());
                        realm.copyToRealmOrUpdate(dataSource.get(i));

                    }
                    realm.commitTransaction();
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    Realm realm = Realm.getInstance(getConfig());
                    RealmResults<MusicObjModel> ss = realm.where(MusicObjModel.class).findAll();
                    Log.i("On", "result="+ss.size());
                    realm.commitTransaction();
                }
            }.execute();

        } catch (Exception e) {
            Log.i("On", "error 111=" + e.toString());
            e.printStackTrace();
        }
    }

}
