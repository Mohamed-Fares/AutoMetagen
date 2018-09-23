package eg.edu.nu.bioinf.models;

public class DiamondParams {

	public static final String OPT_THREADS_NUMBER = "-p";
	public static final int THREADS_NUM_AUTOMATIC = 0;
	
	public static final String[] SENSITIVITY_LEVELS_LABELS = {"default", "sensitive", "more-sensitive"};
	public static final String[] SCORING_MATRIX_ARR = {"BLOSUM45", "BLOSUM50", "BLOSUM62", "BLOSUM80", "BLOSUM90", "PAM250", "PAM70", "PAM30"};
	public static final String[] OUTPUT_FORMATS = {"BLAST pairwise format", "BLAST XML format", "BLAST tabular format", "DIAMOND alignment archive (DAA)", "SAM format", "Taxonomic classification", "PAF format"};

	private int threadsNum;
	private int sensitivityLevel;
	private int frameShiftPenalty;
	private int gapOpenPenalty;
	private int gapExtenstionPenalty;
	private int scoringMatrix;
	private int outputFormat;
	private int topHits;
	private int maxTragetSeqsAlignment;
	private int topPercentAlignment;
	private float maxExpecValueAlignment;
	private int minScoreAlignment;

	public int getThreadsNum() {
		return threadsNum;
	}

	public void setThreadsNum(int threadsNum) {
		this.threadsNum = threadsNum;
	}

	public int getSensitivityLevel() {
		return sensitivityLevel;
	}

	public void setSensitivityLevel(int sensitivityLevel) {
		this.sensitivityLevel = sensitivityLevel;
	}

	public int getFrameShiftPenalty() {
		return frameShiftPenalty;
	}

	public void setFrameShiftPenalty(int frameShiftPenalty) {
		this.frameShiftPenalty = frameShiftPenalty;
	}

	public int getGapOpenPenalty() {
		return gapOpenPenalty;
	}

	public void setGapOpenPenalty(int gapOpenPenalty) {
		this.gapOpenPenalty = gapOpenPenalty;
	}

	public int getGapExtenstionPenalty() {
		return gapExtenstionPenalty;
	}

	public void setGapExtenstionPenalty(int gapExtenstionPenalty) {
		this.gapExtenstionPenalty = gapExtenstionPenalty;
	}

	public int getScoringMatrix() {
		return scoringMatrix;
	}

	public void setScoringMatrix(int scoringMatrix) {
		this.scoringMatrix = scoringMatrix;
	}

	public int getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(int outputFormat) {
		this.outputFormat = outputFormat;
	}

	public int getTopHits() {
		return topHits;
	}

	public void setTopHits(int topHits) {
		this.topHits = topHits;
	}

	public int getMaxTragetSeqsAlignment() {
		return maxTragetSeqsAlignment;
	}

	public void setMaxTragetSeqsAlignment(int maxTragetSeqsAlignment) {
		this.maxTragetSeqsAlignment = maxTragetSeqsAlignment;
	}

	public int getTopPercentAlignment() {
		return topPercentAlignment;
	}

	public void setTopPercentAlignment(int topPercentAlignment) {
		this.topPercentAlignment = topPercentAlignment;
	}

	public float getMaxExpecValueAlignment() {
		return maxExpecValueAlignment;
	}

	public void setMaxExpecValueAlignment(float maxExpecValueAlignment) {
		this.maxExpecValueAlignment = maxExpecValueAlignment;
	}

	public int getMinScoreAlignment() {
		return minScoreAlignment;
	}

	public void setMinScoreAlignment(int minScoreAlignment) {
		this.minScoreAlignment = minScoreAlignment;
	}

}