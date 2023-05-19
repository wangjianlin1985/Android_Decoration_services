package com.mobileclient.activity;

import java.util.Date;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.OrderInfo;
import com.mobileclient.service.OrderInfoService;
import com.mobileclient.domain.WashMeal;
import com.mobileclient.service.WashMealService;
import com.mobileclient.domain.UserInfo;
import com.mobileclient.service.UserInfoService;
import com.mobileclient.domain.OrderState;
import com.mobileclient.service.OrderStateService;
import com.mobileclient.util.ActivityUtils;
import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import android.app.Activity;
import android.content.Intent;
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
public class OrderInfoDetailActivity extends Activity {
	// �������ذ�ť
	private Button btnReturn;
	// ����������ſؼ�
	private TextView TV_orderId;
	// ����װ���ײͿؼ�
	private TextView TV_washMealObj;
	// ����Ԥ�������ؼ�
	private TextView TV_orderCount;
	// �����µ��û��ؼ�
	private TextView TV_userObj;
	// ������ϵ�绰�ؼ�
	private TextView TV_telephone;
	// �����µ�ʱ��ؼ�
	private TextView TV_orderTime;
	// ��������״̬�ؼ�
	private TextView TV_orderState;
	// ������ע��Ϣ�ؼ�
	private TextView TV_memo;
	/* Ҫ����Ķ�����Ϣ */
	OrderInfo orderInfo = new OrderInfo(); 
	/* ��������ҵ���߼��� */
	private OrderInfoService orderInfoService = new OrderInfoService();
	private WashMealService washMealService = new WashMealService();
	private UserInfoService userInfoService = new UserInfoService();
	private OrderStateService orderStateService = new OrderStateService();
	private int orderId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȥ��Activity�����״̬��
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.orderinfo_detail);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("�鿴��������");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		// ͨ��findViewById����ʵ�������
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_orderId = (TextView) findViewById(R.id.TV_orderId);
		TV_washMealObj = (TextView) findViewById(R.id.TV_washMealObj);
		TV_orderCount = (TextView) findViewById(R.id.TV_orderCount);
		TV_userObj = (TextView) findViewById(R.id.TV_userObj);
		TV_telephone = (TextView) findViewById(R.id.TV_telephone);
		TV_orderTime = (TextView) findViewById(R.id.TV_orderTime);
		TV_orderState = (TextView) findViewById(R.id.TV_orderState);
		TV_memo = (TextView) findViewById(R.id.TV_memo);
		Bundle extras = this.getIntent().getExtras();
		orderId = extras.getInt("orderId");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				OrderInfoDetailActivity.this.finish();
			}
		}); 
		

		initViewData();
		
		
		Button btnEvaluate = (Button) this.findViewById(R.id.btnEvaluate);
		btnEvaluate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putInt("washMealObj", orderInfo.getWashMealObj());
				intent.putExtras(bundle);
				intent.setClass(OrderInfoDetailActivity.this, MealEvaluateUserAddActivity.class);
				startActivityForResult(intent,ActivityUtils.ADD_CODE);
			}
		}); 
		
		Declare declare = (Declare) OrderInfoDetailActivity.this.getApplication();
		if(!declare.getIdentify().equals("user")) {
			btnEvaluate.setVisibility(View.GONE);
		}
		
	}
	/* ��ʼ����ʾ������������ */
	private void initViewData() {
	    orderInfo = orderInfoService.GetOrderInfo(orderId); 
		this.TV_orderId.setText(orderInfo.getOrderId() + "");
		WashMeal washMealObj = washMealService.GetWashMeal(orderInfo.getWashMealObj());
		this.TV_washMealObj.setText(washMealObj.getMealName());
		this.TV_orderCount.setText(orderInfo.getOrderCount() + "");
		UserInfo userObj = userInfoService.GetUserInfo(orderInfo.getUserObj());
		this.TV_userObj.setText(userObj.getName());
		this.TV_telephone.setText(orderInfo.getTelephone());
		this.TV_orderTime.setText(orderInfo.getOrderTime());
		OrderState orderState = orderStateService.GetOrderState(orderInfo.getOrderState());
		this.TV_orderState.setText(orderState.getStateName());
		this.TV_memo.setText(orderInfo.getMemo());
	} 
}
