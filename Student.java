package com.example.icarus.intentdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by icarus9527 on 2017/3/31.
 */

//实现Parcel able接口，复写describlContents方法和writeToParcel方法，在writeToParcel方法里将Student类中的字段一一写出
    //创建一个名为CRREATOP的常量，实现Parcelable.Creator接口,并复写createFromParcel()和newArray()这两个方法
    //在createFromParcel中读取刚才写出的字段，读取的顺序一定要和刚才写出的顺序一致，newArray只要返回一个Student数组并使用方法中传入的size作为数组大小
    //Parcelable方式传递对象会比Serializable有效率，所以一般推荐使用Parcelable方式
public class Student implements Parcelable{
    private String name;
    private int age;

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    public static final Creator<Student> CREATOR = new Creator<Student>(){

        @Override
        public Student createFromParcel(Parcel source) {
            String name = source.readString();
            int age = source.readInt();
            Student student = new Student(name,age);
            return student;
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
