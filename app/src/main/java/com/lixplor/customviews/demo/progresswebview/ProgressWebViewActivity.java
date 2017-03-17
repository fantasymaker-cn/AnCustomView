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
 * Author  :  Fantasymaker
 * Web     :  http://blog.fantasymaker.cn
 * Email   :  me@fantasymaker.cn
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
