package com.example.listener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public TextView show_text;
    public static String mail_tag="mail_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[] {Manifest.permission.CALL_PHONE},1);
        show_text=findViewById(R.id.show_text);
        show_text.setText("To call say \" call (contact name)\" \n to open contact say \"contact\"" +
                "\n to open facebook or youtube say \" open facebook or youtube \" " +
                "\n to send mail say \" send mail \"");
    }
    public void decission(String rq){
        if(rq.contains("open")){
            String web_url="";
            if(rq.contains("facebook")){
                web_url="(url of your fb account)";
            }
            if(rq.contains("youtube")){
                web_url="https://www.youtube.com/";
            }
            Intent web_intent=new Intent(Intent.ACTION_VIEW);
            web_intent.setData(Uri.parse(web_url));
            startActivity(web_intent);
        }
        else if(rq.contains("contact")||rq.contains("contacts")){
            Intent contact_intent=new Intent(this,creat_contact.class);
            startActivity(contact_intent);

        }
        else if(rq.contains("video call")){
            String web_url=" (url of the persons massanger call) ";
            Intent video_intent=new Intent(Intent.ACTION_VIEW);
            video_intent.setData(Uri.parse(web_url));
            startActivity(video_intent);
        }

        else if(rq.contains("call")){
            data_handeller D_h=new data_handeller(this);

            int init=rq.indexOf("call")+5;
            String calling_person=rq.substring(init);
            contacts person=D_h.find(calling_person);
            if(person==null){
                Toast.makeText(this,"it's null",Toast.LENGTH_SHORT).show();
                return;
            }

            if(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                Intent call=new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:"+person.getPhn()));
                startActivity(call);
            }
            else{
                Toast.makeText(MainActivity.this,"problem",Toast.LENGTH_SHORT).show();
            }

        }
        else if(rq.contains("send")){
            if(rq.contains("mail")){
                int init=rq.indexOf("to");
                String address=rq.substring(init+3);
                Intent mail_intent=new Intent(this,Email_activity.class);
                //mail_intent.putExtra(mail_tag,address);
                startActivity(mail_intent);


            }
        }

    }
    public void listener(View view){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:{
                if(resultCode==RESULT_OK&& data!=null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String req=result.get(0);
                    req=req.toLowerCase();
                    show_text.setText(req);
                    decission(req);

                }
            }

                break;
        }

    }
}
