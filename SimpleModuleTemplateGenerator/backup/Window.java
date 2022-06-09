package smtg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class Window {
	private static JTextField txtType;
	private static JTextField txtName;
	private static JTextField txtValue;
	private static JTable table;
	private static boolean isBeingAdded = false;
	private static boolean isBeingEdited = false;

	@SuppressWarnings("unused")
	private static void createAndShowGUI() {

		///// DIMENSIONS, DO NOT TOUCH, might use one day.
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		///// --------------------------------------------------------------------------------------------
		///// /////
		///// FRAME SETUP && READER/WRITER
		final Frame frame = new Frame("YSGIG");
		final FileWriter fw = frame.getFw();
		final FileReader fr = frame.getFr();
		final ArrayList<Item> items = frame.getItems();

		JPanel pTop = new JPanel();
		frame.getContentPane().add(pTop, BorderLayout.NORTH);
		pTop.setLayout(new BoxLayout(pTop, BoxLayout.X_AXIS));

		JPanel pBottom = new JPanel();
		pBottom.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(pBottom, BorderLayout.SOUTH);

		final JPanel pLeft = new JPanel();
		pLeft.setVisible(false);
		frame.getContentPane().add(pLeft, BorderLayout.WEST);

		JPanel pRight = new JPanel();
		frame.getContentPane().add(pRight, BorderLayout.EAST);
		GroupLayout gl_pRight = new GroupLayout(pRight);
		gl_pRight.setHorizontalGroup(gl_pRight.createParallelGroup(Alignment.LEADING).addGap(0, 10, Short.MAX_VALUE));
		gl_pRight.setVerticalGroup(gl_pRight.createParallelGroup(Alignment.LEADING).addGap(0, 313, Short.MAX_VALUE));
		pRight.setLayout(gl_pRight);

		JPanel pCenter = new JPanel();
		frame.getContentPane().add(pCenter, BorderLayout.CENTER);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		pTop.add(toolBar);

		///// BUTTONS
		JButton btnSubmit = new JButton("Submit");
		JButton btnClear = new JButton("Clear");
		JButton btnNew = new JButton("New");
		JButton btnEdit = new JButton("Edit");
		final JButton btnLoad = new JButton("Load");
		final JButton btnSave = new JButton("Save");
		JButton btnReload = new JButton("Reload");
		JButton btnDelete = new JButton("Delete");
		JButton btnQuit = new JButton("Quit");

		///// LABELS
		JLabel lblType = new JLabel("Type");
		JLabel lblName = new JLabel("Name");
		JLabel lblValue = new JLabel("Value");
		JLabel lblID = new JLabel("ID");

		///// TEXTPANES
		JTextPane txtBottomOne = new JTextPane();
		txtBottomOne.setDisabledTextColor(Color.BLACK);
		txtBottomOne.setForeground(Color.RED);
		txtBottomOne.setBackground(Color.LIGHT_GRAY);
		txtBottomOne.setText(
				"This application is a simple test of knowledge gained through tutorial, made by Konrad K. @ 2022");
		txtBottomOne.setEditable(false);
		txtBottomOne.setEnabled(false);

		final JTextPane txtCenter = new JTextPane();
		txtCenter.setBackground(new Color(240, 240, 240));
		txtCenter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCenter.setDisabledTextColor(new Color(0, 0, 0));
		txtCenter.setEnabled(false);
		txtCenter.setText(
				"Here should be loaded a default list.\r\nIf it's not, no worries - you're probably running the program first time.\r\nElse check if you have the file default.sav or if it is in the main directory.");

		///// TEXTFIELDS
		txtType = new JTextField();
		txtType.setColumns(1);
		txtName = new JTextField();
		txtName.setColumns(1);
		txtValue = new JTextField();
		txtValue.setColumns(1);
		final JTextField txtID = frame.intTxtField();
		txtID.setColumns(1);
		txtID.setEditable(false);

		///// ADDING STUFF
		toolBar.add(btnNew);
		toolBar.add(btnEdit);
		toolBar.add(btnLoad);
		toolBar.add(btnSave);
		toolBar.add(btnReload);
		toolBar.add(btnDelete);
		toolBar.add(btnQuit);
		GroupLayout gl_pBottom = new GroupLayout(pBottom);
		gl_pBottom.setHorizontalGroup(gl_pBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pBottom.createSequentialGroup().addGap(106).addComponent(txtBottomOne,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		gl_pBottom.setVerticalGroup(gl_pBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pBottom.createSequentialGroup().addGap(5).addComponent(txtBottomOne,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		pBottom.setLayout(gl_pBottom);

		///// TABLES
		table = new JTable(new DefaultTableModel(new Object[][] { { "ID", "Type", "Name", "Value" }, },
				new String[] { "ID", "Type", "Name", "Value" }));
		GroupLayout gl_pCenter = new GroupLayout(pCenter);
		gl_pCenter.setHorizontalGroup(gl_pCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pCenter.createSequentialGroup().addContainerGap()
						.addComponent(txtCenter, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE).addContainerGap())
				.addGroup(gl_pCenter.createSequentialGroup().addGap(73)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE).addGap(73)));
		gl_pCenter.setVerticalGroup(gl_pCenter.createParallelGroup(Alignment.LEADING).addGroup(gl_pCenter
				.createSequentialGroup().addContainerGap()
				.addComponent(txtCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(224, Short.MAX_VALUE)));
		pCenter.setLayout(gl_pCenter);
		final DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (Item item : items) {
			model.addRow(new Object[] { item.getID() - 1, item.getType(), item.getName(), item.getValue() });
		}

		///// LAYOUT
		GroupLayout gl_pLeft = new GroupLayout(pLeft);
		gl_pLeft.setHorizontalGroup(gl_pLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pLeft.createSequentialGroup()
						.addGroup(gl_pLeft.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pLeft.createSequentialGroup().addGap(71).addComponent(lblName))
								.addGroup(gl_pLeft.createSequentialGroup().addGap(72).addComponent(lblType))
								.addGroup(gl_pLeft.createSequentialGroup().addGap(71).addComponent(lblValue)))
						.addContainerGap())
				.addGroup(gl_pLeft.createSequentialGroup().addGap(20)
						.addComponent(txtType, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE).addGap(21))
				.addGroup(gl_pLeft.createSequentialGroup().addGap(20)
						.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE).addGap(21))
				.addGroup(gl_pLeft.createSequentialGroup().addGap(20).addComponent(btnClear)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnSubmit)
						.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(gl_pLeft.createSequentialGroup().addGap(79).addComponent(lblID).addContainerGap(79,
						Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						gl_pLeft.createSequentialGroup().addGap(20)
								.addGroup(gl_pLeft.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtID, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 128,
												Short.MAX_VALUE)
										.addComponent(txtValue, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 128,
												Short.MAX_VALUE))
								.addGap(21)));
		gl_pLeft.setVerticalGroup(gl_pLeft.createParallelGroup(Alignment.LEADING).addGroup(gl_pLeft
				.createSequentialGroup().addContainerGap().addComponent(lblType)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblName)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblValue)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblID)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
				.addGroup(
						gl_pLeft.createParallelGroup(Alignment.BASELINE).addComponent(btnClear).addComponent(btnSubmit))
				.addContainerGap()));
		pLeft.setLayout(gl_pLeft);
		///// --------------------------------------------------------------------------------------------
		///// /////
		///// LISTENERS /////

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBeingAdded && !isBeingEdited) {
					//if(txtType.getText().isEmpty() || txtName.getText().isEmpty() || txtValue.getText().isEmpty()) {
					//	JOptionPane.showMessageDialog(frame, "Please input data in all the fields");
					//}else {	
					model.addRow(new Object[] { "ID", "Type", "Name", "Value" });
					txtID.setText(table.getRowCount() + "");
					table.setValueAt(table.getRowCount() - 1, table.getRowCount() - 1, 0);
					table.setValueAt(txtType.getText(), table.getRowCount() - 1, 1);
					table.setValueAt(txtName.getText(), table.getRowCount() - 1, 2);
					table.setValueAt(txtValue.getText(), table.getRowCount() - 1, 3);
					Item item = new Item(getValue(txtID), txtType.getText(), txtName.getText(),
							txtValue.getText());
					items.add(item);
					txtType.setText("");
					txtName.setText("");
					txtValue.setText("");
					txtCenter.setText("Entry added.");
					//}
				} else if (!isBeingAdded && isBeingEdited) {
					int i = getValue(txtID);
					if (i > table.getRowCount() - 1 || i < 1) {
						i = (table.getRowCount() - 1);
						JOptionPane.showMessageDialog(frame, "Incorrect ID");
					} else {
					table.setValueAt(i, i, 0);
					table.setValueAt(txtType.getText(), i, 1);
					table.setValueAt(txtName.getText(), i, 2);
					table.setValueAt(txtValue.getText(), i, 3);
					items.get(i-1).edit((getValue(txtID))+1, txtType.getText(), txtName.getText(), txtValue.getText());
					txtType.setText("");
					txtName.setText("");
					txtValue.setText("");
					txtCenter.setText("Entry edited.");
					}
				}
			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtType.setText("");
				txtName.setText("");
				txtValue.setText("");
				txtCenter.setText("Entry cleared.");
				txtID.setEditable(false);
			}
		});
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isBeingAdded = true;
				isBeingEdited = false;
				pLeft.setVisible(true);
				txtCenter.setText("Enter data to add new Item.");
				txtID.setText(table.getRowCount() + "");
				txtID.setEditable(false);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isBeingAdded = false;
				isBeingEdited = true;
				pLeft.setVisible(true);
				System.out.println("File editing");
				txtCenter.setText("Remember to specify entry ID to edit");
				txtID.setText(table.getRowCount() - 1 + "");
				txtID.setEditable(true);
			}
		});
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fExists = 3;
				String name = JOptionPane.showInputDialog(frame, "Specify file name to load");
				if (name != null && !name.isEmpty()) {
					if (!name.isEmpty()) { /// name provided successfully
						if (name.endsWith(".sav")) { /// if user added .sav it cuts it off for file loading
							name = name.substring(0, name.length() - 4);
						}
						ArrayList<Item> itemList = fr.load(name); /// try to load file by specified filename
						if (itemList == null) {
							fExists = JOptionPane.showConfirmDialog(frame, "There is no such file. Load default?"); // pop-up
						}
						switch (fExists) {
						case 0:
							itemList = fr.loadOnStart();
							break;
						case 1:
							btnLoad.doClick();
						case 2:
							return;
						default:
							break;
						}
						clearTable(table, model); /// prepare table for loading
						items.clear();
						for (Item item : itemList) {
							model.addRow(
									new Object[] { item.getID() - 1, item.getType(), item.getName(), item.getValue() });
									items.add(item);
						}
					} else { //// File not found / Input not empty - loading default
						fr.loadOnStart();
					}
				} else if (name == null) {
					return; ///// Name not provided / Input empty - pressed OK.
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter filename!");
				}
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fExists = 2;
				if (!items.isEmpty()) {
					String name = JOptionPane.showInputDialog(frame, "Specify file name to save");
					if (name == null)
						return;
					if (!name.isEmpty()) {
						Matcher m = Pattern.compile("[^A-Za-z0-9]").matcher(name);
						Matcher m2 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]").matcher(name);
						if (m.find()) {
							JOptionPane.showMessageDialog(frame,
									"Please only use letters a-z, A-Z or numbers 0-9 in file name");
							return;
						}
						if (!m.find() || m2.find()) {
							fExists = 0;
							if (name.endsWith(".sav")) { /// if user added .sav it cuts it off for file loading
								name = name.substring(0, name.length() - 4);
							}
							if (fr.fileExists(name)) {
								fExists = JOptionPane.showConfirmDialog(frame,
										"File already exists. Do you want to overwrite?");
							}
							switch (fExists) {
							case 0:
								fw.write(name, items);
								break;
							case 1:
								btnSave.doClick();
							case 2:
								return;
							default:
								break;
							}
						} else {
							JOptionPane.showMessageDialog(frame,
									"Please only use letters a-z, A-Z or numbers 0-9 in file name");
						}
					}
					if (name.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Please enter filename!");
					}
				} else {
					JOptionPane.showMessageDialog(frame, "There's nothing to save.");
				}
			}
		});
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCenter.setText("Program reloaded.");
				pLeft.setVisible(false);
				clearTable(table, model);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pLeft.setVisible(false);
				String name = JOptionPane.showInputDialog(frame, "Specify file name to delete");
				if (name == null) return;
				if (!name.isEmpty()) {
					if (name.endsWith(".sav")) { /// if user added .sav it cuts it off for file loading
						name = name.substring(0, name.length() - 4);
					}
					if (fr.fileExists(name)) {
						fw.deleteFile(name + ".sav");
						JOptionPane.showMessageDialog(frame, "File deleted succesfully");
						clearTable(table, model);
						clearItems(items);
						txtCenter.setText("List deleted.");
					}else {
						JOptionPane.showMessageDialog(frame, "Could not delete file/missing file with that name");
					}
				}
			}
		});
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.quit(items);
			}
		});
	}

	public static void clearTable(JTable table, DefaultTableModel model) {
		for (int i = 0; i < table.getColumnCount(); i++) {
			for (int j = 1; j < table.getRowCount(); j++) {
				table.setValueAt("", j, i);
			}
		}
		while (table.getRowCount() > 1) {
			model.removeRow(table.getRowCount() - 1);
		}
	}

	public static void clearItems(ArrayList<Item> items) {
		items.removeAll(items);
	}

	public static void setItems(ArrayList<Item> items, ArrayList<Item> itemList) {
		items = itemList;
	}

	public static int getValue(JTextField tf) {
		int i = Integer.parseInt(tf.getText(), 10);
		return i;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void run() {
		createAndShowGUI();
	}
}
