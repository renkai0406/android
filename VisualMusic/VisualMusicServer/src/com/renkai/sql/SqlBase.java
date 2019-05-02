package com.renkai.sql;

import com.renkai.repertory.CreateInfo;
import com.renkai.repertory.FavouriteInfo;
import com.renkai.repertory.FriendInfo;
import com.renkai.repertory.LastHistoryInfo;
import com.renkai.repertory.LoginInfo;
import com.renkai.repertory.SongInfo;
import com.renkai.repertory.UserInfo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SqlBase {

	private static SqlBase sql;

	private SqlBase() {
		// TODO Auto-generated constructor stub
	}

	public static SqlBase createSqlBase() {
		if (sql == null)
			sql = new SqlBase();
		return sql;
	}

	public void initSql() throws SQLException {

	}

	public void close() {

	}

	/**
	 * 向数据库login表中添加用户登陆数据
	 * 
	 * @param login_info
	 *            用户名及密码信息
	 * @throws Exception
	 */
	public void addLoginInfo(LoginInfo login_info) throws Exception {
		addLoginInfo(login_info.getUser_name(), login_info.getUser_password());
	}

	/**
	 * 向数据库login表中添加用户登陆数据
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            登录密码
	 * @throws Exception
	 */
	public void addLoginInfo(String username, String password) throws Exception {
		String sql = "insert into login (user_name, user_password) values (?,?)";
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(sql);

		pStatement.setString(1, username);
		pStatement.setString(2, password);
		pStatement.executeUpdate(sql);

		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(connection);
	}

	/**
	 * 根据user_id更改数据库login表中的数据
	 * 
	 * @param login_info
	 *            用户名、密码、记录id信息
	 * @throws Exception
	 */
	public void updateLoginInfoByID(LoginInfo login_info) throws Exception {
		updateLoginInfoByID(login_info.getUser_id(), login_info.getUser_name(), login_info.getUser_password());
	}

	/**
	 * 根据user_id更改数据库login表中的数据
	 * 
	 * @param id
	 *            记录id
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 */
	public void updateLoginInfoByID(int id, String username, String password) {
		String sql = "update login set user_name=?, user_password=? where user_id=?";
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			pStatement.setInt(3, id);
			pStatement.executeUpdate(sql);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据键值对在login表中查找记录数据
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 返回name=value的记录；如果不存在改记录，返回null
	 */
	private LoginInfo getLoginInfo(String name, String value) {
		String sql = "select * from login where " + name + "=" + value;
		LoginInfo login_info = null;
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				login_info = new LoginInfo();
				login_info.setUser_id(resultSet.getInt(1));
				login_info.setUser_name(resultSet.getString(2));
				login_info.setUser_password(resultSet.getString(3));
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login_info;
	}

	/**
	 * 在login中查找对应此用户名的用户登陆数据
	 * 
	 * @param user_name
	 *            用户名
	 * @return 返回记录，如果不存在该用户名，返回null
	 */
	public LoginInfo getLoginInfoByName(String user_name) {
		return getLoginInfo("user_name", "\'" + user_name + "\'");
	}

	/**
	 * 在login表中查找对应此id的用户登陆数据
	 * @param user_id 用户登陆id
	 * @return 返回记录，如果不存在该id，返回null
	 */
	public LoginInfo getLoginInfoById(int user_id) {
		return getLoginInfo("user_id", user_id + "");
	}

	public void setUserInfo(UserInfo user_info) throws Exception {
		String sql = "insert into user values (?,?,?)";
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(sql);

		pStatement.setInt(1, user_info.getUser_id());
		pStatement.setInt(2, user_info.getUser_sex());
		pStatement.setInt(3, user_info.getUser_age());
		pStatement.executeUpdate(sql);

		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(connection);
	}

	public UserInfo getUserInfo(int user_id) {
		String sql = "selcet * from user where user_id=" + user_id;
		UserInfo user_info = new UserInfo();
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);

			ResultSet resultSet = pStatement.executeQuery();

			while (resultSet.next()) {

				user_info.setUser_id(resultSet.getInt(1));
				user_info.setUser_sex(resultSet.getInt(2));
				user_info.setUser_age(resultSet.getInt(3));
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_info;
	}

	/**
	 * 像数据库中添加用户信息
	 * 
	 * @param user_info
	 *            用于保存用户数据
	 * @throws Exception
	 */
	public void addUserInfo(UserInfo user_info) throws Exception {
		String sql = "insert into user(user_sex, user_birth, user_tag, user_add, user_blood, user_age) values (?,?,?,?,?,?)";
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(sql);

		pStatement.setInt(1, user_info.getUser_sex());
		pStatement.setDate(2, new Date(user_info.getUser_birth().getTime()));
		pStatement.setString(3, user_info.getUser_tag());
		pStatement.setString(4, user_info.getUser_add());
		pStatement.setInt(5, user_info.getUser_blood());
		pStatement.setInt(6, user_info.getUser_age());
		pStatement.executeUpdate(sql);

		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(connection);
	}

	/**
	 * 像数据库中添加Friend信息
	 * @param friend_info 好友信息
	 */
	public void addFriendInfo(FriendInfo friend_info) {
		String sql = "insert into friend values (?,?,?)";
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, friend_info.getFriend_id());
			pStatement.setInt(2, friend_info.getUser_id());
			pStatement.setInt(3, friend_info.getUser_id());
			pStatement.setString(4, friend_info.getMemo_name());
			pStatement.executeUpdate(sql);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void updateFriendInfo(FriendInfo friend_info) throws Exception {
		String sql = "insert into friend values (?,?,?)";
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(sql);

		pStatement.setInt(1, friend_info.getFriend_id());
		pStatement.setInt(2, friend_info.getUser_id());
		pStatement.setInt(3, friend_info.getFriend_user_id());
		pStatement.setString(4, friend_info.getMemo_name());
		pStatement.executeUpdate(sql);

		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(connection);
	}

	/**
	 * 查找用户好友信息
	 * @param user_id 用户id
	 * @return 返回用户好友信息列表
	 */
	public List<FriendInfo> getFriendsInfo(int user_id) {
		String sql = "selcet * from friend where user_id=" + user_id;
		LinkedList<FriendInfo> friends = new LinkedList<>();
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);

			ResultSet resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				friends.add(new FriendInfo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4)));
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friends;
	}

	public void setCreateInfo(SongInfo song_info) throws Exception {
		String sql = "insert into create values (?,?,?,?,?,?)";
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(sql);

		pStatement.setInt(1, song_info.getUser_id());
		pStatement.setString(2, song_info.getSong_name());
		pStatement.setString(3, song_info.getSong_singer());
		pStatement.setString(4, song_info.getSong_location());
		pStatement.setString(6, song_info.getLyric_location());
		pStatement.executeUpdate(sql);

		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(connection);
	}

	/**
	 * 根据song_id从数据库中查找歌曲
	 * 
	 * @param song_id
	 *            歌曲编号
	 * @return 歌曲信息。如果查找不到，返回null
	 */
	public SongInfo getSongInfoById(int song_id) {
		String sql = "select * from song where song_id=" + song_id;
		Connection connection;
		SongInfo song_info = null;
		try {
			connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			song_info = new SongInfo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return song_info;
	}

	/**
	 * 在数据库中查找用户歌曲信息列表
	 * @param user_id 用户id
	 * @return 返回该用户的歌曲信息列表
	 */
	public List<SongInfo> getSongsInfo(int user_id){
		String sql = "selcet * from song where user_id=" + user_id;
		List<SongInfo> songs = new LinkedList<>();
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				SongInfo song_info = new SongInfo(resultSet.getInt(1), resultSet.getInt(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
				songs.add(song_info);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return songs;
	}

	public void addCreateInfo(SongInfo song_info) throws Exception {
		String sql = "insert into create values (?,?,?,?,?,?)";
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(sql);

		pStatement.setInt(1, song_info.getUser_id());
		pStatement.setString(2, song_info.getSong_name());
		pStatement.setString(3, song_info.getSong_singer());
		pStatement.setString(4, song_info.getSong_location());
		pStatement.setString(6, song_info.getLyric_location());
		pStatement.executeUpdate(sql);

		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(connection);
	}

	public List<CreateInfo> getCreatesInfo(int user_id) {
		String sql = "selcet * from create where user_id=" + user_id;
		List<CreateInfo> creates = new LinkedList<>();
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);

			ResultSet resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				creates.add(new CreateInfo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return creates;
	}

	public void addFavouriteInfo(SongInfo song_info) throws Exception {
		String sql = "insert into favourite values (?,?,?,?,?,?)";
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(sql);

		pStatement.setInt(1, song_info.getUser_id());
		pStatement.setString(2, song_info.getSong_name());
		pStatement.setString(3, song_info.getSong_singer());
		pStatement.setString(4, song_info.getSong_location());
		pStatement.setString(6, song_info.getLyric_location());
		pStatement.executeUpdate(sql);

		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(connection);
	}

	public void updateFavouriteInfo(SongInfo song_info) throws Exception {
		String sql = "insert into favourite values (?,?,?,?,?,?)";
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(sql);

		pStatement.setInt(1, song_info.getUser_id());
		pStatement.setString(2, song_info.getSong_name());
		pStatement.setString(3, song_info.getSong_singer());
		pStatement.setString(4, song_info.getSong_location());
		pStatement.setString(6, song_info.getLyric_location());
		pStatement.executeUpdate(sql);

		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(connection);
	}

	/**
	 * 根据favour_id查询用户喜欢歌曲信息
	 * 
	 * @param favour_id
	 *            喜欢歌曲id
	 * @return 喜欢歌曲信息
	 */
	public FavouriteInfo getFavouriteInfo(int favour_id) {
		String sql = "selcet * from favourite where favour_id=" + favour_id;
		FavouriteInfo favour_info = null;
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			favour_info = new FavouriteInfo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return favour_info;
	}

	/**
	 * 获取用户喜欢歌曲
	 * 
	 * @param user_id
	 *            用户id
	 * @return 返回该用户喜欢的歌曲列表
	 */
	public List<FavouriteInfo> getFavouritesInfo(int user_id) {
		String sql = "selcet * from favourite where user_id=" + user_id;
		List<FavouriteInfo> favours = new LinkedList<>();
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);

			ResultSet resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				FavouriteInfo favour_info = new FavouriteInfo(resultSet.getInt(1), resultSet.getInt(2),
						resultSet.getInt(3));
				favours.add(favour_info);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return favours;
	}

	/**
	 * 判断歌曲是否在favourite表中
	 * 
	 * @param song_id
	 *            歌曲id
	 * @return 如果歌曲在favourite表中，返回true，否则返回false
	 */
	public boolean isFavourite(int song_id) {
		String sql = "selcet * from favourite where song_id=" + song_id;
		boolean result = false;
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			result = resultSet.next();
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List getHistoryInfo(int user_id) {
		String sql = "selcet * from history where user_id=" + user_id;
		List song_list = new ArrayList();
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);

			ResultSet resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				SongInfo song_info = new SongInfo();
				song_info.setUser_id(resultSet.getInt(1));
				song_info.setSong_name(resultSet.getString(2));
				song_info.setSong_singer(resultSet.getString(3));
				song_info.setSong_location(resultSet.getString(4));
				song_info.setLyric_location(resultSet.getString(5));
				song_list.add(song_info);
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return song_list;
	}

	public void addHistoryInfo(SongInfo song_info) throws Exception {
		String sql = "insert into history values (?,?,?,?,?,?)";
		Connection connection = DBUtil.getConnection();
		PreparedStatement pStatement = connection.prepareStatement(sql);

		pStatement.setInt(1, song_info.getUser_id());
		pStatement.setString(2, song_info.getSong_name());
		pStatement.setString(3, song_info.getSong_singer());
		pStatement.setString(4, song_info.getSong_location());
		pStatement.setString(6, song_info.getLyric_location());
		pStatement.executeUpdate(sql);

		DBUtil.closePreparedStatement(pStatement);
		DBUtil.closeConnection(connection);
	}

	public void addLastHistoryInfo(LastHistoryInfo last_history) {
		
	}
	
	/**
	 * 获取用户最近在听信息
	 *  user_id 用户id
	 * @return 如果用户已经听过歌曲，返回最后题词听歌信息；否则返回null
	 */
	public LastHistoryInfo getLastHistoryInfo(int user_id) {
		String sql = "selcet * from last_history where user_id=" + user_id;
		LastHistoryInfo last_history = null;
		try {
			Connection connection = DBUtil.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				last_history = new LastHistoryInfo();
				last_history.setLast_id(resultSet.getInt(1));
				last_history.setUser_id(resultSet.getInt(2));
				last_history.setSong_id(resultSet.getInt(3));
				last_history.setOther_see(resultSet.getBoolean(4));
			}
			DBUtil.closeResultSet(resultSet);
			DBUtil.closePreparedStatement(pStatement);
			DBUtil.closeConnection(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return last_history;
	}
	
	/*
	private int boolToInt(boolean value) {
		return value?1:0;
	}
	
	private boolean intToBool(int value) {
		return value!=0;
	}
	*/
}
