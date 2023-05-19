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
	// ������ѯ��ť
	private Button btnQuery;
	// ����װ���ײ�������
	private Spinner spinner_washMealObj;
	private ArrayAdapter<String> washMealObj_adapter;
	private static  String[] washMealObj_ShowText  = null;
	private List<WashMeal> washMealList = null; 
	/*װ���ײ͹���ҵ���߼���*/
	private WashMealService washMealService = new WashMealService();
	// �����µ��û�������
	private Spinner spinner_userObj;
	private ArrayAdapter<String> userObj_adapter;
	private static  String[] userObj_ShowText  = null;
	private List<UserInfo> userInfoList = null; 
	/*�û�����ҵ���߼���*/
	private UserInfoService userInfoService = new UserInfoService();
	// ������ϵ�绰�����
	private EditText ET_telephone;
	// �����µ�ʱ�������
	private EditText ET_orderTime;
	// ��������״̬������
	private Spinner spinner_orderState;
	private ArrayAdapter<String> orderState_adapter;
	private static  String[] orderState_ShowText  = null;
	private List<OrderState> orderStateList = null; 
	/*����״̬����ҵ���߼���*/
	private OrderStateService orderStateService = new OrderStateService();
	/*��ѯ�����������浽���������*/
	private OrderInfo queryConditionOrderInfo = new OrderInfo();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȥ��Activity�����״̬��
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.orderinfo_query);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("���ö�����ѯ����");
		ImageView back_btn = (ImageView) this.findViewById(R.id.back_btn);
		back_btn.setOnClickListener(new android.view.View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btnQuery = (Button) findViewById(R.id.btnQuery);
		spinner_washMealObj = (Spinner) findViewById(R.id.Spinner_washMealObj);
		// ��ȡ���е�װ���ײ�
		try {
			Declare declare = (Declare) OrderInfoShopQueryActivity.this.getApplication();
			washMealList = washMealService.shopQueryWashMeal(declare.getUserName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int washMealCount = washMealList.size();
		washMealObj_ShowText = new String[washMealCount+1];
		washMealObj_ShowText[0] = "������";
		for(int i=1;i<=washMealCount;i++) { 
			washMealObj_ShowText[i] = washMealList.get(i-1).getMealName();
		} 
		// ����ѡ������ArrayAdapter��������
		washMealObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, washMealObj_ShowText);
		// ����װ���ײ������б�ķ��
		washMealObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter ��ӵ�spinner��
		spinner_washMealObj.setAdapter(washMealObj_adapter);
		// ����¼�Spinner�¼�����
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
		// ����Ĭ��ֵ
		spinner_washMealObj.setVisibility(View.VISIBLE);
		spinner_userObj = (Spinner) findViewById(R.id.Spinner_userObj);
		// ��ȡ���е��û�
		try {
			userInfoList = userInfoService.QueryUserInfo(null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int userInfoCount = userInfoList.size();
		userObj_ShowText = new String[userInfoCount+1];
		userObj_ShowText[0] = "������";
		for(int i=1;i<=userInfoCount;i++) { 
			userObj_ShowText[i] = userInfoList.get(i-1).getName();
		} 
		// ����ѡ������ArrayAdapter��������
		userObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, userObj_ShowText);
		// �����µ��û������б�ķ��
		userObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter ��ӵ�spinner��
		spinner_userObj.setAdapter(userObj_adapter);
		// ����¼�Spinner�¼�����
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
		// ����Ĭ��ֵ
		spinner_userObj.setVisibility(View.VISIBLE);
		ET_telephone = (EditText) findViewById(R.id.ET_telephone);
		ET_orderTime = (EditText) findViewById(R.id.ET_orderTime);
		spinner_orderState = (Spinner) findViewById(R.id.Spinner_orderState);
		// ��ȡ���еĶ���״̬
		try {
			orderStateList = orderStateService.QueryOrderState(null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int orderStateCount = orderStateList.size();
		orderState_ShowText = new String[orderStateCount+1];
		orderState_ShowText[0] = "������";
		for(int i=1;i<=orderStateCount;i++) { 
			orderState_ShowText[i] = orderStateList.get(i-1).getStateName();
		} 
		// ����ѡ������ArrayAdapter��������
		orderState_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, orderState_ShowText);
		// ���ö���״̬�����б�ķ��
		orderState_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter ��ӵ�spinner��
		spinner_orderState.setAdapter(orderState_adapter);
		// ����¼�Spinner�¼�����
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
		// ����Ĭ��ֵ
		spinner_orderState.setVisibility(View.VISIBLE);
		/*������ѯ��ť*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��ȡ��ѯ����*/
					queryConditionOrderInfo.setTelephone(ET_telephone.getText().toString());
					queryConditionOrderInfo.setOrderTime(ET_orderTime.getText().toString());
					Intent intent = getIntent();
					//����ʹ��bundle��������������
					Bundle bundle =new Bundle();
					//�����������Ȼ�Ǽ�ֵ�Ե���ʽ
					bundle.putSerializable("queryConditionOrderInfo", queryConditionOrderInfo);
					intent.putExtras(bundle);
					setResult(RESULT_OK,intent);
					finish();
				} catch (Exception e) {}
			}
			});
	}
}
