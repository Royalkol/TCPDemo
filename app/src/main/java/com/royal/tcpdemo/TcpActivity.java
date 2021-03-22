package com.royal.tcpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpActivity extends AppCompatActivity {

    ExecutorService exec = Executors.newCachedThreadPool();
    private Button mBtnrelay1, mBtnrelay2, mBtnrelay3, mbtnConnection;
    private EditText mEdIp, mSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);

        mBtnrelay1 = findViewById(R.id.btn_relay1);
        mBtnrelay2 = findViewById(R.id.btn_relay2);
        mBtnrelay3 = findViewById(R.id.btn_relay3);
        mEdIp = findViewById(R.id.et_ip);
        mSocket = findViewById(R.id.et_socket);
        mbtnConnection = findViewById(R.id.btn_connection);
        OnClick onClick = new OnClick();
//        mBtnrelay1.setOnClickListener(onClick);
        mBtnrelay2.setOnClickListener(onClick);
        mBtnrelay3.setOnClickListener(onClick);
        mbtnConnection.setOnClickListener(onClick);


        //创建一个指定客户端
//        TcpClient tcpClient = new TcpClient(mSocket.getText().toString(), Integer.parseInt(mSocket.getText().toString()));
        TcpClient tcpClient = new TcpClient("192.168.1.199", 12345);


//        mbtnConnection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //连接到服务器
////                tcpClient.run();
//                try {
//                    Log.d("TcpActivity", "---链接开始---");
//                    exec.execute(tcpClient);
//                    Log.d("TcpActivity", "---链接成功---");
//                } catch (Exception e) {
//
//                }
//            }
//        });

        mBtnrelay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myHandler.sendMessage(message);
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        tcpClient.send("AT+STACH1=1" + "\r");
                    }
                });
            }
        });


    }

    class OnClick implements View.OnClickListener {
        TcpClient tcpClient = new TcpClient("192.168.1.199", 12345);

        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
//                case R.id.btn_relay1:
//                    exec.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.d("TcpActivity", "---打开开关1---");
//                            tcpClient.send("AT+STACH1=1" + "\r");
//                            Log.d("TcpActivity", "---打开开关1完毕---");
//
//                        }
//                    });
//                    break;
                case R.id.btn_relay2:
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("TcpActivity", "---打开开关2---");
                            tcpClient.send("AT+STACH2=1" + "\r");
                            Log.d("TcpActivity", "---打开开关2完毕---");
                        }
                    });
                    break;
                case R.id.btn_relay3:
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("TcpActivity", "---打开开关3---");
                            tcpClient.send("AT+STACH3=1" + "\r");
                            Log.d("TcpActivity", "---打开开关3完毕---");
                        }
                    });
                    break;
                case R.id.btn_connection:
                    try {
                        Log.d("TcpActivity", "---链接开始---");
                        exec.execute(tcpClient);
                        Log.d("TcpActivity", "---链接成功---");
                    } catch (Exception e) {

                    }
                    break;
            }
        }
    }
}