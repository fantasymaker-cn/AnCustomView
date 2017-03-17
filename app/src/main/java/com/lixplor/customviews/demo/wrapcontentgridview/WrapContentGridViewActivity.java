/*
 *  Copyright 2016 Lixplor
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.lixplor.customviews.demo.wrapcontentgridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.lixplor.fastcustomview.SquareImageView;
import com.lixplor.fastcustomview.WrapContentGridView;
import com.lixplor.customviews.R;
import com.lixplor.customviews.base.BaseActivity;

/**
 * Created :  2016-09-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class WrapContentGridViewActivity extends BaseActivity {

    @BindView(R.id.wcgv)
    WrapContentGridView mWcgv;

    private GridAdapter mGridAdapter;

    @Override
    protected int setLayout() {
        return R.layout.act_wrapcontentgridview;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        if (mGridAdapter == null) {
            mGridAdapter = new GridAdapter();
            mWcgv.setAdapter(mGridAdapter);
        }
        mGridAdapter.notifyDataSetChanged();
    }

    public static class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.item_wcgv, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        public static class ViewHolder {
            @BindView(R.id.siv)
            SquareImageView mSiv;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
