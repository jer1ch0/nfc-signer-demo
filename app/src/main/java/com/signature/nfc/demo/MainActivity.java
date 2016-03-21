package com.signature.nfc.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.signature.nfc.demo.R;

import com.signature.nfc.lib.RsaApduActivity;


public class MainActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button bSign = (Button) findViewById(R.id.bSign);
        bSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult( initSigner(), READ_REQUEST_CODE);
            }
        });
    }

    public Intent initSigner(){
        EditText etMessage = (EditText) findViewById(R.id.etMessage);
        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        EditText etKeyId = (EditText) findViewById(R.id.etKeyId);
        Intent intent = new Intent(MainActivity.this, RsaApduActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("message", etMessage.getText().toString());
        bundle.putString("password", etPassword.getText().toString());
        bundle.putString("key", etKeyId.getText().toString());
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == READ_REQUEST_CODE) {
            if(resultCode == RESULT_OK){
                if (data!= null && data.hasExtra("result")) {
                    Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_LONG).show();
                }
            }
            if(resultCode == RESULT_CANCELED){
                if (data!= null && data.hasExtra("error")) {
                    Toast.makeText(this, data.getStringExtra("error"), Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}
