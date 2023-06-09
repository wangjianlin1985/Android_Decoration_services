package com.mobileclient.activity;

import java.util.Date;
import com.mobileclient.domain.MealEvaluate;
import com.mobileclient.service.MealEvaluateService;
import com.mobileclient.domain.WashMeal;
import com.mobileclient.service.WashMealService;
import com.mobileclient.domain.UserInfo;
import com.mobileclient.service.UserInfoService;
import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
public class MealEvaluateDetailActivity extends Activity {
	// 声明返回按钮
	private Button btnReturn;
	// 声明评价id控件
	private TextView TV_evaluateId;
	// 声明被评套餐控件
	private TextView TV_washMealObj;
	// 声明评价内容控件
	private TextView TV_evaluateContent;
	// 声明评价用户控件
	private TextView TV_userObj;
	// 声明评价时间控件
	private TextView TV_evaluateTime;
	/* 要保存的套餐评价信息 */
	MealEvaluate mealEvaluate = new MealEvaluate(); 
	/* 套餐评价管理业务逻辑层 */
	private MealEvaluateService mealEvaluateService = new MealEvaluateService();
	private WashMealService washMealService = new WashMealService();
	private UserInfoService userInfoService = new UserInfoService();
	private int evaluateId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// 设置当前Activity界面布局
		setContentView(R.layout.mealevaluate_detail);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("查看套餐评价详情");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		// 通过findViewById方法实例化组件
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_evaluateId = (TextView) findViewById(R.id.TV_evaluateId);
		TV_washMealObj = (TextView) findViewById(R.id.TV_washMealObj);
		TV_evaluateContent = (TextView) findViewById(R.id.TV_evaluateContent);
		TV_userObj = (TextView) findViewById(R.id.TV_userObj);
		TV_evaluateTime = (TextView) findViewById(R.id.TV_evaluateTime);
		Bundle extras = this.getIntent().getExtras();
		evaluateId = extras.getInt("evaluateId");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				MealEvaluateDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* 初始化显示详情界面的数据 */
	private void initViewData() {
	    mealEvaluate = mealEvaluateService.GetMealEvaluate(evaluateId); 
		this.TV_evaluateId.setText(mealEvaluate.getEvaluateId() + "");
		WashMeal washMealObj = washMealService.GetWashMeal(mealEvaluate.getWashMealObj());
		this.TV_washMealObj.setText(washMealObj.getMealName());
		this.TV_evaluateContent.setText(mealEvaluate.getEvaluateContent());
		UserInfo userObj = userInfoService.GetUserInfo(mealEvaluate.getUserObj());
		this.TV_userObj.setText(userObj.getName());
		this.TV_evaluateTime.setText(mealEvaluate.getEvaluateTime());
	} 
}
