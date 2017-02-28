package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Altynay on 2/27/2017.
 */
public class Win extends JFrame {

    private final int NUM_GRID_ROWS = 2;
    private final int NUM_GRID_COLS = 2;
    private final int NUM_BTN_GRPS = 2;
    private final int NUM_RADIO_BTNS = 4;
    private final String[] TYPES = {"Bin","Oct","Dec","Hex"};
    private final String[] LABELS = {"Enter value to be converted: ","Result: ","From: ","To: "};

    public Win(){

        //!!! TO DO: tie location and size to screen size & resolution
        setBounds(500,200,500,500);
        setTitle("Converter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel jpMain = new JPanel();
        add(jpMain);
        jpMain.setLayout(new GridLayout(NUM_GRID_ROWS,NUM_GRID_COLS));

        JPanel[] jp = new JPanel[NUM_GRID_ROWS*NUM_GRID_COLS];
        JLabel[] labels = new JLabel[NUM_GRID_ROWS*NUM_GRID_COLS];
        for (int i = 0; i < NUM_GRID_ROWS*NUM_GRID_COLS; i++) {
            jp[i] = new JPanel();
            jpMain.add(jp[i]);
            jp[i].setLayout(new FlowLayout());
            jp[i].setBackground(new Color(150-i * 10, 150-i * 10, 150-i * 10));
            labels[i] = new JLabel(LABELS[i]);
            jp[i].add(labels[i]);
        }

        //Editable text field for entering value to be converted
        JTextField jtf = new JTextField(20);
        jp[0].add(jtf);

        //Editable text field for entering value to be converted
        JTextField jtf2 = new JTextField(20);
        jp[1].add(jtf2);
        jtf2.setEditable(false);

        ButtonGroup[] buttonGroups = new ButtonGroup[NUM_BTN_GRPS];
        for (int i = 0; i < NUM_BTN_GRPS; i++) {
            buttonGroups[i] = new ButtonGroup();
            JRadioButton[] jrb = new JRadioButton[4];
            for (int j = 0; j < NUM_RADIO_BTNS; j++) {
                jrb[j] = new JRadioButton(TYPES[j]);
                buttonGroups[i].add(jrb[j]);
                jp[2+i].add(jrb[j]);
            }
        }


        JButton btnConvert = new JButton("Convert");
        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jtfValue = Integer.parseInt(jtf.getText());
                String rdbtnFrom = buttonGroups[0].getSelection().toString();
                String rdbtnTo = buttonGroups[1].getSelection().toString();
                jtf2.setText(convert(jtfValue, rdbtnFrom, rdbtnTo));
            }
        });
        add(btnConvert,BorderLayout.SOUTH);

        setVisible(true);
    }

    private static String convert(int bin,String rdbtnFrom, String rdbtnTo) {

        //!!! TO DO: use switch to implement all different conversion options using radio buttons
        final int SHIFT_SIZE = 4;
        final int HALF_BYTE = 0x0F;
        final char[] HEX_DIGITS = {
                '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        System.out.println("Original String value is: " + bin);

        //For some reason there is an error when getting Integer from jtfValue

        StringBuilder hex = new StringBuilder(10000);

        for (int i = 7; i >= 0 ; i--) {
            int j = bin & HALF_BYTE;
            hex.setCharAt(i,HEX_DIGITS[j]);
            bin >>= SHIFT_SIZE;
        }
        return hex.toString();
    }

}
