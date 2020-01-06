package com.anioncode.witcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private WitcherAdapterSound mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        setContentView(R.layout.activity_main);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        recyclerView=findViewById(R.id.item_sound);
        ArrayList<ModelWitcher> myDataset=new ArrayList<>();
        myDataset.add(new ModelWitcher("\"Toss a coin to your Witcher\"","raw1"));
        myDataset.add(new ModelWitcher("\"I want you to burst ,you son of..\"","raw2"));
        myDataset.add(new ModelWitcher("\"Fuck\"","raw3"));
        myDataset.add(new ModelWitcher("\"hmm..\"","raw4"));

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
        mAdapter = new WitcherAdapterSound(myDataset,this);
        recyclerView.setAdapter(mAdapter);
    }
}
