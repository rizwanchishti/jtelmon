package net.learn2develop.PurchaseOrders;

import java.math.BigDecimal;
import java.util.Date;

public class HeaderComenziVext {
    private String comId;
    private String nrdoc;
    private Date dataC;
    private String gestiuneId;
    private String nrlcId;
    private String tertId;
    private Date dataL;
    private Character facturat;
    private String userId;
    private Date operare;
    private Short verstor;
    private Integer nivacc;
    private Character tiparit;
    private Integer zscadenta;
    private String nrfact;
    private Date dataF;
    private BigDecimal valoare;
    private BigDecimal valDiscExpl;
    private BigDecimal prDiscExpl;
    private Date creare;
    private Integer nrop;
    private String idExt;
    private String msgId;

    public void ComenziVExt() {
    	// this.valoare=0;
    }

    public void ComenziVExt(String comId) {
        this.comId = comId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getNrdoc() {
        return nrdoc;
    }

    public void setNrdoc(String nrdoc) {
        this.nrdoc = nrdoc;
    }

    public Date getDataC() {
        return dataC;
    }

    public void setDataC(Date dataC) {
        this.dataC = dataC;
    }

    public String getGestiuneId() {
        return gestiuneId;
    }

    public void setGestiuneId(String gestiuneId) {
        this.gestiuneId = gestiuneId;
    }

    public String getNrlcId() {
        return nrlcId;
    }

    public void setNrlcId(String nrlcId) {
        this.nrlcId = nrlcId;
    }

    public String getTertId() {
        return tertId;
    }

    public void setTertId(String tertId) {
        this.tertId = tertId;
    }

    public Date getDataL() {
        return dataL;
    }

    public void setDataL(Date dataL) {
        this.dataL = dataL;
    }

    public Character getFacturat() {
        return facturat;
    }

    public void setFacturat(Character facturat) {
        this.facturat = facturat;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOperare() {
        return operare;
    }

    public void setOperare(Date operare) {
        this.operare = operare;
    }

    public Short getVerstor() {
        return verstor;
    }

    public void setVerstor(Short verstor) {
        this.verstor = verstor;
    }

    public Integer getNivacc() {
        return nivacc;
    }

    public void setNivacc(Integer nivacc) {
        this.nivacc = nivacc;
    }

    public Character getTiparit() {
        return tiparit;
    }

    public void setTiparit(Character tiparit) {
        this.tiparit = tiparit;
    }

    public Integer getZscadenta() {
        return zscadenta;
    }

    public void setZscadenta(Integer zscadenta) {
        this.zscadenta = zscadenta;
    }

    public String getNrfact() {
        return nrfact;
    }

    public void setNrfact(String nrfact) {
        this.nrfact = nrfact;
    }

    public Date getDataF() {
        return dataF;
    }

    public void setDataF(Date dataF) {
        this.dataF = dataF;
    }

    public BigDecimal getValoare() {
        return valoare;
    }

    public void setValoare(BigDecimal valoare) {
        this.valoare = valoare;
    }

    public BigDecimal getValDiscExpl() {
        return valDiscExpl;
    }

    public void setValDiscExpl(BigDecimal valDiscExpl) {
        this.valDiscExpl = valDiscExpl;
    }

    public BigDecimal getPrDiscExpl() {
        return prDiscExpl;
    }

    public void setPrDiscExpl(BigDecimal prDiscExpl) {
        this.prDiscExpl = prDiscExpl;
    }

    public Date getCreare() {
        return creare;
    }

    public void setCreare(Date creare) {
        this.creare = creare;
    }

    public Integer getNrop() {
        return nrop;
    }

    public void setNrop(Integer nrop) {
        this.nrop = nrop;
    }

    public String getIdExt() {
        return idExt;
    }

    public void setIdExt(String idExt) {
        this.idExt = idExt;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

}
