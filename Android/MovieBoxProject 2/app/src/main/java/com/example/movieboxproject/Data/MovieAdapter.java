package com.example.movieboxproject.Data;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.movieboxproject.R;


import java.util.List;

public class MovieAdapter extends BaseAdapter {

    Context context;
    List<Movie> list;
    int resource;

    public MovieAdapter(Context context, int resource, List<Movie> list) {
        this.context = context;
        this.list = list;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.liTitle = convertView.findViewById(R.id.li_title);
            holder.liDirector = convertView.findViewById(R.id.li_director);
            holder.liRating = convertView.findViewById(R.id.li_year);
            convertView.setTag(holder);
        }

        Movie movie = list.get(position);
        holder = (ViewHolder) convertView.getTag();
        holder.liTitle.setText(movie.getTitle());
        holder.liDirector.setText(movie.getDirector());
        holder.liRating.setText(movie.getRating());

        return convertView;
    }


    static class ViewHolder {
        TextView liTitle;
        TextView liDirector;
        TextView liRating;
    }

    public void updateList(List<Movie> newMovies) {
        list = newMovies;
        notifyDataSetChanged();
    }
}



