package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Role;
import com.websystique.springmvc.model.Store;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserStoreMapping;

public interface UserService {

	User findById(int id);
	
	void saveUser(User user);
	
	void saveRole(Role role);
	
	void saveStore(Store store);
	
	void updateUser(User user);
	
	void deleteUserByUserId(String userId);

	List<User> findAllUsers(); 
	
	User findUserByUserId(String userId);

	boolean isUserIdUnique(Integer id, String userId);

	List<Role> getRoleList();
	
	List<User> getUserList();
	
	List<Store> getStoreList();
	
	List<User> findAllUsersStore();

	void saveUserStoreMapping(UserStoreMapping userstore); 
	
}
