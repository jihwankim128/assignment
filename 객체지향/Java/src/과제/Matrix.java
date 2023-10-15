package ����;

import java.util.Scanner;
//Scanner Library ����� ���� import
public class Matrix {
	private static Scanner sc;
	static double[][] array; //������ ����� ���� 2���� �迭
	static double[][] trans; //��ġ��� �迭
	static double[][] inverse; //����� �迭
	static int squareChk = 0; //����������� üũ
	static int N, M; //N*M�� Matrix�� ���� ����
	
	public static int inverseMatrix() { //�����
		if(squareChk==1&& N!=1) { // ��������̸鼭 1x1 ����� �ƴ� ��
			inverse = new double[N][M];
			for(int i=0;i<N;i++) //������� �̿��� ���μ� ��� ���ϱ�
				for(int j=0;j<M;j++) 
					inverse[i][j] = smallMatrix(array,i,j); 
			transPoseMatrix(inverse);//������� ���ϴ� ���� ���μ� ����� ��ġ��ķ� ��ȯ
			
			for(int i=0;i<N;i++) //���μ����*1/��Ľ� == ����� ����
				for(int j=0;j<M;j++) 
					inverse[i][j] = trans[i][j] * (1.0/Determinant(array)); 
			return 1; //������� �������� ��ȯ
		}
		else if (squareChk==1&&N==1) return 0; // 1x1 Matrix ���� ��ȯ
		else return -1; // ������� ����
	}
	public static double Determinant(double[][] arr) {
		int size = arr.length; 
		//3x3 �̻��� ��Ľ��� ���ϱ� ���ؼ��� ����ĽĿ��� �ٽ� ��Ľ��� ���ؾ��ϹǷ� ����� �پ���.
		int i=0;
		double det=0;
		if(squareChk==1) {
			if(size==1) return arr[0][0];
			else if(size==2) {
				return arr[0][0]*arr[1][1]-arr[0][1]*arr[1][0];//ad-bc
			}
			else {
				for(i=0, det=0; i<size;i++) {
					det+=arr[i][0]*smallMatrix(arr,i,0);
					//A[i][j]*|�����, ����� i���� 0���� ����
				}
			}
			return det; 
		}
		return 999; //��Ľ����� ������ ���� ���� ��ȯ �Ͽ� ��������� �ƴ��� �˸�
	}
	public static double smallMatrix(double[][] matrix, int a, int b) {
		int size = matrix.length;
		double[][] small = new double[size-1][size-1]; 
		//������� ���ؾ��ϹǷ� Det() �� arr���� size�� �ϳ� �۴�
		int i,j,x,y;
		for(i=0,x=0;i<size;i++) {
			if(i!=a) { // Det �� arr[a]���� �����ϱ� ����.
				for(j=0, y=0;j<size;j++) {
					if(j!=b) {//Det �� arr[i][b]���� �����ϱ� ����.
						small[x][y] = matrix[i][j]; //matrix�� ������̵�.
						y++;
					}
				}
				x++;
			}
		}
		
		double det = Math.pow(-1, (a+1)+(b+1))*Determinant(small); 
		// �迭�� index�� 0���� �����ϴ� a+1,b+1 -> ����Ľ� (-1)^i+j*��Ľ�
		return det;
	}
	public static void transPoseMatrix(double[][] arr) { // ��ġ���
		trans = new double[M][N];
		for(int i=0;i<M;i++) // A[3][2] -> A[2][3] ���� �ٲ�ϱ� ���� ���� ��ġ�� ������ ��İ� �ݴ��
			for(int j=0;j<N;j++) 
				trans[i][j]=arr[j][i]; 
	}
	public static void inputMatrix() {
		sc = new Scanner(System.in);
		System.out.println("N * M�� MATRIX");
		System.out.print("N = ");
		N = sc.nextInt();
		System.out.print("M = ");
		M = sc.nextInt();
		sc.close();
		System.out.print("\n");
		array = new double[N][M]; //N*M Matrix
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++) {
				array[i][j] = Math.floor(Math.random()*6); 
				//5������ ���� ��, �ĵ��� ������ �� ���� �´��� ����� Ȯ���ϱ� ���� ���� ����.
			}
		if(N==M) squareChk = 1; // ��ġ��� üũ
		else squareChk = 0;
	}
	public static void showMatrix(double[][] arr) { //�Էƹ��� �迭 ���
		int size1 = arr.length; //��ġ����� ��츦 �����ؼ� arr.length �� ������ ����
		int size2 = arr[0].length;
			for(int i=0;i<size1;i++) {
				for(int j=0;j<size2;j++){
					System.out.printf("%f,",arr[i][j]); //���
				}
				System.out.println("");
			}
		
	}
	public static void main(String[] args) {
		inputMatrix();
		System.out.print("------������ ���------\n");
		showMatrix(array);
		System.out.print("\n");
		
		
		transPoseMatrix(array);
		System.out.print("(a) ��ġ ���(transpose)�� ���϶�\n");
		showMatrix(trans);
		System.out.print("\n");
		

		System.out.print("(b)���� ����̸� ��Ľ�(deteminant)�� ���� ���϶�.\n");
		System.out.print(Determinant(array)==999?"��������� �������� �ʽ��ϴ�.\n":" ��Ľ��� �� = "+Determinant(array)+"\n");
		System.out.print("\n");
		
		System.out.print("(c)�����ϸ� ������� ���϶�.\n");
		if(inverseMatrix()==1) showMatrix(inverse);
		else if(inverseMatrix()==0) System.out.print((1/Determinant(array)));
		else System.out.print("��������� �ƴϹǷ� ������� ���� �� �����ϴ�.");
	}
}
