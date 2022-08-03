import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Properties;

public class MyForm2 extends JFrame{
    private JButton printbutton;
    public JPanel panel1;
    private JTextField printernameField;
    private JTextField papernameField;
    private JButton printerpaperbutton;
    private JTextArea textArea1;
    private JButton savebutton;

    final String PRINTERKEY="PRINTERKEY";
    final String PAPERKEY="PAPERKEY";
    final String PROPFILENAME= "Setting.properties";
    MyForm2(){
        Properties prop = new Properties();
        Path propertfilePath= Paths.get(PROPFILENAME);
        try {
            if(!Files.exists(propertfilePath)) {
                Files.createFile(propertfilePath);
            }
            prop.load(Files.newBufferedReader(propertfilePath, StandardCharsets.UTF_8));
            String printername = prop.getProperty(PRINTERKEY);
            String papername = prop.getProperty(PAPERKEY);
            printernameField.setText(printername);
            papernameField.setText(papername);
        } catch (IOException e) {
            e.printStackTrace();
        }

        printbutton.setMnemonic(KeyEvent.VK_P);
        printerpaperbutton.setMnemonic(KeyEvent.VK_S);

        printbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        printerpaperbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                if(pj.printDialog()){
// http://h.keikun.info/~zemi/java/j2sdk/guide/jps/spec/printing2d.fm1.html
                    // https://codezine.jp/article/detail/11670
                    // https://codezine.jp/article/detail/11483
                }
//pj.pageDialog(new PageFormat());

            }
        });

        savebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                prop.setProperty(PRINTERKEY,printernameField.getText());
                prop.setProperty(PAPERKEY,papernameField.getText());
                //TODO save
            }
        });
        printbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Print2DPrinterJob sp = new Print2DPrinterJob();
            }
        });
    }

}
