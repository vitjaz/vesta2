package vesta.aapp.customserv.com.vesta.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vesta.aapp.customserv.com.vesta.Adapters.ProductAdapter;
import vesta.aapp.customserv.com.vesta.Model.Products;
import vesta.aapp.customserv.com.vesta.R;
import vesta.aapp.customserv.com.vesta.RequestHandler;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    // укажите свой адрес
    private static final String URL_ALL_PRODUCTS = "http://android.vestatrade.ru/getProductFromCategory.php";

    // Имена узлов JSON
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private DrawerLayout drawerLayout;
    private static final String TAG_PID = "product_id";
    private static final String TAG_NAME = "name";
    private ProgressDialog progressDialog;
    private ArrayList<Products> mProducts;
    private ProductAdapter pAdapter;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        final Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        Intent i = getIntent();
        id = i.getStringExtra("id");



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        readProduct();
    }

    public void readProduct() {
        mProducts = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ALL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("products");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject product = jsonArray.getJSONObject(i);
                                String product_id = product.getString("product_id");
                                String name = product.getString("name");
                                String image = product.getString("image");
                                Products products = new Products(name, product_id, image);
                                mProducts.add(products);
                            }
                            pAdapter = new ProductAdapter(ProductActivity.this, mProducts);
                            recyclerView.setAdapter(pAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };


        RequestHandler.getInstance(ProductActivity.this).addToRequestQueue(stringRequest);
        mProducts.clear();
        //progressDialog.dismiss();

    }
}
