package securityPatternPlugin.handlers;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class EventDispatcher {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			// apply Windows Look and Feel to all frames lanched from this class
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Windows".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		//lanch the first frame from this event dispatcher
		new FrameDebut().show();
	}

}
