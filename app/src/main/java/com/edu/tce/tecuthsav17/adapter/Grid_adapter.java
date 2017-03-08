package com.edu.tce.tecuthsav17.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.tce.tecuthsav17.R;
import com.edu.tce.tecuthsav17.model.Grid;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by RaamKumr on 2/15/2017.
 */

public class Grid_adapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();
    private int menuImages[]={R.drawable.arch,R.drawable.civil,R.drawable.cse,R.drawable.ece,R.drawable.eee,R.drawable.it,R.drawable.mech,R.drawable.mechat,R.drawable.workshop};
    public Grid_adapter(Context context,int layoutResourceId,ArrayList data)
    {
        super(context,layoutResourceId,data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        ViewHolder holder=null;
        if(row==null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row=inflater.inflate(layoutResourceId,parent,false);
            holder=new ViewHolder();
            holder.dept_name = (TextView)row.findViewById(R.id.grid_item_id);
            holder.imageView = (ImageView)row.findViewById(R.id.grid_img);
            row.setTag(holder);
        }else {
            holder=(ViewHolder)row.getTag();
        }
        Grid item = (Grid) data.get(position);
        holder.dept_name.setText(item.getmDepartment());
        Picasso.with(getContext()).load(menuImages[position]).into(holder.imageView);
        return row;
    }

    private static class ViewHolder{
        TextView dept_name;
        ImageView imageView;
    }
}
