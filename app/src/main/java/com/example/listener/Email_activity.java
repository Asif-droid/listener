package com.example.listener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class Email_activity extends AppCompatActivity {
    public TextView view_add;
    public TextView view_sub;
    public TextView view_text;
    public String mail_add;
    public String mail_name;
    public String mail_sub;
    public String mail_main;
    public String msg="x";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_activity);
        view_add=findViewById(R.id.add);
        view_sub=findViewById(R.id.sub);
        view_text=findViewById(R.id.main);

        //Intent scnd_int=getIntent();
        //mail_name=(String)scnd_int.getSerializableExtra(MainActivity.mail_tag);
        //setMail_add(mail_name);
    }

    public void voice_writing(int rq_code){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        startActivityForResult(intent,rq_code);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:{
                if(resultCode==RESULT_OK&& data!=null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mail_name=result.get(0);
                    mail_name=mail_name.toLowerCase();
                    view_add.setText(mail_name);
                    setMail_add(mail_name);


                }
            }

            break;
            case 20:{
                if(resultCode==RESULT_OK&& data!=null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mail_sub=result.get(0);
                    mail_sub=mail_sub.toLowerCase();
                    view_sub.setText(mail_sub);


                }
            }

            break;
            case 30:{
                if(resultCode==RESULT_OK&& data!=null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mail_main=result.get(0);
                    mail_main=mail_main.toLowerCase();
                    view_text.setText(mail_main);


                }
            }

            break;

        }

    }
    public void add_string(View view){
        voice_writing(10);
    }


    public  void sub_string(View view){
        voice_writing(20);
        mail_sub=msg;
        view_sub.setText(mail_sub);
    }
    public void text_writing(View view){
        voice_writing(30);
        mail_main=msg;
        view_text.setText(mail_main);
    }
    public void setMail_add(String name){
        data_handeller D_h=new data_handeller(this);
        contacts person=D_h.find(name);

        mail_add=person.getMail_add();
        view_add.setText(mail_add);

    }
    public void mailsend(View view){
        Intent mail_int=new Intent(Intent.ACTION_SEND);
        mail_int.setType("text/plain");
        mail_int.putExtra(Intent.EXTRA_EMAIL,new String[]{mail_add});
        mail_int.putExtra(Intent.EXTRA_SUBJECT,mail_sub);
        mail_int.putExtra(Intent.EXTRA_TEXT,mail_main);
        startActivity(mail_int);
    }
}