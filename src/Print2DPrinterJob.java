import java.awt.font.TextAttribute;
import java.io.*;
import java.awt.*;
import java.net.*;
import java.awt.image.*;
import java.awt.print.*;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;

/*
https://docs.oracle.com/javase/jp/7/technotes/guides/jps/spec/printing2d.fm1.html#998764
https://docs.oracle.com/javase/jp/7/technotes/guides/jps/spec/appendix_2DPrinterJob.fm.html#997825

* */
public class Print2DPrinterJob implements Printable {

    public Print2DPrinterJob() {

        /* Construct the print request specification.
         * The print data is a Printable object.
         * the request additonally specifies a job name, 2 copies, and
         * landscape orientation of the media.
         */
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(OrientationRequested.LANDSCAPE);
        aset.add(new Copies(2));
        aset.add(new JobName("My job", null));

        /* Create a print job */
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(this);
        /* locate a print service that can handle the request */
        PrintService[] services =
                PrinterJob.lookupPrintServices();

        if (services.length > 0) {
            System.out.println("selected printer " + services[0].getName());
            try {
                pj.setPrintService(services[0]);
                pj.pageDialog(aset);
                if(pj.printDialog(aset)) {
                    pj.print(aset);
                }
            } catch (PrinterException pe) {
                System.err.println(pe);
            }
        }
    }

    public int print(Graphics g,PageFormat pf,int pageIndex) {

        if (pageIndex == 0) {
            Graphics2D g2d= (Graphics2D)g;

            AttributedString as1 = new AttributedString("MultiFont Text.");
            as1.addAttribute(TextAttribute.FONT,new Font(
                    "Dialog",Font.PLAIN,18),10,15);
            as1.addAttribute(TextAttribute.WIDTH,TextAttribute.WIDTH_EXTENDED);
            AttributedCharacterIterator ac1 = as1.getIterator();
            g2d.drawString(ac1,250,250);

//            g2d.translate(pf.getImageableX(), pf.getImageableY());
            g2d.setColor(Color.black);
            g2d.drawString("example string", 250, 250);
            g2d.fillRect(0, 0, 200, 200);
            return Printable.PAGE_EXISTS;
        } else {
            return Printable.NO_SUCH_PAGE;
        }
    }

//    public static void main(String arg[]) {
//
//        Print2DPrinterJob sp = new Print2DPrinterJob();
//    }

}

