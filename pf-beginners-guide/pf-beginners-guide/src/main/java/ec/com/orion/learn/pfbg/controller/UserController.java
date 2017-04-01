package ec.com.orion.learn.pfbg.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ec.com.orion.learn.pfbg.model.User;

@ManagedBean
@RequestScoped
public class UserController {

	private User registrationUser;
	private User loginUser;
	private String loginStatus;

	public UserController() {
		registrationUser = new User();
		loginUser = new User();
	}

	public User getRegistrationUser() {
		return registrationUser;
	}

	public void setRegistrationUser(User registrationUser) {
		this.registrationUser = registrationUser;
	}
	
	public String register(){
		System.out.println("registering user: "+this.registrationUser);
		String msg = "User registered successfully";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "registration.jsf?faces-redirect=true";
	}
	
	public String updateUser(){
		System.out.println("Updating user: "+registrationUser);
		String msg = "User updated succesfully";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		return "userDetails.jsf";
	}
	
	public String deleteUser(){
		System.out.println("Deleting user:"+registrationUser);
		String msg = "User deleted Succesfully";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
		return "userDetails.jsf";
	}
	
	public void checkUserNamesExists(){
		String userName = this.registrationUser.getUserName();
		if("admin".equalsIgnoreCase(userName) || "test".equalsIgnoreCase(userName)){
			String msg = "UserName ["+userName+"] already in use!";
			FacesContext.getCurrentInstance().addMessage("registrationForm:userName", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,msg));
		}
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String login(){
		boolean validCredentials = "admin".equals(loginUser.getUserName()) || "test".equals(loginUser.getUserName());
		this.loginStatus = validCredentials? "Login Succesfull" : "Login Failed";
		return null;
	}
}
