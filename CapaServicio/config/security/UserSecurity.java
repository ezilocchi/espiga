package security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import entity.BaseEntity;

@Entity
public class UserSecurity extends BaseEntity{
	
	@Column(length=20,nullable=false)
	private String login;
	@Column(length=20,nullable=false)
	private String pass;
	@ManyToOne	
	private UserGroup group;	
	
	public UserSecurity() {		
	}
	
	public UserSecurity(String login, String pass) {
		super();
		this.login = login;
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public UserGroup getGroup() {
		return group;
	}
	public void setGroup(UserGroup group) {
		this.group = group;
	}
	
}
