//============================================================================
// Name        : Cpp006wxPostgres.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// https://jtelmon.googlecode.com/svn/trunk/CppPostgresConnection/src/pgDB1.cpp
//============================================================================

// #include <iostream>
#include "PostgresConnection.h"

using namespace std;


int mainC() {

	PGconn *conn = NULL;
	conn = ConnectDB();
	CreateEmployeeTable(conn);

	InsertEmployeeRec(conn, "Mario", "Hewardt");
	InsertEmployeeRec(conn, "Daniel", "Pravat");
	InsertEmployeeRec(conn, "Victor", "Ponta");
	InsertEmployeeRec(conn, "Crin", "Antonescu");

	FetchEmployeeRec(conn);
	CloseConn(conn);

return 0;
}


int main1() {

	PGconn *conn = NULL;
	conn = ConnectDB();
	FetchRecords(conn);
	CloseConn(conn);

return 0;
}
