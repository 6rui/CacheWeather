package com.demo.liur.cacheweather.ui.area_list;

import com.demo.liur.cacheweather.R;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

public class AreaListActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private Toolbar toolbar;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_list);
        initView();
    }

    /**
     * 初始化布局控件
     */
    private void initView() {
        collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        collapsingToolbarLayout.setTitle("卡奇天气");
        toolbar.setTitle("地区选择");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter<AViewHolder>() {
            @Override
            public AViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new AViewHolder(LayoutInflater.from(AreaListActivity.this)
                        .inflate(R.layout.item_area_list, parent, false));
            }

            @Override
            public void onBindViewHolder(AViewHolder holder, int position) {
                holder.textView.setText("GG");
            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });

    }

    class AViewHolder extends ViewHolder {
        TextView textView;

        public AViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
