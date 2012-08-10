#include <iostream>
#ifdef __unix__
	#include "portablegetch.h"
#elif _MSC_VER  || __WIN32__ || __MS_DOS__
	#include"conio.h"
#endif

using namespace std;

int main()
{
	int counter;
	int sum(0);
	int first;
	int last;
	int sum2(0);
	char maiDoriti[1] ;
	char mD ;
	mD='y';
	while (mD=='y')
	{
		cout << "Introduceti primul numar :";
		cin >> first;
		cout<< "Introduceti al doilea numar:";
		cin >> last;

		for(  counter = first; counter <= last; counter++ )
		 			sum=sum+counter;

	    cout << "Suma dintre ele Prin metoda 1 este " << sum << endl;
		sum2 = ((last+first)*(last-first+1))/2;
	    cout << "Suma dintre ele Prin metoda 2 este " << sum2 << endl;
	    cout << "Mai doriti y/n" << endl;
	    mD = getch();
	    while(mD!='y' && mD!='n'){
	    	mD = getch();
	    }
	    sum  = 0;
		sum2 = 0;
	}
	cout << "Programul se inchde";
	getch();
	return 1;
}
