package com.pranjal.myapplicatio;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.pranjal.myapplicatio.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
     ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        TextView textView = findViewById(R.id.textView);
        textView.setText(printString());
        ImageView imageShare = findViewById(R.id.imageView);
        ImageView imageDownload = findViewById(R.id.imageView2);
        ImageView imageCopy = findViewById(R.id.imageView3);

        imageCopy.setOnClickListener(v -> copyMethod());

        imageDownload.setOnClickListener(v -> captureScreen());

        imageShare.setOnClickListener(v -> shareIt());

//        reminderNotification();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_download) {
            captureScreen();
            return true;
        }
        if (id == R.id.action_rate) {
            Intent intentDev = new Intent(this,DeveloperContact.class);
            startActivity(intentDev);
            return true;
        }
        if (id == R.id.action_share_app) {

            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            String shareBody = "https://play.google.com/store/apps/details?id=com.pranjal.myapplicatio";
            intent.setType("link");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Share in text format");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent, "Share with"));


//            String url = "https://play.google.com/store/apps/details?id=com.pranjal.myapplicatio";
//            Uri webpage = Uri.parse(url);
//            Intent intent_web= new Intent(Intent.ACTION_VIEW , webpage);
//            if(intent_web.resolveActivity(getPackageManager()) != null) {
//                startActivity(intent_web);
//            }
//            return true;
        }
        if (id == R.id.action_about) {
            Intent intentAb = new Intent(this,AboutActivity.class);
            startActivity(intentAb);
            return true;
        }
        if (id == R.id.action_copy) {
            copyMethod();
            return true;
        }
        if (id == R.id.action_share) {
            shareIt();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void copyMethod(){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
    }


    private void captureScreen() {

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        View v = findViewById(R.id.textView);
        v.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);

        try {
            FileOutputStream fos = new FileOutputStream(new File(Environment
                    .getExternalStorageDirectory().toString(), "SCREEN"
                    + System.currentTimeMillis() + ".png"));
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Downloaded to phone storage", Toast.LENGTH_SHORT).show();
    }

    public void shareIt(){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        String shareBody = text;
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Share in text format");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(intent, "Share with"));
    }
    String text;
    private String printString(){
        String month = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        String dd = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        int monthInt = Integer.parseInt(month);

        if (monthInt == 1){
            monthInt =7;
        }else if(monthInt == 2){
            monthInt = 8;
        }else if(monthInt == 3){
            monthInt = 9;
        }else if(monthInt == 4){
            monthInt = 10;
        }else if(monthInt == 5){
            monthInt = 11;
        }

        switch (monthInt) {
            case 6 : {
                text = getResources().getStringArray(R.array.june)[(Integer.parseInt(dd)) - 1];
                return text;
            }
            case 7 : {
                text = getResources().getStringArray(R.array.july)[(Integer.parseInt(dd)) - 1];
                return text;
            }
            case 8 : {
                text = getResources().getStringArray(R.array.august)[(Integer.parseInt(dd)) - 1];
                return text;
            }
            case 9 : {
                text = getResources().getStringArray(R.array.september)[(Integer.parseInt(dd)) - 1];
                return text;
            }
            case 10 : {
                text = getResources().getStringArray(R.array.october)[(Integer.parseInt(dd)) - 1];
                return text;
            }
            case 11 : {
                text = getResources().getStringArray(R.array.november)[(Integer.parseInt(dd)) - 1];
                return text;
            }
            case 12: {
                text = getResources().getStringArray(R.array.december)[(Integer.parseInt(dd)) - 1];
                return text;
            }

            default:
                text = "error";
        }

        return text;
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit").setMessage("Are you sure?").setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }
        }).setNegativeButton("No",null).show();
    }
}