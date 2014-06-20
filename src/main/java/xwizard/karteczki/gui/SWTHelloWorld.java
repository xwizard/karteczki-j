package xwizard.karteczki.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/*
 * From http://www.mkyong.com/swt/swt-hello-world-example/
 */
public class SWTHelloWorld {
 
public static void main (String [] args) {
    Display display = new Display ();
    Shell shell = new Shell(display);
 
    Text helloWorldTest = new Text(shell, SWT.NONE);
    helloWorldTest.setText("Hello World SWT");
    helloWorldTest.pack();
    
    Button button = new Button(shell, SWT.PUSH);
    button.setText("Click");
    button.pack();
 
    shell.pack();
    shell.open ();
    while (!shell.isDisposed ()) {
        if (!display.readAndDispatch ()) display.sleep ();
    }
    display.dispose ();
}
}