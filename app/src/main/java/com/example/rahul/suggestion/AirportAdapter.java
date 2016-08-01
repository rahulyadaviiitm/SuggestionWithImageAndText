package com.example.rahul.suggestion;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rahul on 27-06-2016.
 */
public class AirportAdapter extends ArrayAdapter<Detail> {
    AirportAdapter airAdapter;
    private Timer theTimer = null;
    public static final int DELAY = 1000;
    boolean timer;
    String str;
    Context context;
    int resource, textViewResourceId, imageViewResourceId;
    List<Detail> dlist, tempItems, suggestions;
    private DisplayMetrics metrics;

    public AirportAdapter(Context context, int resource, int imageViewResourceId, int textViewResourceId, List<Detail> dlist, DisplayMetrics metrics) {
        super(context, resource, textViewResourceId, dlist);
        this.context = context;
        this.resource = resource;
        this.imageViewResourceId = imageViewResourceId;
        this.textViewResourceId = textViewResourceId;
        this.dlist = dlist;
        this.metrics = metrics;
        tempItems = new ArrayList<Detail>(dlist); // this makes the difference.
        suggestions = new ArrayList<Detail>();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_layout, parent, false);
        }
        Detail detail = dlist.get(position);
        if (detail != null) {
            TextView lblName = (TextView) view.findViewById(R.id.txt_name);
            ImageView lblImage = (ImageView) view.findViewById(R.id.icon_image);
            if (lblName != null && lblImage != null) {
                lblName.setText(detail.getName());
                lblImage.setImageResource(detail.getImage());
            }
        }

        Animation animation = null;
        //    animation = new TranslateAnimation(metrics.widthPixels / 2, 0, 0, 0);
//       animation = new TranslateAnimation(0, 0, metrics.heightPixels, 0);
        //  animation = new ScaleAnimation((float) 1.0, (float) 1.0, (float) 0, (float) 1.0);
        animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
//        animation = AnimationUtils.loadAnimation(context, R.anim.hyperspace_out);
//        animation = AnimationUtils.loadAnimation(context, R.anim.wave_scale);
//        animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
//        animation = AnimationUtils.loadAnimation(context, R.anim.push_left_out);
//        animation = AnimationUtils.loadAnimation(context, R.anim.push_up_in);
        //     animation = AnimationUtils.loadAnimation(context, R.anim.shake);
        animation.setDuration(1000);
        view.startAnimation(animation);
        return view;

    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(final Object resultValue) {
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Detail detail : tempItems) {
                    if (detail.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(detail);
                        // airAdapter.startTimer();
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Detail> filterList = (ArrayList<Detail>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (final Detail detail : filterList) {

                    // Do something after 5s = 5000ms
                    add(detail);
                    notifyDataSetChanged();
                }

            }
        }

    };
}