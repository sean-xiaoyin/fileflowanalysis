package edu.utsa.fileflow.analysis;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.utsa.fileflow.cfg.FlowPoint;
import edu.utsa.fileflow.utilities.FileFlowHelper;

/**
 * 
 * @author Rodney Rodriguez
 *
 */
public class AnalysisTest {

	private static final String TEST_SCRIPT = "scripts/test.ffa";

	@Rule
	public ExpectedException expectException = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTraversal() throws Exception {
		FlowPoint cfg = FileFlowHelper.generateControlFlowGraphFromScript(new File(TEST_SCRIPT));
		Analyzer analyzer = new Analyzer();
		analyzer.analyze(cfg);
	}

}
