package CE203;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CE203_1905188_ASS1 {

    public static void main(String[] args) {
        Frame f = new Frame();              // Makes a frame
        f.setSize(900,800);     // sets the frame  size
        f.setVisible(true);                 // sets it to visible

    }
}
class ID implements Comparable<ID>{


    // id attribute
    int id = 000000;
    boolean f =false;

    // constructor should input an ID as a String or int and set it to the attribute id - to be modified
    public ID(String ID)
    {
        if(ID.length() == 6)
        {
            for (int i = 0; i<6;i++)
            {
                if(ID.charAt(i) >='0' && ID.charAt(i) <='9')
                {
                    this.id = Integer.parseInt(ID);
                }
                else
                {
                    f=true;
                }
            }
        }
        else
        {
            f = true;
        }

    }


    // gets a stored ID
    public int getID() {
        return id;
    }


    // sets the input parameter to an ID this can be modified to input a string in which case you will need to convert
    // the parameter to an int
    public void setID(String inputID) {
        id = Integer.parseInt(inputID);
    }


    @Override
    // method used for comparing ID objects based on stored ids
    public int compareTo(ID o) {
        boolean c = true;
        int compareID = o.getID();
        if(this.getID()==compareID) {
            c= true;
        }
        else
            c= false;


        return 0;

    }

    // outputs a string representation of the object
    public String toString()
    {
        return ("ID = "+id);
    }

}

class Frame extends JFrame {
    private JTextField input,R,G,B;         //declaring text fields required
    private JTextArea displayID;            //this is where the output will be outputted


    Color color;                            //used to set colour of text

    ArrayList<ID> idlist;                   //list that contains all the ids


    public Frame()
    {
        idlist = new ArrayList<ID>();                       //creates a arraylist
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //program terminate son close
        setTitle("Assignment-1");                           // sets title

        JLabel prompt = new JLabel("Type ID");
        JLabel r = new JLabel("R");
        JLabel g = new JLabel("G");
        JLabel b = new JLabel("B");


        input = new JTextField(10);
        R = new JTextField(10);
        G = new JTextField(10);
        B = new JTextField(10);


        JButton ADDID = new JButton("ADD ID");
        JButton Display = new JButton("Display");
        JButton Sort = new JButton("Sort");
        JButton RemoveID = new JButton("Remove ID");
        JButton Clear = new JButton("Clear All");
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();
        JPanel TextArea = new JPanel();


        TextPanel.add(prompt);
        TextPanel.add(input);
        TextPanel.add(r);
        TextPanel.add(R);
        TextPanel.add(g);
        TextPanel.add(G);
        TextPanel.add(b);
        TextPanel.add(B);

        ButtonPanel.add(ADDID);
        ButtonPanel.add(Display);
        ButtonPanel.add(Sort);
        ButtonPanel.add(RemoveID);
        ButtonPanel.add(Clear);


        displayID = new JTextArea(400,200);                  //set size for the textarea
        JScrollPane dispPane = new JScrollPane(displayID);               //adds a scroll pane for text area
        dispPane.setPreferredSize(new Dimension(800,650));
        TextArea.add(dispPane,BorderLayout.CENTER);
        //add panels
        add(ButtonPanel, BorderLayout.SOUTH);
        add(TextArea, BorderLayout.CENTER);
        add(TextPanel, BorderLayout.NORTH);


        input.addActionListener(new ActionListener() {                               //action listner for the id input field

            public void actionPerformed(ActionEvent e) {
                try {
                    if (input.getText().length() == 6)                                            //checks for the length
                        displayID.setText(" " + Integer.parseInt(input.getText()) + " ");  // if lenngth is 6 displays it
                    else if (input.getText().length() != 6) {                                           // else invalid id
                        JOptionPane.showMessageDialog(null,"Invalid ID, should contain exactly 6 numbers");
                        input.setText("");
                    }
                }
                catch (NumberFormatException ex) {                         //checks whether the input is a number ror not
                    JOptionPane.showMessageDialog(null, "The id is not valid id");
                }
            }
        });
        ADDID.addActionListener(new ActionListener() {                      // action listner for add id button
            public void actionPerformed(ActionEvent e) {
                ID id = new ID(input.getText());//gets input
                try {

                    if (e.getSource() == ADDID) {// if clicked then
                        if (input.getText().length() == 6) {                         //checks for number of characters in id
                            idlist.add(id);// if 6 then adds id
                        }

                        else
                            JOptionPane.showMessageDialog(null,"Invalid ID"); // else return invlaid id
                        for(ID i : idlist)
                            displayID.setText(i + " was added \n");
                        input.setText("");
                        input.requestFocus();
                    }



                }
                catch (NumberFormatException ex) // checks whether id is a number or not
                {

                    JOptionPane.showMessageDialog(null, "Invalid ID, enter number");
                }
            }


        });
        Display.addActionListener(new ActionListener() {                    // action listner for display button
            public void actionPerformed(ActionEvent e) {

                displayID.setText("");
                try{
                    String red = R.getText();                                  //gets value for red
                    String green = G.getText();                                //gets value for green
                    String blue = B.getText();                                 ////gets value for blue

                    int r = Integer.parseInt(red.trim());
                    int g = Integer.parseInt(green.trim());
                    int b = Integer.parseInt(blue.trim());
                    
                    if(r >= 0 && r <=255 && g>=0 && g<=255 && b>=0 && b<=255) {  //checks for the valid value for r,g,b
                        color = new Color(r, g, b);
                        if (e.getSource() == Display)
                            displayID.setForeground(color);                      //sets colour
                        for (ID i : idlist)
                            displayID.append(i + "\n");
                        displayID.repaint();                                    //repaints text
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "invalid value for r,g,b");
                    }

                }
                catch (NumberFormatException n)
                {

                    JOptionPane.showMessageDialog(null,"invalid value, enter r,g,b values");
                }


            }

        });

        Sort.addActionListener(new ActionListener() {                             // action listener for sort button
            public void actionPerformed(ActionEvent e) {
                Sort.setText("");
                Collections.sort(idlist);             //sorts in ascending order
                displayID.setText(idlist + "");
                input.setText("");
                input.requestFocus();
            }
        });

        //removes the top most id in the list
        RemoveID.addActionListener(new ActionListener() {                        //action listener for remove button
            public void actionPerformed(ActionEvent e) {

                try {
                    for (ID i : idlist ) {
                        if (idlist.contains(i)) {
                            idlist.remove(i);
                            displayID.setText(i + "was removed");

                        }


                    }
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid id");
                }
            }
        });
        Clear.addActionListener(new ActionListener() {                        //clears the list
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==Clear)
                {
                    idlist.clear();
                }
                displayID.setText("");
                input.setText("");
                input.requestFocus();
            }
        });

    }


}

