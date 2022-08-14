package com.creativeshakeeb.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, contact,dob;
    Button Insert, Update, Delete, View ;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        Insert = findViewById(R.id.btnInsert);
        Update = findViewById(R.id.btnUpdate);
        Delete = findViewById(R.id.btnDelete);
        View = findViewById(R.id.btnView);

        DB = new DBHelper(this);
    }

    public void insert_button(android.view.View view) {
        String nameTXT = name.getText().toString();
        String contactTXT = contact.getText().toString();
        String dobTXT = dob.getText().toString();

        Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT);
        if (checkinsertdata == true) {
            Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    public void update_data(android.view.View view) {
        String nameTXT = name.getText().toString();
        String contactTXT = contact.getText().toString();
        String dobTXT = dob.getText().toString();

        Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);
        if (checkupdatedata == true) {
            Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete_data(android.view.View view) {
        String nameTXT = name.getText().toString();

        Boolean checkdeletedata = DB.deletedata(nameTXT);
        if (checkdeletedata == true) {
            Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Not deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void view_data(android.view.View view) {
        Cursor res = DB.gedtata();
        if (res.getCount()==0){
            Toast.makeText(MainActivity.this, "Please Enter Data", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (res.moveToNext()){
            stringBuffer.append("Name: "+res.getString(0) +"\n" );
            stringBuffer.append("Contact: "+res.getString(1) +"\n" );
            stringBuffer.append("DOB: " +res.getString(2) +"\n\n" );
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Entries");
        builder.setMessage(stringBuffer.toString());
        builder.show();
    }
}