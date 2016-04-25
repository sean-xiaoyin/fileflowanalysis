package edu.utsa.fileflow.analysis;

public abstract class AnalysisSubDomain extends AnalysisDomain implements Comparable<AnalysisSubDomain> {

	public abstract AnalysisSubDomain merge(AnalysisSubDomain domain);

	public abstract AnalysisSubDomain setTop();

	public abstract AnalysisSubDomain setBottom();

	public abstract AnalysisSubDomain top();

	public abstract AnalysisSubDomain bottom();

	public abstract int compareTo(AnalysisSubDomain o);

	public abstract AnalysisSubDomain clone();

}
