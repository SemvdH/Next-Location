package com.a1.nextlocation.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
    private ImageButton imageButton;

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

        this.imageButton = view.findViewById(R.id.couponBackButton);
        this.imageButton.setOnClickListener(v -> {
            StatisticFragment statisticFragment = new StatisticFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, statisticFragment).addToBackStack(null).commit();
        });

        CouponListManager.INSTANCE.setContext(this.getContext());
        CouponListManager.INSTANCE.load();
        this.couponList = CouponListManager.INSTANCE.getCouponList();

        this.couponAdapter = new CouponAdapter(this.getContext(), this.couponList, clickedPosition -> showPopup(this.couponList.get(clickedPosition)));

        this.couponRecyclerView.setLayoutManager(this.layoutManager);
        this.couponRecyclerView.setAdapter(this.couponAdapter);
        return view;
    }

    private void showPopup(Coupon coupon) {
        AlertDialog.Builder activateBuilder = new AlertDialog.Builder(getContext());
        AlertDialog.Builder couponCodeBuilder = new AlertDialog.Builder(getContext());
        activateBuilder.setMessage(getResources().getString(R.string.activate_question));
        activateBuilder.setCancelable(true);
        activateBuilder.setPositiveButton(R.string.activate, (dialog, which) -> {
            dialog.cancel();
            couponCodeBuilder.setMessage("Code: " + coupon.getCode());
            couponCodeBuilder.setPositiveButton(R.string.done, (dialog1, which1) -> {
                dialog.cancel();
            });
            AlertDialog couponCodePopup = couponCodeBuilder.create();
            couponCodePopup.show();
        });
        activateBuilder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());
        AlertDialog couponPopup = activateBuilder.create();
        couponPopup.show();

    }
}