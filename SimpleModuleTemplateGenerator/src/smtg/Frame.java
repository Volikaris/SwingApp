package smtg;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private static final long serialVersionUID = -3257542491402854139L;
	private FileWriter fw = new FileWriter();
	private FileReader fr = new FileReader();
	private ArrayList<Item> items = new ArrayList<>();

	public Frame(String name) {
		this.setName(name);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 450);
		this.setVisible(true);
		items = fr.loadOnStart();
	}

	public void quit(ArrayList<Item> items) {
		fw.saveDefaultOnQuitting(items);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	public FileWriter getFw() {
		return fw;
	}

	public FileReader getFr() {
		return fr;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}


