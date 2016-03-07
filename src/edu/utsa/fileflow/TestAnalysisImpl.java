package edu.utsa.fileflow;

import edu.utsa.fileflow.analysis.Analysis;
import edu.utsa.fileflow.analysis.AnalysisDomain;
import edu.utsa.fileflow.antlr.FileFlowParser.ExpressionContext;
import edu.utsa.fileflow.antlr.FileFlowParser.FunctionCallContext;
import edu.utsa.fileflow.cfg.FlowPointContext;

public class TestAnalysisImpl implements Analysis {

	@Override
	public void enterProg(AnalysisDomain<?> domain, FlowPointContext context) {
		System.out.println("enterProg");
	}

	@Override
	public void touch(AnalysisDomain<?> domain, FlowPointContext context) {
		System.out.println("touch");
		FunctionCallContext ctx = (FunctionCallContext) context.getContext();
		for (ExpressionContext expr : ctx.expression()) {
			System.out.println(expr.getText());
		}
	}

	@Override
	public void mkdir(AnalysisDomain<?> domain, FlowPointContext context) {
		System.out.println("mkdir");
	}

	@Override
	public void copy(AnalysisDomain<?> domain, FlowPointContext context) {
		System.out.println("copy");
	}

	@Override
	public void remove(AnalysisDomain<?> domain, FlowPointContext context) {
		System.out.println("remove");
	}

}
