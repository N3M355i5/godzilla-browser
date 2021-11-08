package application;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class Controller implements Initializable{
	@FXML
	private WebView myWebView;
	@FXML
	private TextField urlField, searchField;
	
	private WebEngine engine;
	
	private String homePage;
	
	private double zoom = 1.0;
	
	private WebHistory webHistory;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		engine = myWebView.getEngine();
		homePage = "google.com";
		urlField.setText(homePage);
		loadPage();
	}
	public void loadPage() {
		engine.load("http://www."+urlField.getText());
	}
	public void refreshPage() {
		zoom=1.0;
		myWebView.setZoom(zoom);
		engine.reload();
	}
	public void searchPage() {
		engine.load("http://www.google.com/search?q="+searchField.getText());
	}
	public void zoomIn() {
		myWebView.setZoom(zoom+=0.25);
	}
	public void zoomOut() {
		myWebView.setZoom(zoom-=0.25);
	}
	public void displayHistory() {
		webHistory = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = webHistory.getEntries();
		for(WebHistory.Entry entry : entries) {
			System.out.println(entry.getUrl()+" "+entry.getLastVisitedDate());
		}
	}
	public void back() {
		webHistory = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = webHistory.getEntries();
		webHistory.go(-1);
		urlField.setText(entries.get(webHistory.getCurrentIndex()).getUrl());
		
	}
	public void forward() {
		webHistory = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = webHistory.getEntries();
		webHistory.go(+1);
		urlField.setText(entries.get(webHistory.getCurrentIndex()).getUrl());
	}
	public void executeJs() {
		engine.executeScript("window.location = \"https://www.youtube.com\"");
	}
}
