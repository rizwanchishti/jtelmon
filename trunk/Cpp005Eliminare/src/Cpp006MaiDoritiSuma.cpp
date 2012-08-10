#include <iostream>
#ifdef __unix__
	#include "portablegetch.h"
#elif _MSC_VER  || __WIN32__ || __MS_DOS__
	#include"conio.h"
#endif

// Tema : sa se verifice daca al 2-lea nr e mai mic decat primul ...
using namespace std;

int main()
{
	int counter , first , last;
	int sum1(0) , sum2(0) ;
	char maiDoriti ='y';
	while (maiDoriti=='y')
	{
		cout << "Suma numerelor cuprinse intre primul si al doilea numar"<< endl;
		cout << "Introduceti primul numar :";
		cin >> first;
		cout<< "Introduceti al doilea numar:";
		cin >> last;

		for(  counter = first; counter <= last; counter++ )
		 			sum1=sum1+counter;

	    cout << "Suma dintre ele Prin metoda 1 este " << sum1 << endl;
		sum2 = ((last+first)*(last-first+1))/2;
	    cout << "Suma dintre ele Prin metoda 2 este " << sum2 << endl;
	    cout << "Mai doriti y/n" << endl;
	    maiDoriti = getch();
	    while(maiDoriti!='y' && maiDoriti!='n'){
	    	maiDoriti = getch();
	    }
	    sum1 = 0;
		sum2 = 0;
	}
	cout << "Programul se inchde";
	getch();
	return 1;
}
