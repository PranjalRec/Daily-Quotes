package com.pranjal.myapplicatio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DeveloperContact extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_contact);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageView hackerrank = findViewById(R.id.hackerrank);
        ImageView whatsapp = findViewById(R.id.whatsapp);
        ImageView github = findViewById(R.id.github);
        ImageView email =  findViewById(R.id.email);

        TextView text_hackerrank = findViewById(R.id.Thackerrank);
        TextView text_whatsapp = findViewById(R.id.Twhatsapp);
        TextView text_github = findViewById(R.id.Tgithub);
        TextView text_email=  findViewById(R.id.Temail);

        hackerrank.setOnClickListener(v -> openHackerrank());
        whatsapp.setOnClickListener(v -> openWhatsapp());
        email.setOnClickListener(v -> openEmail());
        github.setOnClickListener(v -> openGithub());


        text_hackerrank.setOnClickListener(v -> openHackerrank());
        text_whatsapp.setOnClickListener(v -> openWhatsapp());
        text_email.setOnClickListener(v -> openEmail());
        text_github.setOnClickListener(v -> openGithub());

    }

    void openHackerrank(){
        String url = "https://www.hackerrank.com/pranjalshivamsi1";
        Uri webpage = Uri.parse(url);
        Intent intent_hack= new Intent(Intent.ACTION_VIEW , webpage);
            startActivity(intent_hack);
    }

    void openGithub(){
        String url = "https://github.com/PranjalRec";
        Uri webpage = Uri.parse(url);
        Intent intent_git= new Intent(Intent.ACTION_VIEW , webpage);
            startActivity(intent_git);
    }


    void openWhatsapp(){
//        String url = "https://api.whatsapp.com/send?phone="+"+917307391054";
        String url = "https://www.linkedin.com/in/pranjal-singh-2aa880201/";
        Intent intent_whatsapp = new Intent(Intent.ACTION_VIEW);
        intent_whatsapp.setData(Uri.parse(url));
        startActivity(intent_whatsapp);
    }

    void openEmail(){
        Intent intent_email = new Intent(Intent.ACTION_SEND);
        intent_email.putExtra(Intent.EXTRA_EMAIL, new String[]{"pranjalshivamsingh4@gmail.com"});

        intent_email.setType("message/rfc822");

        startActivity(Intent.createChooser(intent_email, "Choose an Email client :"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}