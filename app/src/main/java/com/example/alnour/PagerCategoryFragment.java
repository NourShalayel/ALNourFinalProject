package com.example.alnour;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.content.pm.PackageManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.util.ArrayList;


public class PagerCategoryFragment extends Fragment {
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private StorageReference sref;
    private FirebaseStorage storage;
    final Context c = getContext();

    ArrayList<Category> cat_list = new ArrayList<>();
    String productFile;

    Button downloadCat_btn;
    public PagerCategoryFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_pager_category, container, false);
        downloadCat_btn = v.findViewById(R.id.downloadCat_btn);

        downloadCat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCategoryFromDB();

                DownloadFile();
            }
        });
        return  v ;

    }

    public void DownloadFile(){


//        Log.e("uri", "DownloadFile: " + sref.child("Document/file.txt").getDownloadUrl().toString() );
        Uri uri = Uri.parse("https://console.firebase.google.com/u/0/project/alnour-78dec/storage/alnour-78dec.appspot.com/files/~2FDocument/file.txt");
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setTitle("Download ....");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "" + System.currentTimeMillis());
        DownloadManager manager = (DownloadManager)getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);


    }

    public void readCategoryFromDB() {

        cat_list.clear();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("products");
        Task<DataSnapshot> task = ref.get();
        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    Iterable<DataSnapshot> data = task.getResult().getChildren();

                    for (DataSnapshot snap : data) {
                        Category pro = snap.getValue(Category.class);
                        cat_list.add(pro);
                        Log.e("ee", "" + cat_list);
                    }

                    productFile = new Gson().toJson(cat_list);
                    sref = FirebaseStorage.getInstance().getReference().child("Document");
                    sref.child("file.txt").putBytes(productFile.toString().getBytes()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(getContext() , "Success" ,Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext() , "Failed" ,Toast.LENGTH_SHORT).show();
                            String errorMessage = task.getException().getMessage();
                            Log.e("error", "onFailure: "+ errorMessage );

                        }
                    });
                } else {
                    String errorMessage = task.getException().getMessage();
//                    Toast.makeText(getActivity(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

}