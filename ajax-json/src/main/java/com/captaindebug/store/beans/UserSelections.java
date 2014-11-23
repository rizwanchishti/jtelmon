/*
 * http://www.javacodegeeks.com/2013/05/spring-mvc-ajax-and-json-part-1-setting-the-scene.html
 * http://www.javacodegeeks.com/2013/05/spring-mvc-ajax-and-json-part-2-the-server-side-code.html
 * http://www.javacodegeeks.com/2013/05/spring-mvc-ajax-and-json-part-3-the-client-side-code.html
 */
 
package com.captaindebug.store.beans;

import java.util.Collections;
import java.util.List;

/**
 * Models the user's selections - data returned by the form.
 * 
 * @author Roger
 * 
 *         Created 07:33:38 21 Apr 2013
 * 
 */
public class UserSelections {

	private List<String> selection = Collections.emptyList();

	public List<String> getSelection() {
		return selection;
	}

	public void setSelection(List<String> selection) {
		this.selection = selection;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Selections are: ");

		for (String str : selection) {
			sb.append(str);
			sb.append(",  ");
		}

		return sb.toString();
	}
}
