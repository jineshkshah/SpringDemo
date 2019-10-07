package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Role;
import com.websystique.springmvc.model.Store;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserStoreMapping;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		return getByKey(id);
	}

	public void saveUser(User user) {
		persist(user);
	}
	

	public void saveRole(Role role) {
		saveRoleAbs(role);
	}

	public void saveStore(Store store) {
		// TODO Auto-generated method stub
		saveStoreAbs(store);
		
	}
	
	public void deleteUserByUserId(String userId) {
		Query query = getSession().createSQLQuery("delete from User where userId = :userId");
		query.setString("userId", userId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

	public User findUserByUserId(String userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (User) criteria.uniqueResult();
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Query query = getSession().createSQLQuery("update User set firstName =:firstName, lastName =:lastName, role =:role, userId =:userId  where id =:id");
		query.setInteger("id", user.getId());
		query.setString("firstName", user.getFirstName());
		query.setString("lastName", user.getLastName());
		query.setString("role", user.getRole());
		query.setString("userId", user.getUserId());
		query.executeUpdate();
		//update(user);
		
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		//Criteria criteria = createEntityCriteria();
		//return (List<Role>) criteria.list();
		
	    Query query = getSession().createQuery("select roleName from Role");
	    List<Role> role = (List<Role>) query.list();
	    return role;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsersStore() {
		// TODO Auto-generated method stub
		   Query query = getSession().createSQLQuery("select u.userName as userName, u.userId as userId, u.role as role, s.storeName as storeName from user u join store s join user_store_mapping us where u.id = us.userId and s.id = us.storeId")
				   .setResultTransformer(Transformers.aliasToBean(User.class));
		    List<User> userStoreData = (List<User>) query.list();
		    return userStoreData;
		  

	}

	public List<User> getUserList() {
		// TODO Auto-generated method stub
		 Query query = getSession().createSQLQuery("select userName as userName, id as id from User")
				 .setResultTransformer(Transformers.aliasToBean(User.class));
		    List<User> users = (List<User>) query.list();
		    return users;
	}

	@SuppressWarnings("unchecked")
	public List<Store> getStoreList() {
		// TODO Auto-generated method stub
		Query query = getSession().createSQLQuery("select storeName as storeName, id as id from Store")
				.setResultTransformer(Transformers.aliasToBean(Store.class));
	    List<Store> stores = (List<Store>) query.list();
	    return stores;
	}

	@SuppressWarnings("unchecked")
	public void saveUserStoreMapping(UserStoreMapping userstore) {
		// TODO Auto-generated method stub
		saveUserStoreMappingAbs(userstore);
		
	}

	
}
