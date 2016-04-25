package edu.utsa.fileflow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.utsa.fileflow.analysis.AnalysisException;
import edu.utsa.fileflow.analysis.Analyzer;
import edu.utsa.fileflow.analysis.DummyAnalysis;
import edu.utsa.fileflow.analysis.DummyAnalysisSubDomain;
import edu.utsa.fileflow.cfg.FlowPoint;
import edu.utsa.fileflow.testutils.GraphvizGenerator;
import edu.utsa.fileflow.utilities.FileFlowHelper;

public class Main2 {

	private static final String TEST_SCRIPT = "scripts/test.ffa";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		FlowPoint cfg = FileFlowHelper.generateControlFlowGraphFromScript(new File(TEST_SCRIPT));
		String dot = GraphvizGenerator.generateDOT(cfg);
		GraphvizGenerator.saveDOTToFile(dot, TEST_SCRIPT + ".dot");
		System.out.println("DOT file written to: '" + TEST_SCRIPT + ".dot'");

		Analyzer analyzer;
		try {
			analyzer = new Analyzer();

			// FIXME: make this work
			DummyAnalysisSubDomain domain = new DummyAnalysisSubDomain();
			DummyAnalysis analysis = new DummyAnalysis();
			analyzer.addPlugin(domain, analysis);

			analyzer.analyze(cfg);
		} catch (AnalysisException e) {
			System.out.println(e.getMessage());
		}
	}
}
