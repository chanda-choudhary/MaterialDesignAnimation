package com.example.user.materialdesignanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SharedElementActivity extends AppCompatActivity {

    RelativeLayout revealDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Inside your Activity (if you did not enable transitions in your theme)
        //For AppCompat getWindow must be called before calling super.onCreate()
        //must be called before setContentView().
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);

        initPage();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean addSupportNavigateUp(){
        finishAfterTransition();
        return true;
    }

    private void initPage()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shared Element Transition");

        revealDemo=findViewById(R.id.click_reveal);
        revealDemo.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                makeCirculerRevealAnimation(revealDemo);
            }
        });

        Button btnExit=findViewById(R.id.exit_button);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void makeCirculerRevealAnimation(View view) {
        final TextView textDesc=findViewById(R.id.textDesc);

        int centerX=(view.getLeft() + view.getRight())/2;
        int centerY=(view.getTop() + view.getBottom())/2;
        float radius=Math.max(textDesc.getWidth(),textDesc.getHeight())*2.0f;

        if(textDesc.getVisibility()==View.INVISIBLE)
        {
            textDesc.setVisibility(View.VISIBLE);
            textDesc.setText(R.string.description);
            ViewAnimationUtils.createCircularReveal(textDesc,centerX,centerY,0,radius).start();
        }
        else{
            Animator reveal=ViewAnimationUtils.createCircularReveal(textDesc,centerX,centerY,radius,0);
            reveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    textDesc.setVisibility(View.INVISIBLE);
                }
            });
            reveal.start();
        }
    }
}
