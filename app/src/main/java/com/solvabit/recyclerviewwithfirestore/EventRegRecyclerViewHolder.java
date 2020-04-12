package com.solvabit.recyclerviewwithfirestore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class EventRegRecyclerViewHolder extends RecyclerView.ViewHolder {


    public   TextView sub_event_name_text_view, prize_text_view;
    public   CheckBox checkBox_bt;


    public EventRegRecyclerViewHolder(View itemView) {
        super(itemView);



        /*checkBox_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                try
                {
                    // the String to int conversion happens here
                    int prize =Integer.parseInt(prize_text_view.getText().toString().trim());
                    // print out the value after the conversion
                }
                catch (NumberFormatException nfe)
                {
                    System.out.println("NumberFormatException: " + nfe.getMessage());
                }


            }
        });*/
    }


}
