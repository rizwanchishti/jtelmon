package org.coenraets.directory;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderData {

    private int line;

    private String client;
    
    private String product;

    private String pieces;

    private String discount;

	
	public OrderData() {

    }
	
	public int getLine() {
		return line;
	}

	public void setLine(int lineorder) {
		this.line = lineorder;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPieces() {
		return pieces;
	}

	public void setPieces(String pieces) {
		this.pieces = pieces;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discountNumber) {
		this.discount = discountNumber;
	}


}
