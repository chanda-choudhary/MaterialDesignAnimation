package com.example.user.materialdesignanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RippleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        getSupportActionBar().setTitle("Ripple Animation");
    }
    public void dummyClick(View view)
    {
        
    }
}
