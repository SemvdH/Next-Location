package com.a1.nextlocation.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private ImageButton backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);

        //Makes the recyclerview
        this.couponRecyclerView = view.findViewById(R.id.coupon_recyclerview);
        this.couponRecyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this.getContext());


        //Loads the couponList
        CouponListManager.INSTANCE.setContext(this.getContext());
        CouponListManager.INSTANCE.load();
        this.couponList = CouponListManager.INSTANCE.getCouponList();

        this.couponAdapter = new CouponAdapter(this.getContext(), this.couponList, clickedPosition -> showPopup(this.couponList.get(clickedPosition)));

        //Initialises the back button
        this.backButton = view.findViewById(R.id.coupon_back_button);
        this.backButton.setOnClickListener(v -> {
            StatisticFragment statisticFragment = new StatisticFragment();
            if (getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, statisticFragment).addToBackStack(null).commit();
        });

        this.couponRecyclerView.setLayoutManager(this.layoutManager);
        this.couponRecyclerView.setAdapter(this.couponAdapter);
        return view;
    }

    /**
     * shows the popup of a coupon
     *
     * @param coupon the coupon that will be displayed
     */
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