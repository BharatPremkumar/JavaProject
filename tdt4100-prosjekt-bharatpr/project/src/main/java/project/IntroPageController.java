package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class IntroPageController {
	
	

	@FXML
	private Label label;
	
	//knapp som, når trykket, tar deg med til neste scene
	@FXML
	private void handelButtonAction(ActionEvent event) throws Exception {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	 }
	
	
}
