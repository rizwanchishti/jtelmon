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
void CloseConn(PGconn *conn);
void FetchRecords(PGconn *conn);

#endif /* POSTGRESCONNECTION_H_ */
