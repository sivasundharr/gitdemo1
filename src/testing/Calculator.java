package testing;

import java.util.Scanner;

public class Calculator {
	
	public int fact(int num) {
		int f = 1;
		
		for(int i=num;i>1;i--) {
			f = f*i;
		}
		
		return f;
	}
	
	public int add(int a,int b) {
		return a+b;
	}
	
	public static void main(String args[]) {
		
		Calculator c = new Calculator();
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		System.out.println(c.fact(n));
		
		sc.close();
		
	}

}
