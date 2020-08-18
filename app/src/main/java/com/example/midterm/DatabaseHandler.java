package com.example.midterm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    Context context;
    private static String DATABASE_NAME= "USER_DATABASE.DB";
    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "create table userInfo (image BLOB" + ",firstName TEXT" + ",lastName TEXT" + ",DOB TEXT" + ",address TEXT" + ",education TEXT" + ",skills TEXT" + ",experiences TEXT)";
    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(createTableQuery);
            Toast.makeText(context, "Table created successfully!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void storeUserInformation(ModelClass objectModelClass){
        try{
            SQLiteDatabase objectSQLiteDatabase = this.getWritableDatabase();
            objectSQLiteDatabase.execSQL("DELETE FROM userInfo");
            Bitmap imageToStoreBitmap=objectModelClass.getmImage();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);
            imageInBytes = objectByteArrayOutputStream.toByteArray();

            ContentValues objectContentValues = new ContentValues();
            objectContentValues.put("image",imageInBytes);
            objectContentValues.put("firstName",objectModelClass.getmFirstName());
            objectContentValues.put("lastName",objectModelClass.getmLastName());
            objectContentValues.put("DOB",objectModelClass.getmDOB());
            objectContentValues.put("address",objectModelClass.getmAddress());
            objectContentValues.put("education",objectModelClass.getmEducation());
            objectContentValues.put("skills",objectModelClass.getmSkills());
            objectContentValues.put("experiences",objectModelClass.getmExperience());

            long checkIfQueryRuns = objectSQLiteDatabase.insert("userInfo",null,objectContentValues);
            if(checkIfQueryRuns != -1){
                Toast.makeText(context, "Data added Successfully", Toast.LENGTH_SHORT).show();
                objectSQLiteDatabase.close();
            }
            else {
                Toast.makeText(context, "Fails to add data", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteAllRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM userInfo"); //delete all rows in a table
        db.close();
    }

    public ArrayList<ModelClass> getUserInfo(){
        try {
            SQLiteDatabase objectSQLiteDatabase = this.getReadableDatabase();

            ArrayList<ModelClass> objectModelClassList = new ArrayList<>();

            Cursor objectCursor = objectSQLiteDatabase.rawQuery("select * from userInfo",null);
            if(objectCursor.getCount() != 0){
                while (objectCursor.moveToNext()){
                    byte [] imageBytes = objectCursor.getBlob(0);
                    Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    String firstName = objectCursor.getString(1);
                    String lastName = objectCursor.getString(2);
                    String DOB = objectCursor.getString(3);
                    String address = objectCursor.getString(4);
                    String education = objectCursor.getString(5);
                    String skills = objectCursor.getString(6);
                    String experiences = objectCursor.getString(7);
                    objectModelClassList.add(new ModelClass(objectBitmap,firstName,lastName,DOB,address,education,skills,experiences));
                }
                return objectModelClassList;
            }
            else {
                Toast.makeText(context, "No values exit in the database", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;

        }
    }
}
