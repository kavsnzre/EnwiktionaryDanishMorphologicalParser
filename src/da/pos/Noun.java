/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniroma1.lcl.wimmp.da.pos;

import it.uniroma1.lcl.wimmp.da.Constants;
import it.uniroma1.lcl.wimmp.da.DanishMorphoRuleNoun;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author michele
 */
public class Noun {

    private final String lemma;
    private String template;

    public Noun(String lemma, String template) {
        this.lemma = lemma;
        this.template = template;
    }

    public DanishMorphoRuleNoun createAllFlections() {
        String singularDefinite = null;
        String pluralIndefinite = null;
        String pluralDefinite = null;
        String genitiveSingularIndefinite = null;
        String genitiveSingularDefinite = null;
        String genitivePluralIndefinite = null;
        String genitivePluralDefinite = null;

        if (isRegularNounTemplate()) {
            Constants.allEntriesCount++;
            Constants.consideredEntriesCount++;

            if (isPluralOnly()) {
                System.out.println(template);
                // You just do nothing
            } else if (isSingularOnly()) {
                System.out.println(template);
                if (template.matches("(.)*(\\|g(2)?=(n|c)){1,2}(.)*")) {
                    String field;
                    Scanner scanTemplate = new Scanner(template);
                    scanTemplate.useDelimiter("\\|");
                    while (scanTemplate.hasNext()) {
                        field = scanTemplate.next();
                        if (field.matches("g(2)?=(n|c)")) {
                            if (field.endsWith("c")) {
                                if (lemma.endsWith("e")) {
                                    singularDefinite = lemma + "n";
                                } else {
                                    singularDefinite = lemma + "en";
                                }
                            } else if (field.endsWith("n")) {
                                if (lemma.endsWith("e")) {
                                    singularDefinite = lemma + "t";
                                } else {
                                    singularDefinite = lemma + "et";
                                }
                            }
                        }
                    }
                }
            } else if (template.startsWith("{{da-noun-infl|")) {
                System.out.println(template);
                template = template.replaceAll("(\\{\\{da-noun-infl)|(\\}\\})", "");
                Scanner scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");

                String field;
                Scanner scanLabeledField;
                List<String> unlabeledFields = new LinkedList<>();
                HashMap<String, String> labeledFields = new HashMap<>();
                while (scanTemplate.hasNext()) {
                    field = scanTemplate.next();
                    if (field.matches(".*=.*")) {
                        // is a labeled field
                        scanLabeledField = new Scanner(field);
                        scanLabeledField.useDelimiter("=");
                        labeledFields.put(scanLabeledField.next(), scanLabeledField.next());
                        // e.g. 
                        // stem=kropp
                        // first time scanLabeledField.next() returns "stem"
                        // second time scanLabeledField.next() returns "kropp"
                    } else {
                        unlabeledFields.add(field);
                    }
                }

                String stem = lemma;
                if ((labeledFields.keySet()).contains("stem")) {
                    stem = labeledFields.get("stem");
                }
                boolean uncountable = false;
                if ((labeledFields.keySet()).contains("n") && (labeledFields.get("n")).equals("sg")) {
                    uncountable = true;
                }

                // extract singular definite
                if ((unlabeledFields.get(0)).matches("en|n|et|t")) {
                    singularDefinite = stem + (unlabeledFields.get(0));
                } else {
                    singularDefinite = unlabeledFields.get(0);
                }
                unlabeledFields.remove(0);
                if ((labeledFields.keySet()).contains("sg-def-2")) {
                    singularDefinite += "/" + (labeledFields.get("sg-def-2"));
                }

                // calculate singular genitives
                String sgIndef;
                try (Scanner scanSingIndef = new Scanner(lemma)) {
                    scanSingIndef.useDelimiter("/");
                    genitiveSingularIndefinite = "";
                    while (scanSingIndef.hasNext()) {
                        sgIndef = scanSingIndef.next();
                        if (sgIndef.matches(".*(s|z|x)")) {
                            genitiveSingularIndefinite += sgIndef + "'/";
                        } else {
                            genitiveSingularIndefinite += sgIndef + "s/";
                        }
                    }
                    if (genitiveSingularIndefinite.equals("")) {
                        genitiveSingularIndefinite = null;
                    } else {
                        genitiveSingularIndefinite = genitiveSingularIndefinite.substring(0, genitiveSingularIndefinite.lastIndexOf("/"));
                    }
                }

                String sgDef;
                try (Scanner scanSingDef = new Scanner(singularDefinite)) {
                    scanSingDef.useDelimiter("/");
                    genitiveSingularDefinite = "";
                    while (scanSingDef.hasNext()) {
                        sgDef = scanSingDef.next();
                        if (sgDef.matches(".*(s|z|x)")) {
                            genitiveSingularDefinite += sgDef + "'/";
                        } else {
                            genitiveSingularDefinite += sgDef + "s/";
                        }
                    }
                    if (genitiveSingularDefinite.equals("")) {
                        genitiveSingularDefinite = null;
                    } else {
                        genitiveSingularDefinite = genitiveSingularDefinite.substring(0, genitiveSingularDefinite.lastIndexOf("/"));
                    }
                }

                if (!uncountable) {
                    // extract plural indefinite
                    if (unlabeledFields.isEmpty() || (unlabeledFields.get(0)).matches("|-")) {
                        pluralIndefinite = lemma;
                    } else if ((unlabeledFields.get(0)).matches("er|r|e|s")) {
                        pluralIndefinite = stem + (unlabeledFields.get(0));
                        unlabeledFields.remove(0);
                    } else {
                        pluralIndefinite = unlabeledFields.get(0);
                        unlabeledFields.remove(0);
                    }
                    if ((labeledFields.keySet()).contains("pl-indef-2")) {
                        pluralIndefinite += "/" + (labeledFields.get("pl-indef-2"));
                    }

                    // calculate plural definite (custom rule)
                    String plIndef;
                    try (Scanner scanPlIndef = new Scanner(pluralIndefinite)) {
                        scanPlIndef.useDelimiter("/");
                        pluralDefinite = "";
                        while (scanPlIndef.hasNext()) {
                            plIndef = scanPlIndef.next();
                            if (plIndef.matches("(.*)e(r)?")) {
                                pluralDefinite += plIndef + "ne/";
                            } else {
                                pluralDefinite += plIndef + "ene/";
                            }
                        }
                    }
                    if (pluralDefinite.equals("")) {
                        pluralDefinite = null;
                    } else {
                        pluralDefinite = pluralDefinite.substring(0, pluralDefinite.lastIndexOf("/"));
                    }

                    // calculate plural genitives
                    try (Scanner scanPlIndef = new Scanner(pluralIndefinite)) {
                        scanPlIndef.useDelimiter("/");
                        genitivePluralIndefinite = "";
                        while (scanPlIndef.hasNext()) {
                            plIndef = scanPlIndef.next();
                            if (plIndef.matches(".*(s|z|x)")) {
                                genitivePluralIndefinite += plIndef + "'/";
                            } else {
                                genitivePluralIndefinite += plIndef + "s/";
                            }
                        }
                    }
                    if (genitivePluralIndefinite.equals("")) {
                        genitivePluralIndefinite = null;
                    } else {
                        genitivePluralIndefinite = genitivePluralIndefinite.substring(0, genitivePluralIndefinite.lastIndexOf("/"));
                    }
                    String plDef;
                    try (Scanner scanPlDef = new Scanner(pluralDefinite)) {
                        scanPlDef.useDelimiter("/");
                        genitivePluralDefinite = "";
                        while (scanPlDef.hasNext()) {
                            plDef = scanPlDef.next();
                            if (plDef.matches(".*(s|z|x)")) {
                                genitivePluralDefinite += plDef + "'/";
                            } else {
                                genitivePluralDefinite += plDef + "s/";
                            }
                        }
                    }
                    if (genitivePluralDefinite.equals("")) {
                        genitivePluralDefinite = null;
                    } else {
                        genitivePluralDefinite = genitivePluralDefinite.substring(0, genitivePluralDefinite.lastIndexOf("/"));
                    }
                }
            } else if (template.matches("\\{\\{da-noun(\\|(g(2)?\\=(n|c)))+\\}\\}")) {
                System.out.println(template);
                template = template.replaceAll("(\\{\\{da-noun)|(\\}\\})", "");
                Scanner scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");
                String gender;

                // extracting singulars
                while (scanTemplate.hasNext()) {
                    gender = (scanTemplate.next()).replaceAll("g(2)?\\=", "");
                    if (gender.equals("n")) {
                        if (lemma.endsWith("e")) {
                            singularDefinite = lemma + "t";
                        } else {
                            singularDefinite = lemma + "et";
                        }
                    } else // gender.equals("c")==true
                     if (lemma.endsWith("e")) {
                            singularDefinite = lemma + "n";
                        } else {
                            singularDefinite = lemma + "en";
                        }
                }
                // calculate singular genitives
                String sgIndef;
                try (Scanner scanSingIndef = new Scanner(lemma)) {
                    scanSingIndef.useDelimiter("/");
                    genitiveSingularIndefinite = "";
                    while (scanSingIndef.hasNext()) {
                        sgIndef = scanSingIndef.next();
                        if (sgIndef.matches(".*(s|z|x)")) {
                            genitiveSingularIndefinite += sgIndef + "'/";
                        } else {
                            genitiveSingularIndefinite += sgIndef + "s/";
                        }
                    }
                    if (genitiveSingularIndefinite.equals("")) {
                        genitiveSingularIndefinite = null;
                    } else {
                        genitiveSingularIndefinite = genitiveSingularIndefinite.substring(0, genitiveSingularIndefinite.lastIndexOf("/"));
                    }
                }

                if (singularDefinite != null) {
                    String sgDef;
                    try (Scanner scanSingDef = new Scanner(singularDefinite)) {
                        scanSingDef.useDelimiter("/");
                        genitiveSingularDefinite = "";
                        while (scanSingDef.hasNext()) {
                            sgDef = scanSingDef.next();
                            if (sgDef.matches(".*(s|z|x)")) {
                                genitiveSingularDefinite += sgDef + "'/";
                            } else {
                                genitiveSingularDefinite += sgDef + "s/";
                            }
                        }
                        if (genitiveSingularDefinite.equals("")) {
                            genitiveSingularDefinite = null;
                        } else {
                            genitiveSingularDefinite = genitiveSingularDefinite.substring(0, genitiveSingularDefinite.lastIndexOf("/"));
                        }
                    }
                }
                // We have not infos for plural forms
            } else if (template.startsWith("{{da-noun|")) {
                System.out.println(template);
                template = template.replaceAll("(\\{\\{da-noun)|(\\}\\})", "");
                Scanner scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");

                String field;
                Scanner scanLabeledField;
                List<String> unlabeledFields = new LinkedList<>();
                HashMap<String, String> labeledFields = new HashMap<>();
                while (scanTemplate.hasNext()) {
                    field = scanTemplate.next();
                    if (field.matches(".*=.*")) {
                        // is a labeled field
                        scanLabeledField = new Scanner(field);
                        scanLabeledField.useDelimiter("=");
                        labeledFields.put(scanLabeledField.next(), scanLabeledField.next());
                        // e.g. 
                        // stem=kropp
                        // first time scanLabeledField.next() returns "stem"
                        // second time scanLabeledField.next() returns "kropp"
                    } else {
                        unlabeledFields.add(field);
                    }
                }
                String stem = lemma;
                if ((labeledFields.keySet()).contains("stem")) {
                    stem = labeledFields.get("stem");
                }
                boolean uncountable = false;
                if (unlabeledFields.size() >= 2 && (unlabeledFields.get(1)).equals("-")) {
                    uncountable = true;
                }

                if (!unlabeledFields.isEmpty()) {
                    // extract singular definite
                    if ((unlabeledFields.get(0)).matches("en|n|et|t")) {
                        singularDefinite = stem + (unlabeledFields.get(0));
                    } else if ((unlabeledFields.get(0)).equals("-")) {
                        singularDefinite = lemma;
                        uncountable = true;
                        // e.g. {{da-noun|head=[[det]] [[med]] [[småt]]|-}}
                    } else {
                        singularDefinite = unlabeledFields.get(0);
                    }
                    unlabeledFields.remove(0);
                    if ((labeledFields.keySet()).contains("sg-def-2")) {
                        singularDefinite += "/" + (labeledFields.get("sg-def-2"));
                    }

                    // calculate singular indefinite genitive
                    String sgIndef;
                    try (Scanner scanSingIndef = new Scanner(lemma)) {
                        scanSingIndef.useDelimiter("/");
                        genitiveSingularIndefinite = "";
                        while (scanSingIndef.hasNext()) {
                            sgIndef = scanSingIndef.next();
                            if (sgIndef.matches(".*(s|z|x)")) {
                                genitiveSingularIndefinite += sgIndef + "'/";
                            } else {
                                genitiveSingularIndefinite += sgIndef + "s/";
                            }
                        }
                        if (genitiveSingularIndefinite.equals("")) {
                            genitiveSingularIndefinite = null;
                        } else {
                            genitiveSingularIndefinite = genitiveSingularIndefinite.substring(0, genitiveSingularIndefinite.lastIndexOf("/"));
                        }
                    }

                    if (!uncountable) {
                        // extract plural indefinite

                        if (unlabeledFields.isEmpty() || (unlabeledFields.get(0)).equals("")) {
                            pluralIndefinite = lemma;
                        } else if ((unlabeledFields.get(0)).matches("er|r|e|s")) {
                            pluralIndefinite = stem + (unlabeledFields.get(0));
                            unlabeledFields.remove(0);
                        } else {
                            pluralIndefinite = unlabeledFields.get(0);
                            unlabeledFields.remove(0);
                        }
                        if ((labeledFields.keySet()).contains("pl-indef-2")) {
                            pluralIndefinite += "/" + (labeledFields.get("pl-indef-2"));
                        }

                        // calculate plural definite (custom rule) 
                        String plIndef;
                        try (Scanner scanPlIndef = new Scanner(pluralIndefinite)) {
                            scanPlIndef.useDelimiter("/");
                            pluralDefinite = "";
                            while (scanPlIndef.hasNext()) {
                                plIndef = scanPlIndef.next();
                                if (plIndef.matches("(.*)e(r)?")) {
                                    pluralDefinite += plIndef + "ne/";
                                } else {
                                    pluralDefinite += plIndef + "ene/";
                                }
                            }
                            if (pluralDefinite.equals("")) {
                                pluralDefinite = null;
                            } else {
                                pluralDefinite = pluralDefinite.substring(0, pluralDefinite.lastIndexOf("/"));
                            }
                        }

                        // calculate plural genitives
                        String plurIndef;
                        try (Scanner scanPlurIndef = new Scanner(pluralIndefinite)) {
                            scanPlurIndef.useDelimiter("/");
                            genitivePluralIndefinite = "";
                            while (scanPlurIndef.hasNext()) {
                                plurIndef = scanPlurIndef.next();
                                if (plurIndef.matches(".*(s|z|x)")) {
                                    genitivePluralIndefinite += plurIndef + "'/";
                                } else {
                                    genitivePluralIndefinite += plurIndef + "s/";
                                }
                            }
                        }
                        if (genitivePluralIndefinite.equals("")) {
                            genitivePluralIndefinite = null;
                        } else {
                            genitivePluralIndefinite = genitivePluralIndefinite.substring(0, genitivePluralIndefinite.lastIndexOf("/"));
                        }
                        String plDef;
                        try (Scanner scanPlDef = new Scanner(pluralDefinite)) {
                            scanPlDef.useDelimiter("/");
                            genitivePluralDefinite = "";
                            while (scanPlDef.hasNext()) {
                                plDef = scanPlDef.next();
                                if (plDef.matches(".*(s|z|x)")) {
                                    genitivePluralDefinite += plDef + "'/";
                                } else {
                                    genitivePluralDefinite += plDef + "s/";
                                }
                            }
                        }
                        if (genitivePluralDefinite.equals("")) {
                            genitivePluralDefinite = null;
                        } else {
                            genitivePluralDefinite = genitivePluralDefinite.substring(0, genitivePluralDefinite.lastIndexOf("/"));
                        }

                    }
                }

                if (singularDefinite != null) {
                    String sgDef;
                    try (Scanner scanSingDef = new Scanner(singularDefinite)) {
                        scanSingDef.useDelimiter("/");
                        genitiveSingularDefinite = "";
                        while (scanSingDef.hasNext()) {
                            sgDef = scanSingDef.next();
                            if (sgDef.matches(".*(s|z|x)")) {
                                genitiveSingularDefinite += sgDef + "'/";
                            } else {
                                genitiveSingularDefinite += sgDef + "s/";
                            }
                        }
                        if (genitiveSingularDefinite.equals("")) {
                            genitiveSingularDefinite = null;
                        } else {
                            genitiveSingularDefinite = genitiveSingularDefinite.substring(0, genitiveSingularDefinite.lastIndexOf("/"));
                        }
                    }
                }

            } else if (template.matches("\\{\\{head\\|da\\|noun(\\|(g(2)?\\=(n|c)))+\\}\\}")) {
                System.out.println(template);
                template = template.replaceAll("(\\{\\{head\\|da\\|noun)|(\\}\\})", "");
                Scanner scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");
                String gender;

                // extracting singulars
                while (scanTemplate.hasNext()) {
                    gender = (scanTemplate.next()).replaceAll("g(2)?\\=", "");
                    if (gender.equals("n")) {
                        if (lemma.endsWith("e")) {
                            singularDefinite = lemma + "t";
                        } else {
                            singularDefinite = lemma + "et";
                        }
                    } else // gender.equals("c")==true
                     if (lemma.endsWith("e")) {
                            singularDefinite = lemma + "n";
                        } else {
                            singularDefinite = lemma + "en";
                        }
                }
                // calculate singular genitives
                String sgIndef;
                try (Scanner scanSingIndef = new Scanner(lemma)) {
                    scanSingIndef.useDelimiter("/");
                    genitiveSingularIndefinite = "";
                    while (scanSingIndef.hasNext()) {
                        sgIndef = scanSingIndef.next();
                        if (sgIndef.matches(".*(s|z|x)")) {
                            genitiveSingularIndefinite += sgIndef + "'/";
                        } else {
                            genitiveSingularIndefinite += sgIndef + "s/";
                        }
                    }
                    if (genitiveSingularIndefinite.equals("")) {
                        genitiveSingularIndefinite = null;
                    } else {
                        genitiveSingularIndefinite = genitiveSingularIndefinite.substring(0, genitiveSingularIndefinite.lastIndexOf("/"));
                    }
                }
                if (singularDefinite != null) {
                    String sgDef;
                    try (Scanner scanSingDef = new Scanner(singularDefinite)) {
                        scanSingDef.useDelimiter("/");
                        genitiveSingularDefinite = "";
                        while (scanSingDef.hasNext()) {
                            sgDef = scanSingDef.next();
                            if (sgDef.matches(".*(s|z|x)")) {
                                genitiveSingularDefinite += sgDef + "'/";
                            } else {
                                genitiveSingularDefinite += sgDef + "s/";
                            }
                        }
                        if (genitiveSingularDefinite.equals("")) {
                            genitiveSingularDefinite = null;
                        } else {
                            genitiveSingularDefinite = genitiveSingularDefinite.substring(0, genitiveSingularDefinite.lastIndexOf("/"));
                        }
                    }
                }

                // We have not infos for plural forms
            } else if (template.matches("\\{\\{head\\|da\\|noun(\\|((g(2)?=(n|c))|("
                    + "definite singular|singular definite|definite|"
                    + "definite plural|plural definite|"
                    + "indefinite plural|plural indefinite|plural|"
                    + "genitive|"
                    + "abbreviation|"
                    + "or)\\|[^\\|=]*))+")) {
                template = template.replaceAll("\\[|\\]", "");
                System.out.println(template);
                template = template.replaceAll("(\\{\\{head\\|da\\|noun)|(\\}\\})", "");
                Scanner scanTemplate = new Scanner(template);
                scanTemplate.useDelimiter("\\|");

                String field;
                String lastLabel = null;
                Scanner scanLabeledField;
                HashMap<String, String> fields = new HashMap<>();
                while (scanTemplate.hasNext()) {
                    field = scanTemplate.next();
                    if (field.matches(".*=.*")) {
                        // is a labeled field
                        scanLabeledField = new Scanner(field);
                        scanLabeledField.useDelimiter("=");
                        fields.put(scanLabeledField.next(), scanLabeledField.next());
                        // e.g. 
                        // stem=kropp
                        // first time scanLabeledField.next() returns "stem"
                        // second time scanLabeledField.next() returns "kropp"
                    } else if (field.equals("or")) {
                        fields.put(lastLabel, fields.get(lastLabel) + "/" + scanTemplate.next());
                        // e.g.
                        // |plural indefinite|hundreder|or|hundred
                        // in fields we'll have (K,V)=("plural indefinite", "hundreder/hundred"
                    } else {
                        if (field.matches("definite singular|singular definite|definite")) {
                            lastLabel = "definite singular";
                        } else if (field.matches("indefinite plural|plural indefinite|plural")) {
                            lastLabel = "indefinite plural";
                        } else if (field.matches("definite plural|plural definite")) {
                            lastLabel = "definite plural";
                        } else if (field.equals("genitive")) {
                            lastLabel = "genitive";
                        }
                        fields.put(lastLabel, scanTemplate.next());
                    }
                }

                // genitive singular indefinite
                String sgIndef;
                try (Scanner scanSingIndef = new Scanner(lemma)) {
                    scanSingIndef.useDelimiter("/");
                    genitiveSingularIndefinite = "";
                    while (scanSingIndef.hasNext()) {
                        sgIndef = scanSingIndef.next();
                        if (sgIndef.matches(".*(s|z|x)")) {
                            genitiveSingularIndefinite += sgIndef + "'/";
                        } else {
                            genitiveSingularIndefinite += sgIndef + "s/";
                        }
                    }
                    if (genitiveSingularIndefinite.equals("")) {
                        genitiveSingularIndefinite = null;
                    } else {
                        genitiveSingularIndefinite = genitiveSingularIndefinite.substring(0, genitiveSingularIndefinite.lastIndexOf("/"));
                    }
                }

                // singular definite and genitive singular definite
                if (fields.containsKey("definite singular")) {
                    singularDefinite = fields.get("definite singular");
                    if (singularDefinite.contains(" / ")) {
                        singularDefinite = singularDefinite.replaceAll(" \\/ ", "\\/");
                    }
                    String singDef;
                    try (Scanner scanSingDef = new Scanner(singularDefinite)) {
                        scanSingDef.useDelimiter("/");
                        genitiveSingularDefinite = "";
                        while (scanSingDef.hasNext()) {
                            singDef = scanSingDef.next();
                            if (singDef.matches(".*(s|z|x)")) {
                                genitiveSingularDefinite += singDef + "'/";
                            } else {
                                genitiveSingularDefinite += singDef + "s/";
                            }
                        }
                    }
                    if (genitiveSingularDefinite.equals("")) {
                        genitiveSingularDefinite = null;
                    } else {
                        genitiveSingularDefinite = genitiveSingularDefinite.substring(0, genitiveSingularDefinite.lastIndexOf("/"));
                    }
                }
                // plural indefinite and genitive plural indefinite
                if (fields.containsKey("indefinite plural")) {
                    pluralIndefinite = fields.get("indefinite plural");
                    if (pluralIndefinite.contains(" / ")) {
                        pluralIndefinite = pluralIndefinite.replaceAll(" \\/ ", "\\/");
                    }
                    String plurIndef;
                    try (Scanner scanPlurIndef = new Scanner(pluralIndefinite)) {
                        scanPlurIndef.useDelimiter("/");
                        genitivePluralIndefinite = "";
                        while (scanPlurIndef.hasNext()) {
                            plurIndef = scanPlurIndef.next();
                            if (plurIndef.matches(".*(s|z|x)")) {
                                genitivePluralIndefinite += plurIndef + "'/";
                            } else {
                                genitivePluralIndefinite += plurIndef + "s/";
                            }
                        }
                    }
                    if (genitivePluralIndefinite.equals("")) {
                        genitivePluralIndefinite = null;
                    } else {
                        genitivePluralIndefinite = genitivePluralIndefinite.substring(0, genitivePluralIndefinite.lastIndexOf("/"));
                    }
                }
                // plural definite and genitive plural definite
                if (fields.containsKey("definite plural")) {
                    pluralDefinite = fields.get("definite plural");
                } else if (fields.containsKey("indefinite plural")) {
                    // custom rule
                    String plIndef;
                    try (Scanner scanPlIndef = new Scanner(pluralIndefinite)) {
                        scanPlIndef.useDelimiter("/");
                        pluralDefinite = "";
                        while (scanPlIndef.hasNext()) {
                            plIndef = scanPlIndef.next();
                            if (plIndef.endsWith("e")) {
                                pluralDefinite += plIndef + "ne/";
                            } else {
                                pluralDefinite += plIndef + "ene/";
                            }
                        }
                    }
                    if (pluralDefinite.equals("")) {
                        pluralDefinite = null;
                    } else {
                        pluralDefinite = pluralDefinite.substring(0, pluralDefinite.lastIndexOf("/"));
                    }

                }

                if (pluralDefinite != null) {
                    if (pluralDefinite.contains(" / ")) {
                        pluralDefinite = pluralDefinite.replaceAll(" \\/ ", "\\/");
                    }

                    String plDef;
                    try (Scanner scanPlDef = new Scanner(pluralDefinite)) {
                        scanPlDef.useDelimiter("/");
                        genitivePluralDefinite = "";
                        while (scanPlDef.hasNext()) {
                            plDef = scanPlDef.next();
                            if (plDef.matches(".*(s|z|x)")) {
                                genitivePluralDefinite += plDef + "'/";
                            } else {
                                genitivePluralDefinite += plDef + "s/";
                            }
                        }
                    }
                    if (genitivePluralDefinite.equals("")) {
                        genitivePluralDefinite = null;
                    } else {
                        genitivePluralDefinite = genitivePluralDefinite.substring(0, genitivePluralDefinite.lastIndexOf("/"));
                    }
                }

            } else {
                // other 63 not considered irregular cases
                if (template.startsWith("{{da-noun-infl")
                        || template.startsWith("{{head|da|noun")
                        || template.startsWith("{{da-noun")) {
                    System.out.println("NOT COVERED NOUN: " + template);

                    Constants.consideredEntriesCount--;
                } else {
                    Constants.allEntriesCount--;
                    Constants.consideredEntriesCount--;
                }
                return null;
            }
        } else if (!isRegularNounTemplate()) {
            // Not a good entry, to ignore!
            return null;
        }

        return new DanishMorphoRuleNoun(
                singularDefinite,
                pluralIndefinite,
                pluralDefinite,
                genitiveSingularIndefinite,
                genitiveSingularDefinite,
                genitivePluralIndefinite,
                genitivePluralDefinite
        );
    }

    private boolean isRegularNounTemplate() {

        return !(lemma.equals("Wikisaurus:boller")
                || lemma.equals("Template:da-noun/documentation")
                || template.matches("\\{\\{da-noun(.*)\\?(.*)")
                || template.startsWith("{{head|da|noun form")
                || template.equals("{{head|da|noun}}")
                || template.startsWith("{{head|da|noun plural form"));

    }

    private boolean isPluralOnly() {
        return template.matches("\\{\\{head\\|da\\|noun(.*)plural only\\}\\}")
                || template.contains("{{tcx|plurale tantum|lang=da}}")
                || template.contains("{{label|da|pluralonly}}");
    }

    private boolean isSingularOnly() {
        return template.contains("{{label|da|uncountable}}")
                || template.equals("{{head|da|noun|g=c|indeclinable}}")
                || template.equals("{{head|da|noun|indeclinable}}")
                || template.matches("\\{\\{(head|label)\\|da\\|(.*)(\\(\\'\\'not inflected\\'\\'\\)|uncountable|not used in plural( form)?|no plural|singulare tantum|only in singular indefinite form)(.*)");
    }
}
