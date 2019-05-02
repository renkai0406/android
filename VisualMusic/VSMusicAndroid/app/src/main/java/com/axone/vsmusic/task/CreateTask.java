package com.axone.vsmusic.task;

import android.os.AsyncTask;

import com.axone.vsmusic.activity.PlayingCreatedActivity;
import com.axone.vsmusic.socket.MyClientSocket;

/**
 * Created by 秋水 on 2017/9/14.
 */

public class CreateTask extends AsyncTask<String, Integer, String> implements PlayingTask {

    public PlayingCreatedActivity pcActivity;

    public CreateTask() {
    }

    @Override
    protected String doInBackground(String... strings) {

        String name = strings[0];
        String lyric = strings[1];
        System.out.println("歌曲名：" + name + "/n歌词：" + lyric);
        /*
        //将歌词保存到本地
        String lyricPath = Config.COMMON_LOCATION + Config.TEXT_LOCATION + "/" + name + Config.POSTFIX_TEXT;
        System.out.println("本地歌词路径：" + lyricPath);
        try {
            File lyricFile = new File(lyricPath);
            if(!lyricFile.exists())
                lyricFile.createNewFile();
            BufferedWriter bf_file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(lyricFile)));
            bf_file.write(lyric);
            bf_file.flush();
            bf_file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }*/
        //发送歌词并接受歌曲
        MyClientSocket socket = MyClientSocket.createClient();
        String songPath = socket.createSong(name, lyric);
        return songPath;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pcActivity.initPlay();
        pcActivity.startPlay(s);
        System.out.println("作曲路径：" + s);
    }

    @Override
    public void setPCAcitivity(PlayingCreatedActivity activity) {
        this.pcActivity = activity;
    }
}
