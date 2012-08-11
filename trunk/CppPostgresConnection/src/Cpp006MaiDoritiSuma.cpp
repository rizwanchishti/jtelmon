#include <iostream>
#ifndef PGETCH
	#include "portablegetch.h"
#endif
using namespace std;

int main006()
{
	int counter;
	int sum(0);
	int first;
	int last;
	int sum2(0);
	char maiDoriti[1] ;

	maiDoriti[0]='y';

	while (maiDoriti[0]=='y')
	{
		cout<<"Introduceti primul numar :";
		cin >> first;
		cout<<"Introduceti al doilea numar:";
		cin >> last;

		for(  counter = first; counter <= last; counter++ )
		 			sum=sum+counter;

	    cout<<"Suma dintre ele Prin metoda 1 este "<<sum<<endl;
		sum2=((last+first)*(last-first+1))/2;
	    cout<<"Suma dintre ele Prin metoda 2 este"<<sum2<<endl;
	    cout<<"Mai doriti y/n"<<endl;
	    maiDoriti[0]=getch();
		sum=0;
		sum2=0;
	}
	cout<<"Programul se inchde";
	getch();
	return 1;
}

