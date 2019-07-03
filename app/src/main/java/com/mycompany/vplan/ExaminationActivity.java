package com.mycompany.vplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExaminationActivity extends AppCompatActivity {
    public static final String EXAMINATION_NAME = "examination_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
    }
}
