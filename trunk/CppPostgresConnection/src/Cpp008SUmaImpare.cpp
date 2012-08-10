#include <iostream>
using namespace std;

int main008()
{
	int first(0);
	int last(0);
	int counter(1);
	int sum(0);
	cout<<"Primu numar este: ";
		cin>>first;
	cout<<"Al doilea numar este: ";
		cin>> last;

		for(counter=first; counter<=last ; counter=counter+2)
		{
			sum=sum+counter;
		}
		cout<<"Suma numerelor impare este:"<<sum<<endl;

		// system("pause");
		return 0;
}
