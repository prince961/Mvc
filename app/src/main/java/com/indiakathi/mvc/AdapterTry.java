package com.indiakathi.mvc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mohit on 21-06-2016.
 */
public class AdapterTry extends ArrayAdapter<ModelProducts> {

    private int layout;
    private List<ModelProducts> productList;

    public AdapterTry(Context context, int resource, List<ModelProducts> productList){
        super(context,resource,productList);
        this.productList = productList;
        this.layout = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mainViewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.item_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.button = (Button) convertView.findViewById(R.id.addToCart);
            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"button was clicked"+ productList.get(position).getProductName(),Toast.LENGTH_LONG).show();
                }
            });
            convertView.setTag(viewHolder);
        }
        else {
            mainViewHolder = (ViewHolder) convertView.getTag();
            mainViewHolder.title.setText(getItem(position).getProductName());
        }

        return convertView;
    }

    public class ViewHolder {
        ImageView thumbnail;
        Button button;
        TextView title;
    }
}
