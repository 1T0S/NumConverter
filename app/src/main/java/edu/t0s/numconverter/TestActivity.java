package edu.t0s.numconverter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {
    TextView textViewName;
    Chronometer chronometer;
    private static final int questions = 12;
    private EditText[] dec = new EditText[questions];
    private EditText[] bin = new EditText[questions];
    private EditText[] hex = new EditText[questions];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Bundle bundle = getIntent().getExtras();
        System.out.println("Hello there, " + bundle.getString("name"));

        // Triggers automatically after button from previous intent is clicked
        textViewName = findViewById(R.id.textViewName);
        textViewName.setText(bundle.getString("name"));
        chronometer = findViewById(R.id.chronometer);
        long endTime = SystemClock.elapsedRealtime();
        chronometer.setBase(endTime);
        chronometer.start();
        load();
        fill();
    }

    protected void load(){
        for(int i = 0; i < questions; i++){
            int idDec = getResources().getIdentifier("inputDec" + i, "id", getPackageName());
            int idHex = getResources().getIdentifier("inputHex" + i, "id", getPackageName());
            int idBin = getResources().getIdentifier("inputBin" + i, "id", getPackageName());
            dec[i] = findViewById(idDec);
            hex[i] = findViewById(idHex);
            bin[i] = findViewById(idBin);
        }
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

    protected void fill(){
        for(int i = 0; i < questions; i++){
            // Dec
            if(i < questions  / 3){
                disableEditText(dec[i]);
                dec[i].setTextColor(Color.parseColor("#ffc500"));
                dec[i].setText(Converter.getDec());
            }
            // Bin
            else if(i < questions / 3 * 2){
                disableEditText(bin[i]);
                bin[i].setTextColor(Color.parseColor("#00cba9"));
                bin[i].setText(Converter.getBin());
            }
            // Hex
            else{
                disableEditText(hex[i]);
                hex[i].setTextColor(Color.parseColor("#0099ff"));
                hex[i].setText(Converter.getHex());
            }
        }
    }

    public void finish(View v){
        int points = 0;
        for(int i = 0; i < questions; i++){
            if(i < questions / 3){
                int correct = Integer.parseInt(dec[i].getText().toString());
                points += (Converter.binToDec(bin[i].getText().toString()) == correct) ? 1 : 0;
                points += (Converter.hexToDec(hex[i].getText().toString()) == correct) ? 1 : 0;
            }
            // Bin
            else if(i < questions / 3 * 2){
                int correct = Converter.binToDec(bin[i].getText().toString());
                points += (Converter.getInt(dec[i].getText().toString()) == correct) ? 1 : 0;
                points += (Converter.hexToDec(hex[i].getText().toString()) == correct) ? 1 : 0;
            }
            // Hex
            else{
                int correct = Converter.hexToDec(hex[i].getText().toString());
                points += (Converter.getInt(dec[i].getText().toString()) == correct) ? 1 : 0;
                points += (Converter.binToDec(bin[i].getText().toString()) == correct) ? 1 : 0;
            }
        }
        int mark = getMark(points);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("points", points);
        intent.putExtra("mark", mark);
        startActivity(intent);
    }

    public int getMark(int points){
        int mark = 0;
        int penalty = 0;
        int elapsedTime =  (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000 / 3600);
        if(elapsedTime > 15){
            penalty += elapsedTime - 15;
        }
        if(points > 20) mark = 1;
        else if(points > 16) mark = 2;
        else if(points > 12) mark = 3;
        else if(points > 8) mark = 4;
        else mark = 5;
        mark += penalty;
        if(mark > 5){
            mark = 5;
        }
        return mark;
    }
}
