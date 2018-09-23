package eg.edu.nu.bioinf.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import eg.edu.nu.bioinf.models.DiamondParams;
import net.miginfocom.swing.MigLayout;

public class DiamondParmasPanel extends JDialog {
	
	private JCheckBox chckbxAutomoaticallyDetectNumber;
	private JSpinner threadsSpin;
	private JComboBox<String> sensitivityComboBox;
	private JCheckBox chckbxFrameShiftPenalty;
	private JSpinner spinnerFrameShiftPenalty;
	private JSpinner gapOpenSpinner;
	private JSpinner gapExtSpinner;
	private JComboBox<String> scoringMtxComboBox;
	private JComboBox<String> outputFmtComboBox;
	private JCheckBox chckbxTopHits;
	private JSpinner topHitsOutSpin;
	private JSpinner spinnerTopPercent;
	private JSpinner spinnerMaxTargSeq;
	private JSpinner spinnerMaxExpectedValue;
	private JSpinner spinnerMinScore;
	
	
	DiamondParams params;
	
	public DiamondParmasPanel(DiamondParams params) {
		getContentPane().setLayout(new MigLayout("", "[][][][247.00,grow][]", "[][][][][][][][][][][][][][][][][][]"));
		
		this.params = params;
		JLabel lblRunningThreads = new JLabel("Running Threads :");
		getContentPane().add(lblRunningThreads, "cell 1 1");
		
		threadsSpin = new JSpinner();
		threadsSpin.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		threadsSpin.setEnabled(false);
		getContentPane().add(threadsSpin, "cell 4 1");

		chckbxAutomoaticallyDetectNumber = new JCheckBox("Automoatically Detect Number and use all");
		chckbxAutomoaticallyDetectNumber.setSelected(true);
		chckbxAutomoaticallyDetectNumber.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				threadsSpin.setEnabled(!chckbxAutomoaticallyDetectNumber.isSelected());
				
			}
		});
		getContentPane().add(chckbxAutomoaticallyDetectNumber, "cell 3 1");
		
		
		JLabel lblSensitivity = new JLabel("Sensitivity");
		getContentPane().add(lblSensitivity, "cell 1 2");
		
		sensitivityComboBox = new JComboBox<String>(DiamondParams.SENSITIVITY_LEVELS_LABELS);
		getContentPane().add(sensitivityComboBox, "cell 3 2,growx");
		
		chckbxFrameShiftPenalty = new JCheckBox("Frame Shift Penalty");
		chckbxFrameShiftPenalty.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerFrameShiftPenalty.setEnabled(chckbxFrameShiftPenalty.isSelected());
			}
		});
		getContentPane().add(chckbxFrameShiftPenalty, "cell 1 3");
		
		spinnerFrameShiftPenalty = new JSpinner();
		spinnerFrameShiftPenalty.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerFrameShiftPenalty.setEnabled(false);
		getContentPane().add(spinnerFrameShiftPenalty, "cell 3 3");
		
		JLabel lblGapOpenPenalty = new JLabel("Gap Open Penalty :");
		getContentPane().add(lblGapOpenPenalty, "cell 1 4");
		
		gapOpenSpinner = new JSpinner();
		gapOpenSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		getContentPane().add(gapOpenSpinner, "cell 3 4");
		
		JLabel lblGapExtensionPenalty = new JLabel("Gap Extension Penalty :");
		getContentPane().add(lblGapExtensionPenalty, "cell 1 5");
		
		gapExtSpinner = new JSpinner();
		gapExtSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		getContentPane().add(gapExtSpinner, "cell 3 5");
		
		JLabel lblScoringMatrix = new JLabel("Scoring Matrix :");
		getContentPane().add(lblScoringMatrix, "cell 1 6");
		
		scoringMtxComboBox = new JComboBox<String>(DiamondParams.SCORING_MATRIX_ARR);
		scoringMtxComboBox.setSelectedIndex(2);
		getContentPane().add(scoringMtxComboBox, "cell 3 6,growx");
		
		JSeparator separator = new JSeparator();
		getContentPane().add(separator, "cell 1 7");
		
		JLabel lblOutputOptions = new JLabel("Output options");
		getContentPane().add(lblOutputOptions, "cell 1 8");
		
		JLabel lblFormat = new JLabel("Format:");
		getContentPane().add(lblFormat, "cell 1 9");
		
		outputFmtComboBox = new JComboBox<String>(DiamondParams.OUTPUT_FORMATS);
		outputFmtComboBox.setSelectedIndex(3);
		getContentPane().add(outputFmtComboBox, "cell 3 9,growx");
		
		chckbxTopHits = new JCheckBox("Top # hits");
		chckbxTopHits.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				topHitsOutSpin.setEnabled(chckbxTopHits.isSelected());
			}
		});
		getContentPane().add(chckbxTopHits, "cell 1 10");
		
		topHitsOutSpin = new JSpinner();
		topHitsOutSpin.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		topHitsOutSpin.setEnabled(false);
		getContentPane().add(topHitsOutSpin, "cell 3 10");
		
		JLabel lblReportAlignmentFor = new JLabel("Report Alignment For:");
		getContentPane().add(lblReportAlignmentFor, "cell 1 11");
		
		spinnerMaxTargSeq = new JSpinner();
		spinnerMaxTargSeq.setModel(new SpinnerNumberModel(new Integer(25), new Integer(0), null, new Integer(1)));
		getContentPane().add(spinnerMaxTargSeq, "cell 3 12,growx");

		JRadioButton rdbtnMaxTargetSeqs = new JRadioButton("Max Target Seqs");
		rdbtnMaxTargetSeqs.setSelected(true);
		rdbtnMaxTargetSeqs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				spinnerMaxTargSeq.setEnabled(true);
				spinnerTopPercent.setEnabled(false);
			}
		});
		getContentPane().add(rdbtnMaxTargetSeqs, "cell 1 12");
		
		
		spinnerTopPercent = new JSpinner();
		spinnerTopPercent.setEnabled(false);
		spinnerTopPercent.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		getContentPane().add(spinnerTopPercent, "cell 3 13");

		JRadioButton rdbtnOutTopPercent = new JRadioButton("Top %");
		rdbtnOutTopPercent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerMaxTargSeq.setEnabled(false);
				spinnerTopPercent.setEnabled(true);
			}
		});
		getContentPane().add(rdbtnOutTopPercent, "cell 1 13");
		
		
		ButtonGroup outputBTNGroup = new ButtonGroup();
		outputBTNGroup.add(rdbtnMaxTargetSeqs);
		outputBTNGroup.add(rdbtnOutTopPercent);
		
		
		JLabel lblReportAlignmentWith = new JLabel("Report Alignment with");
		getContentPane().add(lblReportAlignmentWith, "cell 1 14");
		
		JRadioButton rdbtnMaximumExpectedValue = new JRadioButton("Maximum expected value");
		rdbtnMaximumExpectedValue.setSelected(true);
		rdbtnMaximumExpectedValue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerMaxExpectedValue.setEnabled(true);
				spinnerMinScore.setEnabled(false);
				
			}
		});
		getContentPane().add(rdbtnMaximumExpectedValue, "cell 1 15");
		
		spinnerMaxExpectedValue = new JSpinner();
		spinnerMaxExpectedValue.setModel(new SpinnerNumberModel(new Float(0.001), new Float(0), new Float(1), new Float(0.001)));
		getContentPane().add(spinnerMaxExpectedValue, "cell 3 15,growx");
		
		JRadioButton rdbtnMinScore = new JRadioButton("Min Score");
		rdbtnMinScore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerMinScore.setEnabled(true);
				spinnerMaxExpectedValue.setEnabled(false);
			}
		});
		getContentPane().add(rdbtnMinScore, "cell 1 16");
		
		spinnerMinScore = new JSpinner();
		spinnerMinScore.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerMinScore.setEnabled(false);
		getContentPane().add(spinnerMinScore, "cell 3 16,growx");
		
		ButtonGroup scoreBTNGroup = new ButtonGroup();
		scoreBTNGroup.add(rdbtnMinScore);
		scoreBTNGroup.add(rdbtnMaximumExpectedValue);
		
		JButton btnSaveOptions = new JButton("Save Options");
		btnSaveOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxAutomoaticallyDetectNumber.isSelected()) {
					params.setThreadsNum(0);
				} else {
					params.setThreadsNum(Math.max((int) threadsSpin.getValue(), 0));
				}
				params.setSensitivityLevel(sensitivityComboBox.getSelectedIndex());
				if (chckbxFrameShiftPenalty.isSelected()) {
					params.setFrameShiftPenalty((int) spinnerFrameShiftPenalty.getValue());
				}
				
				params.setGapOpenPenalty((int) gapOpenSpinner.getValue());
				params.setGapExtenstionPenalty((int)gapExtSpinner.getValue());
				params.setScoringMatrix(scoringMtxComboBox.getSelectedIndex());
				params.setOutputFormat(outputFmtComboBox.getSelectedIndex());

				if (chckbxTopHits.isSelected()) {
					params.setTopHits((int) topHitsOutSpin.getValue());
				}
				
				if(rdbtnMaxTargetSeqs.isSelected()) {
					params.setMaxTragetSeqsAlignment((int) spinnerMaxTargSeq.getValue());
					params.setTopPercentAlignment(0);
				} else if (rdbtnOutTopPercent.isSelected()) {
					params.setTopPercentAlignment((int) spinnerTopPercent.getValue());
					params.setMaxTragetSeqsAlignment(0);
				} else {
					System.err.println("non alignment output mode is selected");
				}
				
				
				if(rdbtnMaximumExpectedValue.isSelected()) {
					params.setMaxExpecValueAlignment((float) spinnerMaxExpectedValue.getValue());
					params.setMinScoreAlignment(0);
				} else if (rdbtnMinScore.isSelected()) {
					params.setMinScoreAlignment((int) spinnerMinScore.getValue());
					params.setMaxExpecValueAlignment(0);
				}
				
				
				

				StringBuilder builder = new StringBuilder();
				builder.append("threads no : ").append(params.getThreadsNum());
				builder.append("\n sensitivity levels : ")
						.append(DiamondParams.SENSITIVITY_LEVELS_LABELS[params.getSensitivityLevel()]);
				builder.append("\n frame shift penal : ").append(params.getFrameShiftPenalty());
				builder.append("\n gap open penal : ").append(params.getGapOpenPenalty());
				builder.append("\n gap extension penal : ").append(params.getGapExtenstionPenalty());
				builder.append("\n scoring matrix : ")
						.append(DiamondParams.SCORING_MATRIX_ARR[params.getScoringMatrix()]);
				builder.append("\n top hits : ").append(params.getTopHits());
				builder.append("\n Max target seq : ").append(params.getMaxTragetSeqsAlignment());
				builder.append("\n top percent : ").append(params.getTopPercentAlignment());
				builder.append("\n Max expect value: ").append(params.getMaxExpecValueAlignment());
				builder.append("\n min score : ").append(params.getMinScoreAlignment());
				System.out.println(builder.toString());

				DiamondParmasPanel.this.dispose();

			}
		});
		getContentPane().add(btnSaveOptions, "cell 2 17 2 1");
		pack();
	}
	
	
	public static void main(String[] args) {
		DiamondParmasPanel contentPane = new DiamondParmasPanel(new DiamondParams());
		contentPane.setVisible(true);
		
	}
	

}
