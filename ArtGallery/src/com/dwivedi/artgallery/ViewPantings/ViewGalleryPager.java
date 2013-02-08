package com.dwivedi.artgallery.ViewPantings;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.dwivedi.artgallery.Home.HomePageActivity;
import com.dwivedi.artgallery.LazyLoader.ImageLoader;
import com.dwivedi.artgallery.Task.ServiceDataClass.ItemTagClass;
import com.dwivedi.artgallery.Util.CoverFlow;

public class ViewGalleryPager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		int position = getIntent().getExtras().getInt("POSITION");

		ArrayList<ItemTagClass> selectedItemTagClasses = new ArrayList<ItemTagClass>();
		selectedItemTagClasses.clear();

		selectedItemTagClasses = new ArrayList<ItemTagClass>(
				HomePageActivity.itemTagClassesForGrid);

		CoverFlow coverFlow;
		coverFlow = new CoverFlow(this);

		ImageAdapter coverImageAdapter = new ImageAdapter(this,
				selectedItemTagClasses);

		coverFlow.setAdapter(coverImageAdapter);

		coverFlow.setSpacing(-15);
		coverFlow.setSelection(position, true);

		setContentView(coverFlow);

	}

	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;
		private Context mContext;

		private ArrayList<ItemTagClass> selectedItemTagClasses;
		private ImageLoader imageLoader;

		public ImageAdapter(Context c,
				ArrayList<ItemTagClass> selectedItemTagClasses) {
  			this.selectedItemTagClasses = selectedItemTagClasses;
			this.imageLoader = new ImageLoader(c);
		}

		public int getCount() {
			return selectedItemTagClasses.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ItemTagClass itemTagClass = selectedItemTagClasses.get(position);

			String imageURL = itemTagClass.getImageUrl();
			ImageView imageView = new ImageView(mContext);
			imageView.setLayoutParams(new CoverFlow.LayoutParams(120, 180));

			imageView.setScaleType(ScaleType.MATRIX);
			imageLoader.DisplayImage(imageURL, imageView);
			return imageView;
		}

		 
	}

}