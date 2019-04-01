package Project.Devops.Sec;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Pandas {

    private ArrayList<String[]> dataFrames;
    private CsvParser csvParser;
    private String[] columns;
    private HashMap<String,String> columnsType;
    private int nbColumns ;
    private int nbLines;


    public Pandas(ArrayList<String[]> dataFrames) {
        this.dataFrames = dataFrames;
        this.csvParser = null;
        this.columns = this.dataFrames.get(0);
        this.columnsType = new HashMap<>();
        this.nbLines = this.dataFrames.size();
        this.nbColumns = this.columns.length;
        this.typeInference();
    }

    public Pandas(String filname) {
        this.csvParser = new CsvParser(filname);
        this.dataFrames = this.csvParser.getRows();
        this.columns = this.csvParser.getColumns();
        this.columnsType = new HashMap<>();
        this.nbColumns = this.csvParser.getNbColumns();
        this.nbLines = this.csvParser.getNbLines();
        this.typeInference();

    }

    private void typeInference(){
        for (int i =0;i<this.nbColumns;i++){
            if (this.dataFrames.get(1)[i].matches(".*[a-zA-Z]+.*")){
                this.columnsType.put(this.columns[i],"String");
            } else if (this.dataFrames.get(1)[i].contains(".")){
                this.columnsType.put(this.columns[i],"float");
            } else this.columnsType.put(this.columns[i],"int");
        }
    }

    public boolean containsLabel(String label){
        for (String lb : this.columns){
            if (lb.equals(label)){
                return true;
            }
        }
        return false;
    }

    public String getColumnLabel(int index){
        return this.columns[index];
    }

    public int getLabelIndex(String label){
        try {
            for (int i=0;i<this.nbColumns;i++){
                if (label.equals(this.columns[i])) return i;
            }
            throw new NoSuchElementException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String[] getColumn(int columnIndex){
        String[] column = new String[this.nbLines];
        for (int i = 0;i<this.nbLines;i++){
            column[i] =  this.dataFrames.get(i)[columnIndex];
        }
        return column;
    }

    public void printDataFrame(){
        int nb = 0;
        for (String[] row : this.dataFrames){
            System.out.print(nb +":");
            for (int i=0; i<this.nbColumns;i++){
                System.out.print(row[i] + ",");
            }
            nb++;
            System.out.println();
        }
    }


    public void printTopTable(){
        int nbPrintedLines = this.nbLines/10;
        if (nbPrintedLines<1) nbPrintedLines = 1;

        for (int i = 0;i<nbPrintedLines;i++){
            System.out.print(i +":");
            for (int j=0; j<this.nbColumns;j++){
                System.out.print(this.dataFrames.get(i)[j] + ",");
            }
            System.out.println();
        }
    }

    public void printBottomTable(){
        int nbStartingIndexOfPrintedLines = this.nbLines - this.nbColumns/10;
        if (nbStartingIndexOfPrintedLines < 1 ) nbStartingIndexOfPrintedLines = 1;

        for (int i = nbStartingIndexOfPrintedLines;i<this.nbLines;i++){
            System.out.print(i +":");
            for (int j=0; j<this.nbColumns;j++){
                System.out.print(this.dataFrames.get(i)[j] + ",");
            }
            System.out.println();

        }
    }

    public ArrayList<String[]> selectDataframe(int indexOfLine){
        ArrayList<String[]> subSet = new ArrayList<>();

        for (int i = indexOfLine; i<this.nbLines;i++){
            subSet.add(this.dataFrames.get(i));
        }
        return subSet;
    }

    public ArrayList<String[]> selectDataframe(int startingIndex, int lastIndex){
        ArrayList<String[]> subSet = new ArrayList<>();

        if (startingIndex < 0) startingIndex = 0;
        if (lastIndex > this.nbLines) lastIndex = this.nbLines;

        for (int i = startingIndex; i<lastIndex;i++){
            subSet.add(this.dataFrames.get(i));
        }
        return subSet;
    }

    public ArrayList<String[]> selectDataframe(int... selectedLines){
        ArrayList<String[]> subSet = new ArrayList<>();
        for (int row : selectedLines){
            if (row >= 0 && row < this.nbLines){
                subSet.add(this.dataFrames.get(row));
            }
        }
        return subSet;
    }

    public ArrayList<String[]> selectDataframe(String... labels){
        ArrayList<String[]> subSet = new ArrayList<>();

        for ( String lb : labels ){
            if (this.containsLabel(lb)){
                subSet.add(this.getColumn(this.getLabelIndex(lb)));
            }
        }
        return subSet;
    }

    public float average (int index) {

        if ((index > this.nbLines || index < 0 ) || this.columnsType.get(this.getColumnLabel(index)).equals("String")){
            try {
                throw new Exception("Column dont exist ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        float sum = 0;
        for (int i =1; i< this.nbLines;i++){
            sum = sum + Float.parseFloat(this.dataFrames.get(i)[index]);
        }
        return sum/(this.nbLines -1);
    }

    public float average (String label){
        int index = this.getLabelIndex(label);
        return this.average(index);
    }

    public float min (int index) throws Exception{

        if (index > this.nbLines && this.columnsType.get(this.getColumnLabel(index)).equals("String")){
            throw new Exception("Column dont exist");
        }

        float min = Float.parseFloat(this.dataFrames.get(1)[index]);

        for (int i =2; i< this.nbLines; i++){
            if (min > Float.parseFloat(this.dataFrames.get(i)[index])){
                min = Float.parseFloat(this.dataFrames.get(i)[index]);
            }
        }

        return min;
    }

    public float min (String label) throws Exception{
        int index = this.getLabelIndex(label);
        return this.min(index);
    }

    public float max (int index) throws Exception{

        if (index > this.nbLines && this.columnsType.get(this.getColumnLabel(index)).equals("String")){
            throw new Exception("Column dont exist");
        }

        float max = Float.parseFloat(this.dataFrames.get(1)[index]);

        for (int i =2; i< this.nbLines; i++){
            if (max < Float.parseFloat(this.dataFrames.get(i)[index])){
                max = Float.parseFloat(this.dataFrames.get(i)[index]);
            }
        }

        return max;
    }

    public float max (String label) throws Exception{
        int index = this.getLabelIndex(label);
        return this.max(index);
    }


    public ArrayList<String[]> getDataFrames() {
        return dataFrames;
    }

    public String[] getColumns() {
        return columns;
    }

    public HashMap<String, String> getColumnsType() {
        return columnsType;
    }

    public int getNbColumns() {
        return nbColumns;
    }

    public int getNbLines() {
        return nbLines;
    }
}
