package ferjogames.coralsystemdeck.desktop;

import com.badlogic.gdx.Input;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ferjogames.coralsystemdeck.Platform;

/**
 * Created by Fer on 29/09/2017.
 */
public class Desktop implements Platform {
    @Override
    public void getTextInput(final Input.TextInputListener listener, final String title, final String text, final String hint) {
       // Gdx.input.getTextInput(listener, title, text, hint);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run () {
                JPanel panel = new JPanel(new FlowLayout());

                JPanel textPanel = new JPanel() {
					private static final long serialVersionUID = -8057063944682282519L;

					public boolean isOptimizedDrawingEnabled () {
                        return false;
                    };
                };

                textPanel.setLayout(new OverlayLayout(textPanel));
                panel.add(textPanel);

                final JTextField textField = new JTextField(20);
                textField.setText(text);
                textField.setAlignmentX(0.0f);
                textPanel.add(textField);

                final JLabel placeholderLabel = new JLabel(hint);
                placeholderLabel.setForeground(Color.GRAY);
                placeholderLabel.setAlignmentX(0.0f);
                textPanel.add(placeholderLabel, 0);

                textField.getDocument().addDocumentListener(new DocumentListener() {

                    @Override
                    public void removeUpdate (DocumentEvent arg0) {
                        this.updated();
                    }

                    @Override
                    public void insertUpdate (DocumentEvent arg0) {
                        this.updated();
                    }

                    @Override
                    public void changedUpdate (DocumentEvent arg0) {
                        this.updated();
                    }

                    private void updated () {
                        if (textField.getText().length() == 0)
                            placeholderLabel.setVisible(true);
                        else
                            placeholderLabel.setVisible(false);
                    }
                });

                JOptionPane pane = new JOptionPane(panel, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, null,
                        null);

                pane.setInitialValue(null);
                pane.setComponentOrientation(JOptionPane.getRootFrame().getComponentOrientation());

                Border border = textField.getBorder();
                placeholderLabel.setBorder(new EmptyBorder(border.getBorderInsets(textField)));

                JDialog dialog = pane.createDialog(null, title);
                pane.selectInitialValue();

                dialog.addWindowFocusListener(new WindowFocusListener() {

                    @Override
                    public void windowLostFocus (WindowEvent arg0) {
                    }

                    @Override
                    public void windowGainedFocus (WindowEvent arg0) {
                        textField.requestFocusInWindow();
                    }
                });

                dialog.setModal(true);
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                dialog.dispose();

                Object selectedValue = pane.getValue();

                if (selectedValue != null && (selectedValue instanceof Integer)
                        && ((Integer)selectedValue).intValue() == JOptionPane.OK_OPTION) {
                    listener.input(textField.getText());
                } else {
                    listener.canceled();
                }

            }
        });
    }
}
