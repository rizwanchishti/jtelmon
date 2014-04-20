package org.coenraets.directory;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProdusEurobit  {
    private String stocId;
    private String simbol;
    private String denumire;
    private String categorieId;
    private String grupaId;
    private String clasaId;
    private String clasa;
    private String grupa;
    private String categorie;

    public ProdusEurobit() {
    }

    public ProdusEurobit(String stocId) {
        this.stocId = stocId;
    }

    public String getStocId() {
        return stocId;
    }

    public void setStocId(String stocId) {
        this.stocId = stocId;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(String categorieId) {
        this.categorieId = categorieId;
    }

    public String getGrupaId() {
        return grupaId;
    }

    public void setGrupaId(String grupaId) {
        this.grupaId = grupaId;
    }

    public String getClasaId() {
        return clasaId;
    }

    public void setClasaId(String clasaId) {
        this.clasaId = clasaId;
    }

    public String getClasa() {
        return clasa;
    }

    public void setClasa(String clasa) {
        this.clasa = clasa;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stocId != null ? stocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdusEurobit)) {
            return false;
        }
        ProdusEurobit other = (ProdusEurobit) object;
        if ((this.stocId == null && other.stocId != null) || (this.stocId != null && !this.stocId.equals(other.stocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "palo2013.ProduseEurobit[ stocId=" + stocId + " ]";
    }
    
}
