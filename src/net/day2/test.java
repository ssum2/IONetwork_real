package net.day2;

public class test {
	public void ex1() {
		System.out.println("Q1.");
		for(int i=0;i<5;i++) {
			System.out.print("*");
		}
	}
	public void ex2() {
		System.out.println("Q2.");
		for(int i=0;i<5;i++) {
			System.out.println("*");
		}
	}
	public void ex3() {
		System.out.println("Q3.");
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public void ex4() {
		System.out.println("Q4.");
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
	public void ex5() {
		System.out.println("Q5.");
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}
	public void ex6() {
		System.out.println("Q6.");
		for(int i=0;i<5;i++) {
			for(int j=1;j<=5;j++) {
				System.out.print(i+j);
			}
			System.out.println();
		}
	}
	public void ex7() {
		System.out.println("Q7.");
		for(int i=0;i<5;i++) {
			for(int j=5;j<=9;j++) {
				System.out.print(j-i);
			}
			System.out.println();
		}
	}
	public void ex8() {
		System.out.println("Q8.");
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public void ex9() {
		System.out.println("Q9.");
		for(int i=5;i>=1;i--) {
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public void ex10() {
		System.out.println("Q10.");
		for(int i=5;i>=1;i--) {
			for(int j=1;j<=5-i;j++) {
				System.out.print(" ");
			}
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public void ex11() {
		System.out.println("Q11.");
		for(int i=1;i<=5;i++) {
			for(int j=5;j>i;j--) {
				System.out.print(" ");
			}
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public void ex12() {
		System.out.println("Q12.");
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for(int i=4;i>=1;i--) {
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public void ex13() {
		System.out.println("Q13.");
		for(int i=5;i>=1;i--) {
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for(int i=2;i<=5;i++) {
			for(int j=1;j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}		
	}
}

