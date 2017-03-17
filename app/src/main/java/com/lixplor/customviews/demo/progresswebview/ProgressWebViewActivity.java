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

package com.lixplor.customviews.demo.progresswebview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lixplor.fastcustomview.ProgressWebView;
import com.lixplor.customviews.R;
import com.lixplor.customviews.base.BaseActivity;

/**
 * Created :  2016-09-28
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ProgressWebViewActivity extends BaseActivity {

    @BindView(R.id.pwv)
    ProgressWebView mPwv;
    @BindView(R.id.et_url)
    EditText mEtUrl;
    @BindView(R.id.btn_browse)
    Button mBtnBrowse;

    @Override
    protected int setLayout() {
        return R.layout.act_progresswebview;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mEtUrl.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    mBtnBrowse.callOnClick();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mPwv.getSettings().setJavaScriptEnabled(true);
        // custom ProgressBar in ProgressWebView
        mPwv.setProgressBarColor(Color.GREEN);
        mPwv.setProgressBarHeight(4);
    }

    @OnClick(R.id.btn_browse)
    public void onClick() {
        String url = mEtUrl.getText().toString();
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(ProgressWebViewActivity.this, "请输入网址", Toast.LENGTH_LONG).show();
        } else {
            if (!url.startsWith("http://")) {
                url = "http://" + url;
            }
            mPwv.loadUrl(url);
        }
    }
}
