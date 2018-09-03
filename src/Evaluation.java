import java.io.File;
import java.util.HashMap;
import java.util.Scanner;



public class Evaluation {
	
	public static int count= 0;
	
	public static void relevancyTest(String queryFile,String relevantFile) {
		String queryName = "";
		if(queryFile.length()<=19)
		{
			queryName = queryFile.substring(0, 6);
		}
		else
		{
			 queryName = queryFile.substring(0,7);
		}
		HashMap<String,Double> queryMap = queryEvaluate(queryFile);
		HashMap<String,Integer> map = evaluate(relevantFile);
		double queryCount = 0;
		for(String id: queryMap.keySet())
		{
			
			if(map.containsKey(id))
			{
				queryCount = queryCount +1;
				System.out.println(id);
			}
		}
		System.out.println("Summary:");
		System.out.println("Total number of relevant documents in the given file: " +map.size());
		System.out.println("Total number of relevant document in the retrieved file: "+ queryCount);
		System.out.println("The recall for "+ queryName+ " is "+queryCount/map.size());
	}

	public static HashMap<String,Double> queryEvaluate(String file)
	{
		HashMap<String,Double> map = new HashMap<String,Double>();	
		Scanner input = null;
			try {
				input = new Scanner (new File(file));
				count = 1;
				while(input.hasNextLine()) 
				{  	
			      String line = input.nextLine();
			      line = line.replaceAll("\\s{2,}", " ");
			      String [] token = line.split("\\s{1,}");
			      String id = token[2];
			     // System.out.println(id);
			      //String relevant = token[4];
			     // double relevance = Double.parseDouble(relevant);
			      map.put(id, 1.0);
				}
			}
				catch(Exception e)
				{
					e.printStackTrace();
				}
	return map;
	}
	
	public static HashMap<String,Integer> evaluate (String file)
	{	
	HashMap<String,Integer> map = new HashMap<String,Integer>();	
	Scanner input = null;
		try {
			input = new Scanner (new File(file));
			count = 1;
			while(input.hasNextLine()) 
			{  	
		      String line = input.nextLine();
		      line = line.replaceAll("\\s{2,}", " ");
		      String [] token = line.split("\\s{1,}");
		      String id = token[2];
		      //System.out.println(id);
		      String relevant = token[3];
		      int relevance = Integer.parseInt(relevant);
		      if(relevance==1)
		      {
		    	  map.put(id, relevance);
		      }
		      count = count +1;
		    	}

		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
return map;
}
	
}