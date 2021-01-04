package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.recyclerview.CouponAdapter;
import com.a1.nextlocation.recyclerview.CouponListManager;

import java.util.List;

public class CouponFragment extends Fragment {

    private RecyclerView couponRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Coupon> couponList;
    private CouponAdapter couponAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);

        this.couponRecyclerView = view.findViewById(R.id.couponRecyclerView);
        this.couponRecyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this.getContext());

        CouponListManager.INSTANCE.setContext(this.getContext());
        CouponListManager.INSTANCE.load();
        this.couponList = CouponListManager.INSTANCE.getCouponList();

        this.couponAdapter = new CouponAdapter(this.getContext(), this.couponList, onClickedItem -> showPopup());

        this.couponRecyclerView.setLayoutManager(this.layoutManager);
        this.couponRecyclerView.setAdapter(this.couponAdapter);
        return view;
    }

    private void showPopup() {

    }
}