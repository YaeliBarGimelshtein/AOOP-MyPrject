package View;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class showAllCustometsReceivedSMS {
	//button
	private Button done;
	//text and field
	private Label headline;
	private Label Customer;
	private ArrayList<String> allToshow;
	//layout
	private VBox allDetails;
	private HBox headLineBox;
	private Pane middleScreen;
	private BorderPane screen;
	private Stage stage;
	
	
	public showAllCustometsReceivedSMS(Stage stage) {
		this.allToshow=null;
		//buttons
		this.done=new Button("Done");
		this.done.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		
		//texts
		this.headline= new Label("Showing All Customers That Received The SMS");
		this.headline.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		this.headline.setTextFill(Color.DARKSLATEGRAY);
		
		this.Customer= new Label("Customer 1");
		//layout
		this.headLineBox= new HBox();
		this.headLineBox.getChildren().addAll(headline);
		this.headLineBox.setAlignment(Pos.CENTER);
		HBox.setMargin(this.headline, new Insets(40, 0, 0, 0));
		
		this.allDetails = new VBox();
		this.allDetails.getChildren().addAll();
		this.allDetails.setSpacing(30);
		this.allDetails.setAlignment(Pos.CENTER_LEFT);
		
		this.middleScreen=new Pane();
		this.middleScreen.getChildren().addAll(allDetails);
		
		this.screen=new BorderPane();
		this.screen.setTop(headLineBox);
		this.screen.setCenter(middleScreen);
		this.screen.setBottom(done);
		
		Scene scene = new Scene(screen,600,600);
		
		this.stage=stage;
		this.stage.setScene(scene);
	}
	
	public void show() {
		this.stage.show();
	}
	
	public Button getDoneButton() {
		return this.done;
	}
	
	
	public void showAllCustomers() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					Platform.runLater(() -> {
						Customer.setText("BLA BLA BLA!");
					});
				} catch (InterruptedException e1) {

				}

			}
		});
		t.start();
	}

	public void getAllCustomers(ArrayList<String> allConfirmedCustomers) {
		this.allToshow=allConfirmedCustomers;
	}

	
}
