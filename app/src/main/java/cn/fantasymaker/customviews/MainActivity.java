package cn.fantasymaker.customviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fantasymaker.ancustomview.ProgressWebView;
import cn.fantasymaker.ancustomview.SquareImageView;
import cn.fantasymaker.ancustomview.WrapContentGridView;
import cn.fantasymaker.customviews.bean.IntentBean;
import cn.fantasymaker.customviews.constant.Constant;
import cn.fantasymaker.customviews.demo.progresswebview.ProgressWebViewActivity;
import cn.fantasymaker.customviews.demo.squareimageview.SquareImageViewActivity;
import cn.fantasymaker.customviews.demo.wrapcontentgridview.WrapContentGridViewActivity;


public class MainActivity extends AppCompatActivity {

    public static ArrayList<IntentBean> sIntentList = new ArrayList<>();

    @BindView(R.id.rv_btns)
    RecyclerView mRvBtns;
    private ButtonAdapter mButtonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRvBtns.setLayoutManager(new LinearLayoutManager(this));
        mRvBtns.setHasFixedSize(true);
        initData();
        if (mButtonAdapter == null) {
            Log.d("aa", sIntentList.toString());
            mButtonAdapter = new ButtonAdapter(sIntentList);
            mButtonAdapter.setOnItemClickListener(new ButtonAdapter.OnItemClickListener() {
                @Override
                public void onItemClicked(View view, int position) {
                    IntentBean intentBean = sIntentList.get(position);
                    String viewName = intentBean.getName();
                    Class customViewClass = intentBean.getClazz();
                    Intent intent = new Intent(MainActivity.this, customViewClass);
                    intent.putExtra(Constant.IntentKey.KEY_TITLE_NAME, viewName);
                    MainActivity.this.startActivity(intent);
                }
            });
            mRvBtns.setAdapter(mButtonAdapter);
        }
        mButtonAdapter.notifyDataSetChanged();
    }

    private void initData() {
        sIntentList.add(new IntentBean("正方形ImageView", SquareImageView.class.getSimpleName(), SquareImageViewActivity.class));
        sIntentList.add(new IntentBean("包裹内容GridView", WrapContentGridView.class.getSimpleName(), WrapContentGridViewActivity.class));
        sIntentList.add(new IntentBean("带进度条WebView", ProgressWebView.class.getSimpleName(), ProgressWebViewActivity.class));
    }

    public static class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder> {

        private ArrayList<IntentBean> mList;
        private Context mContext;
        private OnItemClickListener mOnItemClickListener;

        public ButtonAdapter(ArrayList<IntentBean> list) {
            mList = list;
        }

        @Override
        public ButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            mContext = parent.getContext();
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_btn, parent, false);
            return new ButtonViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ButtonViewHolder holder, final int position) {
            IntentBean intentBean = mList.get(position);
            String buttonText = intentBean.getNameZh();
            holder.mTv.setText(buttonText);
            holder.mLlRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClicked(v, position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            mOnItemClickListener = onItemClickListener;
        }

        public static class ButtonViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv)
            TextView mTv;
            @BindView(R.id.ll_root)
            LinearLayout mLlRoot;

            public ButtonViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        public interface OnItemClickListener {
            void onItemClicked(View view, int position);
        }
    }
}
