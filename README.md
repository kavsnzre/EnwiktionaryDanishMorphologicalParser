# EnwiktionaryDanishMorphologicalParser
This project produces a textual morphological table for each Danish word listed into enwiktionary.

It has been developed for an academic examination, and in particular all the files that are outside of the **"da"** folder have been made by my instructors.

# What does it actually do?

### The work principle
Let's consider the Danish word *"pseudonym*", listed in enwiktionary at the URL *https://en.wiktionary.org/wiki/pseudonym*

You can see the following informations:

![sample_enwiktionary](https://cloud.githubusercontent.com/assets/18027256/17254846/c53e119c-55b6-11e6-8a9e-93cd3f848c2d.png)
---------

Please note that this word is both a noun and an adjective.

This project extracts the morphological informations from this page and for this case it produces these two textual morphological tables:

![sample_output](https://cloud.githubusercontent.com/assets/18027256/17254978/5e062496-55b7-11e6-9dc0-79bd63b97592.png)
--------

### More details

Actually the program doesn't read directly the Web page but takes in input the **enwiktionary dump**, that is an *.xml* file containing all the informations of enwictionary in a compact and textual pseudo code.
For example for the *pseudonym* word the pseudo code is the following one:

-------
==Danish==

===Pronunciation===
\* {{IPA|/sœvdonyːm/|[sœwd̥oˈnyːˀm]|lang=da}}

===Noun===
{{da-noun|et|er}}

\# [[pseudonym#English|pseudonym]]

====Inflection====
{{da-noun-infl|et|er}}

===Adjective===
{{head|da|adjective|neuter|pseudonymt|definite and plural|pseudonyme}}

\# [[pseudonymous]]

===External links===
\* {{pedialite|lang=da}}

\-\-\-\-
-------

For knowing how you can get this file please see below.

# Getting started (for Linux)
* download the current  **enwiktionary dump**:
  * go here: *https://dumps.wikimedia.org/backup-index.html*
  * look for *"enwiktionary"*; you'll try a link like this: **"yyyy-mm-dd hh:mm:ss enwiktionary: Dump complete|Partial dump"**; click on it!
  * now you are in a page with a lot of links, you have to find a link like **"enwiktionary-yyyymmdd-pages-articles.xml.bz2 XXX.X MB"**; click on it!
  * after the download is terminated, extract **"enwiktionary-yyyymmdd-pages-articles.xml"** file from the **.bz2** archive
* type *cd absolute/path/to/the/project*
* type *java -cp ./wimmp.jar it.uniroma1.lcl.wimmp.Test Danish da path/to/enwiktionary-yyyymmdd-pages-articles.xml
* wait some minutes...
* when the execution is ended in the project folder you'll find the **wimmp_da.txt** file containing all the textual morphological tables that can be extracted from *enwiktionary*

# License
For this project is used the **MIT license**.
