package vesta.aapp.customserv.com.vesta.Model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vesta.aapp.customserv.com.vesta.R;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ExampleViewHolder>{

    private ArrayList<CatalogItem> CatalogList;

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
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        CatalogItem currentItem = CatalogList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
    }

    @Override
    public int getItemCount() {
        return CatalogList.size();
    }
}
