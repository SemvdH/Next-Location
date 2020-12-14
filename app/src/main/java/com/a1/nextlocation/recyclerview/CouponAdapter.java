package com.a1.nextlocation.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1.nextlocation.data.Coupon;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponViewHolder> {

    private Context appContext;
    private List<Coupon> couponList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    class CouponViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CouponViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            clickListener.onItemClick(clickedPosition);
        }
    }

    public CouponAdapter(Context context, List<Coupon> coupon, OnItemClickListener listener){
        appContext = context;
        couponList = coupon;
        clickListener = listener;
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }




}
