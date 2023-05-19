package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.mobileclient.util.HttpUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.domain.OrderInfo;
import com.mobileclient.service.OrderInfoService;
import com.mobileclient.domain.WashMeal;
import com.mobileclient.service.WashMealService;
import com.mobileclient.domain.UserInfo;
import com.mobileclient.service.UserInfoService;
import com.mobileclient.domain.OrderState;
import com.mobileclient.service.OrderStateService;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class OrderInfoAddActivity extends Activity {
	// 声明确定添加按钮
	private Button btnAdd;
	// 声明装修套餐下拉框
	private Spinner spinner_washMealObj;
	private ArrayAdapter<String> washMealObj_adapter;
	private static  String[] washMealObj_ShowText  = null;
	private List<WashMeal> washMealList = null;
	/*装修套餐管理业务逻辑层*/
	private WashMealService washMealService = new WashMealService();
	// 声明预订数量输入框
	private EditText ET_orderCount;
	// 声明下单用户下拉框
	private Spinner spinner_userObj;
	private ArrayAdapter<String> userObj_adapter;
	private static  String[] userObj_ShowText  = null;
	private List<UserInfo> userInfoList = null;
	/*下单用户管理业务逻辑层*/
	private UserInfoService userInfoService = new UserInfoService();
	// 声明联系电话输入框
	private EditText ET_telephone;
	// 声明下单时间输入框
	private EditText ET_orderTime;
	// 声明订单状态下拉框
	private Spinner spinner_orderState;
	private ArrayAdapter<String> orderState_adapter;
	private static  String[] orderState_ShowText  = null;
	private List<OrderState> orderStateList = null;
	/*订单状态管理业务逻辑层*/
	private OrderStateService orderStateService = new OrderStateService();
	// 声明备注信息输入框
	private EditText ET_memo;
	protected String carmera_path;
	/*要保存的订单信息*/
	OrderInfo orderInfo = new OrderInfo();
	/*订单管理业务逻辑层*/
	private OrderInfoService orderInfoService = new OrderInfoService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// 设置当前Activity界面布局
		setContentView(R.layout.orderinfo_add); 
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("添加订单");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		spinner_washMealObj = (Spinner) findViewById(R.id.Spinner_washMealObj);
		// 获取所有的装修套餐
		try {
			washMealList = washMealService.QueryWashMeal(null);
		} catch (Exception e1) { 
			e1.printStackTrace(); 
		}
		int washMealCount = washMealList.size();
		washMealObj_ShowText = new String[washMealCount];
		for(int i=0;i<washMealCount;i++) { 
			washMealObj_ShowText[i] = washMealList.get(i).getMealName();
		}
		// 将可选内容与ArrayAdapter连接起来
		washMealObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, washMealObj_ShowText);
		// 设置下拉列表的风格
		washMealObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_washMealObj.setAdapter(washMealObj_adapter);
		// 添加事件Spinner事件监听
		spinner_washMealObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				orderInfo.setWashMealObj(washMealList.get(arg2).getMealId()); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		spinner_washMealObj.setVisibility(View.VISIBLE);
		ET_orderCount = (EditText) findViewById(R.id.ET_orderCount);
		spinner_userObj = (Spinner) findViewById(R.id.Spinner_userObj);
		// 获取所有的下单用户
		try {
			userInfoList = userInfoService.QueryUserInfo(null);
		} catch (Exception e1) { 
			e1.printStackTrace(); 
		}
		int userInfoCount = userInfoList.size();
		userObj_ShowText = new String[userInfoCount];
		for(int i=0;i<userInfoCount;i++) { 
			userObj_ShowText[i] = userInfoList.get(i).getName();
		}
		// 将可选内容与ArrayAdapter连接起来
		userObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, userObj_ShowText);
		// 设置下拉列表的风格
		userObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_userObj.setAdapter(userObj_adapter);
		// 添加事件Spinner事件监听
		spinner_userObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				orderInfo.setUserObj(userInfoList.get(arg2).getUser_name()); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		spinner_userObj.setVisibility(View.VISIBLE);
		ET_telephone = (EditText) findViewById(R.id.ET_telephone);
		ET_orderTime = (EditText) findViewById(R.id.ET_orderTime);
		spinner_orderState = (Spinner) findViewById(R.id.Spinner_orderState);
		// 获取所有的订单状态
		try {
			orderStateList = orderStateService.QueryOrderState(null);
		} catch (Exception e1) { 
			e1.printStackTrace(); 
		}
		int orderStateCount = orderStateList.size();
		orderState_ShowText = new String[orderStateCount];
		for(int i=0;i<orderStateCount;i++) { 
			orderState_ShowText[i] = orderStateList.get(i).getStateName();
		}
		// 将可选内容与ArrayAdapter连接起来
		orderState_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, orderState_ShowText);
		// 设置下拉列表的风格
		orderState_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_orderState.setAdapter(orderState_adapter);
		// 添加事件Spinner事件监听
		spinner_orderState.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				orderInfo.setOrderState(orderStateList.get(arg2).getOrderStateId()); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		spinner_orderState.setVisibility(View.VISIBLE);
		ET_memo = (EditText) findViewById(R.id.ET_memo);
		btnAdd = (Button) findViewById(R.id.BtnAdd);
		/*单击添加订单按钮*/
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*验证获取预订数量*/ 
					if(ET_orderCount.getText().toString().equals("")) {
						Toast.makeText(OrderInfoAddActivity.this, "预订数量输入不能为空!", Toast.LENGTH_LONG).show();
						ET_orderCount.setFocusable(true);
						ET_orderCount.requestFocus();
						return;	
					}
					orderInfo.setOrderCount(Integer.parseInt(ET_orderCount.getText().toString()));
					/*验证获取联系电话*/ 
					if(ET_telephone.getText().toString().equals("")) {
						Toast.makeText(OrderInfoAddActivity.this, "联系电话输入不能为空!", Toast.LENGTH_LONG).show();
						ET_telephone.setFocusable(true);
						ET_telephone.requestFocus();
						return;	
					}
					orderInfo.setTelephone(ET_telephone.getText().toString());
					/*验证获取下单时间*/ 
					if(ET_orderTime.getText().toString().equals("")) {
						Toast.makeText(OrderInfoAddActivity.this, "下单时间输入不能为空!", Toast.LENGTH_LONG).show();
						ET_orderTime.setFocusable(true);
						ET_orderTime.requestFocus();
						return;	
					}
					orderInfo.setOrderTime(ET_orderTime.getText().toString());
					/*验证获取备注信息*/ 
					if(ET_memo.getText().toString().equals("")) {
						Toast.makeText(OrderInfoAddActivity.this, "备注信息输入不能为空!", Toast.LENGTH_LONG).show();
						ET_memo.setFocusable(true);
						ET_memo.requestFocus();
						return;	
					}
					orderInfo.setMemo(ET_memo.getText().toString());
					/*调用业务逻辑层上传订单信息*/
					OrderInfoAddActivity.this.setTitle("正在上传订单信息，稍等...");
					String result = orderInfoService.AddOrderInfo(orderInfo);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					Intent intent = getIntent();
					setResult(RESULT_OK,intent);
					finish();
				} catch (Exception e) {}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
