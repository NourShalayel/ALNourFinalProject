package com.example.alnour;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISION_STORAGE_CODE = 1000;
    Button savebtn;
    FirebaseFirestore db;
    ArrayList<Category> cat_list = new ArrayList<>();
    String URLSAVE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savebtn = findViewById(R.id.savebtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFile();
//                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
//                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
//                            PackageManager.PERMISSION_DENIED){
//                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                        requestPermissions(permissions , PERMISION_STORAGE_CODE);
//                    }else{
//                        downloadFile();
//                    }
//                }else{
//                    downloadFile();
//                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = (SearchView) item.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void dataFromFirebase() {
        if (cat_list.size() > 0)
            cat_list.clear();

        db = FirebaseFirestore.getInstance();

        db.collection("categories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot : task.getResult()) {
                            URLSAVE = documentSnapshot.getString("categories");
                        }
//                        downloadFile(URLSAVE);
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error ;-.-;", Toast.LENGTH_SHORT).show();
                    }
                })
        ;


    }

    public void downloadFile() {

        Uri uri = Uri.parse("http://www.africau.edu/images/default/sample.pdf");
        DownloadManager.Request request = new DownloadManager.Request(uri);

//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download ....");
//        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISION_STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadFile();
                } else {
                    Toast.makeText(this, " permission denai", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



}