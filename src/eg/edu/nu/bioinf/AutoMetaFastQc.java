package eg.edu.nu.bioinf;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import eg.edu.nu.bioinf.ui.TrimmomaticsParamPane;
import uk.ac.babraham.FastQC.FastQCConfig;
import uk.ac.babraham.FastQC.Analysis.AnalysisRunner;
import uk.ac.babraham.FastQC.Analysis.OfflineRunner;
import uk.ac.babraham.FastQC.Dialogs.WelcomePanel;
import uk.ac.babraham.FastQC.Modules.ModuleFactory;
import uk.ac.babraham.FastQC.Modules.QCModule;
import uk.ac.babraham.FastQC.Report.HTMLReportArchive;
import uk.ac.babraham.FastQC.Results.ResultsPanel;
import uk.ac.babraham.FastQC.Sequence.SequenceFactory;
import uk.ac.babraham.FastQC.Sequence.SequenceFile;
import uk.ac.babraham.FastQC.Sequence.SequenceFormatException;
import uk.ac.babraham.FastQC.Utilities.CasavaBasename;
import uk.ac.babraham.FastQC.Utilities.NanoporeBasename;

public class AutoMetaFastQc extends JPanel {	
	
	public static final String VERSION = "0.11.8.devel";
	
	private JTabbedPane fileTabs;
	private WelcomePanel welcomePanel;
	private File lastUsedDir = null;

	private JPanel pplnCntnPane;
	
	public AutoMetaFastQc() {
		setLayout(new BorderLayout(0, 0));
		
		fileTabs = new JTabbedPane(JTabbedPane.TOP);
		welcomePanel = new WelcomePanel();
		
		
		pplnCntnPane = new JPanel();
		add(pplnCntnPane, BorderLayout.CENTER);
		pplnCntnPane.setLayout(new BoxLayout(pplnCntnPane, BoxLayout.X_AXIS));
			
		
		
		pplnCntnPane.removeAll();
		pplnCntnPane.add(welcomePanel);
		
		JPanel actionButtonsPane = new JPanel();
		actionButtonsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton backButton = new JButton("Back");
		actionButtonsPane.add(backButton);
		
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TrimmomaticsParamPane trimPane = new TrimmomaticsParamPane();
				ViewComponent(trimPane);
				trimPane.setPreferredSize(pplnCntnPane.getPreferredSize());
				validate();
				repaint();
			}

		});
		actionButtonsPane.add(nextButton);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
//		setLocationRelativeTo(null);
//		setJMenuBar(new FastQCMenuBar(this));
		this.setVisible(true);
		}

	public void close () {
		if (fileTabs.getSelectedIndex() >=0) {
			fileTabs.remove(fileTabs.getSelectedIndex());
		}
		if (fileTabs.getTabCount() == 0) {
//			setContentPane(welcomePanel);
			ViewComponent(welcomePanel);
			
		}
	}
	
	public void closeAll () {
		fileTabs.removeAll();
		ViewComponent(welcomePanel);
	}
	
	public void openFile (File[] files) {
		
		// If we're still showing the welcome panel switch this out for
		// the file tabs panel
		if (fileTabs.getTabCount() == 0) {
			pplnCntnPane.removeAll();
			pplnCntnPane.add(fileTabs);
			
			validate();
			repaint();
		}
		
		
		if (FastQCConfig.getInstance().nano) {
			// Some of the files we've been passed might be directories and we would need to
			// list the fast5 files within those directories.
			
			Vector<File> keptFiles = new Vector<File>();
			
			for (int f=0;f<files.length;f++) {
				if (files[f].isDirectory()) {
					File [] fast5files = files[f].listFiles();
					for (int i=0;i<fast5files.length;i++) {
						if (fast5files[i].getName().endsWith(".fast5")) {
							keptFiles.add(fast5files[i]);
						}
					}
				}
				else {
					keptFiles.add(files[f]);
				}
			}
			
			files = keptFiles.toArray(new File[0]);
			
		}
		
		
		File [][] fileGroups;
		
		// See if we need to group together files from a casava group
		if (FastQCConfig.getInstance().casava) {
			fileGroups = CasavaBasename.getCasavaGroups(files);
		}
		else if (FastQCConfig.getInstance().nano) {
			fileGroups = NanoporeBasename.getNanoporeGroups(files);
		}
		else {
			fileGroups = new File [files.length][1];
			for (int f=0;f<files.length;f++) {
				fileGroups[f][0] = files[f];
			}
		}

	
		for (int i=0;i<fileGroups.length;i++) {
			File [] filesToProcess = fileGroups[i];
			lastUsedDir = filesToProcess[0].getParentFile();
			SequenceFile sequenceFile;
			
			
			try {
				sequenceFile = SequenceFactory.getSequenceFile(filesToProcess);
			}
			catch (SequenceFormatException e) {
				JPanel errorPanel = new JPanel();
				errorPanel.setLayout(new BorderLayout());
				errorPanel.add(new JLabel("File format error: "+e.getLocalizedMessage(), JLabel.CENTER),BorderLayout.CENTER);
				fileTabs.addTab(filesToProcess[0].getName(), errorPanel);
				e.printStackTrace();
				continue;
			}
			catch (IOException e) {
				System.err.println("File broken");
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Couldn't read file:"+e.getLocalizedMessage(), "Error reading file", JOptionPane.ERROR_MESSAGE);
				continue;
			}
					
			AnalysisRunner runner = new AnalysisRunner(sequenceFile);
			ResultsPanel rp = new ResultsPanel(sequenceFile);
			runner.addAnalysisListener(rp);
			fileTabs.addTab(sequenceFile.name(), rp);
			

			QCModule [] module_list = ModuleFactory.getStandardModuleList();
	
			runner.startAnalysis(module_list);
		}
	}

	public void saveReport () {
//		JFileChooser chooser;
		
//		if (lastUsedDir == null) {
//			chooser = new JFileChooser();
//		}
//		else {
//			chooser = new JFileChooser(lastUsedDir);
//		}
		
		if (fileTabs.getSelectedComponent() == null) {
			JOptionPane.showMessageDialog(this, "No FastQ files are open yet", "Can't save report", JOptionPane.ERROR_MESSAGE);
			return;
		}
//		chooser.setSelectedFile(new File(((ResultsPanel)fileTabs.getSelectedComponent()).sequenceFile().getFile().getName().replaceAll("stdin:","").replaceAll(".gz$","").replaceAll(".bz2$","").replaceAll(".txt$","").replaceAll(".fastq$", "").replaceAll(".fq$", "").replaceAll(".sam$", "").replaceAll(".bam$", "")+"_fastqc.html"));
//		chooser.setMultiSelectionEnabled(false);
//		chooser.setFileFilter(new FileFilter() {
//		
//			public String getDescription() {
//				return "HTML files";
//			}
//		
//			public boolean accept(File f) {
//				if (f.isDirectory() || f.getName().toLowerCase().endsWith(".html")) {
//					return true;
//				}
//				else {
//					return false;
//				}
//			}
//		
//		});
	
		File reportFile;
//		while (true) {
//			int result = chooser.showSaveDialog(this);
//			if (result == JFileChooser.CANCEL_OPTION) return;
			
//			reportFile = chooser.getSelectedFile();
		reportFile = new File(((ResultsPanel) fileTabs.getSelectedComponent()).sequenceFile().getFile().getName()
				.replaceAll("stdin:", "").replaceAll(".gz$", "").replaceAll(".bz2$", "").replaceAll(".txt$", "")
				.replaceAll(".fastq$", "").replaceAll(".fq$", "").replaceAll(".sam$", "").replaceAll(".bam$", "")
				+ "_fastqc.html");
		if (!reportFile.getName().toLowerCase().endsWith(".html")) {
			reportFile = new File(reportFile.getAbsoluteFile() + ".html");
		}

		// Check if we're overwriting something
		if (reportFile.exists()) {
			int reply = JOptionPane.showConfirmDialog(this, reportFile.getName() + " already exists.  Overwrite?",
					"Overwrite existing file?", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.NO_OPTION) {
				return;
			} /*else {
				break;
			}*/
		} /*else {
			break;
		}*/
		// }
		
		ResultsPanel selectedPanel = (ResultsPanel)fileTabs.getSelectedComponent();
		
		try {
			new HTMLReportArchive(selectedPanel.sequenceFile(), selectedPanel.modules(), reportFile);
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Failed to create archive: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		// See if we just have to print out the version
		if (System.getProperty("fastqc.show_version") != null && System.getProperty("fastqc.show_version").equals("true")) {
			System.out.println("FastQC v"+VERSION);
			System.exit(0);
		}
		
		if (args.length > 0) {
			// Set headless to true so we don't get problems
			// with people working without an X display.
			System.setProperty("java.awt.headless", "true");
			
			// We used to default to unzipping the zip file in 
			// non-interactive runs.  As we now save an HTML
			// report at the top level we no longer do this
			// so unzip is false unless explicitly set to be true.
						
			if (FastQCConfig.getInstance().do_unzip == null) {
				FastQCConfig.getInstance().do_unzip = false;
			}
			
			new OfflineRunner(args);
			System.exit(0);
		}
		
		else {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {}
			
	
			// The interactive default is to not uncompress the
			// reports after they have been generated
			if (FastQCConfig.getInstance().do_unzip == null) {
				FastQCConfig.getInstance().do_unzip = false;
			}
	
			AutoMetaFastQc app = new AutoMetaFastQc();
	
			app.setVisible(true);
		}
	}

	private void ViewComponent(Component comp) {
		pplnCntnPane.removeAll();
		pplnCntnPane.add(comp);
		
		validate();
		repaint();
	}

}

