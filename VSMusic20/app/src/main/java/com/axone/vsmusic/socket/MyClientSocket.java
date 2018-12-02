package com.axone.vsmusic.socket;

import android.app.Activity;

import com.axone.vsmusic.translation.Translation;
import com.axone.vsmusic.translation.Type;
import com.axone.vsmusic.transmodel.CreateModel;
import com.axone.vsmusic.transmodel.CreateModels;
import com.axone.vsmusic.transmodel.FavouriteModel;
import com.axone.vsmusic.transmodel.FavouriteModels;
import com.axone.vsmusic.transmodel.FileModel;
import com.axone.vsmusic.transmodel.FriendModel;
import com.axone.vsmusic.transmodel.FriendModels;
import com.axone.vsmusic.transmodel.InfoModel;
import com.axone.vsmusic.transmodel.LoginModel;
import com.axone.vsmusic.transmodel.RegisterModel;
import com.axone.vsmusic.transmodel.SongModel;
import com.axone.vsmusic.transmodel.SongModels;
import com.axone.vsmusic.util.Config;
import com.axone.vsmusic.util.Permission;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by 秋水 on 2017/8/25.
 */

public class MyClientSocket {

    private static MyClientSocket client;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private boolean running = false;
    private MyClientSocket(){
        init();
    }


    public static MyClientSocket createClient(){
        if(client == null)
            client = new MyClientSocket();
        return client;
    }

    /**
     * 进行初始化，包括套接字连接，与服务器配合初始化输入输出流
     */
    private void init(){

        try {
            System.out.println("连接服务器");
            Socket socket = new Socket();
            SocketAddress socAddress = new InetSocketAddress(Config.SERVER_URL, Config.SERVER_PORT);
            socket.connect(socAddress, 100000);
            System.out.println("连接服务器成功");
            running = true;
            input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            input.readInt();
            output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            output(Type.SUCCESS);

        } catch (IOException e) {
            System.out.println("连接失败：" + e.getMessage());
        }
    }

    private void dispose(){}

    private void output(int msg){
        try {
            output.writeInt(msg);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void output(Translation trans){
        try {
            output.writeObject(trans);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int login(String username, String password){
        output(new LoginModel(username, password));
        try {
            return input.readInt();
            /*switch (flag){
                case Type.EMPTY_USER:
                    break;
                case Type.WRONG_PASSWORD:
                    break;
                case Type.SUCCESS:
                    break;
                default:
                    break;
            }*/
        } catch (IOException e) {
            e.printStackTrace();
            return Type.ERROR;
        }
    }

    public int register(RegisterModel reg_model){
        output(reg_model);
        try {
            return input.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Type.ERROR;
    }

    public CreateModels getCreates(){
        Translation trans = new Translation();
        trans.setType(Type.GET_CREATES_INFO);
        output(trans);
        CreateModels creates = new CreateModels();
        try {
            int size = input.readInt();
            for(int i = 0; i < size; i++){
                try{
                    creates.add((CreateModel) input.readObject());
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return creates;
    }
/*
    public FavouriteModels getFavours(){
        Translation trans = new Translation();
        trans.setType(Type.GET_FAVOURITES_INFO);
        output(trans);
        FavouriteModels favours = null;
        try {
            favours = (FavouriteModels) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return favours;
    }
    */

    public FavouriteModels getFavours(){
        Translation trans = new Translation();
        trans.setType(Type.GET_FAVOURITES_INFO);
        output(trans);
        FavouriteModels favours = new FavouriteModels();
        try {
            int size = input.readInt();
            for (int i = 0; i < size; i++){
                try {
                    favours.add((FavouriteModel) input.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return favours;
    }

    public SongModels getSongs(){
        Translation trans = new Translation();
        trans.setType(Type.GET_SONGS_INFO);
        output(trans);
        SongModels songs = new SongModels();
        try {
            int size = input.readInt();
            for (int i = 0; i < size; i++){
                try {
                    songs.add((SongModel) input.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public FriendModels getFriends(){
        Translation trans = new Translation();
        trans.setType(Type.GET_FRIENDS_INFO);
        output(trans);
        FriendModels friends = new FriendModels();
        try {
            int size = input.readInt();
            for (int i = 0; i < size; i++){
                try {
                    friends.add((FriendModel) input.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return friends;
    }

    /**
     * 从服务器端获取文件数据
     * @param name
     * @param type
     * @return
     */
    public int getFile(String name, int type){

        String path = Config.COMMON_LOCATION;
        switch (type) {
            case Type.TYPE_SONG:
                path = Config.SONG_LOCATION;
                break;
            case Type.TYPE_TEXT:
                path = Config.TEXT_LOCATION;
                break;
            case Type.TYPE_LYRIC:
                path = Config.LYRIC_LOCATION;
                break;
            case Type.TYPE_PICTURE:
                path = Config.PICTURE_LOCATION;
                break;
            default:
                break;
        }

        path += "/" + name;
        System.out.println("开始请求服务器文件" + path);
        byte[] buf = new byte[Config.FILE_BUFFER_SIZE];
        int read_size = 0;
        try {
            DataOutputStream file_output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            while((read_size = input.read(buf)) > 1) {
                System.out.println("大小" + read_size + "数据" + buf.toString());
                file_output.write(buf);

            }
            file_output.flush();
            System.out.println("文件接收完毕");
        }  catch (IOException e) {
            e.printStackTrace();
            return Type.ERROR;
        }
        return Type.SUCCESS;

    }

    /**
     * 向服务器端发送文件
     * @param name
     * @param type
     * @return
     */
    public int setFile(String name, int type, String info, int transType, String location, Activity activity){

        FileModel fm = new FileModel();
        fm.setType(Type.SET_FILE);
        fm.setFilename(name);
        fm.setFileType(type);
        fm.setInfo(info);
        fm.setTransType(transType);
        output(fm);

        String path = "";
        if(name == null){
            path = location;
        }else{
            switch (type) {
                case Type.TYPE_SONG:
                    path = Config.SONG_LOCATION;
                    break;
                case Type.TYPE_TEXT:
                    path = Config.TEXT_LOCATION;
                    break;
                case Type.TYPE_LYRIC:
                    path = Config.LYRIC_LOCATION;
                    break;
                case Type.TYPE_PICTURE:
                    path = Config.PICTURE_LOCATION;
                    break;
                default:
                    break;
            }

            path += "/" + name;
        }

        int read_size = 0;
        byte[] buf = new byte[Config.FILE_BUFFER_SIZE];
        File file = new File(path);
        if(file.exists()){
            System.out.println("文件" + path + "存在");
        }else {
            System.out.println("文件" + path + "不存在");
        }
        new Permission().getExternalPermission(activity);
        DataInputStream file_input;
        try {
            file_input = new DataInputStream(new FileInputStream(file));
            int i = 0;
            while ((read_size = file_input.read(buf)) != -1) {
                output.write(buf, 0, Config.FILE_BUFFER_SIZE);
                Thread.sleep(2);
                System.out.println("第" + i +  "条：返回" + read_size + "数据为" +buf.toString() );
                i++;
            }
            output.flush();
            output.writeByte(0);
            output.flush();
            System.out.println("文件传输完毕");
            file_input.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Type.ERROR;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Type.ERROR;
        }
        return Type.SUCCESS;
    }

    public String matchMusic(String name, String info, String location, Activity activity){
        setFile(name, Type.TYPE_PICTURE, info, Type.MATCH_MUSIC, location, activity);
        String url = null;
        try {
            url = input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 将创作歌曲发送能够到服务器，并从服务器获取音乐文件
     * @param name 文件名
     * @return 返回本地音乐文件目录
     */
    public String createSong(String name, String lyric){
        //setFile(name + ".txt", Type.TYPE_TEXT, lyric, Type.CREATE_SONG, null);
        //向服务器发送歌词
        InfoModel infoModel = new InfoModel();
        infoModel.setType(Type.CREATE_SONG);
        infoModel.setInfo(lyric);
        output(infoModel);
        //获取歌曲
        getFile(name + Config.POSTFIX_SONG, Type.TYPE_SONG);
        String path = Config.SONG_LOCATION + "/" + name + Config.POSTFIX_SONG;
        return path;
    }

    public String getSong(String name, String pathInServer){

        SongModel songModel = new SongModel(name, null, false, 0, pathInServer, null);
        songModel.setType(Type.GET_SONG_INFO);
        output(songModel);
        getFile(name, Type.TYPE_SONG);
        String path = Config.SONG_LOCATION + "/" + name + Config.POSTFIX_SONG;
        return path;
    }

}
