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

package cn.fantasymaker.ancustomview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * WebView with progress bar
 *
 * Created :  2016-09-28
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class ProgressWebView extends WebView {

    private Context mContext;
    private ProgressBar mProgressBar;

    private int mProgressBarColor = Color.RED;
    private int mProgressBarHeight;
    private int mMeasuredWidth;

    public ProgressWebView(Context context) {
        this(context, null);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        mProgressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleHorizontal);
        this.addView(mProgressBar);
        // init default height
        mProgressBarHeight = (int) dpToPx(4);
        // set default progress drawable
        setProgressBarColor(mProgressBarColor);
        mProgressBar.setIndeterminate(false);
        // set web chrome client to listen to loading progress
        this.setWebChromeClient(new ProgressbarClient());
        // set webview client to load url within webview
        this.setWebViewClient(new WebClient());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasuredWidth = getMeasuredWidth();
        ViewGroup.LayoutParams layoutParams = mProgressBar.getLayoutParams();
        layoutParams.height = mProgressBarHeight;
        layoutParams.width = mMeasuredWidth;
        mProgressBar.setLayoutParams(layoutParams);
    }

    /**
     * Set progress bar color
     *
     * @param color color
     */
    public void setProgressBarColor(int color) {
        ClipDrawable progressDrawable = new ClipDrawable(new ColorDrawable(color), Gravity.LEFT, ClipDrawable.HORIZONTAL);
        mProgressBar.setProgressDrawable(progressDrawable);
    }

    /**
     * Set progress bar drawable
     *
     * @param drawable drawable
     */
    public void setProgressBarDrawable(Drawable drawable) {
        mProgressBar.setProgressDrawable(drawable);
    }

    /**
     * set progress bar height
     *
     * @param dp height in dp
     */
    public void setProgressBarHeight(int dp) {
        mProgressBarHeight = (int) dpToPx(dp);
        ViewGroup.LayoutParams layoutParams = mProgressBar.getLayoutParams();
        layoutParams.height = mProgressBarHeight;
        layoutParams.width = mMeasuredWidth;
        mProgressBar.setLayoutParams(layoutParams);
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }

    private class ProgressbarClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                if (mProgressBar.getVisibility() == View.GONE) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
                mProgressBar.setSecondaryProgress(5); // use sec progress to indicate loading start
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private class WebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // do not open external browser
            view.loadUrl(url);
            return true;
        }
    }
}
