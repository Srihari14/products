package com.pagination.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.pagination.BuildConfig;
import com.pagination.R;
import com.pagination.models.Product;
import com.pagination.utils.Constants;

import org.xml.sax.XMLReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_product_title)
    TextView tvProductTitle;
    @BindView(R.id.fl_product_preview)
    FrameLayout flProductPreview;
    @BindView(R.id.imv_main)
    ImageView imvMain;
    @BindView(R.id.tv_instock)
    TextView tvInstock;

    @BindView(R.id.tv_short_desc)
    TextView tvShortDesc;
    @BindView(R.id.tv_long_desc)
    TextView tvLongDesc;
    @BindView(R.id.tv_reviewRating)
    TextView tvReviewRating;
    @BindView(R.id.tv_reviewCount)
    TextView tvReviewCount;
    @BindView(R.id.tv_product_price)
    TextView tvProductPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        String value = getIntent().getStringExtra(Constants.PRODUCT_DETAILS);
        Gson gson = new Gson();
        Product product = gson.fromJson(value, Product.class);

        if (product != null) {

            ImageLoader.getInstance().displayImage(BuildConfig.IP_ADDRESS + product.getProductImage(), imvMain);
            tvProductTitle.setText(product.getProductName());
            if (product.getInStock() != null && product.getInStock()) {
                tvInstock.setVisibility(View.VISIBLE);
            } else {
                tvInstock.setVisibility(View.GONE);
            }

            tvProductPrice.setText("Price " + product.getPrice());
            tvReviewRating.setText(getString(R.string.review_amp_rating) + " " + product.getReviewRating());
            tvReviewCount.setText(getString(R.string.review_amp_count) + " " + product.getReviewCount());

            if (!TextUtils.isEmpty(product.getShortDescription())) {
                tvShortDesc.setText((Html.fromHtml(product.getShortDescription(), null, new UlTagHandler())));
            }
            if (!TextUtils.isEmpty(product.getLongDescription())) {
                tvLongDesc.setText((Html.fromHtml(product.getLongDescription(), null, new UlTagHandler())));
            }
        }


    }

    private class UlTagHandler implements Html.TagHandler {
        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            if (tag.equals("ul") && !opening) output.append("\n  ");
            if (tag.equals("li") && opening) output.append("\n\t•  ");
        }
    }
}
