
package net.learn2develop.PurchaseOrders;


public class CommandLine {

  
    private String denProd;
    private String nrBuc;
    private String disc;
    private String prez;
   

    public CommandLine( String produs, String bucati, String cost, String prezenta) {
      
        this.denProd = produs;
        this.nrBuc = bucati;
        this.disc = cost;
        this.prez = prezenta;
    }

   


    public String getprodus() {
        return denProd;
    }

    public void setprodus(String produs) {
        this.denProd = produs;
    }

    public String getbucati() {
        return nrBuc;
    }

    public void setbucati(String bucati) {
        this.nrBuc = bucati;
    }

    public String getcost() {
        return disc;
    }

    public void setcost(String usercost) {
        this.disc = usercost;
    }
    
    public String getprezenta() {
        return prez;
    }

    public void setprezenta(String prezenta) {
        this.prez = prezenta;
    }

   

}
