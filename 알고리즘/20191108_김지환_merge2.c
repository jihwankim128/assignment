#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int size;
	long long *arr;	
} MergeArray;

void Init(MergeArray* S) {
	int size = 0;
	S->arr = NULL;
	while(1) {
		long long n; char c;
		scanf("%lld%c", &n, &c);
		S->arr = realloc(S->arr, sizeof(long long)*(size+1));
		S->arr[size++] = n;
		if(c=='\n') break;
	}
	S->size = size;
}

void mergeSort(MergeArray* S, int l, int m, int h){
	int i = l, j = m+1, k =l;
	long long* U = malloc(sizeof(long long) * S->size);
    while(i<=m && j<=h) {
        if(S->arr[i]<S->arr[j]) {
            U[k++] = S->arr[i++];
        }
        else {
            U[k++] = S->arr[j++];
        }
    }
    
    if(i > m) {
        for(j; j<=h; j++) {
            U[k++] = S->arr[j];
        }
    }
    else {
        for(i; i<=m; i++) {
            U[k++] = S->arr[i];
        }
    }
    
    for(i=l; i<=h; i++) {
        S->arr[i] = U[i];
    }
    
    free(U);
}
void merge(MergeArray* S, int l, int h) {
    if(l < h) {
        int m = (l+h) / 2;
        merge(S, l, m);
        merge(S, m+1, h);
        mergeSort(S, l, m, h);
    }
}
void print(MergeArray* S) {
	int i;
	for(i = 0; i<S->size;i++) {
		printf("%lld ", S->arr[i]);
	}
}
int main() {
	MergeArray arr;
	Init(&arr);
	merge(&arr, 0, arr.size-1);
	print(&arr);
	free(arr.arr);
	return 0;
}

