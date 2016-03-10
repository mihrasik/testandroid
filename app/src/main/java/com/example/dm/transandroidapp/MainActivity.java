package com.example.dm.transandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etOriginText;
    private EditText etTranslatedText;
    private String originText;
    private String translated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bTranslate = (Button)findViewById(R.id.bTranslate);
        etOriginText = (EditText)findViewById(R.id.etOriginText);
        etTranslatedText = (EditText)findViewById(R.id.etTranslatedText);

        bTranslate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.bTranslate){
            Toast.makeText(this, "Text Translated", Toast.LENGTH_SHORT).show();
            originText = etOriginText.getText().toString();
            originText.replace("\r\n", "%0D%0A").replace("\n", "%0D%0A").replace("\r", "%0D%0A");

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    translated = TranslateUtil.translate(originText);
                        Runnable process = new Runnable() {
                            @Override
                            public void run() {

                                etTranslatedText.setText(translated);
                            }
                        };
                        runOnUiThread(process);

                }
            };

            Thread t = new Thread(r);
            t.start();
        }
    }
}
