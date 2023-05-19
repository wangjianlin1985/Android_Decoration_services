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
	// ����ȷ����Ӱ�ť
	private Button btnAdd;
	// ����װ���ײ�������
	private Spinner spinner_washMealObj;
	private ArrayAdapter<String> washMealObj_adapter;
	private static  String[] washMealObj_ShowText  = null;
	private List<WashMeal> washMealList = null;
	/*װ���ײ͹���ҵ���߼���*/
	private WashMealService washMealService = new WashMealService();
	// ����Ԥ�����������
	private EditText ET_orderCount;
	// �����µ��û�������
	private Spinner spinner_userObj;
	private ArrayAdapter<String> userObj_adapter;
	private static  String[] userObj_ShowText  = null;
	private List<UserInfo> userInfoList = null;
	/*�µ��û�����ҵ���߼���*/
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
	// ������ע��Ϣ�����
	private EditText ET_memo;
	protected String carmera_path;
	/*Ҫ����Ķ�����Ϣ*/
	OrderInfo orderInfo = new OrderInfo();
	/*��������ҵ���߼���*/
	private OrderInfoService orderInfoService = new OrderInfoService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȥ��Activity�����״̬��
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.orderinfo_add); 
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("��Ӷ���");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		spinner_washMealObj = (Spinner) findViewById(R.id.Spinner_washMealObj);
		// ��ȡ���е�װ���ײ�
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
		// ����ѡ������ArrayAdapter��������
		washMealObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, washMealObj_ShowText);
		// ���������б�ķ��
		washMealObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter ��ӵ�spinner��
		spinner_washMealObj.setAdapter(washMealObj_adapter);
		// ����¼�Spinner�¼�����
		spinner_washMealObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				orderInfo.setWashMealObj(washMealList.get(arg2).getMealId()); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// ����Ĭ��ֵ
		spinner_washMealObj.setVisibility(View.VISIBLE);
		ET_orderCount = (EditText) findViewById(R.id.ET_orderCount);
		spinner_userObj = (Spinner) findViewById(R.id.Spinner_userObj);
		// ��ȡ���е��µ��û�
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
		// ����ѡ������ArrayAdapter��������
		userObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, userObj_ShowText);
		// ���������б�ķ��
		userObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter ��ӵ�spinner��
		spinner_userObj.setAdapter(userObj_adapter);
		// ����¼�Spinner�¼�����
		spinner_userObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				orderInfo.setUserObj(userInfoList.get(arg2).getUser_name()); 
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
		orderState_ShowText = new String[orderStateCount];
		for(int i=0;i<orderStateCount;i++) { 
			orderState_ShowText[i] = orderStateList.get(i).getStateName();
		}
		// ����ѡ������ArrayAdapter��������
		orderState_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, orderState_ShowText);
		// ���������б�ķ��
		orderState_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ��adapter ��ӵ�spinner��
		spinner_orderState.setAdapter(orderState_adapter);
		// ����¼�Spinner�¼�����
		spinner_orderState.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				orderInfo.setOrderState(orderStateList.get(arg2).getOrderStateId()); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// ����Ĭ��ֵ
		spinner_orderState.setVisibility(View.VISIBLE);
		ET_memo = (EditText) findViewById(R.id.ET_memo);
		btnAdd = (Button) findViewById(R.id.BtnAdd);
		/*������Ӷ�����ť*/
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*��֤��ȡԤ������*/ 
					if(ET_orderCount.getText().toString().equals("")) {
						Toast.makeText(OrderInfoAddActivity.this, "Ԥ���������벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_orderCount.setFocusable(true);
						ET_orderCount.requestFocus();
						return;	
					}
					orderInfo.setOrderCount(Integer.parseInt(ET_orderCount.getText().toString()));
					/*��֤��ȡ��ϵ�绰*/ 
					if(ET_telephone.getText().toString().equals("")) {
						Toast.makeText(OrderInfoAddActivity.this, "��ϵ�绰���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_telephone.setFocusable(true);
						ET_telephone.requestFocus();
						return;	
					}
					orderInfo.setTelephone(ET_telephone.getText().toString());
					/*��֤��ȡ�µ�ʱ��*/ 
					if(ET_orderTime.getText().toString().equals("")) {
						Toast.makeText(OrderInfoAddActivity.this, "�µ�ʱ�����벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_orderTime.setFocusable(true);
						ET_orderTime.requestFocus();
						return;	
					}
					orderInfo.setOrderTime(ET_orderTime.getText().toString());
					/*��֤��ȡ��ע��Ϣ*/ 
					if(ET_memo.getText().toString().equals("")) {
						Toast.makeText(OrderInfoAddActivity.this, "��ע��Ϣ���벻��Ϊ��!", Toast.LENGTH_LONG).show();
						ET_memo.setFocusable(true);
						ET_memo.requestFocus();
						return;	
					}
					orderInfo.setMemo(ET_memo.getText().toString());
					/*����ҵ���߼����ϴ�������Ϣ*/
					OrderInfoAddActivity.this.setTitle("�����ϴ�������Ϣ���Ե�...");
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
