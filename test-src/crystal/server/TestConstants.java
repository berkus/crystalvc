package crystal.server;

import java.io.File;
import java.util.Vector;

public class TestConstants {

	public static String PROJECT_PATH = null;
	static {
		/**
		 * This should contain the path to your workspace project location.
		 */
		Vector<String> possiblePaths = new Vector<String>();
		possiblePaths.add("/Users/rtholmes/Documents/workspaces/workspace_longitudinal/conflictClient/");
		possiblePaths.add("/Users/rtholmes/Documents/Crystal/"); // MBP
		possiblePaths.add("/homes/gws/brun/tempCrystal/sourceRepo/");
		possiblePaths.add("C:\\Users\\Yuriy\\Desktop\\work\\crystalSource");
		possiblePaths.add("C:\\Users\\brun\\work\\crystalCode");

		for (String possiblePath : possiblePaths) {
			if (new File(possiblePath).exists()) {
				PROJECT_PATH = possiblePath;
				break;
			}
		}

		assert PROJECT_PATH != null : "Make sure your project path is in possiblePaths above.";

		if (!PROJECT_PATH.endsWith(File.separator)) {
			PROJECT_PATH += File.separator;
		}

		System.out.println("Constants::<clinit> - working path: " + PROJECT_PATH);
	}

	public static String HG_COMMAND = null;
	static {
		/**
		 * This should contain the path to your hg binary.
		 */
		Vector<String> possiblePaths = new Vector<String>();
		possiblePaths.add("/usr/local/bin/hg");
		possiblePaths.add("C:\\Program Files (x86)\\TortoiseHg\\hg");
		possiblePaths.add("hg");
		possiblePaths.add("C:\\Program Files\\TortoiseHg\\hg.exe");
		// possiblePaths.add("\"C:\\Program Files\\TortoiseHg\\hg.exe\"");

		for (String possiblePath : possiblePaths) {
			if (new File(possiblePath).exists()) {
				HG_COMMAND = possiblePath;
				break;
			}
		}

		assert HG_COMMAND != null : "Make sure your hg binary is in possiblePaths above.";
		assert new File(HG_COMMAND).exists() : "Cannot find hg executable; this must be defined";

		System.out.println("Constants::<clinit> - HG path: " + HG_COMMAND);
	}

	public static final String TEST_REPOS = "test-repos" + File.separator;
	public static final String TEST_TEMP = "test-temp" + File.separator;
}