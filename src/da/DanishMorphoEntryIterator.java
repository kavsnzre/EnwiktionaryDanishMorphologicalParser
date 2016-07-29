package it.uniroma1.lcl.wimmp.da;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import it.uniroma1.lcl.wimmp.MorphoEntry;
import it.uniroma1.lcl.wimmp.MorphoEntryIterator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DanishMorphoEntryIterator extends MorphoEntryIterator {

    private Iterator<MorphoEntry> myMorphoEntriesIterator;

    /**
     * @param dumps
     */
    /* The following constructor parse the whole dump and:
    1) extract all Danish MorphoEntry contained in it
    2) put them in the List<MorphoEntry> myMorphoEntries.
    
    In particolar for each content of the tag "text" this method:
    1) invokes myDanishPattern.checkMorphoEntry() in order to obtain all Danish 
    MorphoEntry contained in it organized in a local List<MorphoEntry>.
    2) By means of the addAll() method, all the MorphoEntry instances are
    put into the main List<MorphoEntry> myMorphoEntries
    
    Finally, after having extracted all Danish MorphoEntry of all tags "text" of 
    the dump, this method creates an iterator of myMorphoEntries
     */
    public DanishMorphoEntryIterator(String[] dumps) {
        super(dumps);
        Constants.allEntriesCount = 0;
        Constants.consideredEntriesCount = 0;
        Constants.allLemmasPlusForms = 0;

        List<MorphoEntry> myMorphoEntries = new LinkedList<>();

        try {
            String tagTitle = null;
            String tagText;
            DanishPatterns myDanishPattern;
            XMLStreamReader daReader;

            System.out.println("Starting parsing of all the dump, please wait...");

            daReader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(dumps[0]));
            while (daReader.hasNext()) {

                if (daReader.next() == XMLStreamConstants.START_ELEMENT) {

                    switch (daReader.getLocalName()) {
                        case "title":
                            tagTitle = daReader.getElementText();
                            break;
                        case "text":
                            tagText = daReader.getElementText();

                            myDanishPattern = new DanishPatterns(tagTitle, tagText);
                            if (myDanishPattern.isDanish()) {

                                myMorphoEntries.addAll(myDanishPattern.checkMorphoEntry());
                            }
                            break;
                    }
                }
            }
            daReader.close();

            myMorphoEntries.addAll(DanishPatterns.getAllPronouns());
            Constants.allEntriesCount += 36;
            Constants.consideredEntriesCount += 36;
            Constants.allLemmasPlusForms += 103;

            System.out.println("TOT lemmas: " + Constants.allEntriesCount);
            System.out.println("Covered lemmas: " + Constants.consideredEntriesCount);
            System.out.println("Coverage: " + ((double) Constants.consideredEntriesCount) / ((double) Constants.allEntriesCount));
            System.out.println("TOT Covered Lemmas + Morphological Forms: " + Constants.allLemmasPlusForms);

            myMorphoEntriesIterator = myMorphoEntries.iterator();
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(DanishMorphoEntryIterator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean hasNext() {
        return myMorphoEntriesIterator.hasNext();
    }

    @Override
    public MorphoEntry next() {
        return myMorphoEntriesIterator.next();
    }
}
