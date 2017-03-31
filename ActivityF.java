package com.example.icarus.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by icarus9527 on 2017/3/31.
 */

public class ActivityF extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f);

        textView = (TextView) findViewById(R.id.activityf_tv);
        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person_data");
        Student student = intent.getParcelableExtra("student_data");
        if(person!=null){
            textView.setText("name:"+person.getName()+"    age:"+person.getAge());
        }else if(student!=null){
            textView.setText("name:"+student.getName()+"    age:"+student.getAge());
        }

    }
}
