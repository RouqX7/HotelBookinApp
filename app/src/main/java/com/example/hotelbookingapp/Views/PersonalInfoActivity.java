package com.example.hotelbookingapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hotelbookingapp.R;


public class PersonalInfoActivity extends AppCompatActivity {
    Button btnSave;
    EditText ed_Address,ed_Name,ed_Phone,ed_Email,ed_Num;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ed_Num = findViewById(R.id.edNum);
        ed_Phone = findViewById(R.id.edPhone);
        ed_Email = findViewById(R.id.edEmailAddress);
        ed_Address = findViewById(R.id.edAddress);
        ed_Name = findViewById(R.id.editTextPersonName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_Name.getText().toString().equals("")){
                    Toast.makeText(PersonalInfoActivity.this, "Please Enter Your Full Name", Toast.LENGTH_SHORT).show();
                } else if (ed_Email.getText().toString().equals("")) {
                    Toast.makeText(PersonalInfoActivity.this, "Please Enter your Email Address", Toast.LENGTH_SHORT).show();
                } else if (ed_Phone.getText().toString().equals("")){
                    Toast.makeText(PersonalInfoActivity.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                } else if (ed_Address.getText().toString().equals("")){
                    Toast.makeText(PersonalInfoActivity.this, "Please Enter Yur Address", Toast.LENGTH_SHORT).show();
                } else if (ed_Num.getText().toString().equals("")){
                    Toast.makeText(PersonalInfoActivity.this, "Please Enter The Number Of People For This Booking ", Toast.LENGTH_SHORT).show();
                } else {

                    Intent i = new Intent(PersonalInfoActivity.this, RoomInfoActivity.class);
                    i.putExtra("name", ed_Name.getText().toString());
                    i.putExtra("address", ed_Address.getText().toString());
                    i.putExtra("email", ed_Email.getText().toString());
                    i.putExtra("Person's number", ed_Num.getText().toString());
                    i.putExtra("phone", ed_Phone.getText().toString());
                    startActivity(i);
                }
            }
        });
    }

}