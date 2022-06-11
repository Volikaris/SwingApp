package smtg;

public class Math {

	private int dims[];
	
	public Math(InputLogic il) {
		this.setDims(il.getDims());
	}

	public int[] getDims() {
		return dims;
	}

	public void setDims(int dims[]) {
		this.dims = dims;
	}
}
