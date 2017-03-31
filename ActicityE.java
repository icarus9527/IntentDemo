package com.example.icarus.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by icarus9527 on 2017/3/27.
 */
public class ActicityE extends AppCompatActivity{

    private Button btnCallBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e);

        btnCallBack = (Button) findViewById(R.id.activitye_btn_callback);
        btnCallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result","这是返回结果");
                setResult(2,intent);
                finish();
            }
        });
    }
}
