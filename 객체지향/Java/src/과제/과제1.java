package ����;
public class ����1 {
	static double calculator; //����� ����  ���� ����
	public static void main(String[] args) {
		System.out.println("11. ���� �������� ������ �Ʒ� ���� ����϶�.");
		System.out.println("	int i = 3, j = 4, k = 5;");
		int i=3, j=4, k=5; //������ ����
		System.out.println("	float x = 3.45f, y = 6.0f; \n");
		float x=3.45f, y= 6.0f; //�Ǽ��� ����
		
		System.out.println("(a) -x * -y * (i+j) / k ");
		calculator = (-x*-y*(i+j)/k); 
		System.out.println(" -"+x+" * -"+y+" * ("+i+"+"+j+") / "+k+" = "+ calculator+"\n");
		
		System.out.println("(b) i / 5 * y ");
		calculator = (double)i / 5 * y; // i�� �������̶� ���� ������� 1�̸��̸� 0���� ���, �׷��� (double)���� ����ȯ
		System.out.println(" "+i+" / 5 * "+ y + " = " + calculator+"\n");
		
		System.out.println("(c) y % x  ");
		calculator = y % x;
		System.out.println(" "+y+" % "+x+" = "+ calculator+"\n");
		
		System.out.println("(d) x = x / i / y / j ");
		System.out.print(" x = "+x+" / "+i+" / "+y+" / "+j);
		x = x/i/y/j; // ���� x�� x/i/y/j�� ����.
		System.out.println(",   x = "+x+"\n");
		x = 3.45f; //�������� �ʱ⿡ �־��� ������ ����϶� �Ͽ����� x�� �ٽ� 3.45f�� �ʱ�ȭ
		
		System.out.println("(e) Math.exp(j % i) ");
		calculator = Math.exp(j % i);
		System.out.println("Math.exp("+j+" % "+i+") = " + calculator+"\n");
		
		System.out.println("(f) Math.pow(3, 2) ");
		calculator = Math.pow(3, 2);
		System.out.println("Math.pow(3, 2) = " + (int)calculator+"\n");
		//calculator�� �ڷ����� double�̶� 3^2�� ������� �����̹Ƿ� ���������� ���
		
		System.out.println("(g) Math.min(y, Math.max(i, k)) ");
		calculator = Math.min(y, Math.max(i, k));
		if(calculator%(calculator-1)==1) System.out.println("Math.min("+y+", Math.max("+i+", "+k+")) = "+(int)calculator+"\n");
		//x=5, x%x-1 == 5%4 == 1, �������� ��� x%x-1�� ������ 1�� �����Ƿ� 1�� ��� ������� (int)�� ����ȯ �� ��� 
		else System.out.println("Math.min("+y+", Math.max("+i+", "+k+")) = "+calculator+"\n");
		//�ݴ�� 1���� Ŭ ��� �Ǽ��� ��츸 �ش�, �Ǽ��� �״�� ���
		
		System.out.println("(h) Math.ceil(x) % i ");
		calculator = Math.ceil(x)%i; 
		System.out.println(" Math.ceil("+x+") % "+i+" = "+(int)calculator+"\n");
		//Math.ceil�� �ڸ��ø��Լ��̴�. �Ǽ��� ��� �Ҽ������ϰ� 0�̹Ƿ� ���������� ����ϰ� ��Ÿ��.
		
		System.out.println("(i) (x + 2.4) / (140.0 * (i/j) ");
		calculator = (x+2.4)/(140.0 * ((double)i/j)); //i�� �����̹Ƿ� �Ǽ������� ����ȯ
		System.out.println(" ("+x+" + 2.4) / (140.0 * ("+i+"/"+j+") ="+calculator);
	}
}
