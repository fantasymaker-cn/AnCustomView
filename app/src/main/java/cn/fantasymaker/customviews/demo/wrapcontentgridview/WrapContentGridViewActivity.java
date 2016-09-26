/*
 *     Copyright © 2016 Fantasymaker
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy
 *     of this software and associated documentation files (the "Software"), to deal
 *     in the Software without restriction, including without limitation the rights
 *     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *     copies of the Software, and to permit persons to whom the Software is
 *     furnished to do so, subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all
 *     copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *     SOFTWARE.
 */

package cn.fantasymaker.customviews.demo.wrapcontentgridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fantasymaker.ancustomview.SquareImageView;
import cn.fantasymaker.ancustomview.WrapContentGridView;
import cn.fantasymaker.customviews.R;
import cn.fantasymaker.customviews.base.BaseActivity;

/**
 * Created :  2016-09-25
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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
