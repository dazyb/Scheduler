package model;

public class Swap {
	private String a,b;

	public Swap(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	public void swap() {
		String temp;
		temp = this.a;
		this.a = this.b;
		this.b = temp;
	}

	public String getA() {
		return a;
	}

	public String getB() {
		return b;
	}
	

}
