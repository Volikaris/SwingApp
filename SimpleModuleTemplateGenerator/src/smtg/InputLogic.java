package smtg;

import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class InputLogic {

	JPanel popUp = new JPanel();
	JPanel popUp2 = new JPanel();
	JTextField txtAmount = new JTextField(1);
	JTextField txtX = new JTextField(1);
	JTextField txtY = new JTextField(1);
	JTextField txtZ = new JTextField(1);
	JLabel lblX = new JLabel("H:");
	JLabel lblY = new JLabel("W:");
	JLabel lblZ = new JLabel("D:");
	JLabel lblAmount = new JLabel("Amount:");
	private int dims[] = { 0, 0, 0, 0 };

	public void setLayout(JPanel p) {
		GroupLayout gl = new GroupLayout(p);
		p.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(lblX)
				.addComponent(txtX, 50, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(lblY)
				.addComponent(txtY, 50, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(lblZ)
				.addComponent(txtZ, 50, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblX).addComponent(txtX)
						.addComponent(lblY).addComponent(txtY).addComponent(lblZ).addComponent(txtZ)));
	}

	public void setLayout2(JPanel p) {
		GroupLayout gl = new GroupLayout(p);
		p.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(lblAmount).addComponent(txtAmount, 50,
				GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		gl.setVerticalGroup(gl.createSequentialGroup().addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(lblAmount).addComponent(txtAmount)));
	}

	@SuppressWarnings("unused")
	public InputLogic() {
		clearData();
		Math m = new Math(this);
		setLayout(popUp2);
		popUp2.setName("Wardrobe Dimensions");
		setIntFilter(txtX);
		setIntFilter(txtY);
		setIntFilter(txtZ);
		setLayout2(popUp);
		popUp.setName("Modules");
		setIntFilter(txtAmount);
	}

	private static String[] options = { "Wardrobe", "Kitchen", "Other", "Cancel" },
			options2 = { "Built-in", "Free-standing", "Back", "Cancel" },
			options3 = { "Sliding doors", "Normal doors", "Back", "Cancel" };

	public void generateLogic(Frame f) {
		clearData();
		int result = JOptionPane.showOptionDialog(f, "Choose a template to generate", "Generate",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		switch (result) {
		case 0:
			generateLogicWardrobe(f);
			break;
		case 1:
			JOptionPane.showMessageDialog(f, "Option coming up soon");
			break;
		case 2:
			JOptionPane.showMessageDialog(f, "Option coming up soon");
			break;
		default:
			break;
		}

	}

	public void generateLogicWardrobe(Frame f) {
		int result2 = JOptionPane.showOptionDialog(f, "Is it built-in or free-standing", "Wardrobe type",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, options2[0]);
		switch (result2) {
		case 0:
			generateLogicWardrobeDoors(f, result2);
			break;
		case 1:
			generateLogicWardrobeDoors(f, result2);
			break;
		case 2:
			generateLogic(f);
			break;
		default:
			break;
		}
	}

	public void generateLogicWardrobeDoors(Frame f, int i) {
		int result3 = JOptionPane.showOptionDialog(f, "Sliding/Normal doors", "Door type", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options3, options3[0]);
		switch (result3) {
		case 0:
			generateLogicWardrobeDimensions(f, i, result3);
			break;
		case 1:
			generateLogicWardrobeDimensions(f, i, result3);
			break;
		case 2:
			generateLogicWardrobe(f);
			break;
		default:
			break;
		}
	}

	public void generateLogicWardrobeDimensions(Frame f, int i, int j) {
		int result4 = JOptionPane.showConfirmDialog(f, popUp2, "Please specify Height/Width/Depth of the wardrobe",
				JOptionPane.OK_CANCEL_OPTION);
		if (result4 == JOptionPane.OK_OPTION && !txtX.getText().equalsIgnoreCase("")
				&& !txtY.getText().equalsIgnoreCase("") && !txtZ.getText().equalsIgnoreCase("")) {
			dims[0] = getValue(txtX);
			dims[1] = getValue(txtY);
			dims[2] = getValue(txtZ);
			for (int ii = 0; ii < dims.length; ii++) {
				if (dims[ii] > 2700)
					dims[ii] = 2700;
				if (dims[ii] < 1)
					dims[ii] = 1;
			}
			generateLogicWardrobeModules(f, i, j, dims[0], dims[1], dims[2]);
		} else if (txtX.getText().equalsIgnoreCase("") || txtY.getText().equalsIgnoreCase("")
				|| txtZ.getText().equalsIgnoreCase("")) {
			int res = JOptionPane.showConfirmDialog(f, "Please input all the required dimensions", "Continue?",
					JOptionPane.OK_CANCEL_OPTION);
			if (res == JOptionPane.OK_OPTION)
				generateLogicWardrobeDimensions(f, i, j);
		}
	}

	public int[] generateLogicWardrobeModules(Frame f, int i, int j, int h, int w, int d) {
		int result5 = JOptionPane.showConfirmDialog(f, popUp, "Please specify number of modules",
				JOptionPane.OK_CANCEL_OPTION);
		if (result5 == JOptionPane.OK_OPTION && !txtAmount.getText().equalsIgnoreCase("")) {
			dims[3] = getValue(txtAmount);
			if (dims[3] > 10) {
				dims[3] = 10;
				JOptionPane.showMessageDialog(f, "Amount too high, modules set to maximum according to width");
			} else if (dims[3] == 0) {
				dims[3] = 1;
				JOptionPane.showMessageDialog(f, "Amount too high, modules set to minimum according to width");
			}
			for (int dim : dims)
				System.out.println(dim);
			String txt = "You've specified these Dimensions - Height: " + dims[0] + " / Width: " + dims[1] + " / Depth: " + dims[2] + " - with " + dims[3] + " modules.\nGenerating...";
			JOptionPane.showMessageDialog(f, txt);
			return dims;
		} else if (txtAmount.getText().equalsIgnoreCase("")) {
			int res = JOptionPane.showConfirmDialog(f, "Please input amount", "Continue?",
					JOptionPane.OK_CANCEL_OPTION);
			if (res == JOptionPane.OK_OPTION)
				generateLogicWardrobeModules(f, i, j, h, w, d);
			return null;
		} else
			return null;
	}

	public int getValue(JTextField tf) {
		int i = Integer.parseInt(tf.getText(), 10);
		return i;
	}

	public void clearData() {
		for (int i = 0; i < dims.length; i++)
			dims[i] = 0;
		txtX.setText("");
		txtY.setText("");
		txtZ.setText("");
		txtAmount.setText("");
	}

	public void setIntFilter(JTextField tf) {
		AbstractDocument doc = (AbstractDocument) tf.getDocument();
		final int maxCh = 5;
		doc.setDocumentFilter(new DocumentFilter() {
			public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
					throws BadLocationException {
				String text = fb.getDocument().getText(0, fb.getDocument().getLength());
				text += str;
				if ((fb.getDocument().getLength() + str.length() - length) <= maxCh && text.matches("[0-9]+")) {
					super.replace(fb, offs, length, str, a);
				} else {
					Toolkit.getDefaultToolkit().beep();
				}

			}

			public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
					throws BadLocationException {

				String text = fb.getDocument().getText(0, fb.getDocument().getLength());
				text += str;
				if ((fb.getDocument().getLength() + str.length()) <= maxCh && text.matches("[0-9]+")) {
					super.insertString(fb, offs, str, a);
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
	}

	public int[] getDims() {
		return dims;
	}

	public void setDims(int[] dims) {
		this.dims = dims;
	}
}
