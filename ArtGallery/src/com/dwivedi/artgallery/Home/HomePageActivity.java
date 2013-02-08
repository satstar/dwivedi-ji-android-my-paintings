package com.dwivedi.artgallery.Home;

import java.util.ArrayList;
import java.util.Date;

import com.dwivedi.artgallery.R;
import com.dwivedi.artgallery.Task.PantingsServiceTaskResponseHandler;
import com.dwivedi.artgallery.Task.ServiceDataClass.CatTagClass;
import com.dwivedi.artgallery.Task.ServiceDataClass.ItemTagClass;
import com.dwivedi.artgallery.Task.ServiceDataClass.MainTagClass;
import com.dwivedi.artgallery.Util.MyHorizontalScrollView;
import com.dwivedi.artgallery.Util.MyHorizontalScrollView.SizeCallback;
import com.dwivedi.artgallery.ViewPantings.ViewGalleryPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivity extends Activity {

	private MyHorizontalScrollView scrollView;
	private View menu;
	private View app;
	private ListView listViewCat;
	private ImageView btnSlide;
	private GridView gridViewpainting;
	public static ArrayList<ItemTagClass> itemTagClassesForGrid;
	private TextView title;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setUpView();

	}

	private void setUpView() {
		// TODO Auto-generated method stub

		LayoutInflater inflater = LayoutInflater.from(this);
		scrollView = (MyHorizontalScrollView) inflater.inflate(
				R.layout.home_page_main_view, null);
		setContentView(scrollView);

		menu = inflater.inflate(R.layout.home_page_menu_view, null);
		app = inflater.inflate(R.layout.home_page_activity, null);
		ViewGroup tabBar = (ViewGroup) app
				.findViewById(R.id.home_page_action_bar);

		// grid
		title = (TextView)tabBar.findViewById(R.id.tvHomePageTitle);
		title.setText("All Painting");
		gridViewpainting = (GridView) app
				.findViewById(R.id.gridView_home_page_paintings);
		itemTagClassesForGrid = new ArrayList<ItemTagClass>();

		GridImagesAdapter adapter = new GridImagesAdapter(this,
				itemTagClassesForGrid);
		gridViewpainting.setAdapter(adapter);
		
		
		gridViewpainting.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				Intent intent = new Intent(view.getContext(),ViewGalleryPager.class);
				// TODO Auto-generated method stub
   				intent.putExtra("POSITION", position);
				startActivity(intent );
				
				
			}
		});

		updateAdapter(getAllImagesURLs());

		listViewCat = (ListView) menu.findViewById(R.id.lvMenuList);
		ArrayList<CatTagClass> catTagClasses = PantingsServiceTaskResponseHandler.mainTagClass.catTagClasses;
	
		listViewCat.setAdapter(new MenuListAdapter(this,catTagClasses ));

		listViewCat
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> adapterView,
							View view, int position, long id) {
						// TODO Auto-generated method stub

						CatTagClass catTagClass = (CatTagClass) adapterView
								.getItemAtPosition(position);
						ArrayList<ItemTagClass> arrayList = catTagClass.itemTagClasses;
						updateAdapter(arrayList);
						title.setText(catTagClass.getCatName());
					}
				});

		btnSlide = (ImageView) tabBar.findViewById(R.id.ibMenuButton);
		btnSlide.setOnClickListener(new ClickListenerForScrolling(scrollView,
				menu));

		final View[] children = new View[] { menu, app };

		// Scroll to app (view[1]) when layout finished.
		int scrollToViewIdx = 1;
		scrollView.initViews(children, scrollToViewIdx,
				new SizeCallbackForMenu(btnSlide));
	}

	private void updateAdapter(ArrayList<ItemTagClass> arrayList) {
		// TODO Auto-generated method stub
		itemTagClassesForGrid.clear();

		for (int i = 0; i < arrayList.size(); i++) {
			itemTagClassesForGrid.add(arrayList.get(i));
		}

		GridImagesAdapter adapter = (GridImagesAdapter) gridViewpainting
				.getAdapter();
		adapter.notifyDataSetChanged();
	}

	private ArrayList<ItemTagClass> getAllImagesURLs() {
		// TODO Auto-generated method stub

		MainTagClass mainTagClass = PantingsServiceTaskResponseHandler.mainTagClass;

		ArrayList<CatTagClass> catTagClasses = mainTagClass.catTagClasses;
		ArrayList<ItemTagClass> itemTagClassesForAllCat = new ArrayList<ItemTagClass>();
		itemTagClassesForAllCat.clear();
		for (int i = 0; i < catTagClasses.size(); i++) {
			CatTagClass catTagClass = mainTagClass.catTagClasses.get(i);
			ArrayList<ItemTagClass> itemTagClasses = catTagClass.itemTagClasses;

			for (int j = 0; j < itemTagClasses.size(); j++) {
				itemTagClassesForAllCat.add(itemTagClasses.get(j));
			}

		}

		return itemTagClassesForAllCat;

	}

	/**
	 * Helper for examples with a HSV that should be scrolled by a menu View's
	 * width.
	 */
	static class ClickListenerForScrolling implements OnClickListener {
		HorizontalScrollView scrollView;
		View menu;
		/**
		 * Menu must NOT be out/shown to start with.
		 */
		boolean menuOut = false;

		public ClickListenerForScrolling(HorizontalScrollView scrollView,
				View menu) {
			super();
			this.scrollView = scrollView;
			this.menu = menu;
		}

		@Override
		public void onClick(View v) {
			Context context = menu.getContext();
			String msg = "Slide " + new Date();
			Toast.makeText(context, msg, 1000).show();
			System.out.println(msg);

			int menuWidth = menu.getMeasuredWidth();

			// Ensure menu is visible
			menu.setVisibility(View.VISIBLE);

			if (!menuOut) {
				// Scroll to 0 to reveal menu
				int left = 0;
				scrollView.smoothScrollTo(left, 0);
			} else {
				// Scroll to menuWidth so menu isn't on screen.
				int left = menuWidth;
				scrollView.smoothScrollTo(left, 0);
			}
			menuOut = !menuOut;
		}
	}

	/**
	 * Helper that remembers the width of the 'slide' button, so that the
	 * 'slide' button remains in view, even when the menu is showing.
	 */
	static class SizeCallbackForMenu implements SizeCallback {
		int btnWidth;
		View btnSlide;

		public SizeCallbackForMenu(View btnSlide) {
			super();
			this.btnSlide = btnSlide;
		}

		@Override
		public void onGlobalLayout() {
			btnWidth = btnSlide.getMeasuredWidth();
			System.out.println("btnWidth=" + btnWidth);
		}

		@Override
		public void getViewSize(int idx, int w, int h, int[] dims) {
			dims[0] = w;
			dims[1] = h;
			final int menuIdx = 0;
			if (idx == menuIdx) {
				dims[0] = w - btnWidth;
			}
		}
	}
}
