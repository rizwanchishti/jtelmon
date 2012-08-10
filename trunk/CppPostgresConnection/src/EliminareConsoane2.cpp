#include<iostream>
#include<string.h>
#include"conio.h"
void mainec2()
{
 using namespace std;
  char maiDoriti[1] ;
  maiDoriti[0]='y';
while (maiDoriti[0]=='y')
				{
						char caracterele_primite[20],caracterele_ramase[20],vocale[]="aeiouAEIOU";
						char caracterul_testat;
						 int gasit_consoana=1;
						 cout<<"Introduceti va rog un cuvant: ";
						cin >> caracterele_primite;
						strcpy(caracterele_ramase,caracterele_primite);
						int index(0);
						int reindex;
							while(gasit_consoana==1)
							{
								reindex=index;
											for(index=0; index<strlen(caracterele_ramase); index++)
													{
														gasit_consoana=0;
														caracterul_testat=caracterele_ramase[index];
														// pentru a nu se intra in IF trebuie ca caracterul_testat sa fie vocala
																 if (strchr(vocale,caracterul_testat)==0) // if caracterul_testat este consoana
																		{
																			// se sterge caracterul_testat ce reprezinta o consoana
																			 strcpy(caracterele_ramase+index,caracterele_ramase+index+1);
																			 gasit_consoana=1;

																		}  // end_IF
													}      // end_FOR
							}         // end_WHILE
					cout << caracterele_ramase<<endl;
		 cout<<"Mai doriti y/n"<<endl;
				maiDoriti[0]=_getch();
				}
cout<<"Programul se inchde";
_getch();
}
