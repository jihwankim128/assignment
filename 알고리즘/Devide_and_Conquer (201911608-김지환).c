#include <stdio.h>
#include <stdlib.h>

//SubSet�� ã������ Set ���� 
typedef struct {
	int n;
	int size;
	int sum;
	int *arr;
	int *subset;	
} Set;

//Set �ʱ�ȭ 
void Init(Set *S) {
	S->arr = NULL;
	S->sum = 0;
	S->size = 0;
}

// Ž�� ��, ���� input 
void input(Set *S) {
	int n; char c;
	
	scanf("%d%c", &n, &c);
	S->n = n;
	
	// ������ ���ڰ� ������ ��� �Է� ����. 
	while(c!='\n') {
		scanf("%d%c", &n, &c);
		//�������� ����� ������ ������ ���� �Ҵ�
		S->arr = realloc(S->arr, sizeof(int)*(S->size+1));
		S->arr[S->size++] = n;
		
		S->sum+= n;
		 
		if(n == 0) {
			int temp = S->arr[0];
			S->arr[0] = 0;
			S->arr[S->size-1] = temp;
		}
	}
	S->subset = calloc(S->size , sizeof(int));
}


//���� �۾� ������ 
void devide_and_conquer(Set *S, int sum, int n, int subsetSize, int total) {
	if(sum == S->n) {
		int i;
		for(i=0; i<subsetSize; i++) {
			printf("%d ", S->subset[i]);
		}
		printf("\n");
		return;
	}
	
	if(S->size == n || total - sum - S->arr[n] > S->sum) return ;
	
	S->subset[subsetSize] = S->arr[n];
	devide_and_conquer(S, sum + S->arr[n], n+1, subsetSize+1, total-S->arr[n]);
	
	S->subset[subsetSize] = 0;
	devide_and_conquer(S, sum, n+1, subsetSize, total);
}

int main() {
	Set S;
	
	Init(&S);
	input(&S); 
	
	devide_and_conquer(&S, 0, 0, 0, S.sum);
	
	free(S.arr);
	free(S.subset);
	return 0;
}
