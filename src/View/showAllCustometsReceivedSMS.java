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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class showAllCustometsReceivedSMS {
	private int count;
	//button
	private Button done;
	//text and field
	private Label headline;
	private ArrayList<Label> allToshow;
	//layout
	private VBox allDetails;
	private HBox headLineBox;
	private HBox doneBox;
	private StackPane middleScreen;
	private BorderPane screen;
	private Stage stage;
	
	
	public showAllCustometsReceivedSMS(Stage stage) {
		this.count=0;
		this.allToshow=new ArrayList<>();
		//buttons
		this.done=new Button("Done");
		this.done.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		this.done.setDisable(true);
		
		//texts
		this.headline= new Label("Showing All Customers That Received The SMS");
		this.headline.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		this.headline.setTextFill(Color.DARKSLATEGRAY);
		
		//layout
		this.headLineBox= new HBox();
		this.headLineBox.getChildren().addAll(headline);
		this.headLineBox.setAlignment(Pos.CENTER);
		HBox.setMargin(this.headline, new Insets(40, 0, 0, 0));
		
		this.doneBox= new HBox();
		this.doneBox.getChildren().addAll(done);
		this.doneBox.setAlignment(Pos.CENTER);
		HBox.setMargin(this.done, new Insets(0, 0, 40, 0));
		
		this.allDetails = new VBox();
		this.allDetails.getChildren().addAll();
		this.allDetails.setSpacing(30);
		this.allDetails.setAlignment(Pos.CENTER);
		this.allDetails.setVisible(false);
		
		this.middleScreen=new StackPane();
		this.middleScreen.getChildren().addAll(allDetails);
		StackPane.setAlignment(allDetails,Pos.CENTER);
		
		
		this.screen=new BorderPane();
		this.screen.setTop(headLineBox);
		this.screen.setCenter(middleScreen);
		this.screen.setBottom(doneBox);
		
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
					for (int i = 0; i < allToshow.size(); i++) {
						Thread.sleep(2000);

						Platform.runLater(() -> {
							allDetails.getChildren().add(allToshow.get(count));
							allDetails.setVisible(true);
							count++;
						});
					}
					Platform.runLater(() -> {
						done.setDisable(false);
					});
				} catch (InterruptedException e1) {

				}

			}
		});
		t.start();
	}

	public void getAllCustomers(ArrayList<String> allConfirmedCustomers) {
		for (int i = 0; i < allConfirmedCustomers.size(); i++) {
			this.allToshow.add(new Label(allConfirmedCustomers.get(i)));
			this.allToshow.get(i).setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
		}
	}

	public void close() {
		for (int i = 0; i < count; i++) {
			allDetails.getChildren().remove(allToshow.get(i));
		}
		this.allToshow.removeAll(allToshow);
		this.count=0;
		this.stage.close();
	}

	
}
