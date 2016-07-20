package com.glooory.calligraphy.Utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.glooory.calligraphy.Callbacks.HttpCallbackListener;
import com.glooory.calligraphy.Constants.Constants;
import com.glooory.calligraphy.modul.CalliWork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Glooo on 2016/7/15 0015.
 */
public class NetworkUtil {

    //判断是否是使用的移动数据网络
    public static boolean isMobileData(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        return isMobileConn;
    }

    //判断设备是否连接到了网络
    public static boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    //网络请求
    public static void loadPins(final Context context, final HttpCallbackListener httpCallbackListener) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    Logger.d("开始异步请求网络数据");
                    httpRequest(context, Constants.NOR_WORKS_URLS_A, Constants.NOR_WORKS_PINID_A, httpCallbackListener);
                    httpRequest(context, Constants.NOR_WORKS_URLS_B, Constants.NOR_WORKS_PINID_B, httpCallbackListener);
                    httpRequest(context, Constants.FLO_WORKS_URLS, Constants.FLO_WORKS_PINID, httpCallbackListener);
                    if (httpCallbackListener != null) {
//                        Logger.d("异步网络数据请求完成");
                        httpCallbackListener.onHttpRequestFinish();
                    }
                }
            }).start();
    }

    private static void httpRequest(Context context, String requestUrl, String cacheFileName, final HttpCallbackListener listener) {
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            InputStream inputStream = conn.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            FileUtil.savePins(context, response.toString(), cacheFileName);
        } catch (final Exception e) {
            if (listener != null) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onHttpRequestError(e);
                    }
                });
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //解析Json数据的方法
    public static List<CalliWork> parseWorks(String response, List<CalliWork> mList) {
        try {
            JSONObject raw = new JSONObject(response);
            JSONArray pinsArray = raw.getJSONArray("pins");
            for (int i = 0; i < pinsArray.length(); i++) {
                JSONObject pin = pinsArray.getJSONObject(i);
                JSONObject file = pin.getJSONObject("file");
                CalliWork calliWork = new CalliWork();
                calliWork.setId(file.getInt("id"));
                calliWork.setKey(file.getString("key"));
                calliWork.setType(file.getString("type"));
                calliWork.setWidth(file.getInt("width"));
                calliWork.setHeight(file.getInt("height"));
                mList.add(calliWork);
            }
            return mList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
