package com.dwivedi.artgallery.Task.ServiceDataClass;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CatTagClass {
	
	
	 public ArrayList<ItemTagClass> itemTagClasses = new ArrayList<ItemTagClass>();
	 private String catName;
	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}

	 
}
