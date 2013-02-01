package com.dwivedi.artgallery.Home;

import java.util.ArrayList;

import com.dwivedi.artgallery.R;
import com.dwivedi.artgallery.LazyLoader.ImageLoader;
import com.dwivedi.artgallery.Task.ServiceDataClass.ItemTagClass;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridImagesAdapter extends BaseAdapter {
	private static final String LOG_TAG = "MenuImageAdapter";

	private Context context;
	private int width;
	private int height;
	private ArrayList<ItemTagClass> itemTagClasses;

	private ImageLoader imageLoader;

	public GridImagesAdapter(Context context,
			ArrayList<ItemTagClass> itemTagClasses) {
		this.context = context;
		this.itemTagClasses = itemTagClasses;
		imageLoader = new ImageLoader(context);

	}

	public int getCount() {
		return itemTagClasses.size();
	}

	public Object getItem(int index) {

		return itemTagClasses.get(index);
	}

	public long getItemId(int index) {
		return index;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View view;
		if (convertView == null) {
			// setUpLayout(position);

			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			width = display.getWidth();
			height = display.getHeight();
			int gridLayoutWidth = width * 6 / 10;
			int gridLayoutHeight = height * 6 / 10;
			int gridWidth = (int) (gridLayoutWidth / 3);
			int gridHeight = (int) (gridLayoutHeight / 3);

			view = inflater.inflate(R.layout.layout_grid_view_item, null);
			view.setLayoutParams(new GridView.LayoutParams(gridWidth,
					gridHeight));

			ImageView ivExperts = (ImageView) view.findViewById(R.id.img);
			ivExperts.setPadding(2, 2, 2, 2);

			String url = itemTagClasses.get(position).getImageUrl();
			imageLoader.DisplayImage(url, ivExperts);

		} else

			view = convertView;
		return view;
	}

	public static class ViewHolder {
		static LinearLayout layout;
		static ImageView imageView;
		static TextView textView;
	}

}
