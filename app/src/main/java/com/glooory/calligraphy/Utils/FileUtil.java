package com.glooory.calligraphy.utils;

import android.app.Activity;
import android.content.Context;

import com.glooory.calligraphy.callbacks.FileCacheListener;
import com.glooory.calligraphy.constants.Constants;
import com.glooory.calligraphy.modul.CalliWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glooo on 2016/7/17 0017.
 */
public class FileUtil {

    //检查缓存文件是否存在
    public static boolean cacheIsExist(Context context, String fileName) {
        File cache = new File(context.getCacheDir(), fileName);
        return cache.exists();
    }

    //将请求到的网络数据保存到文件中
    public static boolean savePins(Context context, String response, String fileName) {
        if (response.isEmpty()) {
            return false;
        }
        File rootFile = context.getCacheDir();
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File cacheFile = new File(rootFile, fileName);
        if (!cacheFile.exists()) {
            try {
                cacheFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            inputStream = new ByteArrayInputStream(response.getBytes());
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedWriter = new BufferedWriter(new FileWriter(cacheFile, false));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //从缓存文件中读取数据
    public static void readPins(final Context context, int worksType,
                                final FileCacheListener listener) {
        if (worksType == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    Logger.d("开始异步从缓存文件中取出普通作品数据");
//                    Logger.d(context == null);
                    List<CalliWork> listA, listB;
                    listA = readFromCache(context, "dfal", listener);
                    listB = readFromCache(context, Constants.NOR_WORKS_PINID_B, listener);
                    if (listA != null && listB != null) {
                        listA.addAll(listB);
                    } else {
                        return;
                    }
                    if (listener != null && !listB.isEmpty()) {
//                        Logger.d("异步从缓存文件中取出普通作品数据完成");
                        final List<CalliWork> finalListB = listA;
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.readCacheFinish(finalListB);
                            }
                        });
                    }
                }
            }).start();
        } else if (worksType == 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    Logger.d("开始异步从缓存文件中取出加花作品数据");
                    List<CalliWork> list;
                    list = readFromCache(context, Constants.FLO_WORKS_PINID, listener);
                    if (list == null) {
                        return;
                    }
                    if (listener != null && !list.isEmpty()) {
//                        Logger.d("异步从缓存文件中取出加花作品数据完成");
                        final List<CalliWork> finalList = list;
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.readCacheFinish(finalList);
                            }
                        });
                    }
                }
            }).start();
        }

    }

    public static List<CalliWork> readFromCache(Context context, String cacheFileName, FileCacheListener listener) {
        List<CalliWork> mList = new ArrayList<>();
        File cacheFile = new File(context.getCacheDir(), cacheFileName);
        if (!cacheFile.exists()) {
            return null;
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(cacheFile));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            if (!stringBuilder.toString().isEmpty()) {
                List<CalliWork> works = NetworkUtil.parseWorks(stringBuilder.toString(), mList);
                return works;
            }
        } catch (Exception e) {
            if (listener != null) {
                listener.readCacheError(e);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
