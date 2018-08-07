package vo;

import java.io.Serializable;

public class DirectoryVO implements Serializable{

	private int dict_id;
	private String dict_name;
	private String created_by;
	private String access_type;
	private String username;
	private String managerlist;
	
	public String getManagerlist() {
		return managerlist;
	}
	public void setManagerlist(String managerlist) {
		this.managerlist = managerlist;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getDict_id() {
		return dict_id;
	}
	public void setDict_id(int dict_id) {
		this.dict_id = dict_id;
	}
	public String getDict_name() {
		return dict_name;
	}
	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getAccess_type() {
		return access_type;
	}
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	
	
}
