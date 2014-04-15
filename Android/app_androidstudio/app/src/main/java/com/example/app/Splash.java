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

public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(new MYGIFView(this));

    }

    private class MYGIFView extends View{

        Movie movie;//,movie1;
        InputStream is=null;//,is1=null;
        long moviestart;

        public MYGIFView(Context context) {
            super(context);

            //Provide your own gif animation file

            is=context.getResources().openRawResource(R.raw.spin);
            movie=Movie.decodeStream(is);

        }

        @Override
        protected void onDraw(Canvas canvas) {

            canvas.drawColor(Color.WHITE);
            super.onDraw(canvas);
            long now=android.os.SystemClock.uptimeMillis();
            System.out.println("now="+now);
            if (moviestart == 0) { // first time
                moviestart = now;

            }
            System.out.println("\tmoviestart="+moviestart);
            int relTime = (int)((now - moviestart) % movie.duration()) ;
            System.out.println("time="+relTime+"\treltime="+movie.duration());
            movie.setTime(0);
            movie.setTime(relTime);
            movie.draw(canvas,this.getWidth()/2-20,this.getHeight()/2-40);
            this.invalidate();
        }
    }

}


//Here's the class


