package com.ying.easy_choice;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ying.easy_choice.utils.ViewTools;
import com.ying.easy_choice.view.IndexViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.ly_top_fill)
    LinearLayout ly_top_fill;
    /**
     * 视图翻页
     */
    @BindView(R.id.view_pager)
    IndexViewPager view_pager;

    /**
     * 随机选项
     */
    @BindView(R.id.tab_ly_random)
    LinearLayout tab_ly_random;
    @BindView(R.id.tab_img_random)
    ImageView tab_img_random;
    @BindView(R.id.tab_tev_random)
    TextView tab_tev_random;


    /**
     * 日程选项
     */
    @BindView(R.id.tab_ly_schedule)
    LinearLayout tab_ly_schedule;
    @BindView(R.id.tab_img_schedule)
    ImageView tab_img_schedule;
    @BindView(R.id.tab_tev_schedule)
    TextView tab_tev_schedule;

    /**
     * 我的选项
     */
    @BindView(R.id.tab_ly_personal)
    LinearLayout tab_ly_personal;
    @BindView(R.id.tab_img_personal)
    ImageView tab_img_personal;
    @BindView(R.id.tab_tev_personal)
    TextView tab_tev_personal;

    private List<Integer> tabs_drawable_id_select;
    private List<Integer> tabs_drawable_id_grey;

    private List<LinearLayout> tabs_ly;
    private List<ImageView> tabs_img;
    private List<TextView> tabs_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        initData();
        initView();
    }

    /**
     * 数据初始化
     */
    private void initData() {

    }

    /**
     * 控件初始化
     */
    private void initView() {
        ButterKnife.bind(this);//控件绑定
        initStatusBar();
        initViewPager();
    }

    /**
     * 初始化导航窗口
     */
    private void initViewPager() {
        tabs_drawable_id_select = Arrays.asList(R.drawable.random_select, R.drawable.schedule_select, R.drawable.personal_select);
        tabs_drawable_id_grey = Arrays.asList(R.drawable.random_grey, R.drawable.schedule_grey, R.drawable.personal_grey);
        tabs_ly = Arrays.asList(tab_ly_random, tab_ly_schedule, tab_ly_personal);
        tabs_img = Arrays.asList(tab_img_random, tab_img_schedule, tab_img_personal);
        tabs_text = Arrays.asList(tab_tev_random, tab_tev_schedule, tab_tev_personal);
        for (LinearLayout tab_ly : tabs_ly) {
            tab_ly.setOnClickListener(tabs_ly_onclick_listener);
        }
        selectItem(0);
        view_pager.setScanScroll(false);//设置禁止横向滑动
    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        ViewTools.setBarTextColor(this, true);
        if (ly_top_fill != null) {
            int status_bar_height = ViewTools.getStatusBarHeight(getApplicationContext());
            ly_top_fill.setPadding(0, status_bar_height, 0, 0);
        }
    }

    /**
     * tab栏点击回调
     */
    View.OnClickListener tabs_ly_onclick_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LinearLayout tab_ly = (LinearLayout) view;
            //过滤非法视图响应
            if (tabs_ly.contains(tab_ly)) {
                selectItem(tabs_ly.indexOf(tab_ly));
            }
        }
    };

    private void selectItem(int idx) {
        for (int i = 0; i < tabs_img.size(); i++) {
            if (i == idx) {
                tabs_img.get(i).setBackgroundResource(tabs_drawable_id_select.get(i));
                tabs_text.get(i).setTextColor(Color.parseColor("#429eed"));
            } else {
                tabs_img.get(i).setBackgroundResource(tabs_drawable_id_grey.get(i));
                tabs_text.get(i).setTextColor(Color.parseColor("#d3d3d3"));
            }
        }
    }

    /**
     * 视图点击事件
     *
     * @param view 被点击的视图
     */
    @Override
    public void onClick(View view) {

    }
}