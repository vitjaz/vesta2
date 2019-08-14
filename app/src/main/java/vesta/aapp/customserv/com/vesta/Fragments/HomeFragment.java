package vesta.aapp.customserv.com.vesta.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vesta.aapp.customserv.com.vesta.Adapters.CustomSwipeAdapter;
import vesta.aapp.customserv.com.vesta.R;


public class HomeFragment extends Fragment {

    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.ViewPager);
        adapter = new CustomSwipeAdapter(view.getContext());
        viewPager.setAdapter(adapter);

        return view;
    }

}
