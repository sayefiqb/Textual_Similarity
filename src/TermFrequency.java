import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class TermFrequency {

	public HashMap<String, HashMap<String,Double>> termFrequency(String infoFile)
	{  
		
     HashMap<String, HashMap<String,Double>> listOfWordsInLine = new HashMap<String, HashMap<String,Double>>();  
	 HashMap<String,Double> tf;
     double countWordsPerLine=0; 
	 Scanner input = null;
		try {
			input = new Scanner (new File(infoFile));
			
			while(input.hasNextLine()) 
			{ 
		String tweet = input.nextLine();
        	String [] token = tweet.split(" ");  
        	String id = token[0];
        	tf = new HashMap<String,Double>();
        	for(int i=1; i<token.length; i++)
			{	
        		
        		
        		if(!tf.containsKey(token[i]))
				{   
        			tf.put(token[i], 1.0);
        			//listOfWordsInLine.put(id, tf);
				}
				
				else 
				{
					countWordsPerLine = tf.get(token[i]) +1.0;
					tf.replace(token[i], countWordsPerLine);
					//listOfWordsInLine.put(id, tf);
				}	
			}
        	   listOfWordsInLine.put(id, tf);
			}
		}
        	catch(Exception e)
    		{
    			e.printStackTrace();;
    		}
        	return listOfWordsInLine;
    			
    }
	
/**	public  HashMap<String, Double> normalizeTermFrequency(String infoFile)
	{
		HashMap<String, Double> normalTermFrequency = new HashMap<String, Double>();
		HashMap<String, HashMap<String,Double>> termFrequency = termFrequency(infoFile);
		double max = findMaxTerm(termFrequency);
		double tf = 0.0;
		for(String key : termFrequency.keySet())
		{
			tf = termFrequency.get(key)/max;
			normalTermFrequency.put(key, tf);
		}
		termFrequency = null;// clearing memory
		System.gc();
		return normalTermFrequency;
		
	}
	**/
	public HashMap<String,HashMap<String,Double>> tf (HashMap<String, HashMap<String,Double>>termFrequencyPerLine)
	{
		HashMap<String, HashMap<String,Double>> frequencyOfWordInLine = termFrequencyPerLine;
		HashMap<String,Double> map;
	//	int count = 0;
		for(String id: frequencyOfWordInLine.keySet())
		{
			
			map = frequencyOfWordInLine.get(id);
			
			double max = map.get(map.keySet().toArray()[0]);
		//	System.out.println(max +" for " + id + " in line " + count);
		//	count = count + 1;
			for(String term: map.keySet())
			{
				if(max<map.get(term))
				{
					max = map.get(term);
				}
			}
			
			for(String term: frequencyOfWordInLine.get(id).keySet())
			{
				frequencyOfWordInLine.get(id).replace(term, frequencyOfWordInLine.get(id).get(term)/max);
			}
		}
	
		return frequencyOfWordInLine;	
	}
	

	
	
	
}
