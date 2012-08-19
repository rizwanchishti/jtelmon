/*
 * PostgresConnection.h
 *
 *  Created on: Aug 16, 2012
 *      Author: depit
 */

#ifndef POSTGRESCONNECTION_H_
#define POSTGRESCONNECTION_H_

#include "libpq-fe.h"
PGconn *ConnectDB();
void CreateEmployeeTable(PGconn *conn);
void InsertEmployeeRec(PGconn*, char*, char *);
void CloseConn(PGconn *conn);
char* FetchRecords(PGconn *conn);
char* FetchEmployeeRec(PGconn *);

#endif /* POSTGRESCONNECTION_H_ */

