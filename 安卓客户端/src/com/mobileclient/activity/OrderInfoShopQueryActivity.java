package com.mobileclient.activity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.OrderInfo;
import com.mobileclient.domain.WashMeal;
import com.mobileclient.service.WashMealService;
import com.mobileclient.domain.UserInfo;
import com.mobileclient.service.UserInfoService;
import com.mobileclient.domain.OrderState;
import com.mobileclient.service.OrderStateService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.ImageView;
import android.widget.TextView;
public class OrderInfoShopQueryActivity extends Activity {
	// 声明查询按钮
	private Button btnQuery;
	// 声明装修套餐下拉框
	private Spinner spinner_washMealObj;
	private ArrayAdapter<String> washMealObj_adapter;
	private static  String[] washMealObj_ShowText  = null;
	private List<WashMeal> washMealList = null; 
	/*装修套餐管理业务逻辑层*/
	private WashMealService washMealService = new WashMealService();
	// 声明下单用户下拉框
	private Spinner spinner_userObj;
	private ArrayAdapter<String> userObj_adapter;
	private static  String[] userObj_ShowText  = null;
	private List<UserInfo> userInfoList = null; 
	/*用户管理业务逻辑层*/
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
	/*查询过滤条件保存到这个对象中*/
	private OrderInfo queryConditionOrderInfo = new OrderInfo();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		// 设置当前Activity界面布局
		setContentView(R.layout.orderinfo_query);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("设置订单查询条件");
		ImageView back_btn = (ImageView) this.findViewById(R.id.back_btn);
		back_btn.setOnClickListener(new android.view.View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btnQuery = (Button) findViewById(R.id.btnQuery);
		spinner_washMealObj = (Spinner) findViewById(R.id.Spinner_washMealObj);
		// 获取所有的装修套餐
		try {
			Declare declare = (Declare) OrderInfoShopQueryActivity.this.getApplication();
			washMealList = washMealService.shopQueryWashMeal(declare.getUserName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int washMealCount = washMealList.size();
		washMealObj_ShowText = new String[washMealCount+1];
		washMealObj_ShowText[0] = "不限制";
		for(int i=1;i<=washMealCount;i++) { 
			washMealObj_ShowText[i] = washMealList.get(i-1).getMealName();
		} 
		// 将可选内容与ArrayAdapter连接起来
		washMealObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, washMealObj_ShowText);
		// 设置装修套餐下拉列表的风格
		washMealObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_washMealObj.setAdapter(washMealObj_adapter);
		// 添加事件Spinner事件监听
		spinner_washMealObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				if(arg2 != 0)
					queryConditionOrderInfo.setWashMealObj(washMealList.get(arg2-1).getMealId()); 
				else
					queryConditionOrderInfo.setWashMealObj(0);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		spinner_washMealObj.setVisibility(View.VISIBLE);
		spinner_userObj = (Spinner) findViewById(R.id.Spinner_userObj);
		// 获取所有的用户
		try {
			userInfoList = userInfoService.QueryUserInfo(null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int userInfoCount = userInfoList.size();
		userObj_ShowText = new String[userInfoCount+1];
		userObj_ShowText[0] = "不限制";
		for(int i=1;i<=userInfoCount;i++) { 
			userObj_ShowText[i] = userInfoList.get(i-1).getName();
		} 
		// 将可选内容与ArrayAdapter连接起来
		userObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, userObj_ShowText);
		// 设置下单用户下拉列表的风格
		userObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_userObj.setAdapter(userObj_adapter);
		// 添加事件Spinner事件监听
		spinner_userObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				if(arg2 != 0)
					queryConditionOrderInfo.setUserObj(userInfoList.get(arg2-1).getUser_name()); 
				else
					queryConditionOrderInfo.setUserObj("");
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
		orderState_ShowText = new String[orderStateCount+1];
		orderState_ShowText[0] = "不限制";
		for(int i=1;i<=orderStateCount;i++) { 
			orderState_ShowText[i] = orderStateList.get(i-1).getStateName();
		} 
		// 将可选内容与ArrayAdapter连接起来
		orderState_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, orderState_ShowText);
		// 设置订单状态下拉列表的风格
		orderState_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_orderState.setAdapter(orderState_adapter);
		// 添加事件Spinner事件监听
		spinner_orderState.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				if(arg2 != 0)
					queryConditionOrderInfo.setOrderState(orderStateList.get(arg2-1).getOrderStateId()); 
				else
					queryConditionOrderInfo.setOrderState(0);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		spinner_orderState.setVisibility(View.VISIBLE);
		/*单击查询按钮*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*获取查询参数*/
					queryConditionOrderInfo.setTelephone(ET_telephone.getText().toString());
					queryConditionOrderInfo.setOrderTime(ET_orderTime.getText().toString());
					Intent intent = getIntent();
					//这里使用bundle绷带来传输数据
					Bundle bundle =new Bundle();
					//传输的内容仍然是键值对的形式
					bundle.putSerializable("queryConditionOrderInfo", queryConditionOrderInfo);
					intent.putExtras(bundle);
					setResult(RESULT_OK,intent);
					finish();
				} catch (Exception e) {}
			}
			});
	}
}
