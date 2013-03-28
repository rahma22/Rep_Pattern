package securityPatternPlugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.ui.IWorkbenchWindow;
//import org.eclipse.ui.handlers.HandlerUtil;
//import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */

	@SuppressWarnings("deprecation")
	public Object execute(ExecutionEvent event) throws ExecutionException {
		/*++++++++++++++++++++>> A ELIMINER <<+++++++++++++++++++++++++++++++
		 * IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Essai1",
				"This menu Provides you different Security Patterns to apply to your \n UML component diagram");*/
		new FrameDebut().show();
		return null;
	}
}
