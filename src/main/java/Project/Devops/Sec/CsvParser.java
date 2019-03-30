package Project.Devops.Sec;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CsvParser {

    private final String fileName;
    private File file;
    private FileReader fileReader;
    private CSVReader csvReader;
    private ArrayList<String[]> rows;
    private String[] columns;
    private int nbColumns ;
    private int nbLines;


    //La lignes dénoté 0 est considéré comme la ligne spécifiant les colonnes.
    public CsvParser(String fileName) {
        // initializing the file
        this.fileName = fileName;

        try {
            this.file = new File(this.fileName);
            if (this.file.canRead()){
                this.fileReader = new FileReader(file);
                this.csvReader = new CSVReader(this.fileReader);
                this.rows = (ArrayList<String[]>) this.csvReader.readAll();
                this.columns = this.rows.get(0);
                this.nbColumns = this.columns.length;
                this.nbLines = this.rows.size();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return file;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public CSVReader getCsvReader() {
        return csvReader;
    }

    public List<String[]> getRows() {
        return rows;
    }

    public String[] getColumns() {
        return columns;
    }

    public int getNbColumns() {
        return nbColumns;
    }

    public int getNbLines() {
        return nbLines;
    }



}
