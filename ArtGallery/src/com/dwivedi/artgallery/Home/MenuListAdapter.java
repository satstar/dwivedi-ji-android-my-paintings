package com.dwivedi.artgallery.Home;

import java.util.ArrayList;

import com.dwivedi.artgallery.R;
import com.dwivedi.artgallery.Task.ServiceDataClass.CatTagClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<CatTagClass> catTagClasses;
	private LayoutInflater inflater;

	public MenuListAdapter(Context context,
			ArrayList<CatTagClass> catTagClasses) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.catTagClasses = catTagClasses;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return catTagClasses.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return catTagClasses.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		ViewHolde holde;
		if (convertView== null) {
			holde = new ViewHolde();
			
			convertView = inflater.inflate(R.layout.menu_list_row_view, null);
			
			holde.tvCatName = (TextView)convertView.findViewById(R.id.tv_menu_cat_title);
			
			convertView.setTag(holde);
			
		} else {

			holde = (ViewHolde)convertView.getTag();
		}
		
		holde.tvCatName.setText(catTagClasses.get(position).getCatName());
		return convertView;
	}
	  class ViewHolde{
		 TextView tvCatName;
	 }

}
