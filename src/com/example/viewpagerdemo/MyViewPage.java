package com.example.viewpagerdemo;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MyViewPage extends Activity {

	   private ViewPager viewPager;//页卡内容  
	    private ImageView imageView;// 动画图片  
	    private TextView textView1,textView2,textView3;  
	    private List<View> views;// Tab页面列表  
	    private int offset = 0;// 动画图片偏移量  
	    private int currIndex = 0;// 当前页卡编号  
	    private int bmpW;// 动画图片宽度  
	    private View view1,view2,view3;//各个页卡  
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.viewpage);  
	        InitImageView();  
	        InitTextView();  
	        InitViewPager();  
	    }  
	    private void InitViewPager() {  
	        viewPager=(ViewPager) findViewById(R.id.vPager);  
	        views=new ArrayList<View>();  
	        LayoutInflater inflater=getLayoutInflater();  
	        view1=inflater.inflate(R.layout.lay1, null);  
	        view2=inflater.inflate(R.layout.lay2, null);  
	        view3=inflater.inflate(R.layout.lay3, null);  
	        views.add(view1);  
	        views.add(view2);  
	        views.add(view3);  
	        viewPager.setAdapter(new MyViewPagerAdapter(views));  
	        viewPager.setCurrentItem(0);  
	        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());  
	    }  
	     /** 
	      *  初始化头标 
	      */  
	  
	    private void InitTextView() {  
	        textView1 = (TextView) findViewById(R.id.text1);  
	        textView2 = (TextView) findViewById(R.id.text2);  
	        textView3 = (TextView) findViewById(R.id.text3);  
	  
	        textView1.setOnClickListener(new MyOnClickListener(0));  
	        textView2.setOnClickListener(new MyOnClickListener(1));  
	        textView3.setOnClickListener(new MyOnClickListener(2));  
	    }  
	  
	    /** 
	     2      * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据 
	     3 */  
	  
	    private void InitImageView() {  
	        imageView= (ImageView) findViewById(R.id.cursor);  
	        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// 获取图片宽度  
	        DisplayMetrics dm = new DisplayMetrics();  
	        getWindowManager().getDefaultDisplay().getMetrics(dm);  
	        int screenW = dm.widthPixels;// 获取分辨率宽度  
	        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量  
	        Matrix matrix = new Matrix();  
	        matrix.postTranslate(offset, 0);  
	        imageView.setImageMatrix(matrix);// 设置动画初始位置  
	    }  
//	<img src="http://img.my.csdn.net/uploads/201211/10/1352554452_1685.jpg" alt="">  
	    /**  
	     *      
	     * 头标点击监听 3 */  
	    private class MyOnClickListener implements OnClickListener{  
	        private int index=0;  
	        public MyOnClickListener(int i){  
	            index=i;  
	        }  
	        public void onClick(View v) {  
	            viewPager.setCurrentItem(index);              
	        }  
	          
	    }  
	      
	    
	  
	    public class MyOnPageChangeListener implements OnPageChangeListener{  
	  
	        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量  
	        int two = one * 2;// 页卡1 -> 页卡3 偏移量  
	        public void onPageScrollStateChanged(int arg0) {  
	              
	              
	        }  
	  
	        public void onPageScrolled(int arg0, float arg1, int arg2) {  
	              
	              
	        }  
	  
	        public void onPageSelected(int arg0) {  
	            /*两种方法，这个是一种，下面还有一种，显然这个比较麻烦 
	            Animation animation = null; 
	            switch (arg0) { 
	            case 0: 
	                if (currIndex == 1) { 
	                    animation = new TranslateAnimation(one, 0, 0, 0); 
	                } else if (currIndex == 2) { 
	                    animation = new TranslateAnimation(two, 0, 0, 0); 
	                } 
	                break; 
	            case 1: 
	                if (currIndex == 0) { 
	                    animation = new TranslateAnimation(offset, one, 0, 0); 
	                } else if (currIndex == 2) { 
	                    animation = new TranslateAnimation(two, one, 0, 0); 
	                } 
	                break; 
	            case 2: 
	                if (currIndex == 0) { 
	                    animation = new TranslateAnimation(offset, two, 0, 0); 
	                } else if (currIndex == 1) { 
	                    animation = new TranslateAnimation(one, two, 0, 0); 
	                } 
	                break; 
	                 
	            } 
	            */  
	            Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);//显然这个比较简洁，只有一行代码。  
	            currIndex = arg0;  
	            animation.setFillAfter(true);// True:图片停在动画结束位置  
	            animation.setDuration(300);  
	            imageView.startAnimation(animation);  
	            Toast.makeText(MyViewPage.this, "您选择了"+ viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();  
	        }  
	          
	    }  
	}  