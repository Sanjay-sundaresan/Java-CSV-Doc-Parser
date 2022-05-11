//Sanjay Sundaresan - 19BPS1113
import java.util.*;
import java.io.*; 
import javax.swing.*;
class csvParser{
    public static void main(String[] args){
        System.out.println("Sanjay Sundaresan 19BPS1113 - Digital Assignment 2 - CSV/DOC Parser");
        Scanner scan = null;
        File obj = new File("/home/sanjay/JAVA/THEORY/DA 2/climate.csv");
		int rcount = 0, count = 0;
        List<List<Float>> store = new ArrayList<List<Float>>(); //Using a 2D float arraylist to store parsed elements dynamically  
		List<String> colnames = new ArrayList<String>();
		colnames.add("Parameter");
	    try {
			Scanner mscan = new Scanner(obj);
			mscan.useDelimiter(System.getProperty("line.separator"));
			//Reading first row to determine number of columns 
			Scanner temp = new Scanner(mscan.next()); 
			temp.useDelimiter(",");
			count = 0;
			while(temp.hasNext()){
				colnames.add(temp.next());
				count++;
			}

			//Storing the contents in the 2D arraylist 
			while(mscan.hasNext()){ //While next row is available 
			    /*scan = new Scanner(mscan.next());
				scan.useDelimiter(","); */ 
				String[] row = mscan.next().split(","); //Comma is used as a delimiter since a CSV file is being processed
				Float[] frow = new Float[count]; //Creating a float array that represents a row 
				int i = 0;
				for(String s:row){
					frow[i] = Float.parseFloat(s);
					i++; 
				}
				store.add(new ArrayList<Float>(Arrays.asList(frow)));
				rcount++; //Keeping track of number of rows 
			}                     		
	    } 
	    catch(NumberFormatException e){ //In case the file does not have numeric contents 
			System.out.println("The given file contains non-numeric elements which are not suitable for statistical analysis.");
	    }
	    catch(Exception e){
		    e.printStackTrace();
	    }

		//Processing column-wise for the statistical summary
		List<String> minList = new ArrayList<String>();
		minList.add("Minimum");
		List<String> Q1List = new ArrayList<String>();
		Q1List.add("Q1");
		List<String> MList = new ArrayList<String>();
		MList.add("Median");
		List<String> Q3List = new ArrayList<String>();
		Q3List.add("Q3");
		List<String> maxList = new ArrayList<String>();
		maxList.add("Maximum");
		List<String> IList = new ArrayList<String>();
		IList.add("InterQuartile Range");
		List<String> LList = new ArrayList<String>();
		LList.add("Lower Outlier Limit");
		List<String> UList = new ArrayList<String>();
		UList.add("Upper Outlier Limit");
		float min = 0, Q1 = 0, median = 0, Q3 = 0, IQR = 0, max = 0;  
		System.out.println("The number of rows in the given file = " + rcount);
		System.out.println("The number of columns in the given file = " + count);   

		//Calculating the min, Q1, median, Q3 and max  for each column 
		for(int r = 0; r < count; r++){
			min = 0; Q1 = 0; median = 0; Q3 = 0; IQR = 0; max = 0;
			List<Float> col = new ArrayList<Float>();
			for(int j = 0; j < rcount; j++){
				col.add(store.get(j).get(r)); 
			}
			Collections.sort(col); //Sorting the values of the column in ascending order 
			min = col.get(0);
			Q1 = col.get(rcount/4);
			if(rcount%2 == 0){ //If the number of elements in the column are even
				median = (float)(col.get(rcount/2) + col.get((rcount/2) + 1));
			}
			else{
				median = (float)(col.get(rcount/2));
			}
			Q3 = col.get(rcount*3/4);
			IQR = Q3-Q1;
			max = col.get(rcount - 1);
			float LL = (float)(Q1 - 1.5*IQR); //Outlier limits 
			float UL = (float)(Q3 + 1.5*IQR);
			
			//Printing results 
			System.out.println("Minimum value of column " + (r+1) + " = " + min); minList.add(String.valueOf(min));
			System.out.println("Q1 value of column " + (r+1) + " = " + Q1); Q1List.add(String.valueOf(Q1));
			System.out.println("Median value of column " + (r+1) + " = " + median); MList.add(String.valueOf(median));
			System.out.println("Q3 value of column " + (r+1) + " = " + Q3); Q3List.add(String.valueOf(Q3));
			System.out.println("Maximum value of column " + (r+1) + " = " + max); maxList.add(String.valueOf(max));
			System.out.println("InterQuartile range of column " + (r+1) + " = " + IQR);  IList.add(String.valueOf(IQR));
			System.out.println("Lower Outlier limit of column " + (r+1) + " = " + LL);  LList.add(String.valueOf(LL));
			System.out.println("Higher Outlier limit of column " + (r+1) + " = " + UL);  UList.add(String.valueOf(UL));
			System.out.println();
		}
		JFrame jFrame = new JFrame();
		List<List<String>> tableData = new ArrayList<List<String>>();
		tableData.add(minList);
		tableData.add(Q1List);
		tableData.add(MList);
		tableData.add(Q3List);
		tableData.add(maxList);
		tableData.add(IList);
		tableData.add(LList);
		tableData.add(UList);
        String[][] td = new String[tableData.size()][];
        for (int k = 0; k < tableData.size(); k++) {
    		List<String> row = tableData.get(k);
    		String[] copy = new String[row.size()];
            for (int j = 0; j < row.size(); j++) {
        		copy[j] = row.get(j);
            }
			td[k] = copy;
		}
		
		//Using Java GUI to display the statistical summary in tabular form 
		JTable jTable = new JTable(td, colnames.toArray(new String[colnames.size()]));
		jTable.setBounds(30, 60, 230, 600);
		JScrollPane jScrollPane = new JScrollPane(jTable);
		jFrame.add(jScrollPane);
		jFrame.setSize(350, 300);
		jFrame.setVisible(true); 
   }
}  
