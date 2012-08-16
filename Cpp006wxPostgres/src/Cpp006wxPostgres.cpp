//============================================================================
// Name        : Cpp006wxPostgres.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
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
