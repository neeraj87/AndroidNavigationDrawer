package com.violet.neerajjadhav.navigationdrawertest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class NavigationDrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Items> itemsArray;

    public NavigationDrawerListAdapter(Context context, ArrayList<Items> itemsArray) {
        this.context = context;
        this.itemsArray = itemsArray;
    }

    private class ViewHolder {
        TextView itemName;
        TextView itemDescription;
        ImageView itemIcon;
    }

    @Override
    public int getCount() {
        return itemsArray.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.drawer_list_item, null);
            holder = new ViewHolder();

            holder.itemName = (TextView) convertView.findViewById(R.id.item_name_txtview);
            holder.itemDescription = (TextView) convertView.findViewById(R.id.item_name_description);
            holder.itemIcon = (ImageView) convertView.findViewById(R.id.item_icon_imgview);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Items item = (Items) getItem(position);
        holder.itemName.setText(item.getItemName());
        holder.itemDescription.setText(item.getItemDesc());
        holder.itemIcon.setBackgroundResource((int)item.getIconId());

        return convertView;
    }
}
