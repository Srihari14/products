package com.pagination.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.pagination.BuildConfig;
import com.pagination.R;
import com.pagination.activity.ProductDetailsActivity;
import com.pagination.listners.OnResultListner;
import com.pagination.models.Product;
import com.pagination.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajkumar
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Product> loanInfoModelArrayList;
    private LayoutInflater mInflater;
    private Context context;
    private OnResultListner onResultListner;

    public ProductsAdapter(Context context, OnResultListner onResultListner) {
        this.mInflater = LayoutInflater.from(context);
        this.loanInfoModelArrayList = new ArrayList<>();
        this.onResultListner = onResultListner;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.light_grey));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.light_grey_1));
        }

        final Product product = loanInfoModelArrayList.get(position);

        if (product != null) {

            ImageLoader.getInstance().displayImage(BuildConfig.IP_ADDRESS + product.getProductImage(), holder.imvProduct);
            holder.tvProductDesc.setText(product.getProductName());
            if (product.getInStock() != null && product.getInStock()) {
                holder.tv_instock.setVisibility(View.VISIBLE);
            } else {
                holder.tv_instock.setVisibility(View.GONE);
            }

            holder.tvProductPrice.setText("Price " + product.getPrice());

            holder.tvReviewRating.setText(context.getString(R.string.review_amp_rating) + " " + product.getReviewRating());
            holder.tvReviewCount.setText(context.getString(R.string.review_amp_count) + " " + product.getReviewCount());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    Gson gson = new Gson();
                    intent.putExtra(Constants.PRODUCT_DETAILS, gson.toJson(product));
                    context.startActivity(intent);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return loanInfoModelArrayList.size();
    }

    public void setMoreOrders(List<Product> productArrayList) {
        int prevCount = loanInfoModelArrayList.size();

        loanInfoModelArrayList.addAll(productArrayList);
        notifyItemRangeInserted(prevCount, loanInfoModelArrayList.size() - prevCount);

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_product_item)
        LinearLayout llProductItem;
        @BindView(R.id.imv_product)
        ImageView imvProduct;
        @BindView(R.id.tv_instock)
        TextView tv_instock;
        @BindView(R.id.tv_product_desc)
        TextView tvProductDesc;
        @BindView(R.id.tv_product_price)
        TextView tvProductPrice;
        @BindView(R.id.tv_reviewRating)
        TextView tvReviewRating;
        @BindView(R.id.tv_reviewCount)
        TextView tvReviewCount;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}