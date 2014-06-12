
package net.learn2develop.PurchaseOrders;

public class CommandLine {
	private String comId;
	private String nrLinie;
	private String sDenumireProdus;
    private String nrBuc;
    private String sDiscount;
    private String sPrezenta;
    private String stocId;
    private String pretGross;
    
    
   
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

    public CommandLine( String comId , Integer nrLinie, String produs, String bucati, String discount, String stocId, String pretGross ) {
        this.sDenumireProdus = produs;
        this.nrBuc = bucati;
        this.sDiscount = discount;
        this.stocId=stocId;
        this.pretGross=pretGross;
        this.comId=comId;
        this.nrLinie=nrLinie.toString();
    }

    
    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }
    
    public String getNrLinie() {
        return nrLinie;
    }

    public void setNrLinie(String nrlinie) {
        this.nrLinie = nrlinie;
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

    public String getDenumireProdus() {
        return sDenumireProdus;
    }

    public void setDenumireProdus(String produs) {
        this.sDenumireProdus = produs;
    }

    public String getBucati() {
        return nrBuc;
    }

    public void setBucati(String bucati) {
        this.nrBuc = bucati;
    }

    public String getDiscount() {
        return sDiscount;
    }

    public void setDiscount(String usercost) {
        this.sDiscount = usercost;
    }
    
    public String getprezenta() {
        return sPrezenta;
    }

    public void setprezenta(String prezenta) {
        this.sPrezenta = prezenta;
    }

   

}
