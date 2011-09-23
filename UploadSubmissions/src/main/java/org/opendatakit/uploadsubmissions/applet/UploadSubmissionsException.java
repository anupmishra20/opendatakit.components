package org.opendatakit.uploadsubmissions.applet;

import java.util.Collections;
import java.util.List;

import org.opendatakit.uploadsubmissions.submission.SubmissionResult;

public class UploadSubmissionsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1177712489179443492L;

	private final List<SubmissionResult> detailedResults;

	/**
	 * Construct exception with the error message
	 * 
	 * @param message
	 *            exception message
	 */
	public UploadSubmissionsException(List<SubmissionResult> detailedResults) {
		super(buildMessage(detailedResults));
		this.detailedResults = detailedResults;
	}
	
	public List<SubmissionResult> getDetailedResults() {
		return Collections.unmodifiableList(detailedResults);
	}

	private static String buildMessage(List<SubmissionResult> results) {
		StringBuilder b = new StringBuilder();
		b.append("Details<br/>");
		boolean failuresPresent = false;
		for ( SubmissionResult result : results ) {
			b.append(UploadApplet.SPAN_OPEN);
			b.append("Submission: <b>");
			b.append(result.getFile().getName());
			b.append("</b> Outcome: ");
			if ( result.isSuccess() ) {
				b.append("Success!\n");
			} else {
				failuresPresent = true;
				b.append(result.getFailureReason());
			}
			b.append("</span><br/>"); 
			b.append(UploadApplet.SPAN_CLOSE);
		}
		final String output = (failuresPresent ? "<b>FAILURE(S)</b><br/>" : 
							"<b>COMPLETE SUCCESS</b><br/>") + b.toString();
		return output;
	}
}