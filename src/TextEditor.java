import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;

    TextEditor(){
        //Initialized this frame
        frame = new JFrame();

        //Initialize Menu
        menuBar = new JMenuBar();

        file = new JMenu("File");
        edit  = new JMenu("Edit");

        menuBar.add(file);
        menuBar.add(edit);

        //Initialize Text area
        textArea = new JTextArea();

        //Initialize menu items
        newFile = new JMenuItem("New file");
        openFile = new JMenuItem("Open file");
        saveFile = new JMenuItem("Save file");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));

        panel.add(scrollPane);
        frame.add(panel);


        frame.setJMenuBar(menuBar);
        frame.setBounds(100, 100, 400 , 400);
        frame.setVisible(true);
        frame.setTitle("Text Editor");

    }
    public static void main(String[] args){
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
        if(e.getSource()==saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            fileChooser.setApproveButtonText("Save");
            int chooseOption = fileChooser.showSaveDialog(null);

            if(chooseOption== JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                String filePath = file.getPath();
                try{
                    BufferedWriter outfile = null;
                    outfile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                }catch (Exception exception){
                    System.out.println(exception);
                }
            }
        }
        if(e.getSource()==openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption== JFileChooser.APPROVE_OPTION){
                File file  = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    String intermediate = "", output = "";
                    while((intermediate = bufferedReader.readLine())!=null){
                        output += intermediate+ "\n";
                    }
                    textArea.setText(output);

                }catch (Exception exception){
                    System.out.println(exception);
                }
            }
        }
        if(e.getSource()==cut){
            textArea.cut();
        }
        if(e.getSource()==copy){
            textArea.copy();
        }
        if(e.getSource()==paste){
            textArea.paste();
        }
        if(e.getSource()==selectAll){
            textArea.selectAll();
        }
        if(e.getSource()==close){
            System.exit(0);
        }

    }
}