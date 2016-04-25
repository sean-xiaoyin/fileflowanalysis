package edu.utsa.fileflow.analysis;

import edu.utsa.fileflow.cfg.FlowPointContext;

public class AnalyzerAnalysis implements Analysis<AnalysisDomain> {

	@Override
	public AnalysisDomain onBefore(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> {
			// domain, analysis
			v.onBefore(k, context);
		});
		return domain;
	}

	@Override
	public AnalysisDomain onAfter(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> v.onAfter(k, context));
		return domain;
	}

	@Override
	public AnalysisDomain touch(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> v.touch(k, context));
		return domain;
	}

	@Override
	public AnalysisDomain mkdir(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> v.mkdir(k, context));
		return domain;
	}

	@Override
	public AnalysisDomain copy(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> v.copy(k, context));
		return domain;
	}

	@Override
	public AnalysisDomain remove(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> v.remove(k, context));
		return domain;
	}

	@Override
	public AnalysisDomain enterProg(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> v.enterProg(k, context));
		return domain;
	}

	@Override
	public AnalysisDomain exitProg(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> v.exitProg(k, context));
		return domain;
	}

	@Override
	public AnalysisDomain enterWhileStatement(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> v.enterWhileStatement(k, context));
		return domain;
	}

	@Override
	public AnalysisDomain exitWhileStatement(AnalysisDomain domain, FlowPointContext context) {
		domain.subdomains.forEach((k, v) -> v.exitWhileStatement(k, context));
		return domain;
	}

}
