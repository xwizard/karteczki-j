package xwizard.karteczki.gui;

import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/*
 * From http://www.mkyong.com/swt/swt-hello-world-example/
 */
public class SWTHelloWorld {
  private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("xwizard.karteczki.gui.messages"); //$NON-NLS-1$
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setSize(289, 346);

    shell.pack();
    shell.setLayout(new FillLayout(SWT.HORIZONTAL));
    
    StyledText styledText = new StyledText(shell, SWT.BORDER);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}