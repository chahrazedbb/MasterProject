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
public class Challenge extends JFrame  {

    //*********************************text
    public static JTextField time;
    public static JTextField nbIdeas;
    public static JTextField filePath;
    public static JTable ideatable;
    public static  JScrollPane tAreaScrollPane;
    public static  WineTableModel wineModel;

    public static String fileName="";
    public static String result = "" ;
    public static ArrayList<String> resultsList;

    public  Challenge() {
        JLabel m = new JLabel("   ");
        JLabel m2 = new JLabel("   ");
        resultsList = new  ArrayList<String>();

        //**********************************textarea
        //---------------------------------------------------file path
        filePath = new JTextField();
        filePath.setFont(new Font("Arial", Font.PLAIN, 13));
       // filePath.setLineWrap(true);       // wrap line
        //filePath.setWrapStyleWord(true);  // wrap line at word boundary
        filePath.setPreferredSize( new Dimension( 448, 35 ) );
        filePath.setText("  \n");
        filePath.setBorder(new LineBorder(new Color(212, 212, 212),2));
         //---------------------------------------------------time
        time = new JTextField();
        time.setFont(new Font("Arial", Font.BOLD, 13));
      //  time.setLineWrap(true);       // wrap line
        //time.setWrapStyleWord(true);  // wrap line at word boundary
        time.setPreferredSize( new Dimension( 300, 35 ) );
        time.setText("  Execution Time: ");
        time.setBorder(new LineBorder(new Color(212, 212, 212),2));
        //---------------------------------------------------nb ideas
        nbIdeas = new JTextField();
        nbIdeas.setFont(new Font("Arial", Font.BOLD, 13));
        //nbIdeas.setLineWrap(true);       // wrap line
      //  nbIdeas.setWrapStyleWord(true);  // wrap line at word boundary
        nbIdeas.setPreferredSize( new Dimension( 300, 35 ) );
        nbIdeas.setText("  Total number of ideas: ");
        nbIdeas.setBorder(new LineBorder(new Color(212, 212, 212),2));

        //**********************************tables
         wineModel = new WineTableModel();
         ideatable = new JTable(wineModel);
        // since we’re using values of floats and boolean here, we need
        // to set the cell renderer for every column.
        for (int i =0; i<wineModel.getColumnCount();i++) {
            ideatable.setDefaultRenderer(ideatable.getColumnClass(i), new WineCellRenderer());
        }
        // add rows to our TableModel, each row is represented as a Wine object
        ideatable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tAreaScrollPane = new JScrollPane(ideatable);
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
        //----------------------------------choose file
        JButton chooseFile = new JButton("Choose File");
        chooseFile.setMnemonic(KeyEvent.VK_C);
        chooseFile.setFont(new Font("Lato", Font.BOLD, 13));
        chooseFile.setBackground(new Color(255, 255, 255));
        chooseFile.setPreferredSize(new Dimension(136, 35));
        chooseFile.setBorder(new LineBorder(new Color(50, 152, 218),2));
        chooseFile.setForeground(new Color(50, 152, 218));

        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Select an image");
                jfc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "csv");
                jfc.addChoosableFileFilter(filter);

                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    System.out.println(jfc.getSelectedFile().getPath());
                    fileName = jfc.getSelectedFile().getPath();

                    filePath.setText(fileName);

                }

            }
        });


        //-----------------------------------findrelationship
        JButton findRelationship = new JButton("Find Relationship");
        findRelationship.setMnemonic(KeyEvent.VK_C);
        findRelationship.setFont(new Font("Lato", Font.BOLD, 13));
        findRelationship.setBackground(new Color(0, 174, 239));
        findRelationship.setPreferredSize(new Dimension(136, 35));
        findRelationship.setBorder(new LineBorder(new Color(0, 191, 243),2));
        findRelationship.setForeground(new Color(255, 255, 255));

        findRelationship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //for time of execution
                long startTime = System.nanoTime();

                List<String> ideas = new ArrayList();

                if (fileName == "") {
                    System.out.println("There are no file");
                } else {
                    ideas = readMyFile(fileName);

                    if (ideas.size() == 0) {
                        System.out.println("there are no ideas in the file");
                    }
                    else{
                        ArrayList<double[][]> Sideas = new ArrayList<>();
                        try {
                            Sideas = selectIdeas(ideas);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        rules R = null;
                        try {
                            R = new rules();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        for (int i = 0; i < ideas.size(); i++) {
                            System.out.println("iteration  : " + i);
                            for (int j = Sideas.get(i).length - 1; j > Sideas.get(i).length - 6; j--) {
                                System.out.println("----------------------------------------------------------------------------------------");
                             //   System.out.println("iteration j : " + j);
                              //  System.out.println("----------------------------------------------------------------------------------------");

                                int idea1 = (int) Sideas.get(i)[0][0];
                                int idea2 = (int) Sideas.get(i)[j][0];

                                String what_about1 = ideas.get(idea1).split(";")[0];
                                String what_about2 = ideas.get(idea2).split(";")[0];
                                String how_it_works1 = ideas.get(idea1).split(";")[1];
                                String how_it_works2 = ideas.get(idea2).split(";")[1];

                                System.out.println("idea1 part what: " + what_about1);
                                System.out.println("idea1 part how: " + how_it_works1);
                                System.out.println("idea2 part what: " + what_about2);
                                System.out.println("idea2 part how: " + how_it_works2);


                                triplesExtraction extract = new triplesExtraction();
                                ArrayList<String[]> triplesWhat1 = new ArrayList<String[]>();
                                ArrayList<String[]> triplesWhat2 = new ArrayList<String[]>();


                                ArrayList<String[]> triplesHow1 = new ArrayList<String[]>();
                                ArrayList<String[]> triplesHow2 = new ArrayList<String[]>();

                                try {
                                    triplesWhat1 = extract.Annotation_Extraction(what_about1);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                try {
                                    triplesWhat2 = extract.Annotation_Extraction(what_about2);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }

                                try {
                                    triplesHow1 = extract.Annotation_Extraction(how_it_works1);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                try {
                                    triplesHow2 = extract.Annotation_Extraction(how_it_works2);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }


                                try {
                                    result = R.whatRules(triplesWhat1, triplesWhat2, triplesHow1, triplesHow2);


                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (CorruptConfigFileException e1) {
                                    e1.printStackTrace();
                                }

                                wineModel.addWine(new Wine(ideas.get(idea1), ideas.get(idea2), result));

                                System.out.println("the result : " + result);

                                resultsList.add(ideas.get(idea1) + ";;;" + ideas.get(idea2) + ";;;" + result);
                                // writeMyFile(ideas.get(idea1) + ";;;"+ideas.get(idea2) + ";;;" +  result + "\n","Relationships.csv");

                                result = "";

                            }
                        }
                    }
                }
                resizeColumnWidth(ideatable);
                //for time of execution
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                double seconds = (double)duration / 1_000_000_000.0;
                time.setText("Time of execution: " + seconds + "s");
                System.out.println(String.valueOf(duration));

                nbIdeas.setText("Total number of ideas: " + ideas.size());
            }
        });


        //-----------------------------------clear
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
                time.setText("Time of execution: ");
                nbIdeas.setText("Total number of ideas: ");
                filePath.setText("");
                fileName="";
                if(wineModel.getRowCount()>0) {
                    for (int i = 0; i < resultsList.size(); i++) {
                        wineModel.removeRow(0);
                    }
                }

            }
        });

        //-------------------------------------validation button
        JButton validation = new JButton("Validation");
        validation.setMnemonic(KeyEvent.VK_C);
        validation.setFont(new Font("Lato", Font.BOLD, 13));
        validation.setBackground(new Color(0, 70, 138));
        validation.setPreferredSize(new Dimension(136, 35));
        validation.setBorder(new LineBorder(new Color(0, 70, 138),2));
        validation.setForeground(new Color(255, 255, 255));

        validation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new Challenge2(resultsList);  // Let the constructor do the job
                    }
                });            }
        });




        //***********************************panels
        //---------------------------------------------------north
        JPanel north = new JPanel(new GridBagLayout());
        north.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        north.add(chooseFile,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        m.setPreferredSize(new Dimension(19, 35));
        north.add(m,gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        north.add(filePath,gbc);

        //---------------------------------------------------south
        JPanel south =  new JPanel(new GridBagLayout());
        south.setBackground(Color.white);
        m2.setPreferredSize(new Dimension(193, 35));

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        south.add(findRelationship,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        south.add(clear,gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        south.add(m2,gbc);

        gbc.gridx=3;
        gbc.gridy=0;
        south.add(validation,gbc);

        //---------------------------------------------------center
        JPanel center =  new JPanel(new GridBagLayout());
        JPanel center2 =  new JPanel(new GridBagLayout());

        center.setBackground(Color.white);
        center2.setBackground(Color.white);

        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx = 0.5;
        center2.add(time,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.weightx = 0.5;
        center2.add(nbIdeas,gbc);

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
        cp.add(north,BorderLayout.NORTH);
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

    //**********************************reading file ******************************************************************
    public static List<String> readMyFile(String s){

        String fileName = s;
        String line = null;
        List<String> myStringArray = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                myStringArray.add(line);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
        return myStringArray ;
    }

    //**********************writing file **************************************************************************
    public static void writeMyFile(String s,String fileName){

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
            bufferedWriter.write("idea1; idea2; relationship");
            bufferedWriter.newLine();

            bufferedWriter.write(s);
            bufferedWriter.newLine();

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"  + fileName + "'");
        }
    }

    //******************************selecting ideas *******************************************************************
    public static ArrayList<double[][]> selectIdeas(List<String> fileIdeas) throws IOException {

        ArrayList<double[][]> ideas = new ArrayList<>();

        for (int i = 0 ; i < fileIdeas.size() ; i++){
            int x = 0 ;
            double[][] id = new double[fileIdeas.size()][2];
            id[x][0] = i;
            for (int j = i+1  ; j < fileIdeas.size() ; j++){
                x = x + 1;
                SimilarIdeas s = new SimilarIdeas(fileIdeas.get(i),fileIdeas.get(j));
                double d =  s.getCosineSimilarity() ;
                id[x][0] = j;
                id[x][1] = d;
                System.out.println(id[x][1]);
            }
            ideas.add(id);
            //System.out.println("-------------------------------------------------------------------------------");
        }

        for (int i = 0 ; i < ideas.size() ; i++){

            java.util.Arrays.sort(ideas.get(i), new java.util.Comparator<double[]>() {
                public int compare(double[] a, double[] b) {
                    return Double.compare(a[1], b[1]);
                }
            });

        }

        return ideas ;
    }



    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Challenge();  // Let the constructor do the job
            }
        });
    }
}






//---------------------------------------table
// a simple object that holds data about a particular wine
class Wine {
    private String  Idea1;
    private String  Idea2;
    private String   Relationship;

    public Wine(String Idea1, String Idea2, String Relationship) {
        this.Idea1 = Idea1;
        this.Idea2 = Idea2;
        this.Relationship = Relationship;
    }

    public String getIdea1()     { return Idea1; }
    public String getIdea2()  { return Idea2; }
    public String  getRelationship()    { return Relationship; }
   // public boolean getInStock() { return inStock; }

    public String toString() {
        return "[" + Idea1 + ", " + Idea2 + ", " + Relationship ;}//+ ", " + inStock + "]"; }
}

class WineTableModel extends AbstractTableModel {
    // holds the strings to be displayed in the column headers of our table
    final String[] columnNames = {"Idea1", "Idea2", "Relationship"};

    // holds the data types for all our columns
    final Class[] columnClasses = {String.class, String.class, String.class};

    // holds our data
    final Vector data = new Vector();

    // adds a row
    public void addWine(Wine w) {
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

    public Object getValueAt(int row, int col) {
        Wine wine = (Wine) data.elementAt(row);
        if (col == 0)      return wine.getIdea1();
        else if (col == 1) return wine.getIdea2();
        else if (col == 2) return wine.getRelationship();
        else return null;
    }

    public Object getValueAtRow(int row) {
        Wine wine = (Wine) data.elementAt(row);
        return wine;
    }

    public void removeRow(int row) {
        data.remove(row);
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }
}

class WineCellRenderer extends DefaultTableCellRenderer {


    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column)
    {

        Component cell = super.getTableCellRendererComponent
                (table, value, isSelected, hasFocus, row, column);

        table.setShowHorizontalLines(false);
        table.setGridColor(new Color(0, 191, 243));

        table.setRowHeight(30);

        table.getColumnModel().getColumn(0).setHeaderRenderer(new HeaderRenderer(new Color(145, 219, 249),new Color(0, 191, 243)));
        table.getColumnModel().getColumn(1).setHeaderRenderer(new HeaderRenderer(new Color(145, 219, 249),new Color(0, 191, 243)));
        table.getColumnModel().getColumn(2).setHeaderRenderer(new HeaderRenderer(new Color(245, 153, 157),new Color(241, 109, 126)));


        if (row % 2 != 0 && (column == 0 || column == 1)) {
            cell.setBackground(new Color(145, 219, 249));
        }else if(row % 2 != 0 && column == 2){
            cell.setBackground(new Color(245, 153, 157));
        }
        else if (row % 2 == 0){
            cell.setBackground(new Color(255, 255, 255));
        }

        return cell;

    }
}

 class HeaderRenderer extends JLabel implements TableCellRenderer {

    public HeaderRenderer( Color color, Color bor) {
        setFont(new Font("Microsoft Sans Serif", Font.BOLD, 12));
        setOpaque(true);
        setBackground(color);
        setBorder(new LineBorder(bor,2));
        setPreferredSize(new Dimension(100,30));
    }

     public HeaderRenderer(int width, int hight) {
         setPreferredSize(new Dimension(width,hight));
     }


     @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }

}
