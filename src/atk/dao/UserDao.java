package atk.dao;

import java.sql.SQLException;
import java.util.List;

import atk.model.User;

public interface UserDao {
	public List<User> getAll() throws SQLException;
	public User getUsername(String username) throws SQLException;
	public User save(User user) throws SQLException;
	public User update(User user) throws SQLException;
	public User delate(String username) throws SQLException;
}
