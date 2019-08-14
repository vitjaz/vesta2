package vesta.aapp.customserv.com.vesta.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vesta.aapp.customserv.com.vesta.Adapters.CatalogAdapter;
import vesta.aapp.customserv.com.vesta.Model.CatalogItem;
import vesta.aapp.customserv.com.vesta.R;
import vesta.aapp.customserv.com.vesta.RequestHandler;

public class ViewCatalogFragment extends Fragment {

    private RecyclerView recyclerView;
    private static final String URL_ALL_CATEGORY = "http://android.vestatrade.ru/getAllCategory.php";
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<CatalogItem> catalogList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_catalog, container, false);

       /* catalogList.add(new CatalogItem(R.drawable.cat_0, "Арматура для радиаторов"));
        catalogList.add(new CatalogItem(R.drawable.cat_1, "Баки мембранные"));
        catalogList.add(new CatalogItem(R.drawable.cat_2, "Ванны"));
        catalogList.add(new CatalogItem(R.drawable.cat_2_1_1, "Вентиляция"));
        catalogList.add(new CatalogItem(R.drawable.cat_3, "Водозапорная арматура"));
        catalogList.add(new CatalogItem(R.drawable.cat_4, "Водонагреватели"));
        catalogList.add(new CatalogItem(R.drawable.cat_5, "Водоподготовка"));
        catalogList.add(new CatalogItem(R.drawable.cat_6, "Водосчетчики"));
        catalogList.add(new CatalogItem(R.drawable.cat_7, "Газовое оборудование"));
        catalogList.add(new CatalogItem(R.drawable.cat_9, "Душевые кабины"));
        catalogList.add(new CatalogItem(R.drawable.cat_10, "Емкости для воды и топлива"));
        catalogList.add(new CatalogItem(R.drawable.cat_11, "Инструменты"));
        catalogList.add(new CatalogItem(R.drawable.cat_12, "Канализационные системы"));
        catalogList.add(new CatalogItem(R.drawable.cat_13, "Коллекторы и аксессуары"));
        catalogList.add(new CatalogItem(R.drawable.cat_14, "Контрольно-измерительные приборы"));
        catalogList.add(new CatalogItem(R.drawable.cat_15, "Котельное оборудование"));
        catalogList.add(new CatalogItem(R.drawable.cat_16, "Крепеж"));
        catalogList.add(new CatalogItem(R.drawable.cat_17, "Насосное оборудование"));
        catalogList.add(new CatalogItem(R.drawable.cat_18, "Подводка гибкая"));
        catalogList.add(new CatalogItem(R.drawable.cat_19, "Полотенцесушители"));
        catalogList.add(new CatalogItem(R.drawable.cat_20, "Регулирующая арматура"));
        catalogList.add(new CatalogItem(R.drawable.cat_21, "Радиаторы отопления"));
        catalogList.add(new CatalogItem(R.drawable.cat_22, "Расходные материалы"));
        catalogList.add(new CatalogItem(R.drawable.cat_23, "Резьбовые фитинги"));
        catalogList.add(new CatalogItem(R.drawable.cat_24, "Комплекты стальных труб"));
        catalogList.add(new CatalogItem(R.drawable.cat_25, "Санфаянс и другое"));
        catalogList.add(new CatalogItem(R.drawable.cat_26, "Системы контроля Нептун"));
        catalogList.add(new CatalogItem(R.drawable.cat_27, "Системы теплых полов"));
        catalogList.add(new CatalogItem(R.drawable.cat_28, "Смесители и другое"));
        catalogList.add(new CatalogItem(R.drawable.cat_29, "Теплоизоляция"));
        catalogList.add(new CatalogItem(R.drawable.cat_30, "Теплосчетчики"));
        catalogList.add(new CatalogItem(R.drawable.cat_32_36, "Трубы и фитинги полипропиленовые"));
        catalogList.add(new CatalogItem(R.drawable.cat_32_36, "Трубы и фитинги металлополимерные"));
        catalogList.add(new CatalogItem(R.drawable.cat_32_36, "Трубы и фитинги пластиковые"));
        catalogList.add(new CatalogItem(R.drawable.cat_32_36, "Трубы и фитинги медные"));
        catalogList.add(new CatalogItem(R.drawable.cat_32_36, "Трубы и фитинги нержавеющие"));
        catalogList.add(new CatalogItem(R.drawable.cat_37, "Фильтры и другое"));
        catalogList.add(new CatalogItem(R.drawable.cat_38, "Электроборудование")); */
        //catalogList.add(new CatalogItem(R.drawable.cat_0, "Шланги ТД Сантэл", "1248"));
       // catalogList.add(new CatalogItem(R.drawable.cat_0, "Ручные клапаны RBM", "1112"));


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(getContext());

        readCategory();


        return view;


    }

    public void readCategory() {
        catalogList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_ALL_CATEGORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("category");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject product = jsonArray.getJSONObject(i);
                                String category_id = product.getString("category_id");
                                String name = product.getString("name");

                                catalogList.add(new CatalogItem(R.drawable.asl_checkbox, name, category_id));
                            }

                            adapter = new CatalogAdapter(catalogList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
        };


        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
        catalogList.clear();
    }
}
