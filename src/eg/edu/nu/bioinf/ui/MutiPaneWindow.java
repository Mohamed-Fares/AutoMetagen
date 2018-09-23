package eg.edu.nu.bioinf.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import eg.edu.nu.bioinf.AutoMetaFastQc;

public class MutiPaneWindow extends JFrame{
	
	
	private InputFiles files;
	private AutoMetaFastQc fastQCPanel;
	private TrimmomaticsParamPane trimmomaticsPanel;

	public MutiPaneWindow() {
		
		files = new InputFiles();
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel buttonsPanel = new JPanel();
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		JButton openFilesBTN = new JButton("Open Files");
		openFilesBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenFilesDialog dialog = new OpenFilesDialog(MutiPaneWindow.this);
				dialog.pack();
				dialog.setVisible(true);
				
			}
		});
		buttonsPanel.add(openFilesBTN);
		
//		JPanel panel = new JPanel();
//		getContentPane().add(panel, BorderLayout.WEST);
//		panel.setLayout(new BorderLayout(0, 0));
		fastQCPanel = new AutoMetaFastQc();
		getContentPane().add(fastQCPanel, BorderLayout.CENTER);
		
		trimmomaticsPanel = new TrimmomaticsParamPane();
		getContentPane().add(trimmomaticsPanel, BorderLayout.EAST);
	}
	
	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		MutiPaneWindow window = new MutiPaneWindow();
		window.pack();
		window.setVisible(true);

	}



	public InputFiles getInputFiles() {
		return files;
	}


	public void filesOpened(InputFiles files) {
		this.files = files;
		fastQCPanel.openFile(files.getAllFiles());
		fastQCPanel.saveReport();
		trimmomaticsPanel.setInputFiles(files);
	}
	
	

}
