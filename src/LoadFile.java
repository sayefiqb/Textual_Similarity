import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class LoadFile {

	public static HashMap<String,HashMap<String,Double>> map = new HashMap<String,HashMap<String,Double>>();
	public static HashMap<String,Double> frequency = new HashMap<String,Double>();
	public static HashMap <String,HashMap<String,Double>> loadFile(String infoFile)
	{
		HashMap<String,HashMap<String, Double>> termFrequency = new HashMap<String, HashMap<String,Double>>(); 
		HashMap<String, Double> idAndFrequency;
		double count = 0;
		Scanner input = null;
		try {
			input = new Scanner (new File(infoFile));
			
			while(input.hasNextLine()) 
			{  	
			String tweet = input.nextLine();
			String [] tokens = tweet.split(" ");
			String id = tokens[0];
			for(int i = 1; i<tokens.length; i++)
		{	
				if(tokens[i].equals(" "))
		{
			break;
		}
			else	 {  
				String token = tokens[i];
				if(!termFrequency.containsKey(token))
			{    
					  idAndFrequency = new HashMap<String, Double>();		
					  idAndFrequency.put(id, 1.0); 
					  termFrequency.put(token, idAndFrequency);
					
			}
				else
			{
				 if(termFrequency.get(token).containsKey(id))  
				 {		
					idAndFrequency = new HashMap<String, Double>();
					count = termFrequency.get(token).get(id) + 1.0;
					idAndFrequency = termFrequency.get(token);
					idAndFrequency.replace(id, count);
					termFrequency.replace(token, idAndFrequency);
				 }	
	
				if(!termFrequency.get(token).containsKey(id))
				{
				 idAndFrequency = new HashMap<String, Double>();	
				 idAndFrequency = termFrequency.get(token);
				 idAndFrequency.put(id,1.0);
				 termFrequency.remove(token);
				 termFrequency.put(token, idAndFrequency);	 
		
				}
			
			}
			}	
		}
			
			}
	      }
		
		catch(Exception e)
		{
			e.printStackTrace();;
		}
		return termFrequency;
}
	
	public HashMap<String, Double> idf (HashMap<String,HashMap<String,Double>> tf)
	{
		HashMap<String, Double> idf = new HashMap<String,Double>();
		for(String term: tf.keySet())
		{
			idf.put(term, (Math.log(45920/(double) tf.get(term).size()))/Math.log(2.0));
		}
		
		return idf;
		
	}
	
}
