package smtg;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = -8188532406518588259L;
	private int ID, amount;
	private String type, name, dimX, dimY;
	private Boolean left, right, top, bottom;
	
	public Item(int ID, String type, String name, String dimX, String dimY, int amount, Boolean left, Boolean right, Boolean top, Boolean bottom) {
		this.ID = ID;
		this.type = type;
		this.name = name;
		this.dimX = dimX;
		this.dimY = dimY;
		this.amount = amount;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
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

	public String getDimX() {
		return dimX;
	}

	public void setDimX(String dimX) {
		this.dimX = dimX;
	}
	
	public String getInfo() {
		return getID() + " " + getType() + " " + getName() + " " + getDimX() + " " + getDimY() + " " + getAmount() + " " + getLeft() + " " + getRight() + " " + getTop() + " " + getBottom();
	}
	
	public void edit(int id, String type, String name, String dimX, String dimY, int amount, Boolean left, Boolean right, Boolean top, Boolean bottom) {
		this.ID = id;
		this.type = type;
		this.name = name;
		this.dimX = dimX;
		this.dimY = dimY;
		this.amount = amount;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	public String getDimY() {
		return dimY;
	}

	public void setDimY(String dimY) {
		this.dimY = dimY;
	}

	public Boolean getLeft() {
		return left;
	}

	public void setLeft(Boolean left) {
		this.left = left;
	}

	public Boolean getRight() {
		return right;
	}

	public void setRight(Boolean right) {
		this.right = right;
	}

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

	public Boolean getBottom() {
		return bottom;
	}

	public void setBottom(Boolean bottom) {
		this.bottom = bottom;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
