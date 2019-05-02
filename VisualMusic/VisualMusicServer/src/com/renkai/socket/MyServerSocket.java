package com.renkai.socket;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.HttpClient;

import com.axone.vsmusic.transmodel.CreateModel;
import com.axone.vsmusic.transmodel.FavouriteModel;
import com.axone.vsmusic.transmodel.FileModel;
import com.axone.vsmusic.transmodel.FriendModel;
import com.axone.vsmusic.transmodel.InfoModel;
import com.renkai.api.HttpUtils;
import com.renkai.log.AxoneLogManager;
import com.renkai.log.AxoneLog;
import com.renkai.repertory.CreateInfo;
import com.renkai.repertory.FavouriteInfo;
import com.renkai.repertory.FriendInfo;
import com.renkai.repertory.LastHistoryInfo;
import com.renkai.repertory.LoginInfo;
import com.renkai.repertory.SongInfo;
import com.renkai.repertory.UserInfo;
import com.renkai.sql.SqlBase;
import com.renkai.translation.InfoList;
import com.axone.vsmusic.translation.Translation;
import com.renkai.translation.Type;
import com.axone.vsmusic.transmodel.LoginModel;
import com.axone.vsmusic.transmodel.RegisterModel;
import com.axone.vsmusic.transmodel.SongModel;
import com.baidu.translate.TransApi;
import com.renkai.util.Config;
import com.renkai.util.Location;
import com.renkai.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyServerSocket extends Socket implements Runnable {

	private int user_id;
	private Location location;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private boolean running = false;
	private SqlBase sql;
	AxoneLogManager logManager;

	public MyServerSocket(Socket socket) {
		logManager = AxoneLogManager.createLog();
		System.out.println("客户端接入：" + socket.getInetAddress().toString());
		this.socket = socket;
		init();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		dispose();
	}

	/**
	 * 套接字、输入输出流初始化，数据库连接
	 */
	public void init() {
		try {
			running = true;
			output = new ObjectOutputStream(socket.getOutputStream());
			output(Type.SUCCESS);
			input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			input.readInt();
			sql = SqlBase.createSqlBase();
			System.out.println("服务器初始化完毕");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {

	}

	private void dispose() {
		System.out.println("开始处理消息" + running);
		Object obj;
		Translation tl;
		while (running) {
			try {
				System.out.println("进入while循环");
				obj = input.readObject();
				System.out.println("收到消息：" + obj.toString());
				tl = (Translation) obj;
				System.out.println("type值为" + tl.getType());
				System.out.println("VersionID值为" + obj.hashCode());

				switch (tl.getType()) {
				case Type.LOGIN:
					// 登陆验证
					output(login((LoginModel) obj));
					break;
				case Type.REGISTER:
					output(register((RegisterModel) obj));
					break;
				case Type.UPDATE_USER_INFO:
					output(update_user((RegisterModel) obj));
					break;
				case Type.GET_USER_INFO:
					output(get_user());
					break;
				// 客户端传来歌词信息，开始生成曲子并返回曲子
				case Type.ADD_CREATE_INFO:
					output(add_create_info((SongInfo) obj));
					break;
				case Type.ADD_FAVOURITE_INFO:
					output(add_favourite((SongInfo) obj));
					break;
				case Type.GET_FAVOURITES_INFO:
					get_favourites();
					break;
				case Type.GET_FAVOURITE_INFO:
					break;
				case Type.GET_FRIENDS_INFO:
					get_friends();
					break;
				case Type.CREATE_SONG:
					createMusic((InfoModel) obj);
					break;
				case Type.SET_FILE:
					FileModel fm = (FileModel) obj;
					System.out.println(fm.getTransType());
					switch (fm.getTransType()) {
					case Type.MATCH_MUSIC:
						output(matchMusic(fm));
						break;

					default:
						break;
					}
					break;

				case Type.GET_SONG_INFO:
					getSong((SongModel) obj);
					break;

				default:
					break;
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				running = false;

				e.printStackTrace();
			}
		}
	}

	/**
	 * 响应客户端发来的登陆信息，进行登陆，保存用户状态为登陆状态
	 * 
	 * @param login_model
	 *            客户端传输来的登陆信息
	 * @return
	 */
	private int login(LoginModel login_model) {
		LoginInfo sql_login = sql.getLoginInfoByName(login_model.getUsername());
		if (sql_login.equals(null)) {
			// 该用户名不存在
			return Type.EMPTY_USER;
		} else if (!login_model.getPassword().equals(sql_login.getUser_password())) {
			// 密码错误
			return Type.WRONG_PASSWORD;
		} else {
			// 登陆成功
			user_id = sql_login.getUser_id();
			return Type.SUCCESS;
		}

	}

	/**
	 * 响应客户端发送来的注册信息，进行用户注册
	 * 
	 * @param reg_model
	 * @return
	 */
	private int register(RegisterModel reg_model) {
		int result = register_confirm(reg_model.getUsername(), reg_model.getPassword());
		switch (result) {
		case Type.EMPTY_USER:
			return register_set(reg_model);
		case Type.EXITED_USER:
			return result;
		default:
			return Type.ERROR;
		}

	}

	/**
	 * 用户注册验证
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @return 如果该用户名不存在，返回EMPTY_USER；否则返回EXITED_USER
	 */
	private int register_confirm(String username, String password) {
		LoginInfo sql_login = sql.getLoginInfoByName(username);
		if (sql_login.equals(null)) {
			return Type.EMPTY_USER;
		} else {
			return Type.EXITED_USER;
		}
	}

	/**
	 * 将用户信息写入
	 * 
	 * @param user_info
	 * @return
	 */
	private int register_set(RegisterModel reg_model) {
		try {
			sql.addLoginInfo(new LoginInfo(reg_model.getUsername(), reg_model.getPassword()));
			sql.addUserInfo(new UserInfo(reg_model.getSex(), reg_model.getBirthday(), null, reg_model.getAddress(),
					reg_model.getBlood(), reg_model.getAge()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Type.SUCCESS;
	}

	private int update_user(RegisterModel reg_model) {
		LoginInfo sql_login = sql.getLoginInfoById(user_id);
		sql_login.setUser_name(reg_model.getUsername());
		String password;
		if ((password = reg_model.getPassword()) != null)
			sql_login.setUser_password(password);
		try {
			sql.updateLoginInfoByID(sql_login);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Type.SUCCESS;
	}

	private RegisterModel get_user() {

		LoginInfo sql_login = sql.getLoginInfoById(user_id);
		UserInfo sql_user = sql.getUserInfo(user_id);
		RegisterModel reg_model = new RegisterModel(sql_login.getUser_name(), null, sql_user.getUser_sex(),
				sql_user.getUser_birth(), sql_user.getUser_tag(), sql_user.getUser_add(), sql_user.getUser_blood(),
				sql_user.getUser_age());
		reg_model.setType(Type.GET_USER_INFO);
		return reg_model;
	}

	private int add_create_info(SongInfo song_info) {
		// 将歌曲信息保存到数据库中
		// 启动新线程接收歌曲数据
		return 0;
	}

	private int add_create_data() {
		return 0;
	}

	/**
	 * 获取用户作曲列表
	 */
	private void get_creates() {
		List<CreateInfo> creates = sql.getCreatesInfo(user_id);
		output(creates.size());
		for (CreateInfo create : creates) {
			SongInfo song = sql.getSongInfoById(create.getSong_id());
			output(new CreateModel(song.getSong_name(), sql.isFavourite(song.getSong_id()), song.getSong_location(),
					song.getLyric_location()));
		}
	}

	private int add_favourite(SongInfo song_info) {
		try {
			sql.addFavouriteInfo(song_info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Type.ERROR;
		}
		return Type.SUCCESS;
	}

	// /**
	// * 返回用户喜爱歌曲列表
	// *
	// * @return
	// */
	// private FavouriteModels get_favourites() {
	// // 操作favourite表：根据user_id获取所有song_id
	// List<FavouriteInfo> favours = sql.getFavouriteInfos(user_id);
	// // 操作song表：根据song_id获取所有SongInfo
	// FavouriteModels fms = new FavouriteModels();
	// for (FavouriteInfo favour : favours) {
	// SongInfo song = sql.getSongInfoById(favour.getSong_id());
	// fms.add(new FavouriteModel(song.getSong_name(), song.getSong_type(),
	// song.getSong_location(),
	// song.getLyric_location()));
	// }
	// // 根据SongInfo构造FavouriteModel
	// return fms;
	// }

	/**
	 * 获取用户喜欢歌曲，通过多次output将其返回至客户端
	 */
	private void get_favourites() {
		List<FavouriteInfo> favours = sql.getFavouritesInfo(user_id);
		output(favours.size());
		for (FavouriteInfo favour : favours) {
			SongInfo song = sql.getSongInfoById(favour.getSong_id());
			output(new FavouriteModel(song.getSong_name(), song.getSong_singer(), song.getSong_type(),
					song.getSong_location(), song.getLyric_location()));
		}
	}

	/**
	 * 获取用户歌曲列表
	 */
	private void get_songs() {
		List<SongInfo> songs = sql.getSongsInfo(user_id);
		output(songs.size());
		for (SongInfo song : songs) {
			output(new SongModel(song.getSong_name(), song.getSong_singer(), sql.isFavourite(song.getSong_id()),
					song.getSong_type(), song.getSong_location(), song.getLyric_location()));
		}
	}

	private void getSong(SongModel songModel) {
		// 写入最近在听

		get_file(songModel.getSongLocation());

	}

	/**
	 * 获取我的好友及其最近在听
	 */
	private void get_friends() {
		List<FriendInfo> friends = sql.getFriendsInfo(user_id);
		output(friends.size());
		for (FriendInfo friend : friends) {
			UserInfo friend_user = sql.getUserInfo(friend.getFriend_user_id());
			LastHistoryInfo last_history = sql.getLastHistoryInfo(friend.getFriend_user_id());
			SongInfo song = sql.getSongInfoById(last_history.getSong_id());
			FriendModel friend_model = new FriendModel();
			friend_model.setMemoname(friend.getMemo_name());
			friend_model.setTag(friend_user.getUser_tag());
			if (last_history.isOther_see()) {
				friend_model.setLastHistoryName(song.getSong_name());
				friend_model.setLastHistoryType(song.getSong_type());
				friend_model.setLastHistorySong(song.getSong_location());
				friend_model.setLastHistoryLyric(song.getLyric_location());
			}
			output(friend_model);
		}
	}

	/**
	 * 返回客户端请求的文件数据6流
	 * 
	 * @param url
	 */
	private void get_file(String path) {

		int read_size = 0;
		byte[] buf = new byte[Config.FILE_BUFFER_SIZE];
		File file = new File(path);
		DataInputStream file_input;
		try {
			file_input = new DataInputStream(new FileInputStream(file));
			while ((read_size = file_input.read(buf)) != -1) {
				logManager.writeLog(
						new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "向客户端发送字节，大小：" + read_size));
				output.write(buf, 0, Config.FILE_BUFFER_SIZE);
				Thread.sleep(2);
			}
			output.flush();
			output.writeByte(0);
			output.flush();
			file_input.close();
		} catch (FileNotFoundException e) {
			logManager.writeLog(new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_ERROR_TYPE,
					"文件不存在：" + e.getMessage() + "文件路径：" + path));
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setFile(String path) {
		byte[] buf = new byte[Config.FILE_BUFFER_SIZE];
		int read_size = 0;
		try {
			System.out.println("文件路径：" + path);
			int i = 0;
			DataOutputStream file_output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
			while ((read_size = input.read(buf)) > 1) {
				file_output.write(buf, 0, read_size);
				System.out.println("第" + i + "条：返回" + read_size + "数据为" + buf.toString());
				i++;
			}
			file_output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String getTagFromImage(String imgPath) {

		InputStream in = null;
		InputStream error_in = null;
		String target = "";
		String result = null;
		String deliver = "";
		deliver = Config.DELIVER_PREDICTION_BEFORE + imgPath + Config.DELIVER_PREDICTION_AFTER;
		logManager.writeLog(new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "分隔符:" + deliver + "终止"));
		boolean isValue = false;
		try {
			Process pro = Runtime.getRuntime().exec(new String[] { "sh", "startmatch.sh", imgPath });
			pro.waitFor();
			in = pro.getInputStream();
			error_in = pro.getErrorStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			BufferedReader error_read = new BufferedReader(new InputStreamReader(error_in));
			BufferedWriter bfwriter_file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
					Config.COMMON_LOCATION + Config.LOG_LOCATION + "/log-matchmusic-caffe" + Util.getCurTime() + ".txt",
					true), "UTF-8"));
			String line = "";
			while ((line = read.readLine()) != null || (line = error_read.readLine()) != null) {
				bfwriter_file.write(line + "\n");
				if (isValue) {
					target += line;
				}
				if (deliver.equals(line)) {
					isValue = true;
				}

			}
			bfwriter_file.close();

			logManager.writeLog(
					new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "caffe模型返回数据(裁剪):" + target));
			String[] results = target.split("\"");
			if (results.length > 1)
				result = results[1];
			System.out.println("caffe模型返回标签:\n" + result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String translateToChinese(String value) {

		final String APP_ID = "20170916000083084";
		final String SECURITY_KEY = "KrGv1iMd2tEjRIRfgwtA";
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);
		String transResult = api.getTransResult(value, "en", "zh");
		System.out.println("获得翻译结果：" + transResult);

		JSONObject json_all = JSONObject.fromObject(transResult);

		JSONArray json_trans_results = json_all.getJSONArray("trans_result");

		JSONObject json_trans_result = (JSONObject) json_trans_results.get(0);

		String result = json_trans_result.getString("dst");

		logManager.writeLog(new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "标签翻译结果:" + result));
		return result;

	}
	
private boolean isWhiteAndBlack(String filePath) {
	
	File file  = new File(filePath);
	int[] rgb = new int[3];  
    int[][] result = null;
    if (!file.exists()) {
         return false;
    }
    try {
         BufferedImage bufImg = ImageIO.read(file);
         int height = bufImg.getHeight();
         int width = bufImg.getWidth();
         result = new int[width][height];
         for (int i = 0; i < width; i++) {
              for (int j = 0; j < height; j++) {
            	  int pixel = bufImg.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字  
                  rgb[0] = (pixel & 0xff0000) >> 16;  
                  rgb[1] = (pixel & 0xff00) >> 8;  
                  rgb[2] = (pixel & 0xff);  
                  if(getRgbAbs(rgb[0], rgb[1], rgb[2]) > 50){
              		return false;
              	}
                    
              }
         }
         
    } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
    }
    
    return true;
}
	
	private int getRgbAbs(int r, int g, int b) {
		int rg = Math.abs(r - g);
		int rb = Math.abs(r - b);
		int gb = Math.abs(g - b);
		int maxRgRb = Math.max(rg, rb);
		return Math.max(maxRgRb, gb);
	}

	private String getUrlFromQQMusic(String tag) {
		String result = null;
		String host = "https://ali-qqmusic.showapi.com";
		String path = "/search";
		String method = "GET";
		String appcode = "8f53dfaee1a9475786a1fa44f6a9b420";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("keyword", tag);
		querys.put("page", "1");

		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			System.out.println(response.toString());

			JSONObject json_all = JSONObject.fromObject(EntityUtils.toString(response.getEntity()));

			System.out.println("JSON数据：" + json_all.toString());

			JSONObject json_res_body = JSONObject.fromObject(json_all.getString("showapi_res_body"));

			JSONObject json_pagebean = JSONObject.fromObject(json_res_body.getString("pagebean"));

			JSONArray json_array_contentlist = json_pagebean.getJSONArray("contentlist");

			JSONObject json_content = (JSONObject) json_array_contentlist.get(0);

			result = json_content.getString("m4a");
			// 获取response的body
			// System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private String matchMusic(FileModel fm) {
		// 生成路径
		String path = Config.COMMON_LOCATION + Config.PICTURE_LOCATION;
		if (fm.getFilename() == null) {
			fm.setFilename("temp1.jpg");
		}
		path += "/" + fm.getFilename();
		// 保存图片
		setFile(path);
		// 从caffe模型中获取标签
		String tag ="hello";
		/*
		 * if (fm.getFilename() == null) { tag = getTagFromImage(path, null); }else {
		 * tag = getTagFromImage(path, Config.COMMON_LOCATION + Config.PICTURE_LOCATION
		 * + "/temp1.jpg"); }
		 */
		tag = getTagFromImage(path);
        boolean isWhiteAndBlack = isWhiteAndBlack(path);
        
		logManager.writeLog(new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "音乐文件路径:" + path));
		logManager.writeLog(new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "english tag:" + tag));
		logManager.writeLog(new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "是否为黑白图片:" + isWhiteAndBlack));
		String url = "";
		String value = "";

		if (tag != null) {
			switch (tag) {
			case "backpack":
				value = "你的背包";
				break;
			case "butterfly":
				value = "你不知道的事";
				break;
			case "cake":
				value = "祝你生日快乐";
				break;
			case "lightbulb":
				value = "今天你要嫁给我";
				break;
			case "umbrella":
				value = "雨天";
				break;
				
			case "diamond-ring":
				value = "今天你要嫁给我";
				break;
			case "guitar":
				if(isWhiteAndBlack) {
					value = "一把破吉他";
		        }else {
		        	value = "吉他";
		        }
				break;
				
			case "soccer-ball":
				if(isWhiteAndBlack) {
					value = "一把破吉他1999";
		        }else {
		        	value = "足球";
		        }
				break;
				
				

			default:
				// 将英文标签翻译成中文
				value = translateToChinese(tag) + (isWhiteAndBlack?" 伤":"");
				break;
			}
			
			logManager.writeLog(new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "中文标签:" + value));
			// 从QQ音乐api获取歌曲
			url = getUrlFromQQMusic(value);
		}

		System.out.println("匹配完毕:" + url);
		logManager.writeLog(new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "匹配url:" + url));
		return url;
	}

	private String createMusicFromTensorFlow(String lyric) {
		System.out.println("传入歌词：" + lyric);
		InputStream in = null;
		String target = "";

		try {
			Process pro = Runtime.getRuntime().exec(new String[] { "sh", "createsong.sh", lyric });
			pro.waitFor();
			in = pro.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in));
			BufferedWriter bfwriter_file = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(Config.COMMON_LOCATION + Config.LOG_LOCATION
							+ "/log-createmusic-tensorflow" + Util.getCurTime() + ".txt", true), "UTF-8"));
			String line = "";
			while ((line = read.readLine()) != null) {
				bfwriter_file.write(line + "\n");
				target = line;
			}
			bfwriter_file.close();

			System.out.println("TensorFlow模型返回地址:\n" + target);

		} catch (Exception e) {
			e.printStackTrace();
		}
		target = "/root/deep-learning/melody_rnn/generated/" + target;
		return target;
	}

	private void createMusic(InfoModel im_lyric) {
		/*
		 * // 保存歌词 String lyricPath = Config.COMMON_LOCATION + Config.TEXT_LOCATION; if
		 * (fm_lyric.getFilename() == null) { fm_lyric.setFilename("temp1.txt"); }
		 * lyricPath += "/" + fm_lyric.getFilename(); setFile(lyricPath);
		 */
		/*
		 * // 读取歌词 String lyric = ""; String line = ""; try { BufferedReader bf_file =
		 * new BufferedReader(new FileReader(lyricPath)); while ((line =
		 * bf_file.readLine()) != null) { lyric += line; }
		 * System.out.println("从文件读出歌词：\n" + lyric); bf_file.close(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		// 生成歌曲
		String songPath = createMusicFromTensorFlow(im_lyric.getInfo());
		logManager.writeLog(
				new AxoneLog(Util.getCurTime(), AxoneLogManager.LOG_NORMAL_TYPE, "创作歌曲生成midi文件，服务器存储路径：" + songPath));
		// 返回歌曲
		get_file(songPath);
	}

	private void output(int flag) {
		try {
			output.writeInt(flag);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void output(String url) {
		try {
			output.writeUTF(url);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void output(Translation tl) {
		try {
			output.writeObject(tl);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void output(Translation[] tls) {
		InfoList info_list = new InfoList();
		for (Translation tl : tls) {
			info_list.add(tl);
		}
		output(info_list);
	}

	private void output(LinkedList<Translation> tls) {
		InfoList info_list = new InfoList();
		for (Translation tl : tls) {
			info_list.add(tl);
		}
		output(info_list);
	}

}
