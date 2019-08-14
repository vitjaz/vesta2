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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vesta.aapp.customserv.com.vesta.Activity.DetailProduct;
import vesta.aapp.customserv.com.vesta.Model.Products;
import vesta.aapp.customserv.com.vesta.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

   private Context context;
   private ArrayList<Products> mProducts;

    public ProductAdapter(Context context, ArrayList<Products> mProducts) {
        this.context = context;
        this.mProducts = mProducts;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.products_item, viewGroup, false);

        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Products products = mProducts.get(i);

        viewHolder.id.setText(products.getProduct_id());
        viewHolder.nameProduct.setText(products.getName());
        if(products.getImageRes().equals("null")) {
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher_round);
        } else {
            Picasso.get().load("http://vestatrade.ru/image/" + products.getImageRes()).into(viewHolder.imageView);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DetailProduct.class);
                i.putExtra("id", products.getProduct_id());
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameProduct;
        TextView id;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.product_id);
            nameProduct = itemView.findViewById(R.id.NameProduct);
            imageView = itemView.findViewById(R.id.ImageView);
        }
    }
}
