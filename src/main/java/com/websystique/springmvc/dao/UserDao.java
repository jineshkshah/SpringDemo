package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Role;
import com.websystique.springmvc.model.Store;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserStoreMapping;

public interface UserDao {

	User findById(int id);

	void saveUser(User user);
	
	void deleteUserByUserId(String userId);
	
	List<User> findAllUsers();

	User findUserByUserId(String userId);
	
	void updateUser(User user);

	void saveRole(Role role);

	void saveStore(Store store);

	List<Role> getRoleList();

	List<User> findAllUsersStore();

	List<User> getUserList();

	List<Store> getStoreList();

	void saveUserStoreMapping(UserStoreMapping userstore);

}
