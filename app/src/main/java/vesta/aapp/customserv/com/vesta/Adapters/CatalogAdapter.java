package vesta.aapp.customserv.com.vesta.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import vesta.aapp.customserv.com.vesta.Activity.ProductActivity;
import vesta.aapp.customserv.com.vesta.Model.CatalogItem;
import vesta.aapp.customserv.com.vesta.R;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ExampleViewHolder>{

    private ArrayList<CatalogItem> CatalogList;
    Context context;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.icon_category);
            mTextView1 = itemView.findViewById(R.id.tv_title_category);

        }
    }

    public CatalogAdapter(ArrayList<CatalogItem> exampleList) {
        CatalogList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        context = v.getContext();
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        CatalogItem currentItem = CatalogList.get(position);
        Glide.with(context).load(currentItem.getImageResourse()).into(holder.mImageView);

        holder.mTextView1.setText(currentItem.getText1());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductActivity.class);
                i.putExtra("id", currentItem.getId());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return CatalogList.size();
    }
}
