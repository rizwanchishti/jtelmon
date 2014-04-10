package org.coenraets.directory;

import java.math.BigDecimal;
import java.util.Date;


public class MaiSuntComenzi {
    // private static final long serialVersionUID = 1L;
    //@Basic(optional = false)
    //@NotNull
    //@Column(name = "IDComanda")
    private int iDComanda;
    //@Basic(optional = false)
    //@NotNull
    //@Column(name = "Anulat")
    private boolean anulat;
    //@Basic(optional = false)
    //@NotNull
    //@Column(name = "DataCrearii")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date dataCrearii;
    //@Basic(optional = false)
    //@NotNull
    //@Column(name = "DataValidareBB")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date dataValidareBB;
    //@Basic(optional = false)
    //@NotNull
    //@Column(name = "DataLivrarii")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date dataLivrarii;
    //@Basic(optional = false)
    //@NotNull
    //@Column(name = "DataSosireServer")
    //@Temporal(TemporalType.TIMESTAMP)
    private Date dataSosireServer;
    //@Basic(optional = false)
    //@NotNull
    //@Column(name = "Sincronizat")
    private boolean sincronizat;
    //@Basic(optional = false)
    //@NotNull
    //@Column(name = "Tip")
    private int tip;
    // @Max(value=?)  @Min(value=?)
    // if you know range of your decimal fields consider using these annotations to enforce field validation
    // @Basic(optional = false)
    // @NotNull
    // @Column(name = "Total")
    private BigDecimal total;
    // @Basic(optional = false)
    // @NotNull
    // @Column(name = "Valoare")
    private BigDecimal valoare;
    // @Basic(optional = false)
    // @NotNull
    // @Column(name = "ValoareTVA")
    private BigDecimal valoareTVA;
    // @Column(name = "CMDclientID")
    private Integer cMDclientID;
    // @Column(name = "UtilizatorID")
    private Integer utilizatorID;
    // @Column(name = "VizitaID")
    private Integer vizitaID;
    // @Basic(optional = false)
    // @NotNull
    // @Size(min = 1, max = 255)
    // @Column(name = "Nume")
    private String nume;
    // @Basic(optional = false)
    // @NotNull
    // @Size(min = 1, max = 50)
    // @Column(name = "NumeUtilizator")
    private String numeUtilizator;
    // @Basic(optional = false)
    // @NotNull
    // @Size(min = 1, max = 255)
    // @Column(name = "Prenume")
    private String prenume;
    // @Column(name = "ZonaID")
    private Integer zonaID;
    // @Basic(optional = false)
    // @NotNull
    // @Size(min = 1, max = 100)
    // @Column(name = "Denumire")
    private String denumire;
    // @Basic(optional = false)
    // @NotNull
    // @Size(min = 1, max = 255)
    // @Column(name = "Expr1")
    private String expr1;

    public MaiSuntComenzi() {
    }

    public int getIDComanda() {
        return iDComanda;
    }

    public void setIDComanda(int iDComanda) {
        this.iDComanda = iDComanda;
    }

    public boolean getAnulat() {
        return anulat;
    }

    public void setAnulat(boolean anulat) {
        this.anulat = anulat;
    }

    public Date getDataCrearii() {
        return dataCrearii;
    }

    public void setDataCrearii(Date dataCrearii) {
        this.dataCrearii = dataCrearii;
    }

    public Date getDataValidareBB() {
        return dataValidareBB;
    }

    public void setDataValidareBB(Date dataValidareBB) {
        this.dataValidareBB = dataValidareBB;
    }

    public Date getDataLivrarii() {
        return dataLivrarii;
    }

    public void setDataLivrarii(Date dataLivrarii) {
        this.dataLivrarii = dataLivrarii;
    }

    public Date getDataSosireServer() {
        return dataSosireServer;
    }

    public void setDataSosireServer(Date dataSosireServer) {
        this.dataSosireServer = dataSosireServer;
    }

    public boolean getSincronizat() {
        return sincronizat;
    }

    public void setSincronizat(boolean sincronizat) {
        this.sincronizat = sincronizat;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getValoare() {
        return valoare;
    }

    public void setValoare(BigDecimal valoare) {
        this.valoare = valoare;
    }

    public BigDecimal getValoareTVA() {
        return valoareTVA;
    }

    public void setValoareTVA(BigDecimal valoareTVA) {
        this.valoareTVA = valoareTVA;
    }

    public Integer getCMDclientID() {
        return cMDclientID;
    }

    public void setCMDclientID(Integer cMDclientID) {
        this.cMDclientID = cMDclientID;
    }

    public Integer getUtilizatorID() {
        return utilizatorID;
    }

    public void setUtilizatorID(Integer utilizatorID) {
        this.utilizatorID = utilizatorID;
    }

    public Integer getVizitaID() {
        return vizitaID;
    }

    public void setVizitaID(Integer vizitaID) {
        this.vizitaID = vizitaID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Integer getZonaID() {
        return zonaID;
    }

    public void setZonaID(Integer zonaID) {
        this.zonaID = zonaID;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getExpr1() {
        return expr1;
    }

    public void setExpr1(String expr1) {
        this.expr1 = expr1;
    }
    
}
