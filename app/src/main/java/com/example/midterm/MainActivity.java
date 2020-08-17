package com.example.midterm;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView cImage;
    private EditText cEtFirstName;
    private EditText cEtLastName;
    private EditText cEtDob;
    private EditText cEtAddress;
    private EditText cEtEducation;
    private EditText cEtSkills;
    private EditText cEtExperiences;
    private Button btnCreateCV;

    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageTOStore;

    DatabaseHandler objectDatabaseHandler;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            cImage = findViewById(R.id.ivImage);
            cEtFirstName = findViewById(R.id.etFirstName);
            cEtLastName = findViewById(R.id.etLastName);
            cEtDob = findViewById(R.id.etDOB);
            cEtAddress = findViewById(R.id.etAddress);
            cEtEducation = findViewById(R.id.etEducation);
            cEtSkills = findViewById(R.id.etSkills);
            cEtExperiences = findViewById(R.id.etExperiences);

            objectDatabaseHandler = new DatabaseHandler(this);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public void chooseImage(View objectView){
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(objectIntent, "Select Image"),PICK_IMAGE_REQUEST);

        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {

            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){

                imageFilePath = data.getData();
                imageTOStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                cImage.setImageBitmap(imageTOStore);

            }
            else {
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public void storeData(View view){
        try {
            if(cImage.getDrawable() != null && !cEtFirstName.getText().toString().isEmpty() && imageTOStore!=null){
                objectDatabaseHandler.storeUserInformation(
                        new ModelClass(imageTOStore,cEtFirstName.getText().toString(),cEtLastName.getText().toString(),cEtDob.getText().toString(),cEtAddress.getText().toString(),cEtEducation.getText().toString(),cEtSkills.getText().toString(),cEtExperiences.getText().toString())
                );
            }
            else {
                Toast.makeText(this, "please provide all the information", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        objectDatabaseHandler.deleteAllRecord();


    }
}