package com.solvabit.recyclerviewwithfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

public class Main2Activity extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Event_reg_card> event_reg_cardsArrayList;
    EventRegRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        event_reg_cardsArrayList = new ArrayList<>();
        setUpRecyclerView();
       // setUpFirebase();
      //  uploadDataToFirebase();
     //   loadDataFromFirebase();
    }






    private void setUpRecyclerView() {
        mRecyclerView =findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
