#include <iostream>
int main003()
{
     using  namespace std;
            int a;
            int S(1);
            int count(0);
            int number(1);
     for(a=0; a<10 ; a++)
         {
                count++;
                number++;
                cout << "Suma tuturor numerelor de pana acum este "<< S <<endl;
         S=S+number;
         }
         // system("pause");
     return 0;
}
