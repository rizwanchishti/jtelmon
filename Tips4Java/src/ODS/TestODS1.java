/*
 http://incubator.apache.org/odftoolkit/simple/gettingstartguide.html
 http://www.langintro.com/odfdom_tutorials/
 * 
 */

package ODS;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author a
 */
public class TestODS1 {


   public static void main(String args[]) {
        try {
            // Load the file.
            // Load the file.
            File file = new File("invoice.ods");
            final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);
            // Change date.
            sheet.getCellAt("I10").setValue(new Date());
            // Change strings.
            sheet.setValueAt("Filling test", 1, 1);
            sheet.getCellAt("B27").setValue("On site support");
            // Change number.
            sheet.getCellAt("F24").setValue(3);
            // Or better yet use a named range
            // (relative to the first cell of the range, wherever it might be).
            // sheet.getSpreadSheet().getTableModel("Products").setValueAt(1, 5, 4);
            // Save to file and open it.
            File outputFile = new File("fillingTest.ods");
            OOUtils.open(sheet.getSpreadSheet().saveAs(outputFile));
        } catch (IOException ex) {
            Logger.getLogger(TestODS1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    


}
