package gui;

import java.util.Objects;

import dbConnection.UserDAO;
import dbConnection.UserDAOImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import users.Amministratore;
import users.Elettore;
import users.User;

public class RowUser implements Comparable<RowUser> {
	private final User user;
	//private final SimpleIntegerProperty id;
	private final SimpleStringProperty username;
	private final SimpleStringProperty name;
	private final SimpleStringProperty surname;
	private final SimpleStringProperty type;
	private ButtonBar buttonBar;
	
	public RowUser(User u) {
		user = Objects.requireNonNull(u);
		//id = new SimpleIntegerProperty(user.getId());
		username = new SimpleStringProperty(user.getUsername());
		name = new SimpleStringProperty(user.getName());
		surname = new SimpleStringProperty(user.getSurname());
		if(user instanceof Elettore) {
			type = new SimpleStringProperty("Elettore");
		}else if(user instanceof Amministratore) {
			type = new SimpleStringProperty("Amministratore");
		}else {
			type = new SimpleStringProperty("null");
		}
		
		Button deleteUserButton = new Button("");
		ImageView deletePng = new ImageView(new Image(getClass().getResource("delete.png").toString()));
		deletePng.setFitHeight(20);
		deletePng.setPreserveRatio(true);
		deleteUserButton.setGraphic(deletePng);
		deleteUserButton.setOnAction(event -> deleteUser());
		
		Button blockUserButton = new Button("");
		ImageView blockPng = new ImageView(new Image(getClass().getResource("block.png").toString()));
		blockPng.setFitHeight(20);
		blockPng.setPreserveRatio(true);
		blockUserButton.setGraphic(blockPng);
		blockUserButton.setOnAction(event -> blockUser());
		
		buttonBar = new ButtonBar();
		buttonBar.getButtons().addAll(deleteUserButton, blockUserButton);
	}
	
	private void blockUser() {
		// TODO Auto-generated method stub
	}

	private void deleteUser() {
		UserDAO userDao = new UserDAOImpl();
		userDao.deleteUser(user);
	}

	public User getUser() {
		return user;
	}
	/*
	public int getId() {
		return id.get();
	}
	*/
	public String getUsername() {
		return username.get();
	}
	
	public String getName() {
		return name.get();
	}
	
	public String getSurname() {
		return surname.get();
	}
	
	public String getType() {
		return type.get();
	}
	
	public ButtonBar getButtonBar() {
		return buttonBar;
	}

	@Override
	public int compareTo(RowUser o) {
		return this.getUsername().compareToIgnoreCase(o.getUsername());
	}
}
