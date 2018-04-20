package com.example.pankajchaudhary.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button scan_btn;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView)findViewById(R.id.txtb);
        scan_btn = (Button)findViewById(R.id.scan_btn);




        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator= new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode , resultCode, data);
        if (result!=null) {
            if (result.getContents() == null) {
                Toast.makeText(this,"YOU CANCELLED THE SCANNING " , Toast.LENGTH_LONG).show();
            }
            else {
                String info = result.getContents();
                int p= info.length();
              //  JSONParser parser = new JSONParser();
               // JSONObject json = (JSONObject) parser.parse(stringToParse);
                // Log.d("info",info);


                Toast.makeText(this,result.getContents() , Toast.LENGTH_LONG).show();
                txt.setText(result.getContents());
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
