package com.alexvoronkov.difdevices;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Санек on 01.10.2017.
 */

public class DetailsFragment extends Activity implements Serializable{

    public static final String ANDROID_VERSION = "";
    public ArrayList<AndroidVersion> android;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_details);

        //данные с recyclerView через Serializable в виде массива данных выбранного элемента
        AndroidVersion ver = (AndroidVersion) getIntent().getExtras().getSerializable("obj");

        TextView name = (TextView) findViewById(R.id.name);
        TextView version = (TextView)findViewById(R.id.androidVersion);
        TextView api = (TextView)findViewById(R.id.apiVersion);

        String androidName = ver.getName();
        String androidVer = ver.getVer();
        String androidApi = ver.getApi();

        name.setText(R.string.name + " " + androidName);
        version.setText(R.string.version + " " + androidVer);
        api.setText(R.string.api + " " + androidApi);
    }
}
