//============================================================================
// Name        : Cpp006wxPostgres.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// https://jtelmon.googlecode.com/svn/trunk/CppPostgresConnection/src/pgDB1.cpp
//============================================================================

#include <iostream>
#include "PostgresConnection.h"

using namespace std;

int main() {

	PGconn *conn = NULL;
	conn = ConnectDB();
	FetchRecords(conn);
	CloseConn(conn);

return 0;
}
