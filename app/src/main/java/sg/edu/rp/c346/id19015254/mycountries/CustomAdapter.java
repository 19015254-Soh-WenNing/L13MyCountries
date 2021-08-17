package sg.edu.rp.c346.id19015254.mycountries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Country> countryList;

    public CustomAdapter(Context context, int resource, ArrayList<Country> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        countryList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvCountry = rowView.findViewById(R.id.textViewCountry);
        TextView tvDesc = rowView.findViewById(R.id.textViewDesc);
        TextView tvArea = rowView.findViewById(R.id.tvArea2);
        TextView tvYear = rowView.findViewById(R.id.tvYear2);
        TextView tvCity = rowView.findViewById(R.id.tvCity2);
        ImageView ivNew = rowView.findViewById(R.id.imageViewRecent);
        RatingBar rbStar = rowView.findViewById(R.id.ratingBar3);
        //TextView tvStar = rowView.findViewById(R.id.textViewStar);

        Country currentCountry = countryList.get(position);

        tvCountry.setText(currentCountry.getCountry());
        tvDesc.setText(currentCountry.getDesc());
        tvArea.setText(String.valueOf(currentCountry.getArea()));
        tvYear.setText(String.valueOf(currentCountry.getYear()));
        tvCity.setText(currentCountry.getCity());
        rbStar.setRating(currentCountry.getStars());
        //tvStar.setText(currentSong.toString());

        if (currentCountry.getYear() > 2020)
        {
            ivNew.setImageResource(R.drawable.recent);
        }
        else
        {
            ivNew.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }
}
