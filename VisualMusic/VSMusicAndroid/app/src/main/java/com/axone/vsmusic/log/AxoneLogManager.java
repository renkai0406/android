package com.axone.vsmusic.log;

import android.content.Context;

import com.axone.vsmusic.util.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 秋水 on 2017/11/9.
 */

public class AxoneLogManager {

    public static String LOG_ERROR_START = "<AxoneLogError>";
    public static String LOG_ERROR_END = "</AxoneLogError>";
    public static String LOG_NORMAL_START = "<AxoneLogNormal>";
    public static String LOG_NORMAL_END = "</AxoneLogNormal>";
    public static String LOG_TIME_START = "<AxoneLogTime>";
    public static String LOG_TIME_END = "</AxoneLogTime>";
    public static String LOG_INFO_START = "<AxoneInfoTime>";
    public static String LOG_INFO_END = "</AxoneInfoTime>";

    private static int LOG_ERROR_TYPE = 1;
    private static int LOG_NORMAL_TYPE = 0;

    private String fileName = "Axonelog.txt";
    private static AxoneLogManager log;
    private File logFile;
    private FileOutputStream fos;
    private OutputStreamWriter osw;
    /*private boolean isLoging = false;
    private Queue<AxoneLog> axoneLogs ;*/

    private AxoneLogManager(){
        //axoneLogs = new LinkedList<AxoneLog>();
        init();
    }

    public static AxoneLogManager createLog(){
        if(log == null)
            log = new AxoneLogManager();
        return log;
    }

    public void init(){
        logFile = new File(Config.COMMON_LOCATION + Config.LOG_LOCATION + "/" + fileName);
        if(!logFile.exists()){
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fos = new FileOutputStream(logFile);
            osw = new OutputStreamWriter(fos, "utf-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLog(AxoneLog logInfo){
        switch (logInfo.getType()){
            case 0:
                try {
                    osw.write(AxoneLogManager.LOG_NORMAL_START);
                    osw.write(AxoneLogManager.LOG_TIME_START);
                    osw.write(logInfo.getTime());
                    osw.write(AxoneLogManager.LOG_TIME_END);
                    osw.write(AxoneLogManager.LOG_INFO_START);
                    osw.write(logInfo.getInfo());
                    osw.write(AxoneLogManager.LOG_INFO_END);
                    osw.write(AxoneLogManager.LOG_NORMAL_END);
                    osw.write("\n");
                    osw.flush();
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    osw.write(AxoneLogManager.LOG_ERROR_START);
                    osw.write(AxoneLogManager.LOG_TIME_START);
                    osw.write(logInfo.getTime());
                    osw.write(AxoneLogManager.LOG_TIME_END);
                    osw.write(AxoneLogManager.LOG_INFO_START);
                    osw.write(logInfo.getInfo());
                    osw.write(AxoneLogManager.LOG_INFO_END);
                    osw.write(AxoneLogManager.LOG_ERROR_END);
                    osw.write("\n");
                    osw.flush();
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

}
