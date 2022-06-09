package smtg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FileReader {

	public FileReader() {

	}

	private ArrayList<Item> itemList;

	public ArrayList<Item> load(String fileName) {
		try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(fileName + ".sav"))) {

			@SuppressWarnings("unchecked")
			ArrayList<Item> items = (ArrayList<Item>) os.readObject();
			itemList = items;
			return items;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			System.out.println("IO exception");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exception");
		}
		System.out.println("we're also here");
		return new ArrayList<Item>();
	}

	public ArrayList<Item> loadOnStart() {
		try (ObjectInputStream os = new ObjectInputStream(new FileInputStream("lastUsed.sav"))) {

			@SuppressWarnings("unchecked")
			ArrayList<Item> items = (ArrayList<Item>) os.readObject();
			itemList = items;
			return items;
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("IO exception");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exception");
		}
		return new ArrayList<Item>();
	}

	public ArrayList<Item> getItems() {
		return itemList;
	}
	
	public boolean fileExists(String fileName) {
		try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(fileName + ".sav"))){
			return true;
		} catch (FileNotFoundException e) { 
			return false;
		} catch (IOException e) {
			System.out.println("IO exception");
		}
		return false;
		
	}
}
