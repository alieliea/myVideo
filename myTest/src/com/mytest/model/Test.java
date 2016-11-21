package com.mytest.model;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Test test = new Test();
		test.setA("a");
		List<Test> list = new ArrayList<>();
		list.add(test); 
		test = new Test();
		test.setA("b");
		list.add(test);
		test = new Test();
		test.setA("c");
		list.add(test);
		for(Test a : list){
			a = test.getT(a);
		}
		
		for(Test a : list){
			System.out.println(a.getA());
		}
		
		List<Integer> aaa = new ArrayList<>();
		aaa.add(1);
		for(Integer a : aaa){
			a = test.aa(a);
		}
		System.out.println(aaa);
	}
	
	public Integer aa(Integer aa){
		aa = new Integer(2);
		return aa;
	}
	
	public Test getT(Test test){
		test.setA("dfasd");
		return test;
	}
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}

	private String a;
}