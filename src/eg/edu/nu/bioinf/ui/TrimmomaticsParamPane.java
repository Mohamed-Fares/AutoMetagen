package eg.edu.nu.bioinf.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

import org.usadellab.trimmomatic.TrimmomaticPE;
import org.usadellab.trimmomatic.TrimmomaticSE;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class TrimmomaticsParamPane extends JPanel{
	
	private JCheckBox chckbxToPhredScores;
	private JSpinner targetLengthspinner;
	private JSpinner strictnessSpinner;
	private JCheckBox chckbxMaxInfo;
	private JCheckBox chckbxLeading;
	private JSpinner leadingSpinner;
	private JCheckBox chckbxTrailing;
	private JSpinner trailingSpinner;
	private JCheckBox chckbxSlidingWindow;
	private JSpinner sWindowSpinner;
	private JSpinner thresholdSpinner;
	private JCheckBox chckbxCrop;
	private JSpinner cropSpinner;
	private JCheckBox chckbxHeadCrop;
	private JSpinner headCropSpinner;
	private JCheckBox chckbxMinimumLength;
	private JSpinner minLenSpinner;
	private JRadioButton rdbtnPhred33;
	private JRadioButton rdbtnPhred64;
	
	private InputFiles inputFiles;
	
	public TrimmomaticsParamPane() {
		setLayout(new FormLayout(new ColumnSpec[] {
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
				ColumnSpec.decode("max(52dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(15dlu;default)"),},
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		
		rdbtnPhred33 = new JRadioButton("Phred-33");
		add(rdbtnPhred33, "6, 4");
		
		rdbtnPhred64 = new JRadioButton("Phred-64");
		add(rdbtnPhred64, "8, 4");
		
		chckbxToPhredScores = new JCheckBox("To Phred Scores");
		chckbxToPhredScores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				rdbtnPhred33.setEnabled(selected);
				rdbtnPhred64.setEnabled(selected);
			}
		});
		
		add(chckbxToPhredScores, "4, 4");
		
		
		ButtonGroup phredBTNGroup = new ButtonGroup();
		phredBTNGroup.add(rdbtnPhred33);
		phredBTNGroup.add(rdbtnPhred64);
		
		
		
		JLabel lblTargetLength = new JLabel("Target Length :");
		add(lblTargetLength, "6, 6, right, default");
		
		targetLengthspinner = new JSpinner();
		targetLengthspinner.setEnabled(false);
		add(targetLengthspinner, "8, 6");
		
		JLabel lblStrictness = new JLabel("Strictness :");
		add(lblStrictness, "10, 6, right, default");
		
		strictnessSpinner = new JSpinner();
		strictnessSpinner.setEnabled(false);
		add(strictnessSpinner, "12, 6");
		
		chckbxMaxInfo = new JCheckBox("Max Info");
		chckbxMaxInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				targetLengthspinner.setEnabled(selected);
				strictnessSpinner.setEnabled(selected);
			}
		});
		add(chckbxMaxInfo, "4, 6");
		
		chckbxLeading = new JCheckBox("LEADING :");
		
		leadingSpinner = new JSpinner();
		leadingSpinner.setEnabled(false);
		
		chckbxTrailing = new JCheckBox("TRAILING");
		chckbxLeading.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				leadingSpinner.setEnabled(selected);
				if(selected)
					leadingSpinner.setValue(3);
				else
					leadingSpinner.setValue(0);
				
			}
		});
		
		add(chckbxLeading, "4, 8");
		add(leadingSpinner, "8, 8");
		
		trailingSpinner = new JSpinner();
		trailingSpinner.setEnabled(false);
		
		chckbxTrailing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				trailingSpinner.setEnabled(selected);
				if(selected)
					trailingSpinner.setValue(3);
				else
					trailingSpinner.setValue(0);
				
			}
		});
		
		add(chckbxTrailing, "4, 10");
		add(trailingSpinner, "8, 10");
		
		chckbxSlidingWindow = new JCheckBox("Sliding Window");
		chckbxSlidingWindow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				sWindowSpinner.setEnabled(selected);
				thresholdSpinner.setEnabled(selected);
				if(selected) {
					sWindowSpinner.setValue(4);
					thresholdSpinner.setValue(20);
				} else {
					sWindowSpinner.setValue(0);
					thresholdSpinner.setValue(0);
				}
			}
		});
		add(chckbxSlidingWindow, "4, 12");
		
		JLabel lblWindowSize = new JLabel("Window size :");
		add(lblWindowSize, "6, 12, right, default");
		
		sWindowSpinner = new JSpinner();
		sWindowSpinner.setEnabled(false);
		add(sWindowSpinner, "8, 12");
		
		JLabel lblThreshold = new JLabel("Threshold :");
		add(lblThreshold, "10, 12, right, default");
		
		thresholdSpinner = new JSpinner();
		thresholdSpinner.setEnabled(false);
		add(thresholdSpinner, "12, 12");
		
		chckbxCrop = new JCheckBox("Crop");
		chckbxCrop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				cropSpinner.setEnabled(selected);
			}
		});
		chckbxCrop.setToolTipText("Removes bases regardless of quality from the end of the read,\n so that the read has maximally the specified length after this step has been performed.\nSteps performed after CROP might of course further shorten the read");
		add(chckbxCrop, "4, 14");
		
		JLabel label = new JLabel("length :");
		add(label, "6, 14, right, default");
		
		cropSpinner = new JSpinner();
		cropSpinner.setEnabled(false);
		add(cropSpinner, "8, 14");
		
		chckbxHeadCrop = new JCheckBox("Head Crop");
		chckbxHeadCrop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				headCropSpinner.setEnabled(selected);
			}
		});
		add(chckbxHeadCrop, "4, 16");
		
		JLabel lblLength = new JLabel("length :");
		add(lblLength, "6, 16, right, default");
		
		headCropSpinner = new JSpinner();
		headCropSpinner.setEnabled(false);
		add(headCropSpinner, "8, 16");
		
		chckbxMinimumLength = new JCheckBox("Minimum length");
		chckbxMinimumLength.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				minLenSpinner.setEnabled(selected);
			}
		});
		add(chckbxMinimumLength, "4, 18");
		
		minLenSpinner = new JSpinner();
		minLenSpinner.setEnabled(false);
		add(minLenSpinner, "8, 18");
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(btnRun, "8, 22");
		
	}
	
	
	
	public static void main(String[] args) {
		
		  
		JPanel panel = new TrimmomaticsParamPane();
		panel.setSize(800, 1200);
		panel.setVisible(true);
		JFrame frame = new JFrame("AutoMeta - Trimmomatic Options");
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	private void run() throws IOException {
		ArrayList<String> params = new ArrayList<>();

		if (inputFiles.isSingleEndReads()) {
			File[] inputs = inputFiles.getAllFiles();
			ArrayList<File> trimmedFiles = new ArrayList<>();

			for (int i = 0; i < inputs.length; i++) {
				String filePath = inputs[i].getPath();
				String trimmedFilePath = filePath + InputFiles.TRIM_SUFFIX;

				if (chckbxToPhredScores.isSelected()) {
					if (rdbtnPhred33.isSelected()) {
						params.add("-phred33");
					} else if (rdbtnPhred64.isSelected()) {
						params.add("-phred64");
					}

				}

				params.add(filePath);
				params.add(trimmedFilePath);

				// if(inputFiles.isSingleEndReads()) {
				// File[] inputs = inputFiles.getAllFiles();
				// for (int i = 0; i < inputs.length; i++) {
				// String filePath = inputs[i].getPath();
				// params.add(filePath);
				// }
				//
				// for (int i = 0; i < inputs.length; i++) {
				// params.add(inputs[i].getPath()+InputFiles.TRIM_SUFFIX);
				// }
				//
				//
				// } else {
				// //TODO Add paired End Reads
				// }

				if (chckbxMaxInfo.isSelected()) {
					params.add("MAXINFO:" + targetLengthspinner.getValue().toString() + ":"
							+ strictnessSpinner.getValue().toString());
				}

				if (chckbxLeading.isSelected()) {
					params.add("LEADING:" + leadingSpinner.getValue().toString());
				}

				if (chckbxTrailing.isSelected()) {
					params.add("TRAILING:" + trailingSpinner.getValue().toString());
				}

				if (chckbxCrop.isSelected()) {
					params.add("CROP:" + cropSpinner.getValue().toString());
				}

				if (chckbxHeadCrop.isSelected()) {
					params.add("HEADCROP:" + headCropSpinner.getValue().toString());
				}

				if (chckbxSlidingWindow.isSelected()) {
					params.add("SLIDINGWINDOW:" + sWindowSpinner.getValue().toString() + ":"
							+ thresholdSpinner.getValue().toString());
				}

				if (chckbxMinimumLength.isSelected()) {
					params.add("MINLEN:" + minLenSpinner.getValue().toString());
				}

				String[] paramsArr = new String[params.size()];
				params.toArray(paramsArr);

				boolean trimRunSucss = false;
				if (inputFiles.isSingleEndReads()) {
					trimRunSucss = TrimmomaticSE.run(paramsArr);

				} else {
					trimRunSucss = TrimmomaticPE.run(paramsArr);
				}
				
				
				if (trimRunSucss) {
					trimmedFiles.add(new File(trimmedFilePath));
				} 
				params.clear();

			}
		} else {
			// TODO Add paired End Reads
		}
		
		if (true) {

			DiamondDialog diamondDialog = new DiamondDialog();
			diamondDialog.setInputFiles(inputFiles);
			diamondDialog.pack();
			diamondDialog.setVisible(true);
		}

	}

	public InputFiles getInputFiles() {
		return inputFiles;
	}

	public void setInputFiles(InputFiles inputFiles) {
		this.inputFiles = inputFiles;
	}
	

}
