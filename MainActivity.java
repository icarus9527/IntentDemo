package com.example.icarus.intentdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1,btn2,btn3,btn5,btn6;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.main_btn_1);
        btn2 = (Button) findViewById(R.id.main_btn_2);
        btn3 = (Button) findViewById(R.id.main_btn_3);
        btn5 = (Button) findViewById(R.id.main_btn_5);
        btn6 = (Button) findViewById(R.id.main_btn_6);

        //点击事件的四种响应方式
        //一、直接在setOnClickListener里创建新OnClickListener对象并实现借口方法onclick
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //匿名内部类无法直接访问到外部类，所以不能直接用this
                //显示Intent，直接设置setClass参数，方式简单
                //另一种设置方法Intent intent = new Intent(MainActivity.this,ActivityB.class)
                intent.setClass(MainActivity.this,ActivityB.class);
                startActivity(intent);
            }
        });

        //二、通过新建一个OnClickListener对象来将点击事件与控件分开
        btn2.setOnClickListener(listener);

        //三、通过实现在Activity中实现OnClickListener借口，可以将所有点击事件设置在一个方法体内
        btn3.setOnClickListener(this);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在Person类实现Serializable接口，就可以用Intent将该类对象实例传过去
                Person person = new Person("zhangsan",18);
                Intent intent = new Intent(MainActivity.this,ActivityF.class);
                intent.putExtra("person_data",person);
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student("lisi",19);
                Intent intent = new Intent(MainActivity.this,ActivityF.class);
                intent.putExtra("student_data",student);
                startActivity(intent);
            }
        });


    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*隐式intent，在AndroidManifestes里Activity注册信息中加入action和category标签
            * intent必须设置相同的action和category才可以跳转到指定的Activity
            * 当category的值为 android.intent.category.DEFULT时，可以不在intent里设置categoty，系统会自动加上
            * 每个intent只能指定一个action，但却能通过addCategoty方法指定多个category
            * 使用隐式intent可以调用系统以及其他程序的Activity
            * 如果注册信息里设置了data标签，在intent里也要使用setData方法传递一个Uri对象
            * 当对象内容符合注册信息里的要求时，该Intent才能被响应
            * */
            //直接设置action的方法Intent intent = new Intent("com.example.icarus.ActivityC");
            Intent intent = new Intent();
            intent.setAction("com.example.icarus.ActivityC");
            intent.addCategory("com.example.icarus.IntentDemo.My_Category");
            intent.setData(Uri.parse("http://www.baidu.com"));
            startActivity(intent);
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btn_3:
                /*通过putExtra方法将要传递的数据记录在intent里
                * 在接收数据的Activity里创建一个新的Intent，通过getIntent方法获得实例
                * 通过get***Extra（***表示对应数据类型）来获取数据
                * */
                Intent intent = new Intent(MainActivity.this,ActivityD.class);
                intent.putExtra("request","这是传送过来的数据！！");
                startActivity(intent);
        }
    }
    /*在布局文件中Button加入onclick标签，值设为方法名，这样可以不用实例化Button，缺点是不容易维护
    * 因为难以找到方法对应的控件所在
    * */
    public void setOnBtn4Click(View v){
        /*通过startActivityForResult方法启动intent可以指定有返回值，不过要通过onActivityResult方法来接收数据
        *startActivityForResult的两个参数为Intent对象，RequestCode
        * 在返回数据的Activity中新建一个Intent，将数据传入Intent中，通过setResult方法将Intent传回
        * setResult方法的两个参数为resultCode,Intent对象
             * */
        Log.i("MainActivity","点击成功");
        Intent intent = new Intent(MainActivity.this,ActicityE.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 2){
            String result = data.getStringExtra("result");
            tvResult.setText(result);
        }
    }
}
