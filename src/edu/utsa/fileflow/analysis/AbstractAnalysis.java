package edu.utsa.fileflow.analysis;

import edu.utsa.fileflow.cfg.FlowPointContext;

public abstract class AbstractAnalysis<T extends AnalysisSubDomain> implements Analysis<T> {

	@Override
	public AnalysisDomain onBefore(T domain, FlowPointContext context) {
		return null;
	}

	@Override
	public AnalysisDomain onAfter(T domain, FlowPointContext context) {
		return null;
	}

	@Override
	public AnalysisDomain touch(T domain, FlowPointContext context) {
		return null;
	}

	@Override
	public AnalysisDomain mkdir(T domain, FlowPointContext context) {
		return null;
	}

	@Override
	public AnalysisDomain copy(T domain, FlowPointContext context) {
		return null;
	}

	@Override
	public AnalysisDomain remove(T domain, FlowPointContext context) {
		return null;
	}

	@Override
	public AnalysisDomain enterProg(T domain, FlowPointContext context) {
		return null;
	}

	@Override
	public AnalysisDomain exitProg(T domain, FlowPointContext context) {
		return null;
	}

	@Override
	public AnalysisDomain enterWhileStatement(T domain, FlowPointContext context) {
		return null;
	}

	@Override
	public AnalysisDomain exitWhileStatement(T domain, FlowPointContext context) {
		return null;
	}

}
