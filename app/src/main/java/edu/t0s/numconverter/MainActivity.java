package edu.t0s.numconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editSetFirstName, editSetSecondName, editSetClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editSetFirstName = findViewById(R.id.editSetFirstName);
        editSetSecondName = findViewById(R.id.editSetSecondName);
        editSetClass = findViewById(R.id.editSetClass);
    }

    public void start(View w){
        String firstName = editSetFirstName.getText().toString();
        String secondName = editSetSecondName.getText().toString();
        String trida = editSetClass.getText().toString();
        if(firstName.length() < 3 || secondName.length() < 3 || trida.length() < 2){
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show();
        } else{
            String name = firstName + " " + secondName + ", " + trida;
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }
}