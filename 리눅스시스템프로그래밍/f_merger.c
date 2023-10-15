// Authored by Kimjihwan
// Standard I/O
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Linux System Library
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>

// Magic Number 
#define IO_ERROR -1
#define REQUIRED_ARGC 3
#define NOTHING 0

int g_string_max_size = 1;

// qsort 비교 함수, 캐스팅 후 포인터로 기존 값 찾기.
int compare(const void *a, const void *b) {
	return strcasecmp(*(char **)a, *(char **)b);
}

// 문자를 하나씩 읽어 문자열로 만드는 과정
char *ReadString(int fd) {
	int length = 0;
	int size = 1;
	char *word = malloc(size);
	char character;
	int read_byte;
	
	// READ - 1BYTE씩 읽어서 문자열을 생성.
	while((read_byte = read(fd, &character, 1)) > 0 
		  && character != ' ' && character != '\n') 
	{
		if(length + 1 >= size) {
			size *= 2;
			word = realloc(word, size);
		}
		word[length++] = character;
	}
	
	if(read_byte == IO_ERROR) {
		perror("File Read 중 에러가 발생했습니다");
		exit(EXIT_FAILURE);
	}
	
	if(length == NOTHING) {
		free(word);
		return NULL;
	}
	
	word[length] = '\0';
	return word;
}

int GetStringOfFile(char*** strings, int *string_count, int argc, char** argv) {
	// 입력받은 파일들을 모두 읽어서 모든 문자들을 strings에 저장
	for(int i = 1; i < argc-1; i++) {
		int fd = open(argv[i], O_RDONLY);
		if(fd == IO_ERROR) {
			perror("File Open 중 에러가 발생했습니다");
			return EXIT_FAILURE;
		}
		
		char *string;
		// ReadString 메서드로 파일 하나의 문자열들을 모두 파싱하는 과정
		while((string = ReadString(fd)) != NULL) {
			if(*string_count + 1 >= g_string_max_size) {
				g_string_max_size *= 2;
				*strings = realloc(*strings, sizeof(char *) * g_string_max_size);
			}
			(*strings)[(*string_count)++] = string;
		}
		
		close(fd);
	}
	return EXIT_SUCCESS;
}

int WriteFile(char** strings, int string_count, char *file_name) {
	// 쓰기전용 파일 디스크립터, 파일이 없으면 생성하고 open시 파일의 내용을 비움
	int output_fd = open(file_name, 
						 O_WRONLY | O_CREAT | O_TRUNC, 
						 S_IRUSR | S_IWUSR);
	if(output_fd == IO_ERROR) {
		perror("File Open 중 에러가 발생했습니다");
		return EXIT_FAILURE;
	}
	
	// 쓰기작업
	for(int i = 0; i < string_count; i++) {
		write(output_fd, strings[i], strlen(strings[i]));
		write(output_fd, " ", 1);
		free(strings[i]);
	}
	
	close(output_fd);
	return EXIT_SUCCESS;
} 

int main(int argc, char *argv[]) {
    // 파일을 하나만 입력했을 경우 오류처리
    if(argc < REQUIRED_ARGC) {
        fprintf(stderr, "입력 파일과 출력 파일을 작성하시오.\n");
        return EXIT_FAILURE;
    }
    
	char **strings= NULL;
	int string_count = 0;

	if(GetStringOfFile(&strings, &string_count, argc, argv) == EXIT_FAILURE) {
		return EXIT_FAILURE;
	}
	qsort(strings, string_count, sizeof(char *), compare);
	if(WriteFile(strings, string_count, argv[argc-1]) == EXIT_FAILURE) {
		return EXIT_FAILURE;
	}

	free(strings);
    return EXIT_SUCCESS;
}
