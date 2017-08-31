package com.brucejiao.alien.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


/**
 * @author 工具类，封装常用的操作方法
 */
public class CommUtil {

	/**
	 * NUMBER 1
	 * Alert提示框
	 * 
	 * @param msg
	 *            需要提示的消息
	 * @param context
	 *            当前Activity的Context对象
	 */
	public static void showAlert(String msg, final Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg).setCancelable(false).setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}


	/**
	 * NUMBER 2
	 * Alert提示框
	 * 
	 * @param msg
	 *            需要提示的消息
	 * @param context
	 *            当前Activity的Context对象
	 */
	public static void showAlert(String msg, final Context context, OnClickListener confirClick) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg).setCancelable(false).setPositiveButton("确定", confirClick);

		AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * NUMBER 3
	 * Alert提示框
	 * 
	 * @param msg
	 *            需要提示的消息
	 * @param context
	 *            当前Activity的Context对象
	 *
	 */
	public static void showAlert(String msg, final Context context, OnClickListener confirClick,
			OnClickListener cancelClick) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(msg).setCancelable(false).setPositiveButton("确定", confirClick).setNegativeButton("取消",
				cancelClick);
		AlertDialog alert = builder.create();
		alert.show();
	}

	/**
	 * NUMBER 4
	 * 判断Service服务是否开启
	 * @param mContext
	 * @param className
	 * @return
	 */
	public static boolean isServiceRunning(Context mContext, String className) {

		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);

		List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);

		if (!(serviceList.size() > 0)) {
			return false;
		}

		for (int i = 0; i < serviceList.size(); i++) {

			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}

	/**
	 *  NUMBER 5
	 * 消息提示
	 * 
	 * @param msg
	 *            提示的消息
	 * @param context
	 *            当前Activity的Context对象
	 */
	public static void showToast(String msg, final Context context) {
		Toast toast = new Toast(context);
		toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		toast.show();// 显示
	}

	/**
	 *  NUMBER 6
	 * 获取AndroidManifest.xml中App meteData
	 * 
	 * @param metaDataName
	 * @param context
	 * @return
	 */
	public static String getAppMetaData(String metaDataName, final Context context) {
		ApplicationInfo info;
		String strData = "";
		try {
			String a = context.getPackageName();
			info = context.getPackageManager().getApplicationInfo(a, PackageManager.GET_META_DATA);
			strData = info.metaData.getString(metaDataName);
			System.out.println(strData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strData;
	}

	/**
	 * NUMBER 7
	 * 判断手机是否联网
	 * @param context
	 * @return
	 */
	public static boolean isConnect(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						Log.v("网络状态", "连通状态...........");

						return true;
					}
				}
			}
		} catch (Exception e) {
			Log.v("error", e.toString());
		}
		Log.v("网络状态", "网络不通...........");

		return false;
	}

	/**
	 * NUMBER 8
	 * 判断对象是否为空
	 * @param value
	 * @return boolean
	 */
	public static boolean isNullOrBlank(Object value) {
		if (null == value || value.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * NUMBER 9
	 * 判断集合是否为空
	 * @param value
	 * @return
	 */
	public static boolean isNullOrBlank(List value) {
		if (null == value || value.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * NUMBER 10
	 * Object 转 String
	 * @param value
	 * @return
	 */
	public static String objectString(Object value) {

		if (value == null || "".equals(value)) {
			return "";
		} else {
			return value.toString();
		}

	}


	/**
	 * NUMBER 11
	 * 读取assest下文件
	 * @param mContext
	 * @param fileName
	 * @return
	 */
	public static String getFromAssets(Context mContext, String fileName) {
		try {
			InputStreamReader inputReader = new InputStreamReader(mContext.getResources().getAssets().open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			String Result = "";
			while ((line = bufReader.readLine()) != null)
				Result += line;
			LogUtil.i("vvvvvvvvv\t\t", line);
			return Result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	//

	/**
	 * NUMBER 12
	 * 自定义listView每个Itenview的高度
	 * @param listView
	 * @param adapter
	 */
		public static void setListViewHeightBasedOnChildren(ListView listView, BaseAdapter adapter) {
			// 获取listview的adapter
			if (adapter == null) {
				return;
			}
			// 固定列宽，有多少列
			int col = 1;// listView.getNumColumns();
			int totalHeight = 0;
			// i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
			// listAdapter.getCount()小于等于8时计算两次高度相加
			for (int i = 0; i < adapter.getCount(); i += col) {
				// 获取listview的每一个item
				View listItem = adapter.getView(i, null, listView);
				listItem.measure(0, 0);
				// 获取item的高度和
				totalHeight += listItem.getMeasuredHeight();
			}

			// 获取listview的布局参数
			ViewGroup.LayoutParams params = listView.getLayoutParams();
			// 设置高度
			params.height = totalHeight;
			// 设置margin
			((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
			// 设置参数
			listView.setLayoutParams(params);
		}

	/**
	 * NUMBER 13
	 * 自定义GridView每个Itenview的高度
	 * @param listView
	 * @param adapter
	 */
	public static void setGridViewHeightBasedOnChildren(GridView listView, BaseAdapter adapter) {
		// 获取listview的adapter
		if (adapter == null) {
			return;
		}
		// 固定列宽，有多少列
		int col = 1;// listView.getNumColumns();
		int totalHeight = 0;
		// i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
		// listAdapter.getCount()小于等于8时计算两次高度相加
		for (int i = 0; i < adapter.getCount(); i += col) {
			// 获取listview的每一个item
			View listItem = adapter.getView(i, null, listView);
			listItem.measure(0, 0);
			// 获取item的高度和
			totalHeight += listItem.getMeasuredHeight();
		}

		// 获取listview的布局参数
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		// 设置高度
		params.height = totalHeight;
		// 设置margin
		((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
		// 设置参数
		listView.setLayoutParams(params);
	}

	/**NUMBER 14
	 * 计算GridView宽高
	 * @param gridView
	 */
	public static void calGridViewWidthAndHeigh(int numColumns ,GridView gridView) {


		// 获取GridView对应的Adapter
		ListAdapter listAdapter = gridView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, gridView);
			listItem.measure(0, 0); // 计算子项View 的宽高

			if ((i+1)%numColumns == 0) {
				totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
			}

			if ((i+1) == len && (i+1)%numColumns != 0) {
				totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
			}
		}

		totalHeight += 10;

		ViewGroup.LayoutParams params = gridView.getLayoutParams();
		params.height = totalHeight;
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		gridView.setLayoutParams(params);

	}

	/**NUMBER 15
	 * 打开手机相册
	 */
	public static Intent openCamera(){
		Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
		intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
		intent.setAction(Intent.ACTION_GET_CONTENT);
		return intent;
	}

	/**
	 * NUMBER 16
	 * 加载进度条
	 * @param activity
	 * @param hintText
	 * @return
	 * 用法
	 * 全局变量
	 * private ProgressDialog progress;
	 * 获取对象实例
	 *  progress = CommUtil.showProgress(mContext, "正在加载数据，请稍候...");
	 *  取消进度条
	 * if (progress != null)
		{
		progress.dismiss();
		}
	 */
	/**
	 * NUMBER 16
	 * 加载进度条
	 * @param activity
	 * @param hintText
	 * @return
	 *
	 * 用法
	 * step1:全局变量
	 * 		private ProgressDialog progress;
	 * step2:获取对象实例
	 * 		progress = CommUtil.showProgress(mContext, "正在加载数据，请稍候...");
	 * step3:取消进度条
		   if (progress != null)
			{
			progress.dismiss();
			}
	 */
	public static ProgressDialog showProgress(Activity activity, String hintText) {
		Activity mActivity = null;
		if (activity.getParent() != null) {
			mActivity = activity.getParent();
			if (mActivity.getParent() != null) {
				mActivity = mActivity.getParent();
			}
		} else {
			mActivity = activity;
		}
		final Activity finalActivity = mActivity;
		ProgressDialog window = ProgressDialog.show(finalActivity, "", hintText);
		window.getWindow().setGravity(Gravity.CENTER);

		window.setCancelable(false);
		return window;
	}

}


