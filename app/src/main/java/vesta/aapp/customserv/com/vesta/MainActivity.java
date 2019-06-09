package vesta.aapp.customserv.com.vesta;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vesta.aapp.customserv.com.vesta.Fragments.CartFragment;
import vesta.aapp.customserv.com.vesta.Fragments.HomeFragment;
import vesta.aapp.customserv.com.vesta.Fragments.MyOrderFragment;
import vesta.aapp.customserv.com.vesta.Fragments.SalesFragment;
import vesta.aapp.customserv.com.vesta.Fragments.SendFragment;
import vesta.aapp.customserv.com.vesta.Fragments.ViewCatalogFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FusedLocationProviderClient fusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION) // ask single or multiple permission once
                .subscribe(granted -> {
                    if (granted) {
                        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }else {
                            fusedLocationClient.getLastLocation()
                                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                                        @Override
                                        public void onSuccess(Location location) {
                                            // Got last known location. In some rare situations this can be null.
                                            if (location != null) {
                                                loc_func(location);
                                            }
                                        }
                                    });

                        }
                    } else {

                        // Create Alert using Builder
                        new CFAlertDialog.Builder(this)
                                .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET)
                                .setTitle("Выберете город")
                                .setMessage("Магазин сантехники VESTA-TRADING работает только в этих городах")
                                .setTextGravity(Gravity.CENTER_HORIZONTAL)
                                .setCancelable(false)
                                .addButton("Москва", -1, -1, CFAlertDialog.CFAlertActionStyle.DEFAULT, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                                    Toast.makeText(MainActivity.this, "Вы выбрали город Москву", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }).addButton("Санкт-Петербург", -1, -1, CFAlertDialog.CFAlertActionStyle.DEFAULT, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                                    Toast.makeText(MainActivity.this, "Вы выбрали город Санкт-Петербург", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }).addButton("Самара", -1, -1, CFAlertDialog.CFAlertActionStyle.DEFAULT, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                                    Toast.makeText(MainActivity.this, "Вы выбрали город Самару", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();}).show();
                    }
                });



    }

    //функция для определения местоположения
    private void loc_func(Location location) {
        try
        {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = null;
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String city = addresses.get(0).getLocality();
            Toast.makeText(this, "Город: " + city, Toast.LENGTH_SHORT).show();
            if(!city.equals("Москва") && !city.equals("Санкт-Петербург") && !city.equals("Самара"))
            {
                new CFAlertDialog.Builder(this)
                        .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET)
                        .setTitle("Внимание")
                        .setMessage("Вы находитесь в месте, где нет магазинов компании VESTA-TRADING. Они есть в городах: Москва, Санкт-Петербург, Самара. Пожалуйста, учтите это при заказе.")
                        .setTextGravity(Gravity.CENTER_HORIZONTAL)
                        .setCancelable(false)
                        .addButton("Понятно", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, (dialog, which) -> {
                            dialog.dismiss();
                        }).show();
            }

        }catch(IOException e){e.printStackTrace(); Toast.makeText(this, "Нет доступа к местоположению", Toast.LENGTH_SHORT).show();}




    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_view_catalog:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ViewCatalogFragment()).commit();
                break;
            case R.id.nav_sales:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SalesFragment()).commit();
                break;
            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CartFragment()).commit();
                break;
            case R.id.nav_order:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyOrderFragment()).commit();
                break;
            case R.id.nav_send:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SendFragment()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
