#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// SubSet�� ã�� ���� Set ���� 
typedef struct {
	int n;
	int size;
	int *arr;
	int *subset;
	bool **dp;	
} Set;

// Set �ʱ�ȭ 
void Init(Set *S) {
	S->arr = NULL;
	S->size = 0;
}

// Ž�� ��, ���� input 
void input(Set *S) {
	int n, i; 
	char c;
	
	scanf("%d%c", &n, &c);
	S->n = n;
	
	// ������ ���ڰ� ������ ��� �Է� ����. 
	while(c!='\n') {
		scanf("%d%c", &n, &c);
		// �������� ����� ������ ������ ���� �Ҵ�
		S->arr = realloc(S->arr, sizeof(int)*(S->size+1));
		S->arr[S->size++] = n;
		 
		if(n == 0) {
			int temp = S->arr[0];
			S->arr[0] = 0;
			S->arr[S->size-1] = temp;
		}
	}
	
	S->subset = calloc(S->n, sizeof(int));
	S->dp = (bool **)calloc((S->size + 1), sizeof(bool *));
	
	for (i = 0; i <= S->size; i++) {
		S->dp[i] = (bool *)calloc((S->n + 1), sizeof(bool));
	}
}

//�� Ʈ��ŷ 
//DP ���̺� �����ϴ� �ݴ�� 
void findSubsets(Set *S, int subsetSize, int i, int j) {
	int target = S->arr[i-1];
    if (i == 0 && j == 0) {
    	for(i; i<subsetSize; i++) printf("%d ", S->subset[i]);
		printf("\n");
        return;
    }

    if (i > 0 && S->dp[i - 1][j]) {
        findSubsets(S, subsetSize, i - 1, j);
    }

    if (i > 0 && j >= target && S->dp[i - 1][j - target]) {
    	S->subset[subsetSize] = target;
        findSubsets(S, subsetSize+1, i - 1, j - target);
        S->subset[subsetSize] = 0;
    }
}

// DP���̺� ���� 
void subsetDP(Set *S) {
    int i, j;
    
	for (i = 0; i <= S->size; i++) S->dp[i][0] = true;
    
    for (i = 1; i <= S->size; i++) {
    	int target = S->arr[i-1];
        for (j = 1; j <= S->n; j++) {
            if (j < target) {
                S->dp[i][j] = S->dp[i - 1][j];
            }
            else {
                S->dp[i][j] = S->dp[i - 1][j] || S->dp[i - 1][j - target];
            }
        }
    }
    
    findSubsets(S, 0, S->size, S->n);
}

int main() {
	Set S;
	int i, j;
	
	Init(&S);
	input(&S); 
	
	subsetDP(&S);
	free(S.arr);
	
	for(i = 0; i <= S.size; i++) free(S.dp[i]);
	free(S.dp);
	
	return 0;
}

