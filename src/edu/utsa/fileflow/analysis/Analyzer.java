package edu.utsa.fileflow.analysis;

import java.util.Stack;

import edu.utsa.fileflow.cfg.FlowPoint;
import edu.utsa.fileflow.cfg.FlowPointContext;
import edu.utsa.fileflow.cfg.FlowPointContextType;
import edu.utsa.fileflow.cfg.FlowPointEdge;

public class Analyzer {

	AnalysisDomain domain;
	AnalyzerAnalysis analysis;

	public Analyzer() throws AnalysisException {
		domain = new AnalysisDomain();
		analysis = new AnalyzerAnalysis();

		// TODO: validate domain
		// if (domain.bottom() == null)
		// throw new AnalysisException(domain.getClass().getSimpleName() +
		// "::bottom() cannot be null.");
		// if (domain.top() == null)
		// throw new AnalysisException(domain.getClass().getSimpleName() +
		// "::top() cannot be null.");
	}

	public void addPlugin(AnalysisSubDomain subdomain, AbstractAnalysis<?> analysis) {
		domain.subdomains.put(subdomain, analysis);
	}

	public void analyze(FlowPoint cfg) {
		// initialize the workset
		Stack<FlowPoint> workset = new Stack<>();

		// add the start node to the workset
		updateAnalysis(cfg, domain);
		workset.add(cfg);

		while (!workset.isEmpty()) {
			FlowPoint flowpoint = workset.pop();

			// add all children to the workset
			for (FlowPointEdge edge : flowpoint.getOutgoingEdgeList()) {
				FlowPoint child = edge.getTarget();
				// for each outgoing edge, compute y (new domain)
				// then check if y is different from the old domain
				// if so, update domain and target to workset
				AnalysisDomain y = updateAnalysis(child, flowpoint.domain);
				if (!y.equals(child.domain)) {
					child.domain = y;
					workset.add(child);
				}
			}

		}
	}

	private AnalysisDomain updateAnalysis(FlowPoint target, AnalysisDomain inputDomain) {
		AnalysisDomain result = null;
		FlowPointContext fpctx = target.getContext();
		FlowPointContextType type = fpctx.getType();

		// TODO: find an alternative to this
		if (target.domain == null) {
			target.domain = inputDomain.setBottom();
		}

		analysis.onBefore(inputDomain, fpctx);

		switch (type) {
		case ProgEnter:
			result = analysis.enterProg(inputDomain, fpctx);
			break;
		case ProgExit:
			result = analysis.exitProg(inputDomain, fpctx);
		case FunctionCall:
			if (fpctx.getText().startsWith("touch")) {
				result = analysis.touch(inputDomain, fpctx);
			} else if (fpctx.getText().startsWith("mkdir")) {
				result = analysis.mkdir(inputDomain, fpctx);
			} else if (fpctx.getText().startsWith("rm")) {
				result = analysis.remove(inputDomain, fpctx);
			} else if (fpctx.getText().startsWith("copy")) {
				result = analysis.copy(inputDomain, fpctx);
			}
			break;
		case WhileStatement:
			result = analysis.enterWhileStatement(inputDomain, fpctx);
			break;
		case FlowPoint:
			if (fpctx.getText().equals("EXIT_WHILE")) {
				// TODO: make exitWhile enum
				result = analysis.exitWhileStatement(inputDomain, fpctx);
			}
			break;
		default:
			System.err.println("Not implemented: " + target);
			break;
		}

		analysis.onAfter(inputDomain, fpctx);

		return result;
	}

}
