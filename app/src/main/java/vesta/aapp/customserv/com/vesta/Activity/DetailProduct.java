package vesta.aapp.customserv.com.vesta.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vesta.aapp.customserv.com.vesta.R;
import vesta.aapp.customserv.com.vesta.RequestHandler;

public class DetailProduct extends AppCompatActivity {


    private static final String URL_DETAIL = "http://android.vestatrade.ru/getDetailProduct.php";
    TextView name, desctiprion;
    ImageView imageView;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        name = findViewById(R.id.producrName);
        desctiprion = findViewById(R.id.producrDesc);
        imageView = findViewById(R.id.ImageViewId);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        Toast.makeText(this, "id:" + id, Toast.LENGTH_SHORT).show();
        readProductDetail();
    }

    private void readProductDetail() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DETAIL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject json = new JSONObject(response);
                            JSONArray jsonArray = json.getJSONArray("products");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String image = jsonObject.getString("image");
                            String nameView = jsonObject.getString("name");
                            String descView = jsonObject.getString("description");
                            Toast.makeText(DetailProduct.this,nameView + " " + descView, Toast.LENGTH_LONG).show();

                            if(image.equals("null")) {
                                imageView.setImageResource(R.mipmap.ic_launcher_round);
                            } else {
                                Picasso.get().load("http://vestatrade.ru/image/" + image).into(imageView);
                            }

                            name.setText(nameView);
                            desctiprion.setText(descView);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailProduct.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("id", id);
                return param;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
