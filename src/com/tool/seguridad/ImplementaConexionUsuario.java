package com.tool.seguridad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entidades.User;
import com.inter.conexion.Conexion;
import com.inter.conexion.IUserConnection;

public class ImplementaConexionUsuario implements Conexion, IUserConnection {

	
	private User user;
	private Connection com;
	private static  final String URL ="jdbc:mysql://localhost:3306/almacen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	List<User> listaUser = new ArrayList<User>();
	
	
	public ImplementaConexionUsuario () {
		this.com=this.startedConexion(com, URL);
	}
	
	
	public ImplementaConexionUsuario (User user) {
		this.user=user;
		this.com=this.startedConexion(com, URL);
	}
	
	
	
	//tabla de usuarios la llamare "register" 23-10-2019
	public boolean checkUser() {	
		return this.validar(this.prearaUserStatement(user, "select * from register where user=? and password=?", com));
		//return getDataBaseQuery();
	}
	
	public String llave() {
		this.retornaUsuario(com,listaUser,user);
		return listaUser.get(0).getRol();
	}
	
	
	public ArrayList<User> UserList(String valtoSearch) {
		
		ArrayList<User> userList = new ArrayList<User>();
		Statement st;
		ResultSet rs;
		
		if (com!=null) {
			try {
				st = com.createStatement();
				String query = "SELECT * FROM register WHERE CONCAT_WS( id, user, password, rol) LIKE '%" + valtoSearch
						+ "%';";
				rs = st.executeQuery(query);
				User user;
				while (rs.next()) {
					user = new User(rs.getInt("id"), rs.getString("user"), rs.getString("password"), rs.getString("rol"));
					userList.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			this.com=this.startedConexion(com, URL);
			UserList(valtoSearch);
		}
		return userList;
	}

	
	
	public void addAndUse(String query) {
		
		if(com!=null) {
			Statement st=this.createstament(com);
			this.addDataBase(query, st);
			this.closeStatement(com, st);
		}
		
	}
	
	
}
