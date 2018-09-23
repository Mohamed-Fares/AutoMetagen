package eg.edu.nu.bioinf.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.google.common.io.Files;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import eg.edu.nu.bioinf.models.DiamondParams;
import megan.main.Megan6;

public class DiamondDialog extends JDialog {
	
	private File diamondPath;
	private File database;
	
	static final DiamondParams params = new DiamondParams();
	
	private InputFiles inputFiles;

	public DiamondDialog() {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		setLocationRelativeTo(getParent());
		
		JLabel lblSelectLocationFor = new JLabel("Select location for Diamond Installation");
		getContentPane().add(lblSelectLocationFor, "4, 4");
		
		JButton btnDiamondLocation = new JButton("Diamond Location");
		btnDiamondLocation.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = chooser.showOpenDialog(DiamondDialog.this);
				if (result == JFileChooser.CANCEL_OPTION) return;
				diamondPath = chooser.getSelectedFile();
			}
		});
		getContentPane().add(btnDiamondLocation, "8, 4");
		
		JLabel lblSelectReferenceDatabase = new JLabel("Select Reference Database File");
		getContentPane().add(lblSelectReferenceDatabase, "4, 6");
		
		JButton btnDatabaseFile = new JButton("Database File");
		btnDatabaseFile.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int result = chooser.showOpenDialog(DiamondDialog.this);
				if (result == JFileChooser.CANCEL_OPTION) return;
				File selectedFile = chooser.getSelectedFile();
				if (selectedFile.getName().endsWith(".fasta")) {
					try {
						String diamond_path = diamondPath.getPath()+File.separator+"diamond";
						String output_index = selectedFile.getAbsolutePath()+"_db";
//						params.getFrameShiftPenalty()
						Process process = new ProcessBuilder(diamond_path, "makedb", "--in", selectedFile.getPath(), "-d", output_index).start();
						
						logProcessOutput(process);
						if(!process.isAlive() && process.exitValue()==0) {
							database = new File(output_index);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}				
				} else {
					database = selectedFile;
				}
				
			}

		});
		getContentPane().add(btnDatabaseFile, "8, 6");
		
		JLabel lblAdjustAlignmentParameters = new JLabel("Adjust Alignment Parameters");
		getContentPane().add(lblAdjustAlignmentParameters, "4, 8");
		
		JButton btnDiamondParameters = new JButton("Diamond Parameters");
		btnDiamondParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiamondParmasPanel diamondParmasPanel = new DiamondParmasPanel(params);
				diamondParmasPanel.setVisible(true);
			}
		});
		getContentPane().add(btnDiamondParameters, "8, 8");
		
		JLabel lblStartAlignment = new JLabel("Start Alignment");
		getContentPane().add(lblStartAlignment, "4, 10");
		
		JButton btnRunDiamond = new JButton("Run Diamond");
		btnRunDiamond.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String diamond_path = diamondPath.getPath()+File.separator+"diamond";
					String databasePath;
					if(Files.getFileExtension(database.getAbsolutePath()).equals("dmnd")) {
						databasePath = database.getParentFile().getAbsolutePath()+File.separator + Files.getNameWithoutExtension(database.getAbsolutePath());
					} else {
						// if database is not indexed, index it
						databasePath = "";
					}
					
					System.out.println(databasePath);
					String input = inputFiles.getAllFiles()[0]+InputFiles.TRIM_SUFFIX;
					File inputfile = new File(input);
					
					Process process = new ProcessBuilder(buildProcessParams(params, diamond_path, databasePath, inputfile)).start();
//					Process process = new ProcessBuilder(diamond_path, "blastx", "-d", databasePath, "-q", input,"-o", inputFiles.getAllFiles()[0].getParentFile().getPath()+File.separator+"matches.daa ").start();
//					Process process = new ProcessBuilder(buildProcessParams(params, diamond_path, databasePath, input)).start();
					
					logProcessOutput(process);
					
					if(!process.isAlive() && process.exitValue()==0) {
						new Thread() {
							@Override
							public void run() {

								try {
									//install shutdown hook
									//its run() method is executed for sure as the VM shuts down
									Runnable finalizer = new Runnable() {
										public void run() {
										}
									};
									Runtime.getRuntime().addShutdownHook(new Thread(finalizer));

									//run application
									(new Megan6()).parseArguments(new String[0]);

								} catch (Throwable th) {
									//catch any exceptions and the like that propagate up to the top level
									//		            if (!th.getMessage().equals("Help")) {
									//		                System.err.println("MEGAN fatal error:" + "\n" + th.toString());
									//		                System.exit(1);
									//		            }

									th.printStackTrace(System.out);
								}

							}
						}.start();
						
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		getContentPane().add(btnRunDiamond, "8, 10");
		pack();
	}

	public InputFiles getInputFiles() {
		return inputFiles;
	}
	
	
	private List buildProcessParams(DiamondParams params, String diamond_path, String databasePath, File input) {
		ArrayList<String> paramsList = new ArrayList<>();
		
		paramsList.add(diamond_path);
		paramsList.add("blastx");
		paramsList.add("-d");
		paramsList.add(databasePath);
		paramsList.add("-q");
		paramsList.add(input.getAbsolutePath());
		paramsList.add("-o");
		paramsList.add(input.getParentFile().getPath()+File.separator+"matches.daa");
		
		if(params.getThreadsNum()>0) {
			paramsList.add("--threads");
			paramsList.add(""+params.getThreadsNum());
		}
		
		if(params.getSensitivityLevel() > 0) {
			paramsList.add("--"+DiamondParams.SENSITIVITY_LEVELS_LABELS[params.getSensitivityLevel()]);
		}
		
		if(params.getFrameShiftPenalty() > 0) {
			paramsList.add("--frameshift");
			paramsList.add(""+params.getFrameShiftPenalty());
		}
		
		if(params.getGapOpenPenalty() > 0) {
			paramsList.add("--gapopen");
			paramsList.add(""+params.getGapOpenPenalty());
		}
		
		
		if(params.getGapExtenstionPenalty() > 0) {
			paramsList.add("--gapextend");
			paramsList.add(""+params.getGapExtenstionPenalty());
		}
		
		if(params.getScoringMatrix() > 0) {
			paramsList.add("--matrix");
			paramsList.add(""+DiamondParams.SCORING_MATRIX_ARR[params.getScoringMatrix()]);
		}
		
		if(params.getMaxTragetSeqsAlignment() > 0) {
			paramsList.add("--max-target-seqs");
			paramsList.add(""+params.getMaxTragetSeqsAlignment());
		}
		
		if(params.getTopPercentAlignment() > 0) {
			paramsList.add("--top");
			paramsList.add(""+params.getTopPercentAlignment());
		}
		
		if(params.getMaxExpecValueAlignment() > 0) {
			paramsList.add("--evalue");
			paramsList.add(""+params.getMaxExpecValueAlignment());
		}
		
		if(params.getMinScoreAlignment()> 0) {
			paramsList.add("--min-score");
			paramsList.add(""+params.getMinScoreAlignment());
		}
		
		System.out.println(Arrays.toString(paramsList.toArray()));
		return paramsList;	
	}
	
	private void logProcessOutput(Process process) throws IOException {
		try {
			process.waitFor();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("==========LOG=========\n");
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		
		
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		
		
		System.out.println("==========ERROR=========\n");
		InputStream eis = process.getErrorStream();
		InputStreamReader iesr = new InputStreamReader(eis);
		BufferedReader ebr = new BufferedReader(iesr);
		String eline;
		
		
		while ((eline = ebr.readLine()) != null) {
			System.err.println(eline);
		}
	}

	public void setInputFiles(InputFiles inputFiles) {
		this.inputFiles = inputFiles;
	}
	
	
	
	public static void main(String[] args) {
		DiamondDialog dialog = new DiamondDialog();
		dialog.setVisible(true);
		
	}
	
	
}
