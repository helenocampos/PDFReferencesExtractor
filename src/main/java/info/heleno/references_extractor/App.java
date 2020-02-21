/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.heleno.references_extractor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.edu.icm.cermine.ContentExtractor;
import pl.edu.icm.cermine.bibref.model.BibEntry;
import pl.edu.icm.cermine.bibref.model.BibEntryFieldType;
import pl.edu.icm.cermine.exception.AnalysisException;

/**
 *
 * @author Heleno
 */
public class App {

    public static String getField(List<String> values) {
        String field = "";
        for (String value : values) {
            field += value;
        }
        return field;
    }

    public static void main(String[] args) {
        try {
            if (args.length >= 2) {

                ContentExtractor extractor = new ContentExtractor();
                FileInputStream inputStream = new FileInputStream(args[1]);
                extractor.setPDF(inputStream);
                List<BibEntry> references = extractor.getReferences();
                if (args[0].equals("short")) {
                    for (BibEntry entry : references) {
                        String title = getField(entry.getAllFieldValues(BibEntryFieldType.TITLE));
                        String year = entry.getFirstFieldValue(BibEntryFieldType.YEAR);
                        String publication = getField(entry.getAllFieldValues(BibEntryFieldType.JOURNAL));
                        System.out.println(title + ";" + year + ";" + publication);
                    }
                } else if (args[0].equals("full")) {
                    for (BibEntry entry : references) {
                        System.out.println(entry.toBibTeX());
                    }
                }
            }

        } catch (AnalysisException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
