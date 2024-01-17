#include <stdio.h>
int main(void){
	int month, day;
	scanf("%d %d", &month, &day);
	
	int sum=0, num;
	for(int i=1; i<month; i++ ){
		switch(i){
		case 1:
			sum += 31; break;
		case 2:
			sum += 28; break;
		case 3:
			sum += 31; break;
		case 4:
			sum += 30; break;
		case 5:
			sum += 31; break;
		case 6: 
			sum += 30; break;
		case 7:
			sum += 31; break;
		case 8:
			sum += 31; break;
		case 9:
			sum += 30; break;
		case 10:
			sum += 31; break;
		case 11:
			sum += 30; break;
		}
	}
	num = sum + day;
	num = num%7;
	switch(num){
		case 1:
			printf("MON"); break;
		case 2:
			printf("TUE"); break;
		case 3:
			printf("WED"); break;
		case 4:
			printf("THU"); break;
		case 5:
			printf("FRI"); break;
		case 6:
			printf("SAT"); break;
		case 0:
			printf("SUN"); break;
	}
	return 0;
}