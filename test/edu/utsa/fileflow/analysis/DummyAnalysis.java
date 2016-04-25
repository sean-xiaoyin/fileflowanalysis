package edu.utsa.fileflow.analysis;

import edu.utsa.fileflow.cfg.FlowPointContext;

public class DummyAnalysis extends AbstractAnalysis<DummyAnalysisSubDomain> {

	@Override
	public AnalysisSubDomain onBefore(DummyAnalysisSubDomain domain, FlowPointContext context) {
		return domain;
	}

	@Override
	public AnalysisSubDomain onAfter(DummyAnalysisSubDomain domain, FlowPointContext context) {
		return domain;
	}

	@Override
	public AnalysisSubDomain enterProg(DummyAnalysisSubDomain domain, FlowPointContext context) {
		System.out.println("enterProg");
		domain.flag = 1;
		return domain;
	}

	@Override
	public AnalysisSubDomain exitProg(DummyAnalysisSubDomain domain, FlowPointContext context) {
		System.out.println("exitProg");
		domain.flag = 1;
		return domain;
	}

	@Override
	public AnalysisSubDomain touch(DummyAnalysisSubDomain domain, FlowPointContext context) {
		System.out.println("touch");
		domain.flag = 1;
		// FunctionCallContext ctx = (FunctionCallContext) context.getContext();
		// for (ExpressionContext expr : ctx.expression()) {
		// System.out.println("expr: " + expr.getText());
		// }
		return domain;
	}

	@Override
	public AnalysisSubDomain mkdir(DummyAnalysisSubDomain domain, FlowPointContext context) {
		System.out.println("mkdir");
		domain.flag = 1;
		return domain;
	}

	@Override
	public AnalysisSubDomain copy(DummyAnalysisSubDomain domain, FlowPointContext context) {
		System.out.println("copy");
		domain.flag = 1;
		return domain;
	}

	@Override
	public AnalysisSubDomain remove(DummyAnalysisSubDomain domain, FlowPointContext context) {
		System.out.println("remove");
		domain.flag = 1;
		return domain;
	}

	@Override
	public AnalysisSubDomain enterWhileStatement(DummyAnalysisSubDomain domain, FlowPointContext context) {
		System.out.println("while");
		domain.flag = 1;
		return domain;
	}

	@Override
	public AnalysisSubDomain exitWhileStatement(DummyAnalysisSubDomain domain, FlowPointContext context) {
		System.out.println("exit while");
		domain.flag = 1;
		return domain;
	}

}
