package smtg;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = -8188532406518588259L;
	private int ID;
	private String type, name, value;
	
	public Item(int ID, String type, String name, String value) {
		this.ID = ID;
		this.type = type;
		this.name = name;
		this.value = value;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getInfo() {
		return getID() + " " + getType() + " " + getName() + " " + getValue();
	}
	
	public void edit(int id, String type, String name, String value) {
		this.ID = id;
		this.type = type;
		this.name = name;
		this.value = value;
	}

}
