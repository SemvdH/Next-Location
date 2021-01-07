package com.a1.nextlocation.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.Data;
import com.a1.nextlocation.recyclerview.CouponAdapter;
import com.a1.nextlocation.recyclerview.CouponListManager;

import java.util.List;

public class StatisticFragment extends Fragment {
    private TextView distance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);

        initializeDistanceTextView(view);
        TextView locs = view.findViewById(R.id.statistics_locations_visited);
        TextView timeText = view.findViewById(R.id.statistics_time_value);

        locs.setText("" + Data.INSTANCE.getLocationsVisited());

        long seconds = Data.INSTANCE.getTotalTime() / 1000;
        long p1 = seconds % 60;
        long p2 = seconds / 60;
        long p3 = p2 % 60;
        p2 = p2 / 60;
        timeText.setText(p2 + "u, " + p3 + "m, " + p1 + "s");


        //loads the couponList
        List<Coupon> couponList = CouponListManager.INSTANCE.getCouponList();
        CouponAdapter adapter = new CouponAdapter(this.getContext(), couponList);
        TextView couponNumber = view.findViewById(R.id.couponAmount);
        couponNumber.setText(String.valueOf(adapter.getItemCount()));

        //Initialises the back button
        ImageView backButton = view.findViewById(R.id.statistics_back_button);
        backButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).addToBackStack(null).commit();
        });

        //Initialises the coupon button
        ImageView couponButton = view.findViewById(R.id.coupon_button);
        couponButton.setOnClickListener(v -> {
            CouponFragment couponFragment = new CouponFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, couponFragment).addToBackStack(null).commit();
        });

        //Makes the constraintlayout clickable and opens the same layout as the coupon button
        ConstraintLayout constraintLayout = view.findViewById(R.id.Box4);
        constraintLayout.setOnClickListener(v -> {
            CouponFragment couponFragment = new CouponFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, couponFragment).addToBackStack(null).commit();
        });
        return view;
    }

    private void initializeDistanceTextView(View view){
        distance = view.findViewById(R.id.statistics_km);
        double dist = Data.INSTANCE.getDistanceTraveled()/1000;
        if (getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).getBoolean("imperialSwitch", false))
            distance.setText(""  + String.format("%.1f",dist * 0.621371) + " mi");
        else
            distance.setText(""  + String.format("%.1f",dist) + " km");
    }
}