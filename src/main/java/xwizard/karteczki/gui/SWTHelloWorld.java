package xwizard.karteczki.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import java.util.ResourceBundle;

/*
 * From http://www.mkyong.com/swt/swt-hello-world-example/
 */
public class SWTHelloWorld {
  private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("xwizard.karteczki.gui.messages"); //$NON-NLS-1$
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setSize(152, 105);

    Text helloWorldTest = new Text(shell, SWT.NONE);
    helloWorldTest.setLocation(40, 5);
    helloWorldTest.setText("Hello World SWT");
    helloWorldTest.pack();

    Button button = new Button(shell, SWT.PUSH);
    button.setText("Click");
    button.pack();

    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}