package com.wyr.leetcode.step2.array;

public class Student {
    public String name;

    public Student(String name) {
        this.name = name;
    }


}

class Test implements Cloneable{
    public Student st;

    public static void main(String[] args) throws CloneNotSupportedException {
        Student s1 = new Student("Tom");
        Test t1=new Test();
        t1.st=s1;
        Test t2=(Test)t1.clone();
        System.out.println(t1.st.equals(t2.st));
    }
}
