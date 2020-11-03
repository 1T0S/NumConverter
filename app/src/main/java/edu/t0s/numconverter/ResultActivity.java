package edu.t0s.numconverter;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    TextView tvMark, tvPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvMark = findViewById(R.id.tvMark);
        tvPoints = findViewById(R.id.tvPoints);
        Intent in = getIntent();
        String mark = "Mark: " + in.getIntExtra("mark", 0);
        String points = "Points: " + in.getIntExtra("points", 0);
        tvMark.setText(mark);
        tvPoints.setText(points);
    }
}
