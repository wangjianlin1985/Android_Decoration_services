package com.mobileclient.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.mobileclient.activity.photoListActivity;
import com.mobileclient.app.Declare;

public class HttpUtil {
	// 锟斤拷锟斤拷URL
	public static final String BASE_URL = "http://192.168.1.2:8080/JavaWebProject/";
	public static final String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mobileclient";
	public static final String Cach_Dir =  Declare.context.getCacheDir().getAbsolutePath();

	// 锟斤拷锟紾et锟斤拷锟斤拷锟斤拷锟絩equest
	public static HttpGet getHttpGet(String url) { 
		HttpGet request = new HttpGet(url);
		return request;
	}

	// 锟斤拷锟絇ost锟斤拷锟斤拷锟斤拷锟絩equest
	public static HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url);
		return request;
	}

	// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷应锟斤拷锟斤拷response
	public static HttpResponse getHttpResponse(HttpGet request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}

	// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷应锟斤拷锟斤拷response
	public static HttpResponse getHttpResponse(HttpPost request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}

	// 锟斤拷锟斤拷Post锟斤拷锟襟，伙拷锟斤拷锟接︼拷锟窖拷锟斤拷
	public static String queryStringForPost(String url) {
		// 锟斤拷锟斤拷url锟斤拷锟紿ttpPost锟斤拷锟斤拷
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			// 锟斤拷锟斤拷锟接︼拷锟斤拷锟�
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 锟叫讹拷锟角凤拷锟斤拷锟斤拷晒锟�
			if (response.getStatusLine().getStatusCode() == 200) {
				// 锟斤拷锟斤拷锟接�
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "锟斤拷锟斤拷锟届常锟斤拷";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "锟斤拷锟斤拷锟届常锟斤拷";
			return result;
		}
		return null;
	}

	// 锟斤拷锟斤拷锟接︼拷锟窖拷锟斤拷
	public static String queryStringForPost(HttpPost request) {
		String result = null;
		try {
			// 锟斤拷锟斤拷锟接︼拷锟斤拷锟�
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 锟叫讹拷锟角凤拷锟斤拷锟斤拷晒锟�
			if (response.getStatusLine().getStatusCode() == 200) {
				// 锟斤拷锟斤拷锟接�
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "锟斤拷锟斤拷锟届常锟斤拷";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "锟斤拷锟斤拷锟届常锟斤拷";
			return result;
		}
		return null;
	}

	// 锟斤拷锟斤拷Get锟斤拷锟襟，伙拷锟斤拷锟接︼拷锟窖拷锟斤拷
	public static String queryStringForGet(String url) {
		// 锟斤拷锟紿ttpGet锟斤拷锟斤拷
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			// 锟斤拷锟斤拷锟接︼拷锟斤拷锟�
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 锟叫讹拷锟角凤拷锟斤拷锟斤拷晒锟�
			if (response.getStatusLine().getStatusCode() == 200) {
				// 锟斤拷锟斤拷锟接�
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "锟斤拷锟斤拷锟届常锟斤拷";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "锟斤拷锟斤拷锟届常锟斤拷";
			return result;
		}
		return null;
	}

	public static boolean sendXML(String path, String xml) throws Exception {
		byte[] data = xml.getBytes();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);// 锟斤拷锟酵拷锟絧ost锟结交锟斤拷锟捷ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
		conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(data);
		outStream.flush();
		outStream.close();
		if (conn.getResponseCode() == 200) {
			return true;
		}
		return false;
	}

	public static byte[] sendGetRequest(String path,
			Map<String, String> params, String enc) throws Exception {
		StringBuilder sb = new StringBuilder(path);
		sb.append('?');
		// ?method=save&title=435435435&timelength=89&
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append('=')
					.append(URLEncoder.encode(entry.getValue(), enc))
					.append('&');
		}
		sb.deleteCharAt(sb.length() - 1);

		URL url = new URL(sb.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);

		if (conn.getResponseCode() == 200) {
			return readStream(conn.getInputStream());
		}
		return null;
	}

	public static boolean sendPostRequest(String path,
			Map<String, String> params, String enc) throws Exception {
		// title=dsfdsf&timelength=23&method=save
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(entry.getKey()).append('=')
						.append(URLEncoder.encode(entry.getValue(), enc))
						.append('&');
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		byte[] entitydata = sb.toString().getBytes();// 锟矫碉拷实锟斤拷亩锟斤拷锟斤拷锟斤拷锟斤拷锟�
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);// 锟斤拷锟酵拷锟絧ost锟结交锟斤拷锟捷ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
		// Content-Type: application/x-www-form-urlencoded
		// Content-Length: 38
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		outStream.flush();
		outStream.close();
		if (conn.getResponseCode() == 200) {
			return true;
		}
		return false;
	}

	public static byte[] SendPostRequest(String path,
			Map<String, String> params, String enc) throws Exception {
		// title=dsfdsf&timelength=23&method=save
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(entry.getKey()).append('=')
						.append(URLEncoder.encode(entry.getValue(), enc))
						.append('&');
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		byte[] entitydata = sb.toString().getBytes();// 锟矫碉拷实锟斤拷亩锟斤拷锟斤拷锟斤拷锟斤拷锟�
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(5 * 1000);
		conn.setDoOutput(true);// 锟斤拷锟酵拷锟絧ost锟结交锟斤拷锟捷ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
		// Content-Type: application/x-www-form-urlencoded
		// Content-Length: 38
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entitydata);
		outStream.flush();
		outStream.close();
		if (conn.getResponseCode() == 200) {
			return readStream(conn.getInputStream());
		}
		return null;
	}

	// SSL HTTPS Cookie
	public static boolean sendRequestFromHttpClient(String path,
			Map<String, String> params, String enc) throws Exception {
		List<NameValuePair> paramPairs = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				paramPairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		UrlEncodedFormEntity entitydata = new UrlEncodedFormEntity(paramPairs,
				enc);// 锟矫碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷实锟斤拷锟斤拷锟斤拷
		HttpPost post = new HttpPost(path); // form
		post.setEntity(entitydata);
		DefaultHttpClient client = new DefaultHttpClient(); // 锟斤拷锟斤拷锟�
		HttpResponse response = client.execute(post);// 执锟斤拷锟斤拷锟斤拷
		if (response.getStatusLine().getStatusCode() == 200) {
			return true;
		}
		return false;
	}

	/**
	 * 锟斤拷取锟斤拷
	 * 
	 * @param inStream
	 * @return 锟街斤拷锟斤拷锟斤拷
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	/* 锟较达拷锟侥硷拷锟斤拷Server锟侥凤拷锟斤拷 */
	public static String uploadFile(String filename) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";

		try {
			URL url = new URL(HttpUtil.BASE_URL + "UpPhotoServlet");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			/* 锟斤拷锟斤拷Input锟斤拷Output锟斤拷锟斤拷使锟斤拷Cache */
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			/* 锟斤拷锟矫达拷锟酵碉拷method=POST */
			con.setRequestMethod("POST");
			/* setRequestProperty */
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			/* 锟斤拷锟斤拷DataOutputStream */
			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; "
					+ "name=\"file1\";filename=\"" + filename + "\"" + end);
			ds.writeBytes(end);
			/* 取锟斤拷锟侥硷拷锟斤拷FileInputStream */
			File file = new File(HttpUtil.FILE_PATH + "/" + filename);
			FileInputStream fStream = new FileInputStream(file);
			/* 锟斤拷锟斤拷每锟斤拷写锟斤拷1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = -1;
			/* 锟斤拷锟侥硷拷锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 */
			while ((length = fStream.read(buffer)) != -1) {
				/* 锟斤拷锟斤拷锟斤拷写锟斤拷DataOutputStream锟斤拷 */
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
			fStream.close();
			ds.flush();
			/* 取锟斤拷Response锟斤拷锟斤拷 */
			InputStream is = con.getInputStream();
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			/* 锟斤拷Response锟斤拷示锟斤拷Dialog */
			/* 锟截憋拷DataOutputStream */
			ds.close();
			return b.toString();
		} catch (Exception e) {
			return "";
		}

	}
	
	
	
	private static final String[][] MIME_MapTable={  
            //{锟斤拷缀锟斤拷锟斤拷 MIME锟斤拷锟斤拷}  
            {".3gp",    "video/3gpp"},  
            {".apk",    "application/vnd.android.package-archive"},  
            {".asf",    "video/x-ms-asf"},  
            {".avi",    "video/x-msvideo"},  
            {".bin",    "application/octet-stream"},  
            {".bmp",    "image/bmp"},  
            {".c",  "text/plain"},  
            {".class",  "application/octet-stream"},  
            {".conf",   "text/plain"},  
            {".cpp",    "text/plain"},  
            {".doc",    "application/msword"},  
            {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},  
            {".xls",    "application/vnd.ms-excel"},   
            {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},  
            {".exe",    "application/octet-stream"},  
            {".gif",    "image/gif"},  
            {".gtar",   "application/x-gtar"},  
            {".gz", "application/x-gzip"},  
            {".h",  "text/plain"},  
            {".htm",    "text/html"},  
            {".html",   "text/html"},  
            {".jar",    "application/java-archive"},  
            {".java",   "text/plain"},  
            {".jpeg",   "image/jpeg"},  
            {".jpg",    "image/jpeg"},  
            {".js", "application/x-javascript"},  
            {".log",    "text/plain"},  
            {".m3u",    "audio/x-mpegurl"},  
            {".m4a",    "audio/mp4a-latm"},  
            {".m4b",    "audio/mp4a-latm"},  
            {".m4p",    "audio/mp4a-latm"},  
            {".m4u",    "video/vnd.mpegurl"},  
            {".m4v",    "video/x-m4v"},   
            {".mov",    "video/quicktime"},  
            {".mp2",    "audio/x-mpeg"},  
            {".mp3",    "audio/x-mpeg"},  
            {".mp4",    "video/mp4"},  
            {".mpc",    "application/vnd.mpohun.certificate"},        
            {".mpe",    "video/mpeg"},    
            {".mpeg",   "video/mpeg"},    
            {".mpg",    "video/mpeg"},    
            {".mpg4",   "video/mp4"},     
            {".mpga",   "audio/mpeg"},  
            {".msg",    "application/vnd.ms-outlook"},  
            {".ogg",    "audio/ogg"},  
            {".pdf",    "application/pdf"},  
            {".png",    "image/png"},  
            {".pps",    "application/vnd.ms-powerpoint"},  
            {".ppt",    "application/vnd.ms-powerpoint"},  
            {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"},  
            {".prop",   "text/plain"},  
            {".rc", "text/plain"},  
            {".rmvb",   "audio/x-pn-realaudio"},  
            {".rtf",    "application/rtf"},  
            {".sh", "text/plain"},  
            {".tar",    "application/x-tar"},     
            {".tgz",    "application/x-compressed"},   
            {".txt",    "text/plain"},  
            {".wav",    "audio/x-wav"},  
            {".wma",    "audio/x-ms-wma"},  
            {".wmv",    "audio/x-ms-wmv"},  
            {".wps",    "application/vnd.ms-works"},  
            {".xml",    "text/plain"},  
            {".z",  "application/x-compress"},  
            {".zip",    "application/x-zip-compressed"},  
            {"",        "*/*"}    
	};
	
	
	
	
	
	//锟斤拷锟截凤拷锟斤拷锟斤拷锟斤拷upload锟侥硷拷目录锟斤拷锟侥硷拷
	public static void downloadFile(String uploadPath) {
		//要锟斤拷锟截碉拷锟侥硷拷路锟斤拷
		String urlDownload = "";
		//urlDownload =  "http://192.168.3.39/text.txt";
		urlDownload = HttpUtil.BASE_URL + uploadPath;
		// 锟斤拷么娲拷锟铰凤拷锟斤拷锟斤拷锟斤拷锟�锟斤拷锟斤拷锟侥硷拷锟斤拷目锟斤拷路锟斤拷  
		File f = new File(HttpUtil.FILE_PATH);
		if(!f.exists())  f.mkdir(); 
		
		File f2 = new File(HttpUtil.FILE_PATH + "/upload");
		if(!f2.exists())  f2.mkdir(); 
		
		//准锟斤拷拼锟斤拷锟铰碉拷锟侥硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟节存储锟斤拷锟斤拷锟斤拷募锟斤拷锟斤拷锟�
		String newFilename = HttpUtil.FILE_PATH + "/" + uploadPath; 
		File file = new File(newFilename);
		//锟斤拷锟侥匡拷锟斤拷募锟斤拷丫锟斤拷锟斤拷冢锟斤拷锟缴撅拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷蔷锟斤拷募锟斤拷锟叫э拷锟�
		if(file.exists())  file.delete(); 
		try {
		         // 锟斤拷锟斤拷URL   
		         URL url = new URL(urlDownload);   
		         // 锟斤拷锟斤拷锟斤拷   
		         URLConnection con = url.openConnection();
		         //锟斤拷锟斤拷募锟斤拷某锟斤拷锟�
		         int contentLength = con.getContentLength();
		         System.out.println("锟斤拷锟斤拷 :"+contentLength);
		         // 锟斤拷锟斤拷锟斤拷   
		         InputStream is = con.getInputStream();  
		         // 1K锟斤拷锟斤拷锟捷伙拷锟斤拷   
		         byte[] bs = new byte[1024];   
		         // 锟斤拷取锟斤拷锟斤拷锟斤拷锟捷筹拷锟斤拷   
		         int len;   
		         // 锟斤拷锟斤拷锟斤拷募锟斤拷锟�  
		         OutputStream os = new FileOutputStream(newFilename);   
		         // 锟斤拷始锟斤拷取   
		         while ((len = is.read(bs)) != -1) {   
		             os.write(bs, 0, len);   
		         }  
		         // 锟斤拷希锟斤拷乇锟斤拷锟斤拷锟斤拷锟斤拷锟�  
		         os.close();  
		         is.close(); 
		         openFile(file);   
		} catch (Exception e) {
		        e.printStackTrace();
		} 
	}
	
	
	/** 
	 * 锟斤拷锟侥硷拷 
	 * @param file 
	 */  
	private static void openFile(File file){
	    Intent intent = new Intent();  
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	    //锟斤拷锟斤拷intent锟斤拷Action锟斤拷锟斤拷  
	    intent.setAction(Intent.ACTION_VIEW);  
	    //锟斤拷取锟侥硷拷file锟斤拷MIME锟斤拷锟斤拷  
	    String type = getMIMEType(file);  
	    //锟斤拷锟斤拷intent锟斤拷data锟斤拷Type锟斤拷锟皆★拷  
	    intent.setDataAndType(/*uri*/Uri.fromFile(file), type);  
	    //锟斤拷转  
	    Declare.context.startActivity(intent);    
	      
	}  
	  
	/** 
	 * 锟斤拷锟斤拷锟侥硷拷锟斤拷缀锟斤拷锟斤拷枚锟接︼拷锟組IME锟斤拷锟酵★拷 
	 * @param file 
	 */  
	private static String getMIMEType(File file) {  
	      
	    String type="*/*";  
	    String fName = file.getName();  
	    //锟斤拷取锟斤拷缀锟斤拷前锟侥分革拷锟斤拷"."锟斤拷fName锟叫碉拷位锟矫★拷  
	    int dotIndex = fName.lastIndexOf(".");  
	    if(dotIndex < 0){  
	        return type;  
	    }  
	    /* 锟斤拷取锟侥硷拷锟侥猴拷缀锟斤拷 */  
	    String end=fName.substring(dotIndex,fName.length()).toLowerCase();  
	    if(end=="")return type;  
	    //锟斤拷MIME锟斤拷锟侥硷拷锟斤拷锟酵碉拷匹锟斤拷锟斤拷锟斤拷业锟斤拷锟接︼拷锟組IME锟斤拷锟酵★拷  
	    for(int i=0;i<MIME_MapTable.length;i++){ //MIME_MapTable??锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷锟绞ｏ拷锟斤拷锟組IME_MapTable锟斤拷什么锟斤拷  
	        if(end.equals(MIME_MapTable[i][0]))  
	            type = MIME_MapTable[i][1];  
	    }         
	    return type;  
	}  
	 

}
