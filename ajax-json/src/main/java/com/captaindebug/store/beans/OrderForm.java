/*
 * http://www.javacodegeeks.com/2013/05/spring-mvc-ajax-and-json-part-1-setting-the-scene.html
 * http://www.javacodegeeks.com/2013/05/spring-mvc-ajax-and-json-part-2-the-server-side-code.html
 * http://www.javacodegeeks.com/2013/05/spring-mvc-ajax-and-json-part-3-the-client-side-code.html
 */
 
package com.captaindebug.store.beans;

import java.util.List;

/**
 * Model an order form. This is a list of items and a unique purchase id.
 * 
 * @author Roger
 * 
 *         Created 07:33:18 21 Apr 2013
 * 
 */
public class OrderForm {

	private final List<Item> items;

	private final String purchaseId;

	public OrderForm(List<Item> items, String purchaseId) {
		super();
		this.items = items;
		this.purchaseId = purchaseId;
	}

	public List<Item> getItems() {
		return items;
	}

	public String getPurchaseId() {
		return purchaseId;
	}
}
