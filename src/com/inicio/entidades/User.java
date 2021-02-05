package com.inicio.entidades;

import com.tool.seguridad.EncriptadoPassword;

public class User {

	private String user;
	private String password;
	private String rol;
	private int id;
	
	
	public User(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public User(String user, String password, String rol, int id) {
		super();
		this.user = user;
		this.password = password;
		this.rol = rol;
		this.id = id;
	}
	
	
//clase interma para encriptar el password
	public String protejePassword() {
		return EncriptadoPassword.encriptaPassword(password);
	}

	
	
	@Override
	public String toString() {
		return "User [user=" + user + ", password=" + password + ", rol=" + rol + ", id=" + id + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
