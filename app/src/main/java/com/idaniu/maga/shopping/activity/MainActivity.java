package com.idaniu.maga.shopping.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idaniu.maga.shopping.R;
import com.idaniu.maga.shopping.fragment.Fragment1;
import com.idaniu.maga.shopping.fragment.Fragment2;
import com.idaniu.maga.shopping.fragment.Fragment3;
import com.idaniu.maga.shopping.fragment.Fragment4;
import com.idaniu.maga.shopping.fragment.Fragment5;

import java.util.ArrayList;
import java.util.List;

/**
 * description: ��ҳ
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;       //����չʾ���fragment��ViewPager
    private FragmentPagerAdapter mAdaper;   //FragmentPager��������
    private List<Fragment> fragmentList;	//������ż���Fragment

    private LinearLayout ll1;   //���������
    private LinearLayout ll2;
    private LinearLayout ll3;
    private LinearLayout ll4;
    private LinearLayout ll5;

    //�������е�ͼƬ�����ֿؼ�
    private  ImageView iv1;
    private  ImageView iv2;
    private  ImageView iv3;
    private  ImageView iv4;
    private  ImageView iv5;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    //���ֳ�ʼ��
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        fragmentList = new ArrayList<Fragment>();

        //5�����⣬�����ü���
        ll1 =(LinearLayout)findViewById(R.id.ll_1); //����������Ĳ��֣��ҵ�������������������
        ll2 = (LinearLayout) findViewById(R.id.ll_2);
        ll3 = (LinearLayout) findViewById(R.id.ll_3);
        ll4= (LinearLayout) findViewById(R.id.ll_4);
        ll5= (LinearLayout) findViewById(R.id.ll_5);
        //Ϊ����⼸���������ü���
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);

        //��������Fragment��ʵ�� ��ӵ�fragmentList��
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        Fragment5 fragment5 = new Fragment5();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);

        initTab();  //������Ŀؼ���ʼ��

        //��Adapter��������
        mAdaper = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

        //Ϊviewpager�ؼ����������
        mViewPager.setAdapter(mAdaper);

        //ҳ��ı�����¼��������������ɫ��Ӧ�ŷ����仯
        /*mViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                resetTabView();	//�ȳ�ʼ��

                switch (position) {
                    case 0:
                        iv1.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_1_red));
                        tv1.setTextColor(Color.RED);
                        break;
                    case 1:
                        iv2.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_2_red));
                        tv2.setTextColor(Color.RED);
                        break;
                    case 2:
                        iv3.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_3_red));
                        tv3.setTextColor(Color.RED);
                        break;
                    case 3:
                        iv4.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_4_red));
                        tv4.setTextColor(Color.RED);
                        break;
                    case 4:
                        iv5.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_5_red));
                        tv5.setTextColor(Color.RED);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                //arg0 Ϊpositionҳ����, float arg1Ϊoffsetҳ�淭ת��ɰٷֱ�, int arg2ΪoffsetPxҳ�淭ת��ɵ�����ֵ


             /*
                Log.d("tabline", arg0 + "," + arg1 + "," + arg2);
                //����tabline����߾���
                LinearLayout.LayoutParams lllp = (android.widget.LinearLayout.LayoutParams) tabLine.getLayoutParams();
                lllp.leftMargin = (int) (arg0*screen1_3 + arg1*screen1_3);
                tabLine.setLayoutParams(lllp);
             */
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    //��������Ŀؼ����ó�ʼ��
    private void initTab() {
        iv1 = (ImageView)findViewById(R.id.iv_1);
        iv2 = (ImageView)findViewById(R.id.iv_2);
        iv3 = (ImageView)findViewById(R.id.iv_3);
        iv4 = (ImageView)findViewById(R.id.iv_4);
        iv5 = (ImageView)findViewById(R.id.iv_5);

        tv1 = (TextView)findViewById(R.id.tv_1);
        tv2 = (TextView)findViewById(R.id.tv_2);
        tv3 = (TextView)findViewById(R.id.tv_3);
        tv4 = (TextView)findViewById(R.id.tv_4);
        tv5 = (TextView)findViewById(R.id.tv_5);

        //Ĭ��״̬����һ��ҳ��
        resetTabView();
        iv1.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_1_red));
        tv1.setTextColor(Color.RED);
    }

    //��������ʾ��ʼ״̬��ͼƬ�����֡�ÿ���л�ʱ���Ȼָ���ʼ״̬
    private void resetTabView() {
        iv1.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_1_gray));
        iv2.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_2_gray));
        iv3.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_3_gray));
        iv4.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_4_gray));
        iv5.setImageDrawable(getResources().getDrawable(R.drawable.tab_iv_5_gray));

        tv1.setTextColor(Color.GRAY);
        tv2.setTextColor(Color.GRAY);
        tv3.setTextColor(Color.GRAY);
        tv4.setTextColor(Color.GRAY);
        tv5.setTextColor(Color.GRAY);

    }

    //��������������ʾ��ͬҳ��
    @Override
    public void onClick(View v) {
        int index = 0;	//����ҳ������ֵ

        switch (v.getId()) {
            case R.id.ll_1:
                index = 0;
                break;
            case R.id.ll_2:
                index = 1;
                break;
            case R.id.ll_3:
                index = 2;
                break;
            case R.id.ll_4:
                index = 3;
                break;
            case R.id.ll_5:
                index = 4;
                break;
            default:
                break;
        }

        mViewPager.setCurrentItem(index);
    }
}
