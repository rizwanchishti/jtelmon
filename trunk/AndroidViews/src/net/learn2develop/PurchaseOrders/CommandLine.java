
package net.learn2develop.PurchaseOrders;

public class CommandLine {
    private String sDenumireProdus;
    private String nrBuc;
    private String sDiscount;
    private String sPrezenta;
    private String stocId;
    private String pretGross;
    private String nrlinie;

   
    public CommandLine( String produs, String bucati, String cost, String prezenta) {
        this.sDenumireProdus = produs;
        this.nrBuc = bucati;
        this.sDiscount = cost;
        this.sPrezenta = prezenta;
    }

    public CommandLine( String produs, String bucati, String discount, String stocId, String pretGross ) {
        this.sDenumireProdus = produs;
        this.nrBuc = bucati;
        this.sDiscount = discount;
        this.stocId=stocId;
        this.pretGross=pretGross;
    }

    public String getNrlinie() {
        return nrlinie;
    }

    public void setNrlinie(String nrlinie) {
        this.nrlinie = nrlinie;
    }

    public String getStocId() {
        return stocId;
    }

    public void setStocId(String stocId) {
        this.stocId = stocId;
    }
    
    public String getPretGross() {
        return pretGross;
    }

    public void setPretGross(String pretGross) {
        this.pretGross = pretGross;
    }

    public String getprodus() {
        return sDenumireProdus;
    }

    public void setprodus(String produs) {
        this.sDenumireProdus = produs;
    }

    public String getbucati() {
        return nrBuc;
    }

    public void setbucati(String bucati) {
        this.nrBuc = bucati;
    }

    public String getcost() {
        return sDiscount;
    }

    public void setcost(String usercost) {
        this.sDiscount = usercost;
    }
    
    public String getprezenta() {
        return sPrezenta;
    }

    public void setprezenta(String prezenta) {
        this.sPrezenta = prezenta;
    }

   

}
