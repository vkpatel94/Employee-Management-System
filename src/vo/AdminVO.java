package vo;

import java.io.Serializable;

public class AdminVO implements Serializable{
	private int emp_id;
	private String first_name;
	private String last_name;
	private String type;
	private String username;
	private String status;
	private String manager;
	private String managerlist;
	
	
	public String getManagerlist() {
		return managerlist;
	}
	public void setManagerlist(String managerlist) {
		this.managerlist = managerlist;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
