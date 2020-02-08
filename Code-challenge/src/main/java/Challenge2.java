import de.linguatools.disco.CorruptConfigFileException;
import sun.rmi.runtime.NewThreadAction;

import javax.management.relation.Relation;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import javax.swing.table.*;
import javax.swing.text.DefaultCaret;
import java.util.*;

import static sun.security.krb5.internal.Krb5.DEBUG;

public class Challenge2 extends JFrame  {

    //*********************************text
    public static JTextField precision;
    public static JTextField recall;
    public static JTextField fmeasure;
    public static JTable ideatable;

    public static ArrayList<String> validationList;


    public  Challenge2(ArrayList<String> resultsList) {
        JLabel m2 = new JLabel("   ");

        //**********************************textarea
        precision = new JTextField();
        precision.setFont(new Font("Arial", Font.BOLD, 13));
        //  time.setLineWrap(true);       // wrap line
        //time.setWrapStyleWord(true);  // wrap line at word boundary
        precision.setPreferredSize( new Dimension( 200, 35 ) );
        precision.setText("  Precision: ");
        precision.setBorder(new LineBorder(new Color(212, 212, 212),2));
        //---------------------------------------------------nb ideas
        recall = new JTextField();
        recall.setFont(new Font("Arial", Font.BOLD, 13));
        //nbIdeas.setLineWrap(true);       // wrap line
        //  nbIdeas.setWrapStyleWord(true);  // wrap line at word boundary
        recall.setPreferredSize( new Dimension( 200, 35 ) );
        recall.setText("  Recall: ");
        recall.setBorder(new LineBorder(new Color(212, 212, 212),2));
        //---------------------------------------------------nb ideas
        fmeasure = new JTextField();
        fmeasure.setFont(new Font("Arial", Font.BOLD, 13));
        //nbIdeas.setLineWrap(true);       // wrap line
        //  nbIdeas.setWrapStyleWord(true);  // wrap line at word boundary
        fmeasure.setPreferredSize( new Dimension( 200, 35 ) );
        fmeasure.setText("  F-Measure: ");
        fmeasure.setBorder(new LineBorder(new Color(212, 212, 212),2));

        //**********************************tables
        WineTableModel2 wineModel = new WineTableModel2();
        JTable ideatable = new JTable(wineModel);
        // since we’re using values of floats and boolean here, we need
        // to set the cell renderer for every column.
        for (int i =0; i<wineModel.getColumnCount();i++) {
            ideatable.setDefaultRenderer(ideatable.getColumnClass(i), new WineCellRenderer2());
        }
        // add rows to our TableModel, each row is represented as a Wine object
       for (int i = 0 ; i < resultsList.size() ; i++){
           String[] input = resultsList.get(i).split(";;;");
           wineModel.addWine(new Wine2(input[0],input[1],input[2],"False"));
       }
        resizeColumnWidth(ideatable);
        setValidationColumn(ideatable,ideatable.getColumnModel().getColumn(3));

        ideatable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane tAreaScrollPane = new JScrollPane(ideatable);
        tAreaScrollPane.setPreferredSize( new Dimension( 620, 289 ) );
        tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        //**********************************buttons
        //----------------------------------validate
        JButton Validate = new JButton("Validate");
        Validate.setMnemonic(KeyEvent.VK_C);
        Validate.setFont(new Font("Lato", Font.BOLD, 13));
        Validate.setBackground(new Color(0, 174, 239));
        Validate.setPreferredSize(new Dimension(136, 35));
        Validate.setBorder(new LineBorder(new Color(0, 191, 243),2));
        Validate.setForeground(new Color(255, 255, 255));

        Validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                validationList = new ArrayList<>();
                for (int i = 0 ; i < resultsList.size() ; i++){
                    validationList.add(resultsList.get(i)+";;;"+wineModel.getValueAt(i,3));
                    System.out.println(validationList.get(i));
                }

                Calculator calculator = new Calculator(validationList);
                precision.setText("  Precision: "+String.valueOf(calculator.Precision()));
                recall.setText("  Recall: "+String.valueOf(calculator.Recall()));
                fmeasure.setText("  F-Measure: "+String.valueOf(calculator.fmeasure()));
            }
        });

        JButton clear = new JButton("Clear");
        clear.setMnemonic(KeyEvent.VK_C);
        clear.setFont(new Font("Lato", Font.BOLD, 13));
        clear.setBackground(new Color(255, 240, 84));
        clear.setPreferredSize(new Dimension(136, 35));
        clear.setBorder(new LineBorder(new Color(255, 240, 84),2));
        clear.setForeground(new Color(0, 0, 0));
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                precision.setText("  Precision: ");
                recall.setText("  Recall: ");
                fmeasure.setText("  F-Measure: ");

                for(int i = 0 ; i < wineModel.getRowCount() ; i++) {
                    wineModel.setValueAt("False", i, 3);
                }
            }
        });

        //***********************************panels
         GridBagConstraints gbc = new GridBagConstraints();
        //---------------------------------------------------south
        JPanel south =  new JPanel(new GridBagLayout());
        south.setBackground(Color.white);
        m2.setPreferredSize(new Dimension(330, 35));

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        south.add(Validate,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        south.add(clear,gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        south.add(m2,gbc);


        //---------------------------------------------------center
        JPanel center =  new JPanel(new GridBagLayout());
        JPanel center2 =  new JPanel(new GridBagLayout());

        center.setBackground(Color.white);
        center2.setBackground(Color.white);

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx = 0.5;
        center2.add(precision,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.weightx = 0.5;
        center2.add(recall,gbc);


        gbc.gridx=2;
        gbc.gridy=0;
        gbc.weightx = 0.5;
        center2.add(fmeasure,gbc);

        tAreaScrollPane.getViewport().setBackground(Color.WHITE);
        tAreaScrollPane.setBackground(Color.white);
        gbc.fill=GridBagConstraints.VERTICAL;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weighty = 1.0;
        center.add(tAreaScrollPane,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.weighty = 0.0;

        center.add(center2,gbc);

        // *********************************Setup the content-pane of JFrame in BorderLayout
        Container cp = this.getContentPane();
        cp.setBackground(Color.white);
        cp.setLayout(new BorderLayout(5, 5));
        cp.add(center,BorderLayout.CENTER);
        cp.add(south,BorderLayout.SOUTH);
        //**********************************cp.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Challenge");
        setSize(640, 467);
        setVisible(true);
        setResizable(false);


    }

    //***********************************************resize the table according to the content
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        JTableHeader header = table.getTableHeader();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 100; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
                System.out.println(width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }

    }

    public void setValidationColumn(JTable table,  TableColumn validationColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("True");
        comboBox.addItem("False");
        validationColumn.setCellEditor(new DefaultCellEditor(comboBox));
        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        validationColumn.setCellRenderer(renderer);

    }



}



//---------------------------------------table
// a simple object that holds data about a particular wine
class Wine2 {
    private String  Idea1;
    private String  Idea2;
    private String   Relationship;
    private String  validation;

    public Wine2(String Idea1, String Idea2, String Relationship, String validation) {
        this.Idea1 = Idea1;
        this.Idea2 = Idea2;
        this.Relationship = Relationship;
        this.validation = validation;
    }

    public void  setValidation(String validation){
        this.validation = validation ;
    }

    public String getIdea1()     { return Idea1; }
    public String getIdea2()  { return Idea2; }
    public String  getRelationship()    { return Relationship; }
     public String getvalidation() { return validation; }

    public String toString() {
        return "[" + Idea1 + ", " + Idea2 + ", " + Relationship + ", " + validation;}//+ ", " + inStock + "]"; }
}

class WineTableModel2 extends AbstractTableModel {
    // holds the strings to be displayed in the column headers of our table
    final String[] columnNames = {"Idea1", "Idea2", "Relationship", "validation"};

    // holds the data types for all our columns
    final Class[] columnClasses = {String.class, String.class, String.class, String.class};

    // holds our data
    final Vector data = new Vector();

    // adds a row
    public void addWine(Wine2 w) {
        data.addElement(w);
        fireTableRowsInserted(data.size()-1, data.size()-1);
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int c) {
        return columnClasses[c];
    }

    public String getValueAt(int row, int col) {
        Wine2 wine = (Wine2) data.elementAt(row);
        if (col == 0)      return wine.getIdea1();
        else if (col == 1) return wine.getIdea2();
        else if (col == 2) return wine.getRelationship();
        else if (col == 3) return wine.getvalidation();
        else return null;
    }

    public Object getValueAtRow(int row) {
        Wine2 wine = (Wine2) data.elementAt(row);
        return wine;
    }

    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 3) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                    + " to " + value
                    + " (an instance of "
                    + value.getClass() + ")");
        }

        Wine2 wine = (Wine2) data.elementAt(row);
        wine.setValidation((String) value);
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + data.elementAt(i));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

}

class WineCellRenderer2 extends DefaultTableCellRenderer {


    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column)
    {

        Component cell = super.getTableCellRendererComponent
                (table, value, isSelected, hasFocus, row, column);

        table.setShowHorizontalLines(false);
        table.setGridColor(new Color(0, 191, 243));

        table.setRowHeight(30);

        table.getColumnModel().getColumn(0).setHeaderRenderer(new HeaderRenderer2(new Color(145, 219, 249),new Color(0, 191, 243)));
        table.getColumnModel().getColumn(1).setHeaderRenderer(new HeaderRenderer2(new Color(145, 219, 249),new Color(0, 191, 243)));
        table.getColumnModel().getColumn(2).setHeaderRenderer(new HeaderRenderer2(new Color(145, 219, 249),new Color(0, 191, 243)));
        table.getColumnModel().getColumn(3).setHeaderRenderer(new HeaderRenderer2(new Color(245, 153, 157),new Color(241, 109, 126)));

        if (row % 2 != 0 && (column == 0 || column == 1 || column == 2)) {
            cell.setBackground(new Color(145, 219, 249));
        }else if(row % 2 != 0 && column == 3){
            cell.setBackground(new Color(245, 153, 157));
        }
        else if (row % 2 == 0){
            cell.setBackground(new Color(255, 255, 255));
        }

        return cell;

    }
}

class HeaderRenderer2 extends JLabel implements TableCellRenderer {

    public HeaderRenderer2( Color color, Color bor) {
        setFont(new Font("Microsoft Sans Serif", Font.BOLD, 12));
        setOpaque(true);
        setBackground(color);
        setBorder(new LineBorder(bor,2));
        setPreferredSize(new Dimension(100,30));
    }

    public HeaderRenderer2(int width, int hight) {
        setPreferredSize(new Dimension(width,hight));
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }

}
