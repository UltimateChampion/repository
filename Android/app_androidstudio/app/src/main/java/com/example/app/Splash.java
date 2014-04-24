package com.example.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer t = new Timer();
        t.schedule(new toNext(), 2000);


    }

    private class toNext extends TimerTask {

        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }





}


//Here's the class


