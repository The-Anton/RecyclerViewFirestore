package com.solvabit.recyclerviewwithfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.proto.ConfigPersistence;

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
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setFetchTimeoutInSeconds(3000)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);

        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Main2Activity.this, "Fetch and activate succeeded",
                                    Toast.LENGTH_SHORT).show();
                            mFirebaseRemoteConfig.activate();
                            setupFetch();

                        } else {
                            Toast.makeText(Main2Activity.this, "Fetch failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        event_reg_cardsArrayList = new ArrayList<>();
        setUpRecyclerView();
        setUpFirebase();
      //  uploadDataToFirebase();
        loadDataFromFirebase();
    }






    private void setupFetch(){
     String color = mFirebaseRemoteConfig.getString("colorPrimaryDark");
        LinearLayout layout = findViewById(R.id.layout);
        layout.setBackgroundColor(Color.parseColor(color));
     setTitleColor(Color.parseColor(color));

   }

    private void uploadDataToFirebase() {

        Map<String, Object> Names = new HashMap<>();
        Map<String, Object> Name = new HashMap<>();
        Map<String, Object> t = new HashMap<>();

        Names.put("Names",Name);
        Name.put("1",t);
        Name.put("0",1);
        t.put("Name","Basketball");
        t.put("Fees","100");


        db.document("/Army Institute Of Technology/Events/PACE/Event Details")
                    .set(Names)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Msg", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Msg", "Error writing document", e);
                    }
                });
    }

   public void loadDataFromFirebase() {
       if (event_reg_cardsArrayList.size() > 0) {
           event_reg_cardsArrayList.clear();
       }

       db.document("/Army Institute Of Technology/Events/PACE/Event Details")
               .get()
               .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                          @Override
                                          public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                              if (task.isSuccessful()) {
                                                  DocumentSnapshot document = task.getResult();
                                                  if (document.exists()) {
                                                      long size = document.getLong("Names.0");
                                                      int i = 1;
                                                      while (size > 0) {
                                                          String temp = "Names." + i + ".Name";
                                                          System.out.println(temp + "    ");
                                                          String temp2 = "Names." + i + ".Fees";
                                                          System.out.println(temp2 + "    ");


                                                          String Name = document.getString(temp);
                                                          String Fees = document.getString(temp2);
                                                          System.out.println("//" + Name + "\\" + "    ");
                                                          System.out.println("//" + Fees + "\\" + "    ");

                                                          Event_reg_card event_reg_card = new Event_reg_card(Name, Fees);
                                                          event_reg_cardsArrayList.add(event_reg_card);
                                                          i++;
                                                          size--;
                                                      }
                                                  } else {
                                                      Log.d("Error", "No such document");
                                                  }
                                              } else {
                                                  Log.d("Error", "get failed with ", task.getException());
                                              }
                                              adapter = new EventRegRecyclerViewAdapter(Main2Activity.this, event_reg_cardsArrayList);
                                              mRecyclerView.setAdapter(adapter);
                                          }

                                      }
               )
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();
                   }
               });

   }

        /*db.collection("/Army Institute Of Technology/Events/PACE/Event Scores/Event Names")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot: task.getResult()){
                            Event_reg_card event_reg_card = new Event_reg_card(querySnapshot.getString("Event Name"),
                                    querySnapshot.getString("Event Fees"));
                                    event_reg_cardsArrayList.add(event_reg_card);

                        }
                        adapter = new EventRegRecyclerViewAdapter(Main2Activity.this,event_reg_cardsArrayList);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Main2Activity.this, "Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }*/

    private void setUpFirebase() {
        db =FirebaseFirestore.getInstance();
    }


    private void setUpRecyclerView() {
        mRecyclerView =findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
