package test;

import java.io.Serializable;

public class Singleton implements Serializable{
	private static Singleton instance = new Singleton();
	private Singleton() {
	}
	public static Singleton getInstance(){
		return instance;
	}
	private Object readResovle(){
		return instance;
	}
}
