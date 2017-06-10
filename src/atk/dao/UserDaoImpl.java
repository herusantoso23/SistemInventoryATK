package atk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import atk.common.MessageDefinition;
import atk.model.User;


public class UserDaoImpl implements UserDao{
	private Connection connection;
	private PreparedStatement getAllStatement;
	private PreparedStatement getByIdStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	private final String GET_ALL_QUERY = "SELECT username, password, nama, jabatan, roles FROM user";
	private final String GET_BY_ID_QUERY = "SELECT username, password, nama, jabatan, roles FROM user WHERE username = ?";
	private final String INSERT_QUERY = "INSERT INTO user(username, password, nama, jabatan, roles) VALUES(?,?,?,?,?)";
	private final String UPDATE_QUERY = "UPDATE user SET password=?, nama=?, jabatan=?, roles=? WHERE username=?";
	private final String DELETE_QUERY = "DELETE FROM user WHERE username=?";
	
	public void setConnection(Connection connection) throws SQLException {
		this.connection = connection;
		getAllStatement = this.connection.prepareStatement(GET_ALL_QUERY);
		getByIdStatement = this.connection.prepareStatement(GET_BY_ID_QUERY);
		insertStatement = this.connection.prepareStatement(INSERT_QUERY);
		updateStatement = this.connection.prepareStatement(UPDATE_QUERY);
		deleteStatement = this.connection.prepareStatement(DELETE_QUERY);
	}

	@Override
	public List<User> getAll() throws SQLException {
		List<User> userList = new ArrayList<User>();
		ResultSet rs = getAllStatement.executeQuery();
		while(rs.next()){
			User u = new User();
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setNama(rs.getString("nama"));
			u.setJabatan(rs.getString("jabatan"));
			u.setRoles(rs.getString("roles"));
			
			userList.add(u);
		}
		return userList;
	}

	@Override
	public User getUsername(String username) throws SQLException {
		getByIdStatement.setString(1, username);
		ResultSet rs = getByIdStatement.executeQuery();
		
		if(rs.next()){
			User u = new User();
			
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setNama(rs.getString("nama"));
			u.setJabatan(rs.getString("jabatan"));
			u.setRoles(rs.getString("roles"));
			
			return u;
		}
		
		
		return null;
	}

	@Override
	public User save(User user) throws SQLException {
		insertStatement.setString(1, user.getUsername());
		insertStatement.setString(2, user.getPassword());
		insertStatement.setString(3, user.getNama());
		insertStatement.setString(4, user.getJabatan());
		insertStatement.setString(5, user.getRoles());
		
		int a = insertStatement.executeUpdate();
		
		if(a >= 1){
			JOptionPane.showMessageDialog(null, MessageDefinition.MESSAGE_SAVE_SUCCESS);
		} else {
			JOptionPane.showMessageDialog(null, MessageDefinition.MESSAGE_FAILURE);
		}
		
		return user;
	}

	@Override
	public User update(User user) throws SQLException {
		updateStatement.setString(1, user.getPassword());
		updateStatement.setString(2, user.getNama());
		updateStatement.setString(3, user.getJabatan());
		updateStatement.setString(4, user.getRoles());
		updateStatement.setString(5, user.getUsername());

		int a = updateStatement.executeUpdate();
		
		if(a >= 1){
			
			JOptionPane.showMessageDialog(null, MessageDefinition.MESSAGE_SAVE_SUCCESS);
		} else {
			JOptionPane.showMessageDialog(null, MessageDefinition.MESSAGE_FAILURE);
		}
		
		return user;
	}

	@Override
	public User delate(String username) throws SQLException {
		deleteStatement.setString(1, username);
		int d = deleteStatement.executeUpdate();
		
		if (d >= 1){
			JOptionPane.showMessageDialog(null, MessageDefinition.MESSAGE_DELETE_SUCCESS);
		} else {
			JOptionPane.showMessageDialog(null, MessageDefinition.MESSAGE_FAILURE);
		}
		return null;
	}
	
	
}
