package com.mobileclient.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobileclient.app.Declare;
import com.mobileclient.domain.MealEvaluate;
import com.mobileclient.service.MealEvaluateService;
import com.mobileclient.util.ActivityUtils;import com.mobileclient.util.MealEvaluateSimpleAdapter;
import com.mobileclient.util.HttpUtil;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MealEvaluateUserListActivity extends Activity {
	MealEvaluateSimpleAdapter adapter;
	ListView lv; 
	List<Map<String, Object>> list;
	int evaluateId;
	/* 套餐评价操作业务逻辑层对象 */
	MealEvaluateService mealEvaluateService = new MealEvaluateService();
	/*保存查询参数条件的套餐评价对象*/
	private MealEvaluate queryConditionMealEvaluate;

	private MyProgressDialog dialog; //进度条	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		setContentView(R.layout.mealevaluate_list);
		dialog = MyProgressDialog.getInstance(this);
		Declare declare = (Declare) getApplicationContext();
		String username = declare.getUserName();
		//标题栏控件
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MealEvaluateUserListActivity.this, MealEvaluateQueryActivity.class);
				startActivityForResult(intent,ActivityUtils.QUERY_CODE);//此处的requestCode应与下面结果处理函中调用的requestCode一致
			}
		});
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("套餐评价查询列表");
		ImageView add_btn = (ImageView) this.findViewById(R.id.add_btn);
		add_btn.setOnClickListener(new android.view.View.OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MealEvaluateUserListActivity.this, MealEvaluateAddActivity.class);
				startActivityForResult(intent,ActivityUtils.ADD_CODE);
			}
		}); 
		add_btn.setVisibility(View.GONE);
		
		
		Bundle bundle = this.getIntent().getExtras();
		int washMealObj = bundle.getInt("washMealObj");
		
		queryConditionMealEvaluate = new MealEvaluate();
		queryConditionMealEvaluate.setWashMealObj(washMealObj);
		queryConditionMealEvaluate.setUserObj("");
		queryConditionMealEvaluate.setEvaluateTime("");
		 
		setViews();
	}

	//结果处理函数，当从secondActivity中返回时调用此函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ActivityUtils.QUERY_CODE && resultCode==RESULT_OK){
        	Bundle extras = data.getExtras();
        	if(extras != null)
        		queryConditionMealEvaluate = (MealEvaluate)extras.getSerializable("queryConditionMealEvaluate");
        	setViews();
        }
        if(requestCode==ActivityUtils.EDIT_CODE && resultCode==RESULT_OK){
        	setViews();
        }
        if(requestCode == ActivityUtils.ADD_CODE && resultCode == RESULT_OK) {
        	queryConditionMealEvaluate = null;
        	setViews();
        }
    }

	private void setViews() {
		lv = (ListView) findViewById(R.id.h_list_view);
		dialog.show();
		final Handler handler = new Handler();
		new Thread(){
			@Override
			public void run() {
				//在子线程中进行下载数据操作
				list = getDatas();
				//发送消失到handler，通知主线程下载完成
				handler.post(new Runnable() {
					@Override
					public void run() {
						dialog.cancel();
						adapter = new MealEvaluateSimpleAdapter(MealEvaluateUserListActivity.this, list,
	        					R.layout.mealevaluate_list_item,
	        					new String[] { "evaluateId","washMealObj","evaluateContent","userObj","evaluateTime" },
	        					new int[] { R.id.tv_evaluateId,R.id.tv_washMealObj,R.id.tv_evaluateContent,R.id.tv_userObj,R.id.tv_evaluateTime,},lv);
	        			lv.setAdapter(adapter);
					}
				});
			}
		}.start(); 

		// 添加长按点击
		lv.setOnCreateContextMenuListener(mealEvaluateListItemListener);
		lv.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            	int evaluateId = Integer.parseInt(list.get(arg2).get("evaluateId").toString());
            	Intent intent = new Intent();
            	intent.setClass(MealEvaluateUserListActivity.this, MealEvaluateDetailActivity.class);
            	Bundle bundle = new Bundle();
            	bundle.putInt("evaluateId", evaluateId);
            	intent.putExtras(bundle);
            	startActivity(intent);
            }
        });
	}
	private OnCreateContextMenuListener mealEvaluateListItemListener = new OnCreateContextMenuListener() {
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
			menu.add(0, 0, 0, "编辑套餐评价信息"); 
			menu.add(0, 1, 0, "删除套餐评价信息");
		}
	};

	// 长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {  //编辑套餐评价信息
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// 获取选中行位置
			int position = contextMenuInfo.position;
			// 获取评价id
			evaluateId = Integer.parseInt(list.get(position).get("evaluateId").toString());
			Intent intent = new Intent();
			intent.setClass(MealEvaluateUserListActivity.this, MealEvaluateEditActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("evaluateId", evaluateId);
			intent.putExtras(bundle);
			startActivityForResult(intent,ActivityUtils.EDIT_CODE);
		} else if (item.getItemId() == 1) {// 删除套餐评价信息
			ContextMenuInfo info = item.getMenuInfo();
			AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) info;
			// 获取选中行位置
			int position = contextMenuInfo.position;
			// 获取评价id
			evaluateId = Integer.parseInt(list.get(position).get("evaluateId").toString());
			dialog();
		}
		return super.onContextItemSelected(item);
	}

	// 删除
	protected void dialog() {
		Builder builder = new Builder(MealEvaluateUserListActivity.this);
		builder.setMessage("确认删除吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String result = mealEvaluateService.DeleteMealEvaluate(evaluateId);
				Toast.makeText(getApplicationContext(), result, 1).show();
				setViews();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			/* 查询套餐评价信息 */
			List<MealEvaluate> mealEvaluateList = mealEvaluateService.QueryMealEvaluate(queryConditionMealEvaluate);
			for (int i = 0; i < mealEvaluateList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("evaluateId", mealEvaluateList.get(i).getEvaluateId());
				map.put("washMealObj", mealEvaluateList.get(i).getWashMealObj());
				map.put("evaluateContent", mealEvaluateList.get(i).getEvaluateContent());
				map.put("userObj", mealEvaluateList.get(i).getUserObj());
				map.put("evaluateTime", mealEvaluateList.get(i).getEvaluateTime());
				list.add(map);
			}
		} catch (Exception e) { 
			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}

}
