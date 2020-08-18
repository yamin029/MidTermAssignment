package com.example.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class User_Info extends AppCompatActivity {

    ImageView uImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__info);
        uImage = findViewById(R.id.ivImage);

        DatabaseHandler objectDatabaseHandler = new DatabaseHandler(this);
        ArrayList<ModelClass> objectModelClassList = objectDatabaseHandler.getUserInfo();

        uImage.setImageBitmap(objectModelClassList.get(0).getmImage());
        //System.out.println(objectModelClassList.get(0).getmFirstName());
    }
}