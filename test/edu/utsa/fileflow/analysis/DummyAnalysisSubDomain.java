package edu.utsa.fileflow.analysis;

public class DummyAnalysisSubDomain extends AnalysisSubDomain {

	private static final DummyAnalysisSubDomain TOP = new DummyAnalysisSubDomain();
	private static final DummyAnalysisSubDomain BOTTOM = new DummyAnalysisSubDomain();
	static {
		TOP.flag = 1;
		BOTTOM.flag = 0;
	}

	int flag = 0;

	@Override
	public DummyAnalysisSubDomain merge(AnalysisSubDomain d) {
		DummyAnalysisSubDomain domain = (DummyAnalysisSubDomain) d;
		if (flag != domain.flag) {
			flag = 1;
		}
		return this;
	}

	@Override
	public AnalysisSubDomain setTop() {
		flag = 1;
		return this;
	}

	@Override
	public AnalysisSubDomain setBottom() {
		flag = 0;
		return this;
	}

	@Override
	public DummyAnalysisSubDomain top() {
		return TOP.clone();
	}

	@Override
	public DummyAnalysisSubDomain bottom() {
		return BOTTOM.clone();
	}

	@Override
	public int compareTo(AnalysisSubDomain o) {
		DummyAnalysisSubDomain domain = (DummyAnalysisSubDomain) o;
		if (flag < domain.flag)
			return -1;
		if (flag > domain.flag)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return Integer.toString(flag);
	}

	@Override
	public DummyAnalysisSubDomain clone() {
		DummyAnalysisSubDomain domain = new DummyAnalysisSubDomain();
		domain.flag = flag;
		return domain;
	}

}
