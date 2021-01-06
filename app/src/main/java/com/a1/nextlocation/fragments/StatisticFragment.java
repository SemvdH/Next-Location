package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.StaticData;
import com.a1.nextlocation.recyclerview.CouponAdapter;
import com.a1.nextlocation.recyclerview.CouponListManager;

import java.util.List;

public class StatisticFragment extends Fragment {

    private List<Coupon> couponList;
    private ImageView imageButton;
    private ImageView couponButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);

        TextView distance = view.findViewById(R.id.statistics_km);
        TextView locs = view.findViewById(R.id.statistics_locations_visited);
        TextView timeText = view.findViewById(R.id.statistics_time_value);
        double dist = StaticData.INSTANCE.getDistanceTraveled()/1000;
        distance.setText(""  + String.format("%.1f",dist) + " km");
        locs.setText("" + StaticData.INSTANCE.getLocationsVisited());
        
        long seconds = StaticData.INSTANCE.getTimeWalkedRoute() / 1000;
        long p1 = seconds % 60;
        long p2 = seconds / 60;
        long p3 = p2 % 60;
        p2 = p2 / 60;
        timeText.setText(p2 + ":" + p3 + ":" + p1);


        this.couponList = CouponListManager.INSTANCE.getCouponList();
        CouponAdapter adapter = new CouponAdapter(this.getContext(), this.couponList);
        TextView couponNumber = view.findViewById(R.id.couponAmount);
        couponNumber.setText(String.valueOf(adapter.getItemCount()));


        this.imageButton = view.findViewById(R.id.statistics_back_button);
        this.imageButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).addToBackStack(null).commit();
        });

        this.couponButton = view.findViewById(R.id.coupon_button);
        this.couponButton.setOnClickListener(v -> {
            CouponFragment couponFragment = new CouponFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, couponFragment).addToBackStack(null).commit();
        });

        ConstraintLayout constraintLayout = view.findViewById(R.id.Box4);
        constraintLayout.setOnClickListener(v -> {
            CouponFragment couponFragment = new CouponFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, couponFragment).addToBackStack(null).commit();
        });
        return view;
    }
}