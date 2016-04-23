package com.example.rodri.tempnow.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodri.tempnow.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodri on 4/22/2016.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private LayoutInflater inflater = null;
    List<String> scales;

    public SpinnerAdapter(Activity activity, int textViewResourceId, List<String> scales) {
        super(activity, textViewResourceId, scales);
        try {
            this.activity = activity;
            this.scales = scales;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getCount() {
        return scales.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public class ViewHolder {
        public TextView displayScale;
        public ImageView displaySpinnerArrow;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            v = inflater.inflate(R.layout.spinner_item, parent, false);

            holder.displayScale = (TextView) v.findViewById(R.id.txtScaleName);
            holder.displaySpinnerArrow = (ImageView) v.findViewById(R.id.imgSpinnerArrow);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.displaySpinnerArrow.setImageResource(R.drawable.spinner_arrow);
        if (position == 0) {
            holder.displayScale.setText(R.string.spinner_title);
        } else {
            holder.displayScale.setText(scales.get(position));
            holder.displaySpinnerArrow.setVisibility(View.GONE);
        }

        return v;
    }

}
