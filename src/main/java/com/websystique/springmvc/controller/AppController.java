package com.websystique.springmvc.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.Role;
import com.websystique.springmvc.model.Store;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserStoreMapping;
import com.websystique.springmvc.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUser(ModelMap model) {

		List<User> users = service.findAllUsers();
		System.out.print(users);
		model.addAttribute("users", users);
		return "allemployees";
	}

	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		List<Role> roleList = new ArrayList<Role>();
		roleList = service.getRoleList();
	    
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("roleList", roleList);
		return "registration";
	}


	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/userStoreMapping" }, method = RequestMethod.GET)
	public String newUserStoreMapping(ModelMap model) {
	
		List<User> roleList = new ArrayList<User>();
		roleList = service.findAllUsersStore();
		   User user = new User();
		model.addAttribute("userstoremapping", roleList);
		List<User> users = service.getUserList();
		List<Store> stores = service.getStoreList();
		model.addAttribute("userList", users);
		model.addAttribute("edit", false);
		model.addAttribute("storeList", stores);
		model.addAttribute("user", user);
		System.out.println(stores.get(0).getId());
		return "mapUserStore";
	}

	
	
	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newStore" }, method = RequestMethod.GET)
	public String newStore(ModelMap model) {
		Store store = new Store();
		model.addAttribute("store", store);
		model.addAttribute("edit", false);
		return "store";
	}

	
	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newRole" }, method = RequestMethod.GET)
	public String newRole(ModelMap model) {
		Role role = new Role();
		model.addAttribute("role", role);
		model.addAttribute("edit", false);
		return "role";
	}

	
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isUserIdUnique(user.getId(), user.getUserId())){
			FieldError userIdError =new FieldError("user","userId",messageSource.getMessage("non.unique.userId", new String[]{user.getUserId()}, Locale.getDefault()));
		    result.addError(userIdError);
			return "registration";
		}
		
		service.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName()+" added successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-{userId}-user" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String userId, ModelMap model) {
		
		User user = service.findUserByUserId(userId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{userId}-user" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String userId) {
		System.out.println("This is called......................>");
		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isUserIdUnique(user.getId(), user.getUserId())){
			FieldError userIdError =new FieldError("user","userId",messageSource.getMessage("non.unique.userId", new String[]{user.getUserId()}, Locale.getDefault()));
		    result.addError(userIdError);
			return "registration";
		}
		System.out.println("the usefif is"+userId);
		service.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName()	+ "  " + user.getLastName()	+ "updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an user by it's userId value.
	 */
	@RequestMapping(value = { "/delete-{userId}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String userId) {
		service.deleteUserByUserId(userId);
		return "redirect:/list";
	}

	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newRole" }, method = RequestMethod.POST)
	public String saveRole(@Valid Role role, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "role";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		if(!service.isUserIdUnique(role.getId(), role.rId())){
			FieldError userIdError =new FieldError("user","userId",messageSource.getMessage("non.unique.userId", new String[]{user.getUserId()}, Locale.getDefault()));
		    result.addError(userIdError);
			return "role";
		}
		 */
		
		service.saveRole(role);

		model.addAttribute("success", "Role " + role.getRoleName() + " added successfully");
		return "success";
	}
	
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newStore" }, method = RequestMethod.POST)
	public String saveStore(@Valid Store store, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "store";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
//		if(!service.isUserIdUnique(user.getId(), user.getUserId())){
//			FieldError userIdError =new FieldError("user","userId",messageSource.getMessage("non.unique.userId", new String[]{user.getUserId()}, Locale.getDefault()));
//		    result.addError(userIdError);
//			return "store";
//		}
		
		service.saveStore(store);

		model.addAttribute("success", "Store " + store.getStoreName() +" added successfully");
		return "success";
	}
	
	
	
	@RequestMapping(value = { "/userStoreMapping" }, method = RequestMethod.POST)
	public String saveUserStore(@Valid UserStoreMapping userstore, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "store";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
//		if(!service.isUserIdUnique(user.getId(), user.getUserId())){
//			FieldError userIdError =new FieldError("user","userId",messageSource.getMessage("non.unique.userId", new String[]{user.getUserId()}, Locale.getDefault()));
//		    result.addError(userIdError);
//			return "store";
//		}
		UserStoreMapping obj = new UserStoreMapping();
		obj.setStoreId(userstore.getStoreId());
		obj.setUserId(userstore.getUserId());
		System.out.print(obj.getStoreId());
		//System.out.println(obj.getUserId());
		
		service.saveUserStoreMapping(obj);

		model.addAttribute("success", "User Store mapped successfully");
		return "success";
	}
	
}
