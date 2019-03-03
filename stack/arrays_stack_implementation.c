#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "stdbool.h"
#define MAX_SIZE 101


int top = -1;
char A[MAX_SIZE];

void push(char x) {
  if (top == MAX_SIZE - 1) {
    perror("Stack is full");
    return;
  }
  A[++top] = x;
}

bool isEmpty() {
  if (top == -1) {
    return true;
  }
  return false;
}

char pop() {
  if (!isEmpty()) {
    return A[top--];
  }
  perror("Stack is empty");
  return '0';
}

char peek() {
  if (!isEmpty()){
    return A[top];
  }
  perror("Stack is empty");
  return '0';
}

void print() {
  int i;
  for (i = 0; i <= top; i++) {
    printf("%c ", A[i]);
  }
  printf("\n");
}

int main() {
  char message[10] = {"AHMED"};
  printf("%s\n", message);
  int i;
  for (i = 0; i < strlen(message); i++) {
    push(message[i]);
  }
  for (i = 0; i < strlen(message); i++) {
    char* x = (char*)malloc(sizeof(char));
    *x = pop();
    if (strcmp(x, (char*)"0") != 0) {
      message[i] = *x;
    }
  }
  printf("%s\n", message);
  return 0;
}
