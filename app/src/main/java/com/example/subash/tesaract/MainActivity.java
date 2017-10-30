package com.example.subash.tesaract;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    Button button;
    TextView textView;
    private final int REQ_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenMic();

            }
        });
    }
    public void OpenMic()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak now...");

        try
        {
            startActivityForResult(intent,REQ_CODE);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"MIC NOT OPPENED",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQ_CODE:
                {
                    if (resultCode == RESULT_OK && data!=null)
                    {
                        ArrayList<String> TXT = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        System.out.println("ARRAY LIST "+ TXT);
                        textView.setText(TXT.get(0));
                    }
                    break;
                }
        }

    }
}
