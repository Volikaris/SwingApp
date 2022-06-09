package smtg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileWriter {
	
	public FileWriter() {
		
	}
	
	public void write(String fileName, ArrayList<Item> items) {
		if(fileName.startsWith("lastUsed")) {
			return;
		}
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName + ".sav"))){
			
			os.writeObject(items);
			return;
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception");
		} catch (IOException e) {
			System.out.println("IO exception");
		}
	}
	
	public void saveDefaultOnQuitting(ArrayList<Item> items) {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("lastUsed.sav"))) {

			os.writeObject(items);
			return;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void deleteFile(String fileName) {
		File f = new File(fileName);
		if(f.exists())f.delete();
	}

}
