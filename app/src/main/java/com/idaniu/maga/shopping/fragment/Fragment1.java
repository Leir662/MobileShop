package com.idaniu.maga.shopping.fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idaniu.maga.shopping.Constant;
import com.idaniu.maga.shopping.R;
import com.idaniu.maga.shopping.ShoppingApplication;
import com.idaniu.maga.shopping.bean.BannerBean;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ��һ��fragment��չʾ��ҳ
 * Created by yuanbao15 on 2017/10/8.
 */
public class Fragment1 extends Fragment{
    private static final String TAG = "HomeFragment";

    private ViewPager mViewPager;       //ͼƬ�ֲ����ViewPager
    private BannerPagerAdapter mBannerPagerAdapter;
    private LinearLayout mIndicateLinearLayout;     //Բ��ָʾ��
    private TextView mBannerTitleTextView;      //ͼƬ�ֲ���ÿ��ͼƬ�ı���
//    private RecyclerView mRecyclerView;
    private List<BannerBean> mBannerList;
    private List<ImageView> mIndicateList = new ArrayList<>();   //Բ�㼯��
    private List<ImageView> mBannerViewsList = new ArrayList<>();   //ͼƬչʾ�ļ���

//    private List<HomeBean> mData = new ArrayList<>();
//    private HomeAdapter mHomeAdapter;

    public static Fragment1 newInstance(String tabName){
        Fragment1 fragment = new Fragment1();
//        fragment.mTabName = tabName;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*        //��NetworkOnMainThreadException�쳣���������ҵ���һ��
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());*/

        View v = inflater.inflate(R.layout.frag1, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        mViewPager = (ViewPager) v.findViewById(R.id.viewpager_hotnews);
        mBannerTitleTextView =(TextView) v.findViewById(R.id.tv_title_hotnews);
        mIndicateLinearLayout =(LinearLayout) v.findViewById(R.id.ll_hotnews_indicator);

        mBannerPagerAdapter = new BannerPagerAdapter();
        mViewPager.setAdapter(mBannerPagerAdapter);     //��mViewPager�����������

        //ͼƬ�ֲ���viewpager��ҳ��ı�ʱ��
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for(int j=0; j<mIndicateLinearLayout.getChildCount(); j++){ //ѡ����ҳ����ת����һҳ
                    View view = mIndicateLinearLayout.getChildAt(j);
                    if(j == i){
                        view.setSelected(true);
                        mIndicateList.get(j).setBackgroundResource(R.drawable.indicate_circle_red); //ʵ��Բ���״̬�仯
                    }else{
                        view.setSelected(false);
                        mIndicateList.get(j).setBackgroundResource(R.drawable.indicate_circle_gray);
                    }
                }
                //��������ҲҪ��֮��Ӧ�ĸ���
                mBannerTitleTextView.setText(mBannerList.get(i).getName());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        loadHeaderData();       //��ͼƬ�ֲ���ͼƬ��Դ���ص�λ

    }

    //����ͼƬ��Դ��ʹ��okhttp��ʽ��
    private void loadHeaderData() {
        OkHttpClient client = new OkHttpClient();       //��������ʵ��
        //��������
        Request request = new Request.Builder()
                .url(Constant.HEAD_URL + "?type=1")
                .build();
        //��������
        client.newCall(request).enqueue(new Callback() {     //����ʹ�õ�execute()������ͬ����ʽ����ʦ����enqueue(new Callback())�����û�з���ֵ�ģ��첽��ʽ��
            @Override
            public void onFailure(Call call, IOException e) {
                if(getActivity() == null) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ShoppingApplication.getInstance(), "load data failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();     //��Gson����Json����
                Type type = new TypeToken<List<BannerBean>>(){}.getType();  //��䲻֪���Ǹ����ȡ���ͣ�
                mBannerList = gson.fromJson(responseData, type);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mBannerList != null && mBannerList.size()>=0){
                            mIndicateLinearLayout.removeAllViews(); //����������¼���
                            mBannerViewsList.clear();
                            mIndicateLinearLayout.removeAllViews();

                            for (int j=0; j<mBannerList.size(); j++){
                                //ͼƬ����
                                ImageView imageView = new ImageView(getActivity());
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT));  //���
                                imageView.setBackgroundResource(0);     //��䲻���� �ǳ�����������
                                mBannerViewsList.add(imageView);

                                //Բ��ָʾ������
                                ImageView indicateView = new ImageView(getActivity());
                                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(18,18);   //20*20�����ش�С
                                indicateView.setLayoutParams(lp);
                                indicateView.setBackgroundResource(0);
//                              indicateView.setImageResource(R.drawable.indicate_circle_gray);   //��ͨ������չʾ��������onPageSelected()����������ص���Ч������
//                              indicateView.setPadding(4, 4, 4, 4);    //ÿ��Բ��ͼƬ��ǰ�����Ҿ���
                                mIndicateLinearLayout.addView(indicateView);
                                mIndicateList.add(indicateView);

                            }
                            mBannerPagerAdapter.notifyDataSetChanged();
                            mIndicateLinearLayout.postDelayed(bannerRunnable, 1000);  //

                        }
                    }
                });
            }
        });


    }

    //ͼƬ�Զ��ֲ�����Բ����ô����ͼƬ��
    private Runnable bannerRunnable = new Runnable() {
        @Override
        public void run() {
            //Բ��ָʾ������ͼƬ�仯���仯
            int index = mViewPager.getCurrentItem();
            if(index == mBannerList.size()-1){  //ѭ���ظ�
                index = 0;
            }else{
                index = index+1;
            }

            mViewPager.setCurrentItem(index,true);
            mIndicateLinearLayout.postDelayed(bannerRunnable,3000);
        }
    };

    //�Զ���һ��BannerPagerAdapter������������ͷͼ������
    private class BannerPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            if(mBannerViewsList == null){
                return 0;
            }
            return mBannerViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        //�������������ǷǱ�����д�ģ�Ȼ������ʦ�ġ���Ҫ���������Ӧ��mBannerPagerAdapter.notifyDataSetChanged();
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mBannerViewsList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if(getActivity() == null || getActivity().isFinishing()) return null;
            ImageView view = mBannerViewsList.get(position);
            if(view.getParent() != null){
                ((ViewGroup)view.getParent()).removeView(view);
            }
            view.setImageResource(R.mipmap.ic_launcher);
            Picasso.with(getActivity()).load(mBannerList.get(position).getImgUrl()).into(view);
            container.addView(view);
            return view;
        }
    }


}
