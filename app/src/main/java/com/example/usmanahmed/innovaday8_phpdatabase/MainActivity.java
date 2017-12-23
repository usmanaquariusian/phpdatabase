package com.example.usmanahmed.innovaday8_phpdatabase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText name,username,password;
    Button signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText) findViewById(R.id.editText);
        username=(EditText) findViewById(R.id.editText2);
        password=(EditText) findViewById(R.id.editText3);
        signup=(Button) findViewById(R.id.button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String strname=name.getText().toString();
                String strusername=username.getText().toString();
                String strpassword=password.getText().toString();

                DataSendingClass dataSendingClass;
                dataSendingClass=new DataSendingClass(MainActivity.this);
                dataSendingClass.execute(strname,strusername,strpassword);
            }
        });



    }
}
