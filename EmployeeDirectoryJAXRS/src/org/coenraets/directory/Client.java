package org.coenraets.directory;
// import javax.xml.bind.annotation.XmlRootElement;
public class Client {
	    // private static final long serialVersionUID = 1L;
	    // @Id
	    // @Basic(optional = false)
	    // @NotNull
	    // @Size(min = 1, max = 9)
	    // @Column(name = "tert_id")
	    private String tertId;
	    // @Size(max = 14)
	    // @Column(name = "cui")
	    private String cui;
	    //@Size(max = 2)
	    //@Column(name = "plt")
	    private String plt;
	    //@Size(max = 2147483647)
	    //@Column(name = "client")
	    private String client;
	    //@Size(max = 35)
	    //@Column(name = "categorie")
	    private String categorie;
	    //@Size(max = 35)
	    //@Column(name = "grupa")
	    private String grupa;
	    //@Size(max = 35)
	    //@Column(name = "clasa")
	    private String clasa;
	    //@Size(max = 4)
	    //@Column(name = "categorie_id")
	    private String categorieId;
	    //@Size(max = 4)
	    //@Column(name = "grupa_id")
	    private String grupaId;
	    //@Size(max = 4)
	    //@Column(name = "clasa_id")
	    private String clasaId;

	    public Client() {
	    }

	    public Client(String tertId) {
	        this.tertId = tertId;
	    }

	    public String getTertId() {
	        return tertId;
	    }

	    public void setTertId(String tertId) {
	        this.tertId = tertId;
	    }

	    public String getCui() {
	        return cui;
	    }

	    public void setCui(String cui) {
	        this.cui = cui;
	    }

	    public String getPlt() {
	        return plt;
	    }

	    public void setPlt(String plt) {
	        this.plt = plt;
	    }

	    public String getClient() {
	        return client;
	    }

	    public void setClient(String client) {
	        this.client = client;
	    }

	    public String getCategorie() {
	        return categorie;
	    }

	    public void setCategorie(String categorie) {
	        this.categorie = categorie;
	    }

	    public String getGrupa() {
	        return grupa;
	    }

	    public void setGrupa(String grupa) {
	        this.grupa = grupa;
	    }

	    public String getClasa() {
	        return clasa;
	    }

	    public void setClasa(String clasa) {
	        this.clasa = clasa;
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

	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (tertId != null ? tertId.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof Client)) {
	            return false;
	        }
	        Client other = (Client) object;
	        if ((this.tertId == null && other.tertId != null) || (this.tertId != null && !this.tertId.equals(other.tertId))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "softy.orders.ClientiEurobit[ tertId=" + tertId + " ]";
	    }
	    
	}
