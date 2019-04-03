package Project.Devops.Sec;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    Pandas panda1 = new Pandas("./rsc/testFile10.csv");
    Pandas panda2 = new Pandas(panda1.getDataFrames());
    String str = "Column 1,Column 2,Column 3,Column 4,Column 5,Column 6,Column 7,Column 8,Column 9,Column 10";
    String st1 = "Column 1,1,2,3,4,5,6,7,8,9,10";
    String Column1 = "1,2,3,4,5,6,7,8,9,10";
    CsvParser csvParser = new CsvParser("./rsc/testFile10.csv");

    @Test
    public final void readingColumnsTest() {
        String str = "Column 1,Column 2,Column 3,Column 4,Column 5,Column 6,Column 7,Column 8,Column 9,Column 10";
        assertEquals(panda1.getColumnsName(),str.split(","));
    }

    @Test
    public final void infereTypeTest() {

        HashMap<String,String> types = new HashMap<>();
        types.put("Column 1","int");
        types.put("Column 2","String");
        types.put("Column 3","String");
        types.put("Column 4","int");
        types.put("Column 5","float");
        types.put("Column 6","float");
        types.put("Column 7","int");
        types.put("Column 8","String");
        types.put("Column 9","String");
        types.put("Column 10","float");



        for (String st : panda1.getColumnsType().keySet())
            assertEquals(panda1.getColumnsType().get(st),types.get(st));
    }

    @Test
    public final void constainsLabelTest(){

        assertTrue(panda1.containsLabel("Column 1"));
        assertFalse(panda1.containsLabel("labelNotFound"));
    }

    @Test
    public final void getColumnLabelTest(){

        assertEquals(panda1.getColumnLabel(0),"Column 1");
    }

    @Test
    public final void getLabelIndexTest(){

        assertEquals(panda1.getLabelIndex("Column 1"),0);

    }


    @Test(expected = java.lang.AssertionError.class)
    public void whenExceptionThrown_thenExpectationSatisfied() {
        assertEquals(panda1.getLabelIndex("Column 1000"),0);
    }

    @Test
    public final void getColumnTest(){


        assertArrayEquals(st1.split(","),panda1.getColumn(0));

    }

    @Test
    public final void printTopDataFramTableTest(){
        panda1.printTopTable();
    }

    @Test
    public final void printDataFramTableTest(){
        panda1.printDataFrame();
    }

    @Test
    public final void printBottomDataFramTableTest(){
        panda1.printBottomTable();
    }

    @Test
    public final void SelectDataFramTest1(){
        assertTrue(panda1.getDataFrames().equals(panda1.selectDataframe(0)));
    }

    @Test
    public final void SelectDataFramTest2(){
        assertTrue(panda1.getDataFrames().equals(panda1.selectDataframe(0,panda1.getNbLines())));

    }

    @Test
    public final void SelectDataFramTest3(){
        assertTrue(panda1.getDataFrames().equals(panda1.selectDataframe(0,1,2,3,4,5,6,7,8,9,10)));

        for (String[] st : panda1.selectDataframe(0,1,4,5,6,10)){
            for (int k = 0; k< this.panda1.getNbColumns();k++){
                System.out.print(st[k]);
            }
            System.out.println();
        }

    }

    @Test
    public final void SelectDataFramTest4(){

      ArrayList<String[]> expectedOutPut = new ArrayList<String[]>();
      expectedOutPut.add(this.st1.split(","));
      ArrayList<String[]> actualOutPut = panda1.selectDataframe("Column 1");

      int i =0;
      for (String x : actualOutPut.get(0)){
          assert(x.equals(actualOutPut.get(0)[i]));
          i++;
      }
    }


    @Test
    public final void averageTest(){
        float expectedAverage = (float)(1+2+3+4+5+6+7+8+9+10)/10;

        assertEquals(panda1.average(0),expectedAverage,0.0002);
    }

    @Test(expected = java.lang.Exception.class)
    public final void averageTest2(){
        float expectedAverage = (float)(1+2+3+4+5+6+7+8+9+10)/10;
        assertEquals(panda1.average(-1),expectedAverage,0.0002);
    }

    @Test(expected = java.lang.Exception.class)
    public final void averageTest3(){
        float expectedAverage = (float)(1+2+3+4+5+6+7+8+9+10)/10;
        assertEquals(panda1.average(100),expectedAverage,0.0002);
    }

    @Test
    public final void averageTest4(){
        float expectedAverage = (float)(1+2+3+4+5+6+7+8+9+10)/10;
        float actualAverage = panda1.average("Column 1");
        assertEquals(actualAverage,expectedAverage,0.0002);
    }

    @Test
    public final void minTest(){
        float expectedMin = (float) 1;
        assertEquals(panda1.min(0),expectedMin,0.0002);
        assertEquals(panda1.min("Column 1"),expectedMin,0.0002);
    }
    @Test
    public final void minTest5(){
        float expectedMin = (float) 1;
        assertEquals(panda1.min("Column 1"),expectedMin,0.0002);
    }

    @Test(expected = java.lang.Exception.class)
    public final void minTest2(){
        float expectedMin = (float) 1;
        assertEquals(panda1.min(-1),expectedMin,0.0002);

    }

    @Test(expected = java.lang.Exception.class)
    public final void minTest3(){
        float expectedMin = (float) 1;
        assertEquals(panda1.min(100),expectedMin,0.0002);
    }

    @Test(expected = java.lang.Exception.class)
    public final void minTest4(){
        float expectedMin = (float) 1;
        assertEquals(panda1.min("Not existing Column"),expectedMin,0.0002);
    }


    @Test
    public final void maxTest(){
        float expectedMax = (float) 10;
        assertEquals(panda1.max(0),expectedMax,0.0002);
    }

    @Test
    public final void maxTest2(){
        float expectedMax = (float) 10;
        assertEquals(panda1.max("Column 1"),expectedMax,0.0002);
    }

    @Test(expected = java.lang.Exception.class)
    public final void maxTest3(){
        float expectedMax = (float) 1;
        assertEquals(panda1.max("Not existing Column"),expectedMax,0.0002);
    }

    @Test(expected = java.lang.Exception.class)
    public final void maxTest4(){
        float expectedMax = (float) 1;
        assertEquals(panda1.max(100),expectedMax,0.0002);
    }

    @Test(expected = java.lang.Exception.class)
    public final void maxTest5(){
        float expectedMax = (float) 1;
        assertEquals(panda1.max(-1),expectedMax,0.0002);
    }


    @Test
    public final void csvParserGetFileName() {
        assertEquals(csvParser.getFileName(),"./rsc/testFile10.csv");
    }


    @Test
    public final void personnalizedIs() {


        assertTrue(panda1.personnalizedIs(0,1,1));
        assertTrue(panda1.personnalizedIs(1,2,1));
        assertTrue(panda1.personnalizedIs(2,1,2));

        assertTrue(panda1.personnalizedIs(0,1f,1f));
        assertTrue(panda1.personnalizedIs(1,2f,1f));
        assertTrue(panda1.personnalizedIs(2,1f,2f));

        assertTrue(panda1.personnalizedIs("Mouataz","Mouataz"));


        assertFalse(panda1.personnalizedIs(0,1,2));
        assertFalse(panda1.personnalizedIs(1,1,1));
        assertFalse(panda1.personnalizedIs(2,1,1));

        assertFalse(panda1.personnalizedIs(0,1f,2f));
        assertFalse(panda1.personnalizedIs(1,1f,1f));
        assertFalse(panda1.personnalizedIs(2,1f,1f));

        assertFalse(panda1.personnalizedIs("Mouataz","Pas Mouataz"));

    }


    @Test
    public void SelectionDataframe5() {
        String [] expectedStr = "1,\"Eldon Base for stackable storage shelf\",Muhammed MacIntyre,3,-213.25,38.94,35,Nunavut,Storage & Organization,0.8".split(",");
        ArrayList<String[]> expectedOutput = new ArrayList<>();
        expectedOutput.add(expectedStr);

        ArrayList<String[]> actualOutput = panda1.selectDataframe("Muhammed MacIntyre", "Column 3");


        for (int j = 0; j<expectedOutput.size();j++){
            for (int i = 0;i<expectedOutput.get(j).length; i++){
                assertTrue(expectedOutput.get(j)[i].equals(actualOutput.get(j)[i]));
            }
        }

    }

    @Test (expected = java.lang.Exception.class)
    public void SelectionDataFrame6() {
        panda1.selectDataframe("Muhammed MacIntyre", "Not Existing Column");
    }


    @Test
    public void SelectionDataframe7() {
        String [] expectedStr = "1,\"Eldon Base for stackable storage shelf\",Muhammed MacIntyre,3,-213.25,38.94,35,Nunavut,Storage & Organization,0.8".split(",");
        ArrayList<String[]> expectedOutput = new ArrayList<>();
        expectedOutput.add(expectedStr);

        ArrayList<String[]> actualOutput = panda1.selectDataframe("Muhammed MacIntyre", 2);


        for (int j = 0; j<expectedOutput.size();j++){
            for (int i = 0;i<expectedOutput.get(j).length; i++){
                assertTrue(expectedOutput.get(j)[i].equals(actualOutput.get(j)[i]));
            }
        }

    }

    @Test
    public void SelectionDataFrame8() {

        ArrayList<String[]> expectedOutput = new ArrayList<>();
        expectedOutput.add("1,\"Eldon Base for stackable storage shelf\",Muhammed MacIntyre,3,-213.25,38.94,35,Nunavut,Storage & Organization,0.8".split(","));
        ArrayList<String[]> actualOutput = panda1.selectDataframe(0,1,"Column 1");

        for (int j = 0; j<expectedOutput.size();j++){
            for (int i = 0;i<expectedOutput.get(j).length; i++){
                assertTrue(expectedOutput.get(j)[i].equals(actualOutput.get(j)[i]));
            }
        }
        actualOutput = panda1.selectDataframe(2,2,"Column 1");

        for (int j = 0; j<expectedOutput.size();j++){
            for (int i = 0;i<expectedOutput.get(j).length; i++){
                assertTrue(expectedOutput.get(j)[i].equals(actualOutput.get(j)[i]));
            }
        }

        actualOutput = panda1.selectDataframe(1,9,"Column 1");
        expectedOutput.remove(0);
        expectedOutput.add("10,Xerox 198,Dorothy Badders,678,-226.36,4.98,8.33,Nunavut,Paper,0.38".split(","));

        for (int j = 0; j<expectedOutput.size();j++){
            for (int i = 0;i<expectedOutput.get(j).length; i++){
                assertTrue(expectedOutput.get(j)[i].equals(actualOutput.get(j)[i]));
            }
        }


    }

    @Test (expected = java.lang.Exception.class)
    public void SelectionDataFrame9() {
        panda1.selectDataframe(0,500, "Not Existing Column");
    }

    @Test
    public void SelectionDataFrame10() {

        ArrayList<String[]> expectedOutput = new ArrayList<>();
        expectedOutput.add("1,\"Eldon Base for stackable storage shelf\",Muhammed MacIntyre,3,-213.25,38.94,35,Nunavut,Storage & Organization,0.8".split(","));

        ArrayList<String[]> actualOutput = panda1.selectDataframe(0,-213.25f,"Column 5");

        for (int j = 0; j<expectedOutput.size();j++){
            for (int i = 0;i<expectedOutput.get(j).length; i++){
                assertTrue(expectedOutput.get(j)[i].equals(actualOutput.get(j)[i]));
            }
        }

    }


}


