package 과제;

public class 과제2 {
	static double calculator;
	public static double a(double A, double B,double C) {
		calculator = Math.sqrt(Math.pow(B, 2)-4*A*C);
		return calculator;
	}
	public static double b(double X, double Y) {
		calculator = Math.pow((X*Y),1.0/3.0);
		return calculator;
	}
	public static double c(double R) {
		calculator = Math.floor(Math.PI * Math.pow(R, 2)*100)/100.00;
		return calculator;
	}
	public static double d(double theta) {
		calculator = Math.sin(theta)/Math.cos(theta);
		return calculator;
	}
	public static double e(double X) {
		calculator = X/2;
		return calculator;
	}
	public static double f(double C) {
		double F=1.8*C+32;
		return F;
	}
	public static void main(String[] args) {
		System.out.println("12. 다음 계산을 하는 수식을 만들어라.\n");
		
		System.out.println("(a) B²-4AC 의 제곱근"); // x^2+2x+1, D=0
		System.out.println("x²+2x+1의 D=0이다. (a) = "+a(1,2,1)+"\n");
		
		System.out.println("(b) X와 Y의 곱의 세제곱근");// X*Y=8, 8==2^3, 8의 세제곱근=2
		System.out.println("³√(2x4)=2이다. (b) = "+b(2,4)+"\n");
		
		System.out.println("(c) πR²");// 반지름 4의 원넓이는 16파이
		System.out.println("3.14*4²=약 50.26이다. (C) = "+c(4)+"\n");
		
		System.out.println("(d) sinθ ÷ cosθ"); // sin/cos == tan, tanX=1, X=pi/4
		System.out.println("sin(π/4)÷cos(π/4)= tan(π/4) = "+d(Math.PI/4)+"\n");
		
		System.out.println("(e) X ÷ 2");
		System.out.println("2÷2="+e(2)+"\n");
		
		System.out.println("(f) F=1.8 × C + 32 ");
		System.out.println("섭씨 10.0˚C = 화씨 "+f(10)+"˚C이다.\n");
		
	}

}
