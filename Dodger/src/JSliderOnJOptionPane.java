import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSliderOnJOptionPane {
  public static void main(final String[] args) {
    JFrame parent = new JFrame();

    JOptionPane optionPane = new JOptionPane();
    JSlider slider = getSlider(optionPane);
    optionPane.setMessage(new Object[] { "Select a value: ", slider });
    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
    JDialog dialog = optionPane.createDialog(parent, "My Slider");
    dialog.setVisible(true);
    System.out.println("Input: " + optionPane.getInputValue());
  }

  static JSlider getSlider(final JOptionPane optionPane) {
    JSlider slider = new JSlider();
    slider.setMajorTickSpacing(10);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    ChangeListener changeListener = new ChangeListener() {
      public void stateChanged(ChangeEvent changeEvent) {
        JSlider theSlider = (JSlider) changeEvent.getSource();
        if (!theSlider.getValueIsAdjusting()) {
          optionPane.setInputValue(new Integer(theSlider.getValue()));
        }
      }
    };
    slider.addChangeListener(changeListener);
    return slider;
  }

}