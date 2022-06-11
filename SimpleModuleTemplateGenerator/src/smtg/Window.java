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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class Window {
	private static JTextField txtParent, txtName, txtX, txtY, txtID, txtAmount, txtGenX, txtGenY, txtGenAmount;
	private static JTable table;
	private static boolean isBeingAdded = false, isBeingEdited = false, left, right, top, bottom;
	private static JRadioButton rbLeft, rbRight, rbTop, rbBottom;

	@SuppressWarnings("unused")
	private static void createAndShowGUI() {

		///// DIMENSIONS, DO NOT TOUCH, might use one day.
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		///// --------------------------------------------------------------------------------------------
		///// /////
		///// FRAME SETUP && READER/WRITER
		final Frame frmSimpleSwingApplication = new Frame("YSGIG");
		frmSimpleSwingApplication.setTitle("Quichen 1.11");
		final FileWriter fw = frmSimpleSwingApplication.getFw();
		final FileReader fr = frmSimpleSwingApplication.getFr();
		final ArrayList<Item> items = frmSimpleSwingApplication.getItems();
		final InputLogic il = new InputLogic();

		JPanel pTop = new JPanel();
		frmSimpleSwingApplication.getContentPane().add(pTop, BorderLayout.NORTH);
		pTop.setLayout(new BoxLayout(pTop, BoxLayout.X_AXIS));

		JPanel pBottom = new JPanel();
		pBottom.setBackground(Color.LIGHT_GRAY);
		frmSimpleSwingApplication.getContentPane().add(pBottom, BorderLayout.SOUTH);

		final JScrollPane spLeft = new JScrollPane();
		spLeft.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		spLeft.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frmSimpleSwingApplication.getContentPane().add(spLeft, BorderLayout.WEST);
		final JPanel pLeft = new JPanel();
		spLeft.setViewportView(pLeft);
		pLeft.setVisible(false);

		JScrollPane spCenter = new JScrollPane();
		spCenter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frmSimpleSwingApplication.getContentPane().add(spCenter, BorderLayout.CENTER);
		JPanel pCenter = new JPanel();
		spCenter.setViewportView(pCenter);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		pTop.add(toolBar);

		///// BUTTONS
		JButton btnSubmit = new JButton("Submit");
		JButton btnClear = new JButton("Clear");
		JButton btnNew = new JButton("New");
		JButton btnGenerate = new JButton("Generate");
		JButton btnEdit = new JButton("Edit");
		final JButton btnLoad = new JButton("Load");
		final JButton btnSave = new JButton("Save");
		JButton btnReload = new JButton("Reload");
		JButton btnDelete = new JButton("Delete");
		JButton btnQuit = new JButton("Quit");

		///// LABELS
		JLabel lblParent = new JLabel("Parent");
		JLabel lblName = new JLabel("Name");
		JLabel lblX = new JLabel("DimX");
		JLabel lblID = new JLabel("ID");
		JLabel lblY = new JLabel("DimY");
		JLabel lblNewLabel = new JLabel(" Left     Right      Top     Bottom");
		JLabel lblAmount = new JLabel("Amount");

		///// RADIOBUTTONS
		rbLeft = new JRadioButton("");
		rbRight = new JRadioButton("");
		rbTop = new JRadioButton("");
		rbBottom = new JRadioButton("");

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
		txtCenter.setText("Welcome to Quichen - A quick way to do cut forms for kitchen.");

		///// TEXTFIELDS
		txtParent = new JTextField(1);
		txtParent.setHorizontalAlignment(SwingConstants.CENTER);
		txtName = new JTextField(1);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtX = new JTextField(1);
		txtX.setHorizontalAlignment(SwingConstants.CENTER);
		il.setIntFilter(txtX);
		txtID = new JTextField(1);
		txtID.setHorizontalAlignment(SwingConstants.CENTER);
		il.setIntFilter(txtID);
		txtID.setEditable(false);
		txtAmount = new JTextField(1);
		txtAmount.setHorizontalAlignment(SwingConstants.CENTER);
		il.setIntFilter(txtAmount);
		txtY = new JTextField(1);
		txtY.setHorizontalAlignment(SwingConstants.CENTER);
		il.setIntFilter(txtY);
		txtGenX = new JTextField(1);
		txtGenX.setHorizontalAlignment(SwingConstants.CENTER);
		il.setIntFilter(txtGenX);
		txtGenY = new JTextField(1);
		txtGenY.setHorizontalAlignment(SwingConstants.CENTER);
		il.setIntFilter(txtGenY);
		txtGenAmount = new JTextField(1);
		txtGenAmount.setHorizontalAlignment(SwingConstants.CENTER);
		il.setIntFilter(txtGenAmount);

		///// GENERATION WINDOW
		final JPanel genPanel = new JPanel();
		genPanel.add(new JLabel("Amount:"));
		genPanel.add(txtGenAmount);

		///// ADDING STUFF
		toolBar.add(btnNew);
		toolBar.add(btnGenerate);
		toolBar.add(btnEdit);
		toolBar.add(btnLoad);
		toolBar.add(btnSave);
		toolBar.add(btnReload);
		toolBar.add(btnDelete);
		toolBar.add(btnQuit);

		///// TABLES
		table = new JTable(new DefaultTableModel(
				new Object[][] {
						{ "ID", "Parent", "Name", "DimX", "DimY", "Amount", "Left", "Right", "Top", "Bottom" }, },
				new String[] { "ID", "Parent", "Name", "DimX", "DimY", "Amount", "Left", "Right", "Top", "Bottom" }));

		lblParent.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblY.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		rbLeft.setHorizontalAlignment(SwingConstants.CENTER);
		rbRight.setHorizontalAlignment(SwingConstants.CENTER);
		rbTop.setHorizontalAlignment(SwingConstants.CENTER);
		rbBottom.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout gl_spCenter = new GroupLayout(spCenter);
		GroupLayout gl_pCenter = new GroupLayout(pCenter);
		gl_pCenter.setHorizontalGroup(gl_pCenter.createParallelGroup(Alignment.LEADING).addGroup(gl_pCenter
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE).addComponent(txtCenter,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_pCenter.setVerticalGroup(gl_pCenter.createParallelGroup(Alignment.LEADING).addGroup(gl_pCenter
				.createSequentialGroup().addContainerGap()
				.addComponent(txtCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(10, Short.MAX_VALUE)));
		pCenter.setLayout(gl_pCenter);
		GroupLayout gl_pBottom = new GroupLayout(pBottom);
		gl_pBottom.setHorizontalGroup(gl_pBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pBottom.createSequentialGroup().addGap(106).addComponent(txtBottomOne,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		gl_pBottom.setVerticalGroup(gl_pBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pBottom.createSequentialGroup().addGap(5).addComponent(txtBottomOne,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		pBottom.setLayout(gl_pBottom);

		GroupLayout gl_spLeft = new GroupLayout(spLeft);
		GroupLayout gl_pLeft = new GroupLayout(pLeft);
		gl_pLeft.setHorizontalGroup(gl_pLeft.createParallelGroup(Alignment.LEADING).addGroup(gl_pLeft
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pLeft.createParallelGroup(Alignment.LEADING).addGroup(gl_pLeft.createSequentialGroup()
						.addComponent(txtAmount, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE).addContainerGap())
						.addGroup(gl_pLeft.createSequentialGroup().addGroup(gl_pLeft
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtID, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(lblID, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addGroup(gl_pLeft.createSequentialGroup()
										.addComponent(rbLeft, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE).addGap(18)
										.addComponent(rbRight, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE).addGap(18)
										.addComponent(rbTop, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE).addGap(18)
										.addComponent(rbBottom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(9))
								.addComponent(lblParent, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(txtParent, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addGroup(gl_pLeft.createSequentialGroup().addComponent(btnClear)
										.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
										.addComponent(btnSubmit))
								.addGroup(gl_pLeft.createSequentialGroup()
										.addGroup(gl_pLeft.createParallelGroup(Alignment.LEADING)
												.addComponent(lblX, GroupLayout.PREFERRED_SIZE, 70,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtX, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 70,
														Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_pLeft.createParallelGroup(Alignment.LEADING)
												.addComponent(lblY, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 71,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtY, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 71,
														Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGap(10))
						.addGroup(Alignment.TRAILING,
								gl_pLeft.createSequentialGroup()
										.addComponent(lblAmount, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
										.addContainerGap()))));
		gl_pLeft.setVerticalGroup(gl_pLeft.createParallelGroup(Alignment.LEADING).addGroup(gl_pLeft
				.createSequentialGroup().addContainerGap().addComponent(lblParent)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtParent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(8).addComponent(lblName).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_pLeft.createParallelGroup(Alignment.BASELINE).addComponent(lblX).addComponent(lblY))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_pLeft.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblAmount)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_pLeft.createParallelGroup(Alignment.LEADING).addComponent(rbLeft).addComponent(rbRight)
						.addComponent(rbTop).addComponent(rbBottom))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblID)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(
						gl_pLeft.createParallelGroup(Alignment.BASELINE).addComponent(btnClear).addComponent(btnSubmit))
				.addContainerGap(10, Short.MAX_VALUE)));
		pLeft.setLayout(gl_pLeft);

		///// TABLEMODEL
		final DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (Item item : items) {
			model.addRow(new Object[] { item.getID() - 1, item.getType(), item.getName(), item.getDimX(),
					item.getDimY(), item.getAmount(), (item.getLeft()), getBool(item.getRight()),
					getBool(item.getTop()), getBool(item.getBottom()) });
		}
		///// --------------------------------------------------------------------------------------------
		///// /////
		///// LISTENERS /////

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtParent.getText().isEmpty() || txtName.getText().isEmpty() || txtX.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frmSimpleSwingApplication, "Please input data in all the fields");
				} else {
					if (isBeingAdded && !isBeingEdited) {
						model.addRow(new Object[] { "ID", "Parent", "Name", "DimX", "DimY", "Left", "Right", "Top",
								"Bottom" });
						txtID.setText(table.getRowCount() + "");
						table.setValueAt(table.getRowCount() - 1, table.getRowCount() - 1, 0);
						table.setValueAt(txtParent.getText(), table.getRowCount() - 1, 1);
						table.setValueAt(txtName.getText(), table.getRowCount() - 1, 2);
						table.setValueAt(txtX.getText(), table.getRowCount() - 1, 3);
						table.setValueAt(txtY.getText(), table.getRowCount() - 1, 4);
						table.setValueAt(getValue(txtAmount), table.getRowCount() - 1, 5);
						table.setValueAt(getBool(isLeft()), table.getRowCount() - 1, 6);
						table.setValueAt(getBool(isRight()), table.getRowCount() - 1, 7);
						table.setValueAt(getBool(isTop()), table.getRowCount() - 1, 8);
						table.setValueAt(getBool(isBottom()), table.getRowCount() - 1, 9);

						Item item = new Item(getValue(txtID), txtParent.getText(), txtName.getText(), txtX.getText(),
								txtY.getText(), getValue(txtAmount), isLeft(), isRight(), isTop(), isBottom());
						items.add(item);
						clearAll();
						txtCenter.setText("Entry added.");
					} else if (!isBeingAdded && isBeingEdited) {
						if (txtID.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(frmSimpleSwingApplication, "Incorrect ID");
						} else {
							int i = getValue(txtID);
							if (i > table.getRowCount() - 1 || i < 1) {
								i = (table.getRowCount() - 1);
								JOptionPane.showMessageDialog(frmSimpleSwingApplication, "Incorrect ID");
							} else {
								table.setValueAt(i, i, 0);
								table.setValueAt(txtParent.getText(), i, 1);
								table.setValueAt(txtName.getText(), i, 2);
								table.setValueAt(txtX.getText(), i, 3);
								table.setValueAt(txtY.getText(), i, 4);
								table.setValueAt(getValue(txtAmount), i, 5);
								table.setValueAt(getBool(isLeft()), i, 6);
								table.setValueAt(getBool(isRight()), i, 7);
								table.setValueAt(getBool(isTop()), i, 8);
								table.setValueAt(getBool(isBottom()), i, 9);
								items.get(i - 1).edit((getValue(txtID)) + 1, txtParent.getText(), txtName.getText(),
										txtX.getText(), txtY.getText(), getValue(txtAmount), isLeft(), isRight(),
										isTop(), isBottom());
								clearAll();
								txtCenter.setText("Entry edited.");
							}
						}
					}
				}
			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAll();
				txtCenter.setText("Input cleared.");
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
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				il.generateLogic(frmSimpleSwingApplication);
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
				String name = JOptionPane.showInputDialog(frmSimpleSwingApplication, "Specify file name to load");
				if (name != null && !name.isEmpty()) {
					if (!name.isEmpty()) { /// name provided successfully
						if (name.endsWith(".sav")) { /// if user added .sav it cuts it off for file loading
							name = name.substring(0, name.length() - 4);
						}
						ArrayList<Item> itemList = fr.load(name); /// try to load file by specified filename
						if (itemList == null) {
							fExists = JOptionPane.showConfirmDialog(frmSimpleSwingApplication,
									"There is no such file. Load default?"); // pop-up
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
							model.addRow(new Object[] { item.getID() - 1, item.getType(), item.getName(),
									item.getDimX(), item.getDimY(), item.getAmount(), (item.getLeft()),
									getBool(item.getRight()), getBool(item.getTop()), getBool(item.getBottom()) });
							items.add(item);
						}
					} else { //// File not found / Input not empty - loading default
						fr.loadOnStart();
					}
				} else if (name == null) {
					return; ///// Name not provided / Input empty - pressed OK.
				} else {
					JOptionPane.showMessageDialog(frmSimpleSwingApplication, "Please enter filename!");
				}
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fExists = 2;
				if (!items.isEmpty()) {
					String name = JOptionPane.showInputDialog(frmSimpleSwingApplication, "Specify file name to save");
					if (name == null)
						return;
					if (name.equalsIgnoreCase("default") || name.equalsIgnoreCase("default.sav")) {
						JOptionPane.showMessageDialog(frmSimpleSwingApplication, "Cannot save as default");
						return;
					}
					if (!name.isEmpty()) {
						Matcher m = Pattern.compile("[^A-Za-z0-9]").matcher(name);
						Matcher m2 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]").matcher(name);
						if (m.find()) {
							JOptionPane.showMessageDialog(frmSimpleSwingApplication,
									"Please only use letters a-z, A-Z or numbers 0-9 in file name");
							return;
						}
						if (!m.find() || m2.find()) {
							fExists = 0;
							if (name.endsWith(".sav")) { /// if user added .sav it cuts it off for file loading
								name = name.substring(0, name.length() - 4);
							}
							if (fr.fileExists(name)) {
								fExists = JOptionPane.showConfirmDialog(frmSimpleSwingApplication,
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
							JOptionPane.showMessageDialog(frmSimpleSwingApplication,
									"Please only use letters a-z, A-Z or numbers 0-9 in file name");
						}
					}
					if (name.isEmpty()) {
						JOptionPane.showMessageDialog(frmSimpleSwingApplication, "Please enter filename!");
					}
				} else {
					JOptionPane.showMessageDialog(frmSimpleSwingApplication, "There's nothing to save.");
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
				String name = JOptionPane.showInputDialog(frmSimpleSwingApplication, "Specify file name to delete");
				if (name == null || name.isEmpty() || name.equalsIgnoreCase("default")
						|| name.equalsIgnoreCase("default.sav")) {
					JOptionPane.showMessageDialog(frmSimpleSwingApplication,
							"Could not delete file/missing file with that name");
					return;
				}
				if (!name.isEmpty()) {
					if (name.endsWith(".sav")) { /// if user added .sav it cuts it off for file loading
						name = name.substring(0, name.length() - 4);
					}
					if (fr.fileExists(name)) {
						fw.deleteFile(name + ".sav");
						JOptionPane.showMessageDialog(frmSimpleSwingApplication, "File deleted succesfully");
						clearTable(table, model);
						clearItems(items);
						txtCenter.setText("List deleted.");
					} else {
						JOptionPane.showMessageDialog(frmSimpleSwingApplication,
								"Could not delete file/missing file with that name");
					}
				}
			}
		});
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSimpleSwingApplication.quit(items);
			}
		});
		rbLeft.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (left)
					setLeft(false);
				else
					setLeft(true);
			}
		});
		rbRight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (right)
					setRight(false);
				else
					setRight(true);
			}
		});
		rbTop.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (top)
					setTop(false);
				else
					setTop(true);
			}
		});
		rbBottom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (bottom)
					setBottom(false);
				else
					setBottom(true);
			}
		});
	}

	public static boolean checkEmpty(JTextField tf) {
		if (tf.getText().isEmpty())
			return false;
		else
			return true;
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

	public static boolean isLeft() {
		return left;
	}

	public static void setLeft(boolean left) {
		Window.left = left;
	}

	public static boolean isRight() {
		return right;
	}

	public static void setRight(boolean right) {
		Window.right = right;
	}

	public static boolean isTop() {
		return top;
	}

	public static void setTop(boolean top) {
		Window.top = top;
	}

	public static boolean isBottom() {
		return bottom;
	}

	public static void setBottom(boolean bottom) {
		Window.bottom = bottom;
	}

	public static void clearAll() {
		txtID.setText("");
		txtName.setText("");
		txtParent.setText("");
		txtX.setText("");
		txtY.setText("");
		txtAmount.setText("");
		rbLeft.setSelected(false);
		rbRight.setSelected(false);
		rbTop.setSelected(false);
		rbBottom.setSelected(false);
	}

	public static String getBool(Boolean bool) {
		if (bool)
			return "+";
		else
			return "";
	}
}
