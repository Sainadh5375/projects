#include<stdio.h>
#include<stdlib.h>

#define MAX 10
int c=0;

typedef struct stack {
int items[MAX];
int top;	
}st;

void create(st *s){
	s->top=-1;
}

int isempty(st *s){
	if(s->top==-1){
		return 1;
	}
	else{
		return 0;
	}
}

int isfull(st *s){
	if(s->top==MAX-1){
		return 1;
	}
	else{
		return 0;
	}
}

void push(st *s,int newitem){
	if(isfull(s)){
		printf("Stack is Full\n");
	}	
	else{
		s->top++;
		s->items[s->top]=newitem;
	}
	c++;
}

void pop(st *s){
	if(isempty(s)){
		printf("Stack is empty");
	}
	else{
		printf("Item Popped %d\n",s->items[s->top]);
		s->top--;
	}
	c--;
}

void print(st *s){
	for(int i=0;i<c;i++){
		printf("%d",s->items[i]);
	}
	printf("\n");
}

int main()
{
	st *s=(st *)malloc(sizeof(st));
	create(s);
	push(s,1);
	push(s,2);
	push(s,3);
	push(s,4);
	
	print(s);
	pop(s);
	
	print(s);
}