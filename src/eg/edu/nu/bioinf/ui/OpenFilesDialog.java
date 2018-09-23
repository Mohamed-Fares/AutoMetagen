package eg.edu.nu.bioinf.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import uk.ac.babraham.FastQC.FastQCConfig;
import uk.ac.babraham.FastQC.FileFilters.BAMFileFilter;
import uk.ac.babraham.FastQC.FileFilters.CasavaFastQFileFilter;
import uk.ac.babraham.FastQC.FileFilters.FastQFileFilter;
import uk.ac.babraham.FastQC.FileFilters.MappedBAMFileFilter;
import uk.ac.babraham.FastQC.FileFilters.SequenceFileFilter;

public class OpenFilesDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String OPEN_FILES = "Select Sequence Files";
	
	InputFiles inputFiles;
	MutiPaneWindow parent;
	
	private JFileChooser chooser;
	private String lastUsedDir;
	private JButton btnOpenFiles;
	private JButton btnOpenReverse;
	
	
	MutiPaneWindow window;
	
	
	public OpenFilesDialog(MutiPaneWindow parent) {
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		
		this.parent = parent;
		this.inputFiles = parent.getInputFiles();
		
		setLocationRelativeTo(parent);
		
		JRadioButton rdbtnSingleEndReads = new JRadioButton("Single End Reads");
		rdbtnSingleEndReads.setSelected(inputFiles.isSingleEndReads());
		getContentPane().add(rdbtnSingleEndReads, "8, 4");
		
		JRadioButton rdbtnDoubleEndReads = new JRadioButton("Double End Reads");
		getContentPane().add(rdbtnDoubleEndReads, "12, 4");
		
		ButtonGroup group = new ButtonGroup();
        group.add(rdbtnSingleEndReads);
        group.add(rdbtnDoubleEndReads);
        
		btnOpenFiles = new JButton(OPEN_FILES);
		btnOpenFiles.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				File[] files = openFiles();
				if(inputFiles.isSingleEndReads()) {
					inputFiles.setForwardFiles(files);
					inputFiles.setReverseFiles(files);
					inputFiles.setSingleEndReads(true);
					
				} else {
					inputFiles.setForwardFiles(files);
				}
				
			}

			
		});
		getContentPane().add(btnOpenFiles, "8, 6");
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, "14, 6");
		
		btnOpenReverse = new JButton("Open Reverse");
		btnOpenReverse.setVisible(!inputFiles.isSingleEndReads());
		btnOpenReverse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!inputFiles.isSingleEndReads()) {
					inputFiles.setReverseFiles(openFiles());
				}
			}
		});
		getContentPane().add(btnOpenReverse, "8, 8");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenFilesDialog.this.dispose();
			}
		});
		getContentPane().add(btnCancel, "8, 12");
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenFilesDialog.this.dispose();
				OpenFilesDialog.this.parent.filesOpened(inputFiles);
				
			}
		});
		getContentPane().add(btnOk, "12, 12");
		
		Component verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, "10, 16");
		
		
		
		rdbtnSingleEndReads.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnOpenFiles.setText(OPEN_FILES);
				btnOpenReverse.setVisible(false);
				inputFiles.setSingleEndReads(true);
			}
		});
		
		rdbtnDoubleEndReads.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				inputFiles.setSingleEndReads(false);
				btnOpenFiles.setText("Select Forward Files");
				btnOpenReverse.setVisible(true);
				
			}
		});
		
	}
	
	
	
	public static void main(String[] args) {
		OpenFilesDialog dialog = new OpenFilesDialog(null);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	
	private File[] openFiles() {
		if (lastUsedDir == null) {
			chooser = new JFileChooser();
		}
		else {
			chooser = new JFileChooser(lastUsedDir);
		}
		chooser.setMultiSelectionEnabled(true);
		SequenceFileFilter sff = new SequenceFileFilter();
		chooser.addChoosableFileFilter(sff);
		chooser.addChoosableFileFilter(new FastQFileFilter());
		chooser.addChoosableFileFilter(new CasavaFastQFileFilter());
		chooser.addChoosableFileFilter(new BAMFileFilter());
		chooser.addChoosableFileFilter(new MappedBAMFileFilter());
		chooser.setFileFilter(sff);
		int result = chooser.showOpenDialog(OpenFilesDialog.this.getContentPane());
		if (result == JFileChooser.CANCEL_OPTION) return null;
	
		// See if they forced a file format
		FileFilter chosenFilter = chooser.getFileFilter();
		if (chosenFilter instanceof FastQFileFilter) {
			FastQCConfig.getInstance().setSequenceFormat("fastq");
		}
		if (chosenFilter instanceof CasavaFastQFileFilter) {
			FastQCConfig.getInstance().setSequenceFormat("fastq");
			FastQCConfig.getInstance().setCasavaMode(true);

		}
		else if (chosenFilter instanceof BAMFileFilter) {
			FastQCConfig.getInstance().setSequenceFormat("bam");
		}
		else if (chosenFilter instanceof MappedBAMFileFilter) {
			FastQCConfig.getInstance().setSequenceFormat("bam_mapped");
			System.setProperty("fastqc.sequence_format", "bam_mapped");
		}
		
		
		File [] files = chooser.getSelectedFiles();
		return files;
	}

}
