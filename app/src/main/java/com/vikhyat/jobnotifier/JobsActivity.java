package com.vikhyat.jobnotifier;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



import modal.JobPosting;

public class JobsActivity extends AppCompatActivity {
    ListView JobsPosting;
    ArrayList<JobPosting> jobPostingArray;
    FirebaseFirestore db;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        JobsPosting = findViewById(R.id.PostingsListView);
        pb = findViewById(R.id.pb);
        jobPostingArray = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        setVisible(pb);
        loadDatainListView();
    }

    private void loadDatainListView() {
        db.collection("Postings").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            hideVisible(pb);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            Toast.makeText(JobsActivity.this, "data is present and is being loaded ", Toast.LENGTH_LONG).show();
                            for (DocumentSnapshot d : list) {
                                JobPosting jobPosting = d.toObject(JobPosting.class);
                                jobPostingArray.add(jobPosting);
                            }
                            PostingsListViewAdapter adapter = new PostingsListViewAdapter(JobsActivity.this, 0, jobPostingArray);
                            JobsPosting.setAdapter(adapter);
                        } else {
                            Toast.makeText(JobsActivity.this, "No data available", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(JobsActivity.this, e.toString() + "No data found in database", Toast.LENGTH_LONG).show();
                    }
                });
        db.collection("Positions")
                .addSnapshotListener((snapshots, e) -> {
                    if (e != null) {
                        Log.w("TAG", "listen:error" + e);
                        return;
                    }
                    for (DocumentChange dc : snapshots.getDocumentChanges()){
                        Toast.makeText(this,String.valueOf(dc),Toast.LENGTH_LONG).show();
                        switch(dc.getType()){
                            case ADDED:
                                Toast.makeText(JobsActivity.this,"New msg" + dc.getDocument().toObject(JobPosting.class),Toast.LENGTH_LONG).show();
                                break;
                            case MODIFIED:
                                Log.d("TAG","Modified msg" + dc.getDocument().toObject(JobPosting.class));
                                break;
                            case REMOVED:
                                Log.d("TAG","Removed msg" + dc.getDocument().toObject(JobPosting.class));
                                break;
                        }
                    }
                });
    }
    private void setVisible(ProgressBar pb){
                      pb.setVisibility(View.VISIBLE);
        }

    private void hideVisible(ProgressBar pb){
                      pb.setVisibility(View.GONE);
        }
}