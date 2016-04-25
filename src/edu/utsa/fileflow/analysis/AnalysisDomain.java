package edu.utsa.fileflow.analysis;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This class is the parent domain for all sub domains that should be
 * implemented by the user of this analysis framework. It is a container and
 * wraps many methods for its sub domains. If any functionality is expected of
 * this class, it should also be expected of its defined sub domains. In other
 * words, any methods defined here are just wrapper functions that iterate
 * through all of its sub domains and call each of their respective functions.
 * 
 * @author Rodney Rodriguez
 *
 */
public class AnalysisDomain {

//	List<AnalysisSubDomain> subdomains = new ArrayList<>();
	HashMap<AnalysisSubDomain, AbstractAnalysis> subdomains = new HashMap<>();

	/**
	 * Merges all subdomains defined inside this domain.
	 * 
	 * @param other
	 *            The other domain to merge with the invoking one.
	 * @return The invoking domain.
	 */
	public AnalysisDomain merge(AnalysisDomain other) {
		Iterator<AnalysisSubDomain> it1 = subdomains.keySet().iterator();
		Iterator<AnalysisSubDomain> it2 = other.subdomains.keySet().iterator();
		while (it1.hasNext()) {
			it1.next().merge(it2.next());
		}
		return this;
	}

	/**
	 * Sets all subdomains defined within this domain to be top.
	 * 
	 * @return This domain.
	 */
	public AnalysisDomain setTop() {
		Iterator<AnalysisSubDomain> it1 = subdomains.keySet().iterator();
		while (it1.hasNext()) {
			it1.next().setTop();
		}
		return this;
	}

	/**
	 * Sets all subdomains defined within this domain to be bottom.
	 * 
	 * @return This domain.
	 */
	public AnalysisDomain setBottom() {
		Iterator<AnalysisSubDomain> it1 = subdomains.keySet().iterator();
		while (it1.hasNext()) {
			it1.next().setBottom();
		}
		return this;
	}

	/**
	 * Iterates through all subdomains and compares with the other domain. If
	 * any of their respective subdomains differ these domains are not equal. If
	 * all subdomains are the same then these domains are equal.
	 * 
	 * @param other
	 *            The other domain to compare to.
	 * @return True if all domains are equal; false if any differ.
	 */
	public boolean equals(AnalysisDomain other) {
		Iterator<AnalysisSubDomain> it1 = subdomains.keySet().iterator();
		Iterator<AnalysisSubDomain> it2 = other.subdomains.keySet().iterator();
		while (it1.hasNext()) {
			if (it1.next().compareTo(it2.next()) != 0)
				return false;
		}
		return true;
	}

	// public abstract int compareTo(AnalysisDomain o);

	// public abstract AnalysisDomain clone();

}
