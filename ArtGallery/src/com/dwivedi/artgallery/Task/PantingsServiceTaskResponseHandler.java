package com.dwivedi.artgallery.Task;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.dwivedi.artgallery.Task.ServiceDataClass.CatTagClass;
import com.dwivedi.artgallery.Task.ServiceDataClass.ItemTagClass;
import com.dwivedi.artgallery.Task.ServiceDataClass.MainTagClass;

public class PantingsServiceTaskResponseHandler extends DefaultHandler {
	
	public static  MainTagClass mainTagClass;
	private CatTagClass catTagClass;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if (localName.equalsIgnoreCase("main")) {
			
			mainTagClass = new MainTagClass();
			
			
		}
		if (localName.equalsIgnoreCase("cat")) {
			
			catTagClass = new CatTagClass();
			catTagClass.setCatName(attributes.getValue("name").toString());
			mainTagClass.catTagClasses.add(catTagClass);
			
		}
		
		if (localName.equalsIgnoreCase("item")) {
			ItemTagClass itemTagClass = new ItemTagClass();
			String imageUrl = attributes.getValue("url").toString();
			itemTagClass.setImageUrl(imageUrl );
			catTagClass.itemTagClasses.add(itemTagClass);
			 
		}
		
		 
	}

}
