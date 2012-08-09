#include<iostream>
#include<string.h>
#include"conio.h"
using namespace std;

// Function headers:
double avg(double *sirdezecimale, int size);

int main(){
    // Variable Declaration
    double* sirdezecimale;
    int LungimeSirZecimale;

    // Ask the user for the array length
    cout << "Please input how many numbers you want to put in the array: ";
    cin >> LungimeSirZecimale;

    // Get the user's number input:cout << "Please input " << LungimeSirZecimale << " floating point values: ";
    sirdezecimale = new double[LungimeSirZecimale];
    for(int index=0;index<LungimeSirZecimale;index++)
        cin >> sirdezecimale[index];

    // Output the average value:
    cout << "The average value of the input is: " << avg(sirdezecimale, LungimeSirZecimale) << endl;

    // Release memory.
    delete[] sirdezecimale;

    // Done.
	getch();
    return 0;
}

// avg(double *pArr, int size):
//   PRECONDITION:
//     pArr points to a double array of length size.
//   RETURN VALUE: average value of all values in the array.
double avg(double *sirdezecimale, int size)
{
	double sum = 0.0;

	// Sum up all values.
	for ( int index = 0; index < size; index++ )
		sum += sirdezecimale[index];

	// Return sum / # of values in the array.
	return ( sum / size );
}
