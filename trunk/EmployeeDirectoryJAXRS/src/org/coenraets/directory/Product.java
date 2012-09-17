
/*
 * http://archives.postgresql.org/pgsql-jdbc/2010-01/msg00053.php
 * 
 *  I think that you should forget the Oracle data type and just understand that 
 *  PostgreSQL Numeric and Java BigDecimal are the best match for those data types.
 *  
 *   PostgreSQL:
 *  SELECT ('2.0'::numeric = '2.00'::numeric)::bool
 *   't'
 * And just to be more exact, we will define two different scales.
 *  SELECT ('2.0'::numeric(8,1) = '2.00'::numeric(8,2))::bool
 *   't'
 *
 *  Compared to Java : 
 *  System.out.println((new BigDecimal("2.0").equals(new BigDecimal("2.00"))));
 *  false.
 *  The scales must be identical to get a true result from the equals operator in Java.
 *  
 */


package org.coenraets.directory;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
public class Product {

    private int productID;

    private String productName;

	private int classID;
	
	private BigDecimal price;
    
    private String symbolName;

    private String title;

    private String city;

    private String officePhone;

    private String cellPhone;

    private String email;
    
	private String picture;    

	private String department;


	
	public Product() {

    }
	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int product_id) {
		this.productID = product_id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal the_price) {
		this.price = the_price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String product_name) {
		this.productName = product_name;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbol_name) {
		this.symbolName = symbol_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int class_id) {
		this.classID = class_id;
	}

}
