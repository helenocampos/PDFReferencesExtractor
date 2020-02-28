/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.heleno.references_extractor;

import java.io.File;
import java.io.FileInputStream;
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

    public static void printFullReferences(List<BibEntry> references) {
        for (BibEntry entry : references) {
            System.out.println(entry.toBibTeX());
        }
    }

    public static void printShortReferences(List<BibEntry> references, String source) {
        System.out.println("Source;Title;Year;Publication");
        source = source.replaceFirst("[.][^.]+$", "");  //removes file name extension
        for (BibEntry entry : references) {
            String title = getField(entry.getAllFieldValues(BibEntryFieldType.TITLE));
            String year = entry.getFirstFieldValue(BibEntryFieldType.YEAR);
            String publication = getField(entry.getAllFieldValues(BibEntryFieldType.JOURNAL));
            System.out.println("\"" + source + "\";"+"\"" + title + "\"" + ";" + "\"" + year + "\"" + ";" + "\"" + publication + "\"");
        }
    }

    public static void extractFile(File file, String mode) {
        try {
            ContentExtractor extractor = new ContentExtractor();
            FileInputStream inputStream = new FileInputStream(file);
            extractor.setPDF(inputStream);
            List<BibEntry> references = extractor.getReferences();
            if (mode.equals("short")) {
                printShortReferences(references, file.getName());
            } else if (mode.equals("full")) {
                printFullReferences(references);
            }

        } catch (AnalysisException | IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            File file = new File(args[1]);
            if (file.exists()) {
                if (file.isDirectory()) {
                    for(File subfile: file.listFiles()){
                        extractFile(subfile, args[0]);
                    }
                } else {
                    extractFile(new File(args[1]), args[0]);
                }
            }

        }
    }
}
