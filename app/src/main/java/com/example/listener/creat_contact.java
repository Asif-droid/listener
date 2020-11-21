package com.example.listener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

public class creat_contact extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText mail;
    String C_C_name;
    String C_C_phn;
    String C_C_mail;
    static  int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_contact);
        name=findViewById(R.id.contact_name);
        phone=findViewById(R.id.contact_phn);
        mail=findViewById(R.id.contact_mail);


    }
    public void save_contact(View view){
        C_C_name=name.getText().toString();
        C_C_phn=phone.getText().toString();
        C_C_mail=mail.getText().toString();
        i+=1;
        contacts contact=new contacts(i,C_C_name,C_C_phn,C_C_mail);
        data_handeller D_h=new data_handeller(this);
        D_h.addcontact(contact);
        name.setText("");
        phone.setText("");
        mail.setText("");


    }
    public void search(View view){
        C_C_name=name.getText().toString();
        data_handeller D_h=new data_handeller(this);
        contacts rsl= D_h.find(C_C_name);
        name.setText(rsl.getName());
        phone.setText(rsl.getPhn());
        mail.setText(rsl.getMail_add());

    }

}