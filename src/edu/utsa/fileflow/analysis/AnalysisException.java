package edu.utsa.fileflow.analysis;

@SuppressWarnings("serial")
public class AnalysisException extends Exception {
	public AnalysisException(String message) {
		super("AnalysisException: " + message);
	}
}
