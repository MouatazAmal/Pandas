package Project.Devops.Sec;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
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
                this.rows = new ArrayList<String[]>();
                this.parseCsv();
                this.columns = this.rows.get(0);
                this.nbColumns = this.columns.length;
                this.nbLines = this.rows.size();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parseCsv() throws IOException {
        BufferedReader buff = null;
        String line = "";

        buff = new BufferedReader(fileReader);
        while ((line = buff.readLine()) != null) {

            String[] row = line.split(",");
            this.rows.add(row);
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

    public ArrayList<String[]> getRows() {
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

    public void printDataFrame(){
        for (String[] row : this.rows){
            for (int i =0; i<row.length;i++) {
                System.out.print(row[i]);
            }
            System.out.println();

        }
    }



}
