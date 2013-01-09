/*
 *
 *   21 May 2009 ;
 *   http://jtelmon.googlecode.com/svn/OlapTest7b/src/TransferTest1/testTransfer001.java
 *   http://code.google.com/p/jtelmon/source/browse/OlapTest7b/src/TransferTest1/testTransfer001.java
 *
*/

package palo2013;

import java.sql.*;

public class testTransfer001 {

    protected static void testConnection(){

String url_sursa = "jdbc:postgresql://192.168.1.200:5432/pangram_main_2008";
String username_sursa = "postgres";
String password_sursa = "telinit";
String url_destinatie = "jdbc:postgresql://192.168.1.200:5432/pangram_warehouse_2009";
String username_destinatie = "postgres";
String password_destinatie = "telinit";

try {
    Class.forName("org.postgresql.Driver");
    System.out.println("Driver PostgreSQL OK");
} catch (Exception e) {
    System.err.println("Failed to load Postgres Driver");
}

java.sql.Connection conn_sursa = null;

java.sql.Connection conn_destinatie = null;
Statement stmt_sursa = null;
Statement stmt_destinatie = null;
// http://jdbc.postgresql.org/documentation/83/query.html#query-with-cursor
// http://www.nabble.com/java.sql.Statement-generates-java.lang.OutOfMemoryError-in-big-tabe-td21568763.html
try {
    conn_sursa = DriverManager.getConnection(url_sursa,username_sursa,password_sursa);
    // make sure autocommit is off
    conn_sursa.setAutoCommit(false);
    stmt_sursa = conn_sursa.createStatement();

    // Turn use of the cursor on.
    stmt_sursa.setFetchSize(50);
    conn_destinatie = DriverManager.getConnection(url_destinatie,username_destinatie,password_destinatie);
    stmt_destinatie = conn_destinatie.createStatement();
    System.out.println("Connection to source PostgreSQL OK");
} catch (Exception e) {
    System.err.println("Connection to Postgres failed");
}

try {


String SQL_sursa = " SELECT F.FACT_ID,F.NRDOC,F.DATA_F,f.tiparit,f.verstor,F.VALOARE_VN,F.VAL_DISC_INCL,F.VAL_DISC_EXPL,F.VAL_TVA_C_NOR,F.TERT_ID,F.NRLC_ID,F.GESTIUNE_ID," +
        "G.DENUMIRE AS GEST,T.DENUMIRE AS CLIENT,nl.NICK AS AGENT," +
        "C.STOC_ID,N.SIMBOL,N.DENUMIRE AS PRODUS,C.CANTITATE,C.PRET_VANZARE,C.PR_DISC_INCL,C.PR_DISC_EXPL,ROUND(C.CANTITATE*N.MASA,2)::NUMERIC(11,2) AS MASA,CAP.DENUMIRE AS CATEG_PRODUS," +
        "CAT.DENUMIRE AS CATEG_TERT,CAL.DENUMIRE AS CATEG_AGENT,GRP.DENUMIRE AS GRUPA_PRODUS,GRT.DENUMIRE AS GRUPA_TERT,GRL.DENUMIRE AS GRUPA_AGENT,CLP.DENUMIRE AS CLASA_PRODUS,CLT.DENUMIRE AS CLASA_TERT,CLL.DENUMIRE AS CLASA_AGENT," +
        "S.DENLOC AS LOC,J.DENJ AS JUDET " +
        "FROM FACTURI_V_C F INNER JOIN GESTIUNI G ON F.GESTIUNE_ID=G.GEST_ID INNER JOIN VTERTI T ON F.TERT_ID=T.TERT_ID INNER JOIN SIRUTA S ON T.SIRUTA=S.SIRUTA " +
        "INNER JOIN JUD J ON S.JUD=J.JUD INNER JOIN NUMERE_LUCRU NL ON F.NRLC_ID=NL.NRLC_ID INNER JOIN FACTURI_CV_C C ON F.FACT_ID=C.FACT_ID INNER JOIN NOMSTOC N ON C.STOC_ID=N.STOC_ID " +
        "INNER JOIN CLASA CLP ON N.CLASA_ID=CLP.CLASA_ID AND CLP.FISIER='nomstoc' INNER JOIN CLASA CLT ON T.CLASA_ID=CLT.CLASA_ID AND CLT.FISIER='terti' inner join clasa cll on nl.clasa_id=cll.clasa_id and cll.fisier='numere_lucru' " +
        "inner join grupa grp on n.grupa_id=grp.grupa_id and grp.fisier='nomstoc' inner join grupa grt on t.grupa_ID=grt.grupa_id and grt.fisier='terti' inner join grupa grl on nl.grupa_id=grl.grupa_id and grl.fisier='numere_lucru' " +
        "inner join categorie cap on n.categorie_id=cap.categ_id and cap.fisier='nomstoc' inner join categorie cat on t.categorie_id=cat.categ_id and cat.fisier='terti' inner join categorie cal " +
        "on nl.categorie_id=cal.categ_id and cal.fisier='numere_lucru' where f.data_f>'2009-03-31'";

ResultSet rs_sursa = null;
rs_sursa = stmt_sursa.executeQuery(SQL_sursa);
String SQL_destinatie = "";
String illegal_char = "'";
String legal_char = "`";
    while (rs_sursa.next()) {
        String mS_fact_id = rs_sursa.getString("fact_id");
        String mS_nrdoc = rs_sursa.getString("nrdoc");
        Date mD_data_f = rs_sursa.getDate("data_f");
        String mS_data_f = mD_data_f.toString();
        String mS_tiparit = rs_sursa.getString("tiparit");
        double md_verstor = rs_sursa.getDouble("verstor");
        double md_valoare_vn = rs_sursa.getDouble("valoare_vn");
        double md_val_disc_incl = rs_sursa.getDouble("val_disc_incl");
        double md_val_disc_expl = rs_sursa.getDouble("val_disc_expl");
        double md_val_tva_c_nor = rs_sursa.getDouble("val_tva_c_nor");
        String mS_tert_id = rs_sursa.getString("tert_id");
        String mS_nrlc_id = rs_sursa.getString("nrlc_id");
        String mS_gestiune_id = rs_sursa.getString("gestiune_id");
        String mS_gest = rs_sursa.getString("gest");
        String mS_client = rs_sursa.getString("client");
        String mS_agent = rs_sursa.getString("agent");
        String mS_stoc_id = rs_sursa.getString("stoc_id");
        String mS_simbol = rs_sursa.getString("simbol");
        String mS_produs = rs_sursa.getString("produs");
        double md_cantitate = rs_sursa.getDouble("cantitate");
        double md_pret_vanzare = rs_sursa.getDouble("pret_vanzare");
        double md_pr_disc_incl = rs_sursa.getDouble("pr_disc_incl");
        double md_pr_disc_expl = rs_sursa.getDouble("pr_disc_expl");
        double md_masa = rs_sursa.getDouble("masa");
        String mS_categ_produs = rs_sursa.getString("categ_produs");
        String mS_categ_tert = rs_sursa.getString("categ_tert");
        String mS_categ_agent = rs_sursa.getString("categ_agent");
        String mS_grupa_produs = rs_sursa.getString("grupa_produs");
        String mS_grupa_tert = rs_sursa.getString("grupa_tert");
        String mS_grupa_agent = rs_sursa.getString("grupa_agent");
        String mS_clasa_produs = rs_sursa.getString("clasa_produs");
        String mS_clasa_tert = rs_sursa.getString("clasa_tert");
        String mS_clasa_agent = rs_sursa.getString("clasa_agent");
        String mS_loc = rs_sursa.getString("loc");
        String mS_judet = rs_sursa.getString("judet");
        //String mS_denmat = rs_sursa.getString("denmat");
        mS_client = mS_client.replaceAll(illegal_char, legal_char);
        mS_produs = mS_produs.replaceAll(illegal_char, legal_char);
        //System.out.println(mS_denmat);
          SQL_destinatie = "insert into sales_details(fact_id,nrdoc, data_f, " +
                "tiparit, verstor, valoare_vn, val_disc_incl, val_disc_expl, val_tva_c_nor, " +
                "tert_id,nrlc_id,gestiune_id,gest, client, agent, stoc_id, simbol, produs, " +
                " cantitate, pret_vanzare, pr_disc_incl, pr_disc_expl, masa," +
                " categ_produs, categ_tert, categ_agent, " +
                " grupa_produs, grupa_tert, grupa_agent, " +
                " clasa_produs, clasa_tert, clasa_agent, " +
                " loc, judet) values "+
                "('" +mS_fact_id+"', '"  +mS_nrdoc+ "', '" +mS_data_f+ "' , '" +mS_tiparit +
                "'," +md_verstor+ "," + md_valoare_vn + "," + md_val_disc_incl + "," +
                md_val_disc_expl + "," + md_val_tva_c_nor +
                ",'" + mS_tert_id + "', '"  + mS_nrlc_id  + "', '"  + mS_gestiune_id + "', '"  + mS_gest + "', '"  + 
                mS_client + "', '"  + mS_agent + "', '"  + mS_stoc_id + "', '"  + mS_simbol + "', '"  + mS_produs + "', " +
                md_cantitate + "," + md_pret_vanzare + "," + md_pr_disc_incl + "," + md_pr_disc_expl + "," + md_masa + 
                ",'" + mS_categ_produs + "', '"  + mS_categ_tert  + "', '"  + mS_categ_agent +
                "','" + mS_grupa_produs + "', '"  + mS_grupa_tert  + "', '"  + mS_grupa_agent +
                "','" + mS_clasa_produs + "', '"  + mS_clasa_tert  + "', '"  + mS_clasa_agent +
                "','" + mS_loc + "', '"  + mS_judet  + "')";

        System.out.println(SQL_destinatie);
        stmt_destinatie.execute(SQL_destinatie);
    }
    System.out.println("Transfer OK");
    rs_sursa.close();
    stmt_sursa.close();
    conn_sursa.close();
    stmt_destinatie.close();
    conn_destinatie.close();
}

catch (Exception e) {
    System.err.println("An error has occurred during transfer");
    System.err.println(e);
}

}


    public static void main(String[] args) {

    testConnection();

}

}

/*CREATE DATABASE pangram_warehouse_2009
  WITH OWNER = postgres
       ENCODING = 'SQL_ASCII';

-- Table: sales_details
-- DROP TABLE sales_details;

CREATE TABLE sales_details
(
  fact_id character(10),
  nrdoc character(16),
  data_f date,
  tiparit character(1),
  verstor numeric(1),
  valoare_vn numeric(13,2),
  val_disc_incl numeric(12,2),
  val_disc_expl numeric(12,2),
  val_tva_c_nor numeric(12,2),
  tert_id character(9),
  nrlc_id character(7),
  gestiune_id character(7),
  gest character(30),
  client character(90),
  agent character(10),
  stoc_id character(10),
  simbol character(13),
  produs character(45),
  cantitate numeric(10,3),
  pret_vanzare numeric(12,2),
  pr_disc_incl numeric(6,2),
  pr_disc_expl numeric(6,2),
  masa numeric(11,2),
  categ_produs character(35),
  categ_tert character(35),
  categ_agent character(35),
  grupa_produs character(35),
  grupa_tert character(35),
  grupa_agent character(35),
  clasa_produs character(35),
  clasa_tert character(35),
  clasa_agent character(35),
  loc character(40),
  judet character(20)
)
WITH (OIDS=FALSE);
ALTER TABLE sales_details OWNER TO postgres;

*/

