package 과제;

import java.util.Scanner;
//Scanner Library 사용을 위한 import
public class Matrix {
	private static Scanner sc;
	static double[][] array; //임의의 행렬을 담을 2차원 배열
	static double[][] trans; //전치행렬 배열
	static double[][] inverse; //역행렬 배열
	static int squareChk = 0; //정방행렬인지 체크
	static int N, M; //N*M의 Matrix를 위한 변수
	
	public static int inverseMatrix() { //역행렬
		if(squareChk==1&& N!=1) { // 정방행렬이면서 1x1 행렬이 아닐 때
			inverse = new double[N][M];
			for(int i=0;i<N;i++) //소행렬을 이용한 여인수 행렬 구하기
				for(int j=0;j<M;j++) 
					inverse[i][j] = smallMatrix(array,i,j); 
			transPoseMatrix(inverse);//역행렬을 구하는 과정 여인수 행렬을 전치행렬로 변환
			
			for(int i=0;i<N;i++) //여인수행렬*1/행렬식 == 역행렬 공식
				for(int j=0;j<M;j++) 
					inverse[i][j] = trans[i][j] * (1.0/Determinant(array)); 
			return 1; //역행렬이 존재함을 반환
		}
		else if (squareChk==1&&N==1) return 0; // 1x1 Matrix 임을 반환
		else return -1; // 역행렬이 없음
	}
	public static double Determinant(double[][] arr) {
		int size = arr.length; 
		//3x3 이상의 행렬식을 구하기 위해서는 소행렬식에서 다시 행렬식을 구해야하므로 사이즈가 줄어든다.
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
					//A[i][j]*|소행렬, 소행렬 i열과 0행을 제외
				}
			}
			return det; 
		}
		return 999; //행렬식으로 나오기 힘든 값을 반환 하여 정방행렬이 아님을 알림
	}
	public static double smallMatrix(double[][] matrix, int a, int b) {
		int size = matrix.length;
		double[][] small = new double[size-1][size-1]; 
		//소행렬을 구해야하므로 Det() 내 arr보다 size가 하나 작다
		int i,j,x,y;
		for(i=0,x=0;i<size;i++) {
			if(i!=a) { // Det 내 arr[a]열을 제외하기 위함.
				for(j=0, y=0;j<size;j++) {
					if(j!=b) {//Det 내 arr[i][b]행을 제외하기 위함.
						small[x][y] = matrix[i][j]; //matrix는 소행렬이됨.
						y++;
					}
				}
				x++;
			}
		}
		
		double det = Math.pow(-1, (a+1)+(b+1))*Determinant(small); 
		// 배열의 index는 0부터 시작하니 a+1,b+1 -> 소행렬식 (-1)^i+j*행렬식
		return det;
	}
	public static void transPoseMatrix(double[][] arr) { // 전치행렬
		trans = new double[M][N];
		for(int i=0;i<M;i++) // A[3][2] -> A[2][3] 으로 바뀌니까 열과 행의 위치를 임의의 행렬과 반대로
			for(int j=0;j<N;j++) 
				trans[i][j]=arr[j][i]; 
	}
	public static void inputMatrix() {
		sc = new Scanner(System.in);
		System.out.println("N * M의 MATRIX");
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
				//5이하의 랜덤 값, 식들을 구현할 때 값이 맞는지 수기로 확인하기 위해 적게 설정.
			}
		if(N==M) squareChk = 1; // 전치행렬 체크
		else squareChk = 0;
	}
	public static void showMatrix(double[][] arr) { //입렵받은 배열 출력
		int size1 = arr.length; //전치행렬일 경우를 생각해서 arr.length 로 사이즈 측정
		int size2 = arr[0].length;
			for(int i=0;i<size1;i++) {
				for(int j=0;j<size2;j++){
					System.out.printf("%f,",arr[i][j]); //출력
				}
				System.out.println("");
			}
		
	}
	public static void main(String[] args) {
		inputMatrix();
		System.out.print("------임의의 행렬------\n");
		showMatrix(array);
		System.out.print("\n");
		
		
		transPoseMatrix(array);
		System.out.print("(a) 전치 행렬(transpose)을 구하라\n");
		showMatrix(trans);
		System.out.print("\n");
		

		System.out.print("(b)정방 행렬이면 행렬식(deteminant)의 값을 구하라.\n");
		System.out.print(Determinant(array)==999?"정방행렬이 존재하지 않습니다.\n":" 행렬식의 값 = "+Determinant(array)+"\n");
		System.out.print("\n");
		
		System.out.print("(c)가능하면 역행렬을 구하라.\n");
		if(inverseMatrix()==1) showMatrix(inverse);
		else if(inverseMatrix()==0) System.out.print((1/Determinant(array)));
		else System.out.print("정방행렬이 아니므로 역행렬을 구할 수 없습니다.");
	}
}
