package com.glooory.calligraphy.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.glooory.calligraphy.modul.CalliWork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
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
    public static String loadPins(final String pinUrl) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(pinUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    //解析Json数据的方法
    public static void parseWorks(String response, List<CalliWork> mList) {
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
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
