/*
 *
 *   21 May 2009 ;
 *   http://jtelmon.googlecode.com/svn/OlapTest7b/src/TransferTest1/testTransfer001.java
 *   http://code.google.com/p/jtelmon/source/browse/OlapTest7b/src/TransferTest1/testTransfer001.java
 *
*/

package palo2013;

import java.sql.*;

public class testTransfer002 {

    protected static void testConnection(){

String url_sursa = "jdbc:postgresql://192.168.61.207:5432/pangram_main_2008";
String username_sursa = "postgres";
String password_sursa = "telinit";
String url_destinatie = "jdbc:postgresql://192.168.6.207:5432/pangram_warehouse_2013";
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


String SQL_sursa=" select * from vanzfull";

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

-- Table: vanzfull

-- DROP TABLE vanzfull;

CREATE TABLE vanzfull
(
  doc character(35),
  nrdoc character(16),
  data date,
  depozit character(30),
  filiala character(35),
  agent character(40),
  cui character(14),
  plt character(2),
  client bpchar,
  simbol character(13),
  produs character(45),
  cant numeric(12,2),
  masa numeric(12,4),
  pret numeric(12,2),
  pr_disc_incl numeric(6,2),
  valnet numeric(12,2),
  pret_lista numeric(12,2),
  valbrut numeric(12,2),
  clasat character(35),
  grupat character(35),
  clasap character(35),
  grupap character(35),
  loc character(45),
  jud bpchar,
  zi double precision,
  luna double precision,
  an double precision,
  trimestru double precision
)
WITH (
  OIDS=FALSE
);
ALTER TABLE vanzfull OWNER TO postgres;

*/

