package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.recyclerview.CouponAdapter;
import com.a1.nextlocation.recyclerview.CouponListManager;

import java.util.List;

public class StatisticFragment extends Fragment {

    private List<Coupon> couponList;
    private ImageView imageButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);

        this.couponList = CouponListManager.INSTANCE.getCouponList();
        CouponAdapter adapter = new CouponAdapter(this.getContext(), this.couponList);
        TextView couponNumber = view.findViewById(R.id.couponAmount);
        couponNumber.setText(String.valueOf(adapter.getItemCount()));

        
        this.imageButton = view.findViewById(R.id.route_back_button);
        this.imageButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).addToBackStack(null).commit();
        });

        ConstraintLayout constraintLayout = view.findViewById(R.id.Box4);
        constraintLayout.setOnClickListener(v -> {
            CouponFragment couponFragment = new CouponFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, couponFragment).addToBackStack(null).commit();
        });
        return view;
    }
}