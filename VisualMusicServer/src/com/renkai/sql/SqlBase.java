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
	 * �����ݿ�login��������û���½����
	 * 
	 * @param login_info
	 *            �û�����������Ϣ
	 * @throws Exception
	 */
	public void addLoginInfo(LoginInfo login_info) throws Exception {
		addLoginInfo(login_info.getUser_name(), login_info.getUser_password());
	}

	/**
	 * �����ݿ�login��������û���½����
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ��¼����
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
	 * ����user_id�������ݿ�login���е�����
	 * 
	 * @param login_info
	 *            �û��������롢��¼id��Ϣ
	 * @throws Exception
	 */
	public void updateLoginInfoByID(LoginInfo login_info) throws Exception {
		updateLoginInfoByID(login_info.getUser_id(), login_info.getUser_name(), login_info.getUser_password());
	}

	/**
	 * ����user_id�������ݿ�login���е�����
	 * 
	 * @param id
	 *            ��¼id
	 * @param username
	 *            �û���
	 * @param password
	 *            �û�����
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
	 * ���ݼ�ֵ����login���в��Ҽ�¼����
	 * 
	 * @param name
	 *            ������
	 * @param value
	 *            ����ֵ
	 * @return ����name=value�ļ�¼����������ڸļ�¼������null
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
	 * ��login�в��Ҷ�Ӧ���û������û���½����
	 * 
	 * @param user_name
	 *            �û���
	 * @return ���ؼ�¼����������ڸ��û���������null
	 */
	public LoginInfo getLoginInfoByName(String user_name) {
		return getLoginInfo("user_name", "\'" + user_name + "\'");
	}

	/**
	 * ��login���в��Ҷ�Ӧ��id���û���½����
	 * @param user_id �û���½id
	 * @return ���ؼ�¼����������ڸ�id������null
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
	 * �����ݿ�������û���Ϣ
	 * 
	 * @param user_info
	 *            ���ڱ����û�����
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
	 * �����ݿ������Friend��Ϣ
	 * @param friend_info ������Ϣ
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
	 * �����û�������Ϣ
	 * @param user_id �û�id
	 * @return �����û�������Ϣ�б�
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
	 * ����song_id�����ݿ��в��Ҹ���
	 * 
	 * @param song_id
	 *            �������
	 * @return ������Ϣ��������Ҳ���������null
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
	 * �����ݿ��в����û�������Ϣ�б�
	 * @param user_id �û�id
	 * @return ���ظ��û��ĸ�����Ϣ�б�
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
	 * ����favour_id��ѯ�û�ϲ��������Ϣ
	 * 
	 * @param favour_id
	 *            ϲ������id
	 * @return ϲ��������Ϣ
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
	 * ��ȡ�û�ϲ������
	 * 
	 * @param user_id
	 *            �û�id
	 * @return ���ظ��û�ϲ���ĸ����б�
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
	 * �жϸ����Ƿ���favourite����
	 * 
	 * @param song_id
	 *            ����id
	 * @return ���������favourite���У�����true�����򷵻�false
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
	 * ��ȡ�û����������Ϣ
	 *  user_id �û�id
	 * @return ����û��Ѿ���������������������������Ϣ�����򷵻�null
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
