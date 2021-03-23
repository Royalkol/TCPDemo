package com.royal.tcpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpActivity extends AppCompatActivity {

    private Button mBtnrelayOpen1, mBtnrelayOpen2, mBtnrelayOpen3;
    private Button mBtnrelayClose1,mBtnrelayClose2,mBtnrelayClose3;
    private Button mbtnConnection;
    private EditText mEdIp, mSocket;
    ExecutorService exec = Executors.newCachedThreadPool();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);

        mBtnrelayOpen1 = findViewById(R.id.btn_relayopen1);
        mBtnrelayOpen2 = findViewById(R.id.btn_relayopen2);
        mBtnrelayOpen3 = findViewById(R.id.btn_relayopen3);
        mBtnrelayClose1 = findViewById(R.id.btn_relayclose1);
        mBtnrelayClose2 = findViewById(R.id.btn_relayclose2);
        mBtnrelayClose3 = findViewById(R.id.btn_relayclose3);
        mEdIp = findViewById(R.id.et_ip);
        mSocket = findViewById(R.id.et_socket);
        mbtnConnection = findViewById(R.id.btn_connection);
        OnClick onClick = new OnClick();
        mBtnrelayOpen1.setOnClickListener(onClick);
        mBtnrelayOpen2.setOnClickListener(onClick);
        mBtnrelayOpen3.setOnClickListener(onClick);
        mBtnrelayClose1.setOnClickListener(onClick);
        mBtnrelayClose2.setOnClickListener(onClick);
        mBtnrelayClose3.setOnClickListener(onClick);
        mbtnConnection.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener {
        TcpClient tcpClient = new TcpClient("192.168.1.199", 12345);
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_relayopen1:
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("TcpActivity", "---打开开关1---");
                            tcpClient.send("AT+STACH1=1" + "\r");
                            Log.d("TcpActivity", "---打开开关1完毕---");
                        }
                    });
                    break;
                case R.id.btn_relayopen2:
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("TcpActivity", "---打开开关2---");
                            tcpClient.send("AT+STACH2=1" + "\r");
                            Log.d("TcpActivity", "---打开开关2完毕---");
                        }
                    });
                    break;
                case R.id.btn_relayopen3:
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("TcpActivity", "---打开开关3---");
                            tcpClient.send("AT+STACH3=1" + "\r");
                            Log.d("TcpActivity", "---打开开关3完毕---");
                        }
                    });
                    break;
                case R.id.btn_relayclose1:
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("TcpActivity", "---关闭开关1---");
                            tcpClient.send("AT+STACH1=0" + "\r");
                            Log.d("TcpActivity", "---关闭开关1完毕---");

                        }
                    });
                    break;
                case R.id.btn_relayclose2:
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("TcpActivity", "---关闭开关2---");
                            tcpClient.send("AT+STACH2=0" + "\r");
                            Log.d("TcpActivity", "---关闭开关2完毕---");
                        }
                    });
                    break;
                case R.id.btn_relayclose3:
                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("TcpActivity", "---关闭开关3---");
                            tcpClient.send("AT+STACH3=0" + "\r");
                            Log.d("TcpActivity", "---关闭开关3完毕---");
                        }
                    });
                    break;
                case R.id.btn_connection:
                    try {
                        Log.d("TcpActivity", "---链接开始---");
                        exec.execute(tcpClient);
                        Log.d("TcpActivity", "---链接成功---");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}