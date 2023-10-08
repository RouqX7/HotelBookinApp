package com.example.hotelbookingapp.Views;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelbookingapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RoomInfoActivity extends AppCompatActivity {
    EditText check_In_Text, checkOutText,edNum;
    Spinner select_Room_Spinner;
    Button btn_Preview;
    final Calendar myCalender = Calendar.getInstance();
    String name,email,phone,address,numberOfPerson,roomType;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);

        btn_Preview = findViewById(R.id.btnPreview);
        checkOutText = findViewById(R.id.checkoutDateText);
        check_In_Text = findViewById(R.id.checkInDateText);
        edNum = findViewById(R.id.edNum);
        select_Room_Spinner = findViewById(R.id.selectRoomSpinner);


        Intent i =getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        phone = i.getStringExtra("phone");
        address = i.getStringExtra("address");


        btn_Preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_In_Text.getText().toString().equals("")){
                    Toast.makeText(RoomInfoActivity.this, "Please Enter Your Check In Date", Toast.LENGTH_SHORT).show();
                } else if (checkOutText.getText().toString().equals("")) {
                    Toast.makeText(RoomInfoActivity.this, "Please Enter Your Check In Date", Toast.LENGTH_SHORT).show();
                } else if (edNum.getText().toString().equals("")) {
                    Toast.makeText(RoomInfoActivity.this, "Please Enter the Number Of Rooms Needed ", Toast.LENGTH_SHORT).show();
                } else {

                    Intent i = new Intent(RoomInfoActivity.this, FinalActivity.class);
                    i.putExtra("name", name);
                    i.putExtra("address", address);
                    i.putExtra("email", email);
                    i.putExtra("Person's number", numberOfPerson);
                    i.putExtra("phone", phone);
                    i.putExtra("roomType", roomType);
                    i.putExtra("checkIn", check_In_Text.getText().toString());
                    i.putExtra("checkOut", checkOutText.getText().toString());
                    i.putExtra("ed", edNum.getText().toString());
                    startActivity(i);


                }

            }
        });
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR,year);
                myCalender.set(Calendar.MONTH,month);
                myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel(myCalender,checkOutText);
            }
        };
        check_In_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RoomInfoActivity .this,date,myCalender
                        .get(Calendar.YEAR),myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR,year);
                myCalender.set(Calendar.MONTH,month);
                myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel(myCalender,check_In_Text);
            }
        };

        check_In_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RoomInfoActivity .this,date2,myCalender
                        .get(Calendar.YEAR),myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel(Calendar  myCalender, EditText check_In_Text) {
        String myFormat = "MM//dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        check_In_Text.setText(sdf.format(myCalender));

    }

}