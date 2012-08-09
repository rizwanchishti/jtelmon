/*
 *  EliminareConsoane.cpp
 *  http://informaticasite.ro/probleme-rezolvate-c++/siruri-de-caractere/eliminare-consoane-din-sir.html
 *  Created on: Aug 8, 2012
 */

#include<iostream>
#include<string.h>
// #include"conio.h"
// #include <ncurses.h>
#include "portablegetch.h"

int main()
{
 using namespace std;
 char caracterele_primite[20],caracterele_ramase[20],vocale[]="aeiouAEIOU";
 char caracterul_testat;
 int gasit_consoana=1;
 cin >> caracterele_primite;
 strcpy(caracterele_ramase,caracterele_primite);
 int index(0);
 int reindex;
 while(gasit_consoana==1){
    reindex=index;
    for(index=0; index<strlen(caracterele_ramase); index++)
    {
        gasit_consoana=0;
        caracterul_testat=caracterele_ramase[index];
        // pentru a nu se intra in IF trebuie ca s[i] sa fie vocala
        if (strchr(vocale,caracterul_testat)==0) // if caracterul_testat este consoana
        {
                // se sterge caracterul_testat ce reprezinta o consoana
                strcpy(caracterele_ramase+index,caracterele_ramase+index+1);
                gasit_consoana=1;
                // gasit = 1 daca s[i] consoana
                // daca s[i] este vocala , nu se intra in IF
        }  // end_IF
    }      // end_FOR
 }         // end_WHILE
cout << caracterele_ramase;

// getch();
// getch()->#include"conio.h"->Windows
// include_portablegetch.h
// getchar()->#include<ncurses.h>->Linux

getch();
return 1;
}
