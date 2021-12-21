package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import users.User;
import users.Amministratore;
import users.Elettore;

public class UserDAOImpl implements UserDAO {
	Connection con = null;

	private static Connection getConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/sistema_di_voto?user=Mattia&password=sweng2021");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();

		con = getConnection();
		try {
			PreparedStatement st = null;
			st = con.prepareStatement("SELECT * FROM elettore;");
			ResultSet res = st.executeQuery();
			while (res.next()) {
				users.add(new Elettore(res.getString(2), res.getString(3), res.getString(4)));
			}

			st = null;
			st = con.prepareStatement("SELECT * FROM amministratore;");
			res = st.executeQuery();
			while (res.next()) {
				users.add(new Amministratore(res.getString(2), res.getString(3), res.getString(4)));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return users;
	}

	@Override
	public List<User> findById(int id) {
		List<User> users = new ArrayList<>();

		con = getConnection();
		try {
			PreparedStatement st = null;
			st = con.prepareStatement("SELECT * FROM elettore WHERE elettore.id = ?;");
			st.setInt(1, id);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				users.add(new Elettore(res.getString(2), res.getString(3), res.getString(4)));
			}

			st = null;
			st = con.prepareStatement("SELECT * FROM amministratore WHERE amministratore.id = ?;");
			st.setInt(0, id);
			res = st.executeQuery();
			while (res.next()) {
				users.add(new Amministratore(res.getString(2), res.getString(3), res.getString(4)));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return users;
	}

	@Override
	public List<User> findByName(String name, String surname) {
		List<User> users = new ArrayList<>();

		con = getConnection();
		try {
			PreparedStatement st = null;
			st = con.prepareStatement("SELECT * FROM elettore WHERE elettore.name = BINARY ? AND elettore.surname = BINARY ?;");
			st.setString(1, name);
			st.setString(2, surname);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				users.add(new Elettore(res.getString(2), res.getString(3), res.getString(4)));
			}

			st = null;
			st = con.prepareStatement("SELECT * FROM amministratore WHERE amministratore.name = BINARY ? AND amministratore.surname = BINARY ?;");
			st.setString(1, name);
			st.setString(2, surname);
			res = st.executeQuery();
			while (res.next()) {
				users.add(new Amministratore(res.getString(2), res.getString(3), res.getString(4)));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return users;
	}

	@Override
	public boolean insertUser(User user, String password) {
		con = getConnection();
		password = Encryption.Sha512(password);
		
		PreparedStatement st = null;
		try {
			if(user instanceof Elettore) {
				st = con.prepareStatement("INSERT INTO elettore(name, surname, cF,password) VALUES (?,?,?,?);");
			}else if(user instanceof Amministratore) {
				st = con.prepareStatement("INSERT INTO amministratore(name, surname, username,password) VALUES (?,?,?,?);");
			}else {
				throw new IllegalArgumentException("Undefined user mode.");
			}
			st.setString(1, user.getName());
			st.setString(2, user.getSurname());
			st.setString(3, user.getUsername());
			st.setString(4, password);
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean updateUser(User user) {		
		con = getConnection();
		PreparedStatement st = null;
		try {
			if(user instanceof Elettore) {
				st = con.prepareStatement("INSERT INTO elettore(name, surname, cF,password) VALUES (?,?,?,?) WHERE elettore.name = BINARY ? AND elettore.surname = BINARY ?;");
			}else if(user instanceof Amministratore) {
				st = con.prepareStatement("INSERT INTO amministratore(name, surname, username,password) VALUES (?,?,?,?) WHERE amministratore.name = BINARY ? AND amministratore.surname = BINARY ?;");
			}else {
				throw new IllegalArgumentException("Undefined user mode.");
			}
			st.setString(1, user.getName());
			st.setString(2, user.getSurname());
			st.setString(3, user.getUsername());
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteUser(User user) {
		con = getConnection();
		PreparedStatement st = null;
		try {
			if(user instanceof Elettore) {
				st = con.prepareStatement("DELETE FROM elettore WHERE elettore.id = ?;");
			}else if(user instanceof Amministratore) {
				st = con.prepareStatement("DELETE FROM amministratore(name, surname, username,password) WHERE amministratore.id = ?;");
			}else {
				throw new IllegalArgumentException("Undefined user mode.");
			}
			
			st.setInt(1, user.getUserId());
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

}
