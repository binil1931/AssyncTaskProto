package com.example.binil.assynctaskproto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListViewAdapter extends BaseAdapter {

	Context mContext;
	LayoutInflater inflater;
	private List<ItemListPogo> itemListPogo = null;
	private ArrayList<ItemListPogo> arraylist;

	public ListViewAdapter(Context context, List<ItemListPogo> itemLists)
	{
		mContext = context;
		this.itemListPogo = itemLists;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<ItemListPogo>();
		this.arraylist.addAll(itemLists);
	}

	public class ViewHolder {

		TextView email;
		TextView name;
	}

	@Override
	public int getCount() {
		return itemListPogo.size();
	}

	@Override
	public ItemListPogo getItem(int position) {
		return itemListPogo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent)
	{
		final ViewHolder holder;
		if (view == null) 
		{
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.list_item, null);
            
			holder.email  = (TextView) view.findViewById(R.id.email);
			holder.name     = (TextView) view.findViewById(R.id.name);
			
			view.setTag(holder);
		} 
		else 
		{
			holder = (ViewHolder) view.getTag();
		}

		// Set the results into TextViews
		holder.name.setText(itemListPogo.get(position).getItemName());
		holder.email.setText(itemListPogo.get(position).getProductName());

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0)
			{
				//On things on click

			}
		});

		return view;
	}


}
