/**
 * 
 */
package com.dwivedi.artgallery.Task;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.dwivedi.artgallery.Home.HomePageActivity;
import com.dwivedi.artgallery.Task.ServiceDataClass.MainTagClass; 
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author ashish
 * 
 */
public class PantingsServiceTask extends AsyncTask<Void, Void, MainTagClass> {

	public PantingsServiceTask(Context context) {
		super();
		this.context = context;
	}

	private static final String TAG = "PantingsServiceTask";
	private Context context;

	@Override
	protected MainTagClass doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		MainTagClass mainTagClass = null;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			String URLString = "http://dl.dropbox.com/u/72603672/xyz.xml";
			Log.i(TAG, "Calling url : " + URLString);
			URL sourceURL = new URL(URLString);

			 PantingsServiceTaskResponseHandler handler = new PantingsServiceTaskResponseHandler();
			xmlReader.setContentHandler(handler);

			xmlReader.parse(new InputSource(sourceURL.openStream()));

			mainTagClass = PantingsServiceTaskResponseHandler.mainTagClass;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mainTagClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(MainTagClass result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		Intent intent = new Intent(context, HomePageActivity.class);
		context.startActivity(intent);
	}
}