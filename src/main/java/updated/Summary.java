package updated;

import com.softalks.ebookipedia.SummaryUpdateResponse;

public class Summary {

	private Summary() {
		// SONAR mandatory
	}

	/**
	 * This command should be invoked from a Github action for each change of the
	 * editable/excerpt.html file of a repository based on the Github template
	 * ebookipedia/wikipedia
	 * 
	 * @param args An array with one only item having the value of the
	 *             GITHUB_REPOSITORY variable
	 */
	public static void main(String[] parameters) {
		if (parameters.length == 1) {
			String mirror = parameters[0];
			Runnable response = new SummaryUpdateResponse(mirror);
			response.run();
		} else {
			String error = "Parameter missing: GITHUB_REPOSITORY";
			throw new IllegalStateException(error);
		}
	}

}