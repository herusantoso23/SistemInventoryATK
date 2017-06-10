package atk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import atk.dao.UserDao;
import atk.dao.UserDaoImpl;
import atk.model.User;

public class UserService {
	private Connection connection;
	private UserDao userDao;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public void setDataSource(DataSource dataSource){
		try{
			connection = dataSource.getConnection();
			userDao = new UserDaoImpl();
			((UserDaoImpl)userDao).setConnection(connection);
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
	
	
	public List<User> getUser(){
		try {
			return userDao.getAll();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return new ArrayList<User>();
	}
	
	public User getById(String username){
		User u = null;
		try {
			u = userDao.getUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	public User save(User user){
		try {
			connection.setAutoCommit(false);
			userDao.save(user);
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException ex){
			try{
				connection.rollback();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	public User update(User user){
		try {
			connection.setAutoCommit(false);
			userDao.update(user);
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException ex){
			try{
				connection.rollback();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	public User dlete(String username){
		try {
			connection.setAutoCommit(false);
			userDao.delate(username);
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException ex){
			try{
				connection.rollback();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	
	
	

}
