package atk.main;

import java.util.List;

import atk.config.DatabaseDataSource;
import atk.model.User;
import atk.service.UserService;

public class UserMainTest {

	public static void main(String[] args) {
		UserService userService = new UserService();;
		DatabaseDataSource mysqlDataSource = new DatabaseDataSource();
		
		userService.setDataSource(mysqlDataSource.getMysqlDataSource());
		//Test Delete User
		
		String user = "coba2 p	";
		
		userService.dlete(user);
		
		//Test update user
		/*
		User u = new User();
		u.setUsername("coba2");
		u.setPassword("1234");
		u.setNama("Nama Rubah 2");
		u.setJabatan("Admin");
		u.setRoles("Admin");
		
		userService.save(u);
		*/
		
		//Test simpan user
		/*
		User u = new User();
		u.setUsername("coba1");
		u.setPassword("1234");
		u.setNama("Nama Coba 1");
		u.setJabatan("Admin");
		u.setRoles("Admin");
		
		userService.save(u);
		*/
		
		//Test pencarian data dengan ID
		/*
		String user = "petrus.topaga"; 
		
		List<User> userList = userService.getUser();
		for(User u : userList){
			String username = u.getUsername();
			if(user.equals(username)){
				userService.getById(username);

				System.out.println("Username yang dicari: " + user);
				System.out.println("Username : " + u.getUsername());
				System.out.println("Password : " + u.getPassword());
				System.out.println("Nama : " + u.getNama());
				System.out.println("Jabatan : " + u.getJabatan());
				System.out.println("Roles : " + u.getRoles());
				
			}
		}
		*/
	}

}
