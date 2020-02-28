# PDFReferencesExtractor

[![Build Status](https://travis-ci.org/helenocampos/PDFReferencesExtractor.svg?branch=master)](https://travis-ci.org/helenocampos/PDFReferencesExtractor)

This is a simple use case for the [CERMINE](https://github.com/CeON/CERMINE) API. It uses the API to extract the references of a PDF article and outputs it to the screen.

Simply clone the repository and build with Maven (mvn install) or download the latest release.

Requirements:

Java 1.8


Modes:

full: outputs the bibtex references for a pdf file

short: outputs only titles/year/publication venue of the references for a pdf file. They are separeted by semicollons, to make it easier to create csv files.



How to use:
java -jar refExtractor.jar full pdffile.pdf

or

java -jar refExtractor.jar short pdffile.pdf


If you pass a folder instead of a file as argument, it will attempt to parse all files in that folder and will output in the selected mode.


Alternatively, if you dont want to output to be printed in the terminal, you can redirect the output using "> output.txt"


E.g.: 

java -jar refExtractor.jar short pdffile.pdf > output.txt
