package com.example.passkeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

public class MainActivity extends AppCompatActivity {

    boolean isAddActionItems = false;
    SpeedDialView speedDialView;
    Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speedDialView = findViewById(R.id.speedDial);
        speedDialFloatingButton(savedInstanceState == null);

    }

    protected void speedDialFloatingButton(boolean isAddActionItems){
        if (isAddActionItems){
            speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_password, R.drawable.ic_password)
                    .setLabel("Add Password")
                    .setLabelBackgroundColor(Color.WHITE)
                    .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.blue3, getTheme()))
                    .create());
            speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_card, R.drawable.ic_card)
                    .setLabel("Add Card")
                    .setLabelBackgroundColor(Color.WHITE)
                    .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.blue3, getTheme()))
                    .create());
            speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_secure_note, R.drawable.ic_note)
                    .setLabel("Add Secure Note")
                    .setLabelBackgroundColor(Color.WHITE)
                    .setLabelColor(ResourcesCompat.getColor(getResources(), R.color.blue3, getTheme()))
                    .create());
        }
        //Set main action cliclistener
        speedDialView.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                showToast("Add Action CLicked");
                return false; // True to keep the Speed Dial open
            }

            @Override
            public void onToggleChanged(boolean isOpen) {
                Log.d("MainActivity", "Speed dial toggle state changed.");
            }
        });

        //Set option fabs clicklisteners
        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()){
                    case R.id.fab_add_password:
                        showToast(actionItem.getLabel(MainActivity.this)+"clicked");
                    case R.id.fab_add_card:
                        showToast(actionItem.getLabel(MainActivity.this)+"clicked");
                    case R.id.fab_add_secure_note:
                        showToast(actionItem.getLabel(MainActivity.this)+"clicked");
                }
                return true;
            }
        });

    }

    protected void showToast(String text) {
        //toast.cancel();
        toast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG);
        if (toast != null)
            toast.show();
    }
}