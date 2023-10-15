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

// qsort �� �Լ�, ĳ���� �� �����ͷ� ���� �� ã��.
int compare(const void *a, const void *b) {
	return strcasecmp(*(char **)a, *(char **)b);
}

// ���ڸ� �ϳ��� �о� ���ڿ��� ����� ����
char *ReadString(int fd) {
	int length = 0;
	int size = 1;
	char *word = malloc(size);
	char character;
	int read_byte;
	
	// READ - 1BYTE�� �о ���ڿ��� ����.
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
		perror("File Read �� ������ �߻��߽��ϴ�");
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
	// �Է¹��� ���ϵ��� ��� �о ��� ���ڵ��� strings�� ����
	for(int i = 1; i < argc-1; i++) {
		int fd = open(argv[i], O_RDONLY);
		if(fd == IO_ERROR) {
			perror("File Open �� ������ �߻��߽��ϴ�");
			return EXIT_FAILURE;
		}
		
		char *string;
		// ReadString �޼���� ���� �ϳ��� ���ڿ����� ��� �Ľ��ϴ� ����
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
	// �������� ���� ��ũ����, ������ ������ �����ϰ� open�� ������ ������ ���
	int output_fd = open(file_name, 
						 O_WRONLY | O_CREAT | O_TRUNC, 
						 S_IRUSR | S_IWUSR);
	if(output_fd == IO_ERROR) {
		perror("File Open �� ������ �߻��߽��ϴ�");
		return EXIT_FAILURE;
	}
	
	// �����۾�
	for(int i = 0; i < string_count; i++) {
		write(output_fd, strings[i], strlen(strings[i]));
		write(output_fd, " ", 1);
		free(strings[i]);
	}
	
	close(output_fd);
	return EXIT_SUCCESS;
} 

int main(int argc, char *argv[]) {
    // ������ �ϳ��� �Է����� ��� ����ó��
    if(argc < REQUIRED_ARGC) {
        fprintf(stderr, "�Է� ���ϰ� ��� ������ �ۼ��Ͻÿ�.\n");
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
