package com.inter.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.entidades.User;

public interface IUserConnection {

	
	
	
	
	default public PreparedStatement prearaUserStatement(User usuario, String query, Connection com) {
		PreparedStatement ps = null;

		if (com != null) {
			try {
				ps = com.prepareStatement(query);
				ps.setString(1, usuario.getUser());
				ps.setString(2, usuario.getPassword());

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
		}
		return ps;
	}

	
	// Valida un usuario
	default public boolean validar(PreparedStatement ps) {

		boolean st = false;
		String user = "";

		if (ps != null) {
			try {
				ResultSet rs = ps.executeQuery();
				st = rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return st;
	}


	default public void retornaUsuario(Connection com, List<User> listaUser, User user) {
		
		String query = "select * from register where user=? and password=?;";
		PreparedStatement ps = null;
		if (com != null) {
			try {
				ps = com.prepareStatement(query);
				ps.setString(1, user.getUser());
				ps.setString(2, user.getPassword());
				
				ResultSet rt = ps.executeQuery();
				while (rt.next()) {
					listaUser.add(new User(rt.getInt(1), rt.getString(2), rt.getString(3),rt.getString(4))); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

	default public void updatePassword2(User usuario, int num, Connection com) {
		PreparedStatement ps = null;
		String query = "UPDATE register SET password=?  WHERE id=? ";
		if (com != null) {
			try {
				ps = com.prepareStatement(query);
				ps.setString(1, usuario.getPassword());
				ps.setInt(2, num);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("no establece el statement");
		}
	}

}
