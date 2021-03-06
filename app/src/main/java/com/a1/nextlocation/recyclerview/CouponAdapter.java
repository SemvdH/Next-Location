package com.a1.nextlocation.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Coupon;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponViewHolder> {

    private final Context appContext;
    private final List<Coupon> couponList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    class CouponViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView couponCode;

        public CouponViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        /**
         * Sets the text of the coupon
         *
         * @param text the text that will be set
         */
        public void setTextViewName(String text) {
            TextView couponReward = itemView.findViewById(R.id.coupon_name);
            couponReward.setText(text);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition());
        }
    }

    public CouponAdapter(Context context, List<Coupon> coupon, OnItemClickListener listener) {
        this.appContext = context;
        this.couponList = coupon;
        this.clickListener = listener;

    }

    public CouponAdapter(Context context, List<Coupon> coupon) {
        this.appContext = context;
        this.couponList = coupon;
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false);
        return new CouponViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {
        Coupon coupon = couponList.get(position);
        holder.setTextViewName(coupon.getReward());
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

}
