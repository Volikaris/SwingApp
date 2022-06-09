package smtg;

public class Main {

	public static void main(String[] args) {

			final Window w = new Window();
			
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					w.run();									
				}
			});

	}

}