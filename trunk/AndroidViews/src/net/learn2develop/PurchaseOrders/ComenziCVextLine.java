package net.learn2develop.PurchaseOrders;

import java.math.BigDecimal;

public class ComenziCVextLine  {

    private String comId;
    private String nrlinie;
    private String stocId;
    private String contGest;
    private BigDecimal cantitate;
    private BigDecimal cantitater;
    private BigDecimal livrat;
    private BigDecimal pretVanzare;
    private BigDecimal prDiscIncl;
    private BigDecimal discContr;
    private BigDecimal discCom;
    private BigDecimal pretGross;

    public ComenziCVextLine() {
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
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

    public String getContGest() {
        return contGest;
    }

    public void setContGest(String contGest) {
        this.contGest = contGest;
    }

    public BigDecimal getCantitate() {
        return cantitate;
    }

    public void setCantitate(BigDecimal cantitate) {
        this.cantitate = cantitate;
    }

    public BigDecimal getCantitater() {
        return cantitater;
    }

    public void setCantitater(BigDecimal cantitater) {
        this.cantitater = cantitater;
    }

    public BigDecimal getLivrat() {
        return livrat;
    }

    public void setLivrat(BigDecimal livrat) {
        this.livrat = livrat;
    }

    public BigDecimal getPretVanzare() {
        return pretVanzare;
    }

    public void setPretVanzare(BigDecimal pretVanzare) {
        this.pretVanzare = pretVanzare;
    }

    public BigDecimal getPrDiscIncl() {
        return prDiscIncl;
    }

    public void setPrDiscIncl(BigDecimal prDiscIncl) {
        this.prDiscIncl = prDiscIncl;
    }

    public BigDecimal getDiscContr() {
        return discContr;
    }

    public void setDiscContr(BigDecimal discContr) {
        this.discContr = discContr;
    }

    public BigDecimal getDiscCom() {
        return discCom;
    }

    public void setDiscCom(BigDecimal discCom) {
        this.discCom = discCom;
    }

    public BigDecimal getPretGross() {
        return pretGross;
    }

    public void setPretGross(BigDecimal pretGross) {
        this.pretGross = pretGross;
    }
}
