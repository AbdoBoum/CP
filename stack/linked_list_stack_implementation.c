#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "stdbool.h"

struct stack {
  int val;
  struct stack* next;
}stack;

struct stack* head = NULL;

void push(int x) {
  struct stack* new = (struct stack*)
          malloc(sizeof(struct stack*));
  new->val = x;
  new->next = head;
  head = new;
  printf("Element pushed: %d\n", x);
}

bool isEmpty() {
  if (head == NULL) {
    return true;
  }
  return false;
}

int pop() {
  struct stack* temp;
  int x;
  if (isEmpty()) {
    perror("Stack is empty");
    return -1;
  }
  temp = head;
  x = temp->val;
  head = head->next;
  free(temp);
  return x;
}

int peek() {
  if (isEmpty()) {
    perror("Stack is empty");
    return -1;
  }
  int x = head->val;
  return x;
}

void print() {
  struct stack* temp;
  temp = head;
  if (isEmpty()) {
    perror("Stack is empty");
  }
  while (temp != NULL) {
    printf("%d ", temp->val);
    temp = temp->next;
  }
  printf("\n");
}

int main() {
  push(2); push(3); push(1); push(7);
  print();
  printf("%d\n", peek());
  return 0;
}
