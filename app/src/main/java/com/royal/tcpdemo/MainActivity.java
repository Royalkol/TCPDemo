package com.royal.tcpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnTcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnTcp=findViewById(R.id.btn_tcp);
        OnClick onClick =new OnClick();
        mBtnTcp.setOnClickListener(onClick);
    }


    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch (v.getId()){
                case R.id.btn_tcp:
                    //从主界面跳转到测试界面
                    intent=new Intent(MainActivity.this, TcpActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

}