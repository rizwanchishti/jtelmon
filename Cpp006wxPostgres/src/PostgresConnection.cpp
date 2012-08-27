
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include "libpq-fe.h"
#include <string>
#include <string.h>

using namespace std;

void CloseConn(PGconn *conn)
{
    PQfinish(conn);
    // getchar();
    // exit(1);
}

PGconn *ConnectSalesDB()
{
  PGconn *conn = NULL;

  // Make a connection to the database
  //conn = PQconnectdb("user=postgres password=telinit dbname=SoftySales hostaddr=127.0.0.1 port=5432");
  conn = PQconnectdb("user=postgres password=telinit dbname=SoftySales hostaddr=192.168.61.207 port=5432");

  // Check to see that the backend connection was successfully made
    if (PQstatus(conn) != CONNECTION_OK)
    {
        printf("Connection to database failed");
        CloseConn(conn);
    }

  printf("Connection to database - OK\n");

  return conn;
}


PGconn *ConnectDB()
{
  PGconn *conn = NULL;

  // Make a connection to the database
  conn = PQconnectdb("user=postgres password=telinit dbname=postgres hostaddr=127.0.0.1 port=5432");

  // Check to see that the backend connection was successfully made
    if (PQstatus(conn) != CONNECTION_OK)
    {
        printf("Connection to database failed");
        CloseConn(conn);
    }

  printf("Connection to database - OK\n");

  return conn;
}


/* Create employee table */
void CreateEmployeeTable(PGconn *conn)
{
  // Execute with sql statement
    PGresult *res = PQexec(conn, "CREATE TABLE employee (Fname char(30), Lname char(30))");

  if (PQresultStatus(res) != PGRES_COMMAND_OK)
    {
        printf("Create employee table failed");
        PQclear(res);
        CloseConn(conn);
    }

  printf("Create employee table - OK\n");

  // Clear result
  PQclear(res);
}

/* Append SQL statement and insert record into employee table */
void InsertEmployeeRec(PGconn *conn, char * fname, char * lname)
{
  // Append the SQL statment
  std::string sSQL;
  sSQL.append("INSERT INTO employee VALUES ('");
  sSQL.append(fname);
  sSQL.append("', '");
  sSQL.append(lname);
  sSQL.append("')");

  // Execute with sql statement
  PGresult *res = PQexec(conn, sSQL.c_str());

    if (PQresultStatus(res) != PGRES_COMMAND_OK)
    {
        printf("Insert employee record failed");
        PQclear(res);
        CloseConn(conn);
    }

  printf("Insert employee record - OK\n");

  // Clear result
  PQclear(res);
}



/* Fetch employee record and display it on screen */
const char* FetchRecords(PGconn *conn)
{
  // Will hold the number of field in employee table
  int nFields;
  // char * temp;
  char * temp1;
  char * temp2;
  const char * temp3;
  std::string sTemp;

  temp2 = (char *) malloc(100);

  // Start a transaction block
  PGresult *res  = PQexec(conn, "BEGIN");

    if (PQresultStatus(res) != PGRES_COMMAND_OK)
    {
        printf("BEGIN command failed");
        PQclear(res);
        CloseConn(conn);
    }

   // Clear result
    PQclear(res);

    // Fetch rows from employee table
    res = PQexec(conn, "DECLARE emprec CURSOR FOR select * from softyorders2");
    if (PQresultStatus(res) != PGRES_COMMAND_OK)
    {
        printf("DECLARE CURSOR failed");
        PQclear(res);
        CloseConn(conn);
    }

  // Clear result
    PQclear(res);

    res = PQexec(conn, "FETCH ALL in emprec");

    if (PQresultStatus(res) != PGRES_TUPLES_OK)
    {
        printf("FETCH ALL failed");
        PQclear(res);
        CloseConn(conn);
    }
    nFields = PQnfields(res);

  printf("\nFetch employee record:");
  printf("\n********************************************************************\n");
    for (int i = 0; i < nFields; i++)
    {
    	// temp=PQfname(res, i);
    	temp1=PQfname(res, i);
    	printf("%-50s", temp1 );
    	sprintf(temp2 ,"%-50s" ,  temp1);
    	sTemp.append(temp2);

    }
    printf("\n********************************************************************\n");
    sTemp.append("\n********************************************************************\n");
    for (int i = 0; i < PQntuples(res); i++)
    {
        for (int j = 0; j < nFields; j++){
        	temp1=PQgetvalue(res, i, j);
        	printf("%-50s", temp1);
        	sprintf(temp2 ,"%-50s" ,  temp1);
        	sTemp.append(temp2);
        }
        printf("\n");
    }

    PQclear(res);

    // Close the emprec
    res = PQexec(conn, "CLOSE emprec");
    PQclear(res);

    // End the transaction
    res = PQexec(conn, "END");

  // Clear result
    PQclear(res);
    // return ctemp;

    temp3 = sTemp.c_str();
    return temp3;


}


/* Fetch employee record and display it on screen */
const char* FetchEmployeeRec(PGconn *conn)
{
  // Will hold the number of field in employee table
  int nFields;
  const char * temp3;
  char * temp1;
  char * temp2;
  std::string sTemp;
  std::string sTemp2;

  char *str1 ;

  // malloc <stdlib.h>
  str1  = (char *) malloc(100);
  temp2 = (char *) malloc(100);

  // Start a transaction block
  PGresult *res  = PQexec(conn, "BEGIN");

    if (PQresultStatus(res) != PGRES_COMMAND_OK)
    {
        printf("BEGIN command failed");
        PQclear(res);
        CloseConn(conn);
    }

   // Clear result
    PQclear(res);

    // Fetch rows from employee table
    res = PQexec(conn, "DECLARE emprec CURSOR FOR select * from employee");
    if (PQresultStatus(res) != PGRES_COMMAND_OK)
    {
        printf("DECLARE CURSOR failed");
        PQclear(res);
        CloseConn(conn);
    }

  // Clear result
    PQclear(res);

    res = PQexec(conn, "FETCH ALL in emprec");

    if (PQresultStatus(res) != PGRES_TUPLES_OK)
    {
        printf("FETCH ALL failed");
        PQclear(res);
        CloseConn(conn);
    }

    // Get the field name
    nFields = PQnfields(res);

    // http://en.allexperts.com/q/C-1587/passing-arg-2-sprintf.htm


    // char mystr[] = "MY STRING";
    // sprintf(str1, " %s ", mystr);


  // Prepare the header with employee table field name
  printf("\nFetch employee record:");
  printf("\n********************************************************************\n");
    for (int i = 0; i < nFields; i++)
    {
    	temp1=PQfname(res, i);
    	printf("%-30s", temp1 );
    	// sTemp2 = str(format("%-30s"), temp1 );

    	// dupa malloc trebuie FREE_MEM !!!
    	sprintf(temp2 ,"%-30s" ,  temp1);
    	// sprintf(str1 ,"%-30s" ,  mystr);

    	// sprintf (sTemp2.begin().c_str, "%-30s", temp1);
    	// sprintf (sTemp2.c_str(), "%-30s", temp1);

    	// sprinf(buffer,"Hello test %d %s",someint,somestring);
    	// s = str( format(" %d %d ") % 11 % 22 );

    	// temp2=sprintf("%-30s", temp1 );

    	// printf("%-30s", PQfname(res, i));
    	// strcpy(ctemp,temp);
    	// ctemp+=temp;
    	// strcat (ctemp,temp);
        sTemp.append(temp2);
        // sTemp.append(str1);

    }
          printf("\n********************************************************************\n");
    sTemp.append("\n********************************************************************\n");
    // Next, print out the employee record for each row
    for (int i = 0; i < PQntuples(res); i++)
    {
        for (int j = 0; j < nFields; j++){
        	temp1=PQgetvalue(res, i, j);
        	printf("%-30s", temp1);
        	sprintf(temp2 ,"%-30s" ,  temp1);
            // printf("%-30s", PQgetvalue(res, i, j));
        	// strcpy(ctemp,temp);
        	sTemp.append(temp2);
        }
        printf("\n");
        sTemp.append("\n");
    }

    PQclear(res);

    // Close the emprec
    res = PQexec(conn, "CLOSE emprec");
    PQclear(res);

    // End the transaction
    res = PQexec(conn, "END");

  // Clear result
    PQclear(res);
    temp3 = sTemp.c_str();
    return temp3;
}



/* Erase all record in employee table */
void RemoveAllEmployeeRec(PGconn *conn)
{
  // Execute with sql statement
    PGresult *res = PQexec(conn, "DELETE FROM employee");

    if (PQresultStatus(res) != PGRES_COMMAND_OK)
    {
        printf("Delete employees record failed.");
        PQclear(res);
        CloseConn(conn);
    }

  printf("\nDelete employees record - OK\n");

  // Clear result
  PQclear(res);
}


/* Drop employee table from the database*/
void DropEmployeeTable(PGconn *conn)
{
  // Execute with sql statement
    PGresult *res = PQexec(conn, "DROP TABLE employee");

    if (PQresultStatus(res) != PGRES_COMMAND_OK)
    {
        printf("Drop employee table failed.");
        PQclear(res);
        CloseConn(conn);
    }

  printf("Drop employee table - OK\n");

  // Clear result
  PQclear(res);
}


// #define WORD "this is a test"
int main2(){
PGconn     *conn = NULL;

  conn = ConnectDB();
  // CreateEmployeeTable(conn);
  // InsertEmployeeRec(conn, "Mario", "Hewardt");
  // InsertEmployeeRec(conn, "Daniel", "Pravat");
  // InsertEmployeeRec(conn, "Victor", "Ponta");
  // InsertEmployeeRec(conn, "Crin", "Antonescu");
  FetchRecords(conn);

  // printf("\nPress ENTER to remove all records & table.....\n");
  // getchar();

  // RemoveAllEmployeeRec(conn);
  // DropEmployeeTable(conn);

  CloseConn(conn);

  return 0;

}


