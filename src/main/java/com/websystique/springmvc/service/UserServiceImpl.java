package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.Role;
import com.websystique.springmvc.model.Store;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserStoreMapping;

@Service("employeeService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public void saveUser(User user) {
		dao.saveUser(user);
	}

	public void saveRole(Role role) {
		dao.saveRole(role);
	}
	

	public void saveStore(Store store) {
		// TODO Auto-generated method stub
		dao.saveStore(store);
	}
	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setRole(user.getRole());
			entity.setUserId(user.getUserId());
			entity.setId(user.getId());
			
			dao.updateUser(user);
		}
	}

	public void deleteUserByUserId(String userId) {
		dao.deleteUserByUserId(userId);
	}
	
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public User findUserByUserId(String userId) {
		return dao.findUserByUserId(userId);
	}

	public boolean isUserIdUnique(Integer id, String userId) {
		User user = findUserByUserId(userId);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		return dao.getRoleList();
	}

	public List<User> findAllUsersStore() {
		// TODO Auto-generated method stub
		return dao.findAllUsersStore();
	}

	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return dao.getUserList();
	}

	public List<Store> getStoreList() {
		// TODO Auto-generated method stub
		return dao.getStoreList();
	}

	public void saveUserStoreMapping(UserStoreMapping userstore) {
		// TODO Auto-generated method stub
		dao.saveUserStoreMapping(userstore);
	}


	
}
