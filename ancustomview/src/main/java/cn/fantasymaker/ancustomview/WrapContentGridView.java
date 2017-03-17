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
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * GridView which always wrap content
 *
 * Created :  2016-09-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class WrapContentGridView extends GridView {
    public WrapContentGridView(Context context) {
        this(context, null);
    }

    public WrapContentGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapContentGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Change height measure spec to AT_MOST (= wrap_content)
        // Integer.MAX_VALUE >> 2: calculate entire height by providing a very large height hint.
        // But do not use the highest 2 bits of this integer
        // as the highest 2 bits are reserved for the MeasureSpec mode.
        int wrapContentSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, wrapContentSpec);
    }
}
