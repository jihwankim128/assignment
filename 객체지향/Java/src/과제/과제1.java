package 과제;
public class 과제1 {
	static double calculator; //계산을 위한  변수 선언
	public static void main(String[] args) {
		System.out.println("11. 다음 변수들의 값으로 아래 식을 계산하라.");
		System.out.println("	int i = 3, j = 4, k = 5;");
		int i=3, j=4, k=5; //정수형 선언
		System.out.println("	float x = 3.45f, y = 6.0f; \n");
		float x=3.45f, y= 6.0f; //실수형 선언
		
		System.out.println("(a) -x * -y * (i+j) / k ");
		calculator = (-x*-y*(i+j)/k); 
		System.out.println(" -"+x+" * -"+y+" * ("+i+"+"+j+") / "+k+" = "+ calculator+"\n");
		
		System.out.println("(b) i / 5 * y ");
		calculator = (double)i / 5 * y; // i가 정수형이라 계산시 결과값이 1미만이면 0으로 출력, 그래서 (double)으로 형변환
		System.out.println(" "+i+" / 5 * "+ y + " = " + calculator+"\n");
		
		System.out.println("(c) y % x  ");
		calculator = y % x;
		System.out.println(" "+y+" % "+x+" = "+ calculator+"\n");
		
		System.out.println("(d) x = x / i / y / j ");
		System.out.print(" x = "+x+" / "+i+" / "+y+" / "+j);
		x = x/i/y/j; // 기존 x에 x/i/y/j를 대입.
		System.out.println(",   x = "+x+"\n");
		x = 3.45f; //문제에서 초기에 주어진 값으로 계산하라 하였으니 x를 다시 3.45f로 초기화
		
		System.out.println("(e) Math.exp(j % i) ");
		calculator = Math.exp(j % i);
		System.out.println("Math.exp("+j+" % "+i+") = " + calculator+"\n");
		
		System.out.println("(f) Math.pow(3, 2) ");
		calculator = Math.pow(3, 2);
		System.out.println("Math.pow(3, 2) = " + (int)calculator+"\n");
		//calculator의 자료형이 double이라 3^2의 결과값은 정수이므로 정수형으로 출력
		
		System.out.println("(g) Math.min(y, Math.max(i, k)) ");
		calculator = Math.min(y, Math.max(i, k));
		if(calculator%(calculator-1)==1) System.out.println("Math.min("+y+", Math.max("+i+", "+k+")) = "+(int)calculator+"\n");
		//x=5, x%x-1 == 5%4 == 1, 정수형일 경우 x%x-1이 무조건 1이 나오므로 1일 경우 결과값을 (int)로 형변환 후 출력 
		else System.out.println("Math.min("+y+", Math.max("+i+", "+k+")) = "+calculator+"\n");
		//반대로 1보다 클 경우 실수의 경우만 해당, 실수형 그대로 출력
		
		System.out.println("(h) Math.ceil(x) % i ");
		calculator = Math.ceil(x)%i; 
		System.out.println(" Math.ceil("+x+") % "+i+" = "+(int)calculator+"\n");
		//Math.ceil은 자리올림함수이다. 실수일 경우 소수점이하가 0이므로 정수형으로 깔끔하게 나타냄.
		
		System.out.println("(i) (x + 2.4) / (140.0 * (i/j) ");
		calculator = (x+2.4)/(140.0 * ((double)i/j)); //i가 정수이므로 실수형으로 형변환
		System.out.println(" ("+x+" + 2.4) / (140.0 * ("+i+"/"+j+") ="+calculator);
	}
}
