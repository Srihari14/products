package com.pagination.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pagination.R;
import com.pagination.adapters.ProductsAdapter;
import com.pagination.common.NetworkCallOperations;
import com.pagination.listners.OnResultListner;
import com.pagination.models.AllProducts;
import com.pagination.models.Product;
import com.pagination.utils.Logger;
import com.pagination.utils.RecyclerViewScrollListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvNoData)
    TextView tvNoData;
    @BindView(R.id.progress_load_more)
    ProgressBar progressLoadMore;
    @BindView(R.id.progress_center)
    ProgressBar progressCenter;


    private int TOTAL_PRODUCT_PAGE_COUNT;
    private int currentPage = 1;
    private ProductsAdapter productssAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getProdcutsData(currentPage);


    }

    private void getProdcutsData(final int currentPage) {
        boolean loader = true;
        if (currentPage > 1) {
            loader = false;
            progressLoadMore.setVisibility(View.VISIBLE);
        }else {
            progressCenter.setVisibility(View.VISIBLE);
        }
        NetworkCallOperations.getInstance().getLoanApplications(this, new OnResultListner() {
            @Override
            public void getResult(Object object, boolean isSuccess) {
                progressCenter.setVisibility(View.GONE);
                progressLoadMore.setVisibility(View.GONE);
                if (isSuccess) {
                    AllProducts allProducts = (AllProducts) object;

                    TOTAL_PRODUCT_PAGE_COUNT = (int) Math.ceil(allProducts.getTotalProducts() / allProducts.getPageSize());

                    if (currentPage == 1) {
                        setUpLayoutManagers();
                    }

                    setDataToAdatper(allProducts.getProducts());
                } else {
                    setEmtyView(true);
                }
            }
        }, currentPage);
    }

    private void setDataToAdatper(List<Product> data) {
        if (data != null && data.size() > 0) {
            setEmtyView(false);
            productssAdapter.setMoreOrders(data);
        } else {
            setEmtyView(true);
        }
        //Hide products list if no prodcuts
        if (productssAdapter == null) {
            setEmtyView(true);

        } else {
            if (productssAdapter.getItemCount() <= 0) {
                setEmtyView(true);
            }
        }
    }

    private void setEmtyView(boolean b) {
        if (b) {
            recyclerView.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
        }
    }

    private void setUpLayoutManagers() {
        productssAdapter = new ProductsAdapter(this, new OnResultListner() {
            @Override
            public void getResult(Object object, boolean isSuccess) {
                if (isSuccess) {
                }
            }
        });
        recyclerView.setAdapter(productssAdapter);
        scrollListnerForProductsList();

    }

    private void scrollListnerForProductsList() {
        recyclerView.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {

            }

            @Override
            public void onLoadMore() {
                Logger.getInstance().d("loadMoreItems>" + currentPage + "---" + TOTAL_PRODUCT_PAGE_COUNT);
                if (currentPage < TOTAL_PRODUCT_PAGE_COUNT && TOTAL_PRODUCT_PAGE_COUNT != 0) {
                    currentPage = currentPage + 1;
                    getProdcutsData(currentPage);
                }
            }
        });
    }

}
