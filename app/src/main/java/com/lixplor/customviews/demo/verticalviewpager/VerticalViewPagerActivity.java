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

package com.lixplor.customviews.demo.verticalviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.lixplor.fastcustomview.VerticalViewPager;
import com.lixplor.customviews.R;
import com.lixplor.customviews.base.BaseActivity;

/**
 * Created :  2016-10-07
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class VerticalViewPagerActivity extends BaseActivity {
    @BindView(R.id.vpv)
    VerticalViewPager mVpv;

    private DemoAdapter mDemoAdapter;

    @Override
    protected int setLayout() {
        return R.layout.act_verticalviewpager;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        if (mDemoAdapter == null) {
            mDemoAdapter = new DemoAdapter();
            mVpv.setAdapter(mDemoAdapter);
        }
        mDemoAdapter.notifyDataSetChanged();
    }

    private class DemoAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(container.getContext());
            textView.setGravity(Gravity.CENTER);
            Random random = new Random();
            textView.setBackgroundColor(Color.rgb(random.nextInt(100) + 100, random.nextInt(100) + 100, random.nextInt(100) + 100));
            textView.setText("textview " + position);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
