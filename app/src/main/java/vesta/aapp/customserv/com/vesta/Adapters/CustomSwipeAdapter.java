package vesta.aapp.customserv.com.vesta.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import vesta.aapp.customserv.com.vesta.R;

public class CustomSwipeAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    private int[] image_resourses = {
            R.drawable.w2,
            R.drawable.w3,
            R.drawable.w4,
            R.drawable.w5,
            R.drawable.w6
    };


    public CustomSwipeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image_resourses.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

       // layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // View view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
       // ImageView imageView = view.findViewById(R.id.image_view);
       // imageView.setImageResource(image_resourses[position]);
       // container.addView(view);

        ImageView imageView = new ImageView(context);
        Picasso.get()
                .load(image_resourses[position])
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
