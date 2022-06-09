package smtg;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Frame extends JFrame {

	private static final long serialVersionUID = -3257542491402854139L;
	private FileWriter fw = new FileWriter();
	private FileReader fr = new FileReader();
	private ArrayList<Item> items = new ArrayList<>();

	public Frame(String name) {
		this.setName(name);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 400);
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
	
	public JTextField intTxtField() {
		JTextField tf = new JTextField();
		AbstractDocument doc = (AbstractDocument) tf.getDocument();
		final int maxCh = 5;
		doc.setDocumentFilter(new DocumentFilter() {
			public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException{
				String text = fb.getDocument().getText(0, fb.getDocument().getLength());
				text += str;
				if((fb.getDocument().getLength() + str.length() - length) <= maxCh && text.matches("[0-9]+")) {
					super.replace(fb, offs, length, str, a);
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
					
			}
			public void insertString(FilterBypass fb, int offs, String str,
                    AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCh && text.matches("[0-9]+")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
		});
		return tf;
	}

}
