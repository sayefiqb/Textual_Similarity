
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


public class LoadFileTest {

	public static void LoadData(String dataFile, String queryFile) {
	
		
		LoadFile file = new LoadFile();
		HashMap<String, HashMap<String, Double>> map = file.loadFile(dataFile);
	    HashMap<String, Double> idf = file.idf(map);
	    
	    TermFrequency termFreq = new TermFrequency();
	    HashMap<String,HashMap<String,Double>> tf = termFreq.termFrequency(dataFile);
	    HashMap<String,HashMap<String,Double>> tfidf = termFreq.tf(tf);
	    HashMap<String,HashMap<String,Double>> queries = termFreq.termFrequency(queryFile);
	    HashMap<String,HashMap<String,Double>> queriesTermFrequency = termFreq.tf(queries); 

	     for(String id: tfidf.keySet())
	     {
	    	 	for(String term: tfidf.get(id).keySet())
	    	 	{
	    	 	 if(!term.equals(" "))	
	    	 	 { double tfidfValue= tfidf.get(id).get(term)*idf.get(term);
	    		 tfidf.get(id).replace(term, tfidfValue);
	    	 	 }
	    	 	 }
	     }
	     
	     for(String id: queriesTermFrequency.keySet())
	     	{
	    	 	for(String term: queriesTermFrequency.get(id).keySet())
	    	 		{
	    	 			if(idf.containsKey(term))
	    	 			{	double queryTfIdf= queriesTermFrequency.get(id).get(term)*idf.get(term);
	    	 				queriesTermFrequency.get(id).replace(term, queryTfIdf);
	    	 			}
	    	 		}
	     	}
	    
	     HashMap<String,Double> documentLength = documentLength(tfidf);
	     HashMap<String,Double> queryLength = documentLength(queriesTermFrequency);
	    
	     HashMap<String, HashMap<String,Double>> similarList = new HashMap<String,HashMap<String,Double>>();  
         HashMap<String, Double> similar = new HashMap<String,Double>();
	    
         double weight;
	     double cosSimilar;
	    
	     for(String queryId: queriesTermFrequency.keySet())
	     {  
	    	 similar = new HashMap<String,Double>();
	    	 	for(String id: tfidf.keySet())
	          {
	        	  weight = 0.0;
	        	  cosSimilar = 0.0;
	        	  
	    	     	for(String queryTerm: queriesTermFrequency.get(queryId).keySet())
	    	 	       {
	    	    		      if(tfidf.get(id).containsKey(queryTerm))
	    	    		          {
	    	    			         weight = tfidf.get(id).get(queryTerm)*queriesTermFrequency.get(queryId).get(queryTerm) + weight;
	    	    		          } 
	    	 	        }    	     			
	    	     	 cosSimilar = weight/(documentLength.get(id)*queryLength.get(queryId));
	    	     	 if(cosSimilar>0.05)
	    	     	 {
	    	     		 similar.put(id, cosSimilar);
	    	     		 
	    	     	 }
	    	     	}
	    	 	
	         similarList.put(queryId, similar);
	      }    
 
	     int countWord=1;
	     for(String queryId: similarList.keySet())
	     {	
	    	 List<Entry<String, Double>> myList = entriesSortedByValues(similarList.get(queryId));
	    	 int count = 1;
	    	 	for(int i =0; i<myList.size();i++)
	    	 	{
	    		 if(countWord<10)
	    		 {	
	    			 System.out.println("MB0"+countWord+" Q0 "+myList.get(i).getKey() +" "+count+ " "+myList.get(i).getValue()+" myRun");
	    		 }
	    		 
	    		 if(countWord>=10)
	    		 {
	    			 System.out.println("MB"+countWord+" Q0 "+myList.get(i).getKey() +" "+ count+" "+myList.get(i).getValue()+" myRun");
	    		 }
	    		 count = count + 1;
	    	 	}
	    	 	System.out.println("");
	     countWord = countWord+1;
	     }    
 
	}
	
	public static HashMap<String, Double> documentLength (HashMap<String, HashMap<String, Double>> tfIDF)
	{	
		HashMap<String, Double> documentLength = new HashMap<String, Double>();
		HashMap<String,HashMap<String, Double>> tfIdfResult = tfIDF;
		for(String tweetID: tfIDF.keySet())
		{
			double length = 0;
			for(String id : tfIDF.get(tweetID).keySet())
			{
				length = length + (tfIDF.get(tweetID).get(id))*(tfIDF.get(tweetID).get(id));
			}
			double finalLength = Math.sqrt(length);
			documentLength.put(tweetID, finalLength);
		}
		return documentLength;
	}

	
 public static <K,V extends Comparable<? super V>> 
    List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {

List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

Collections.sort(sortedEntries, 
    new Comparator<Entry<K,V>>() {
        @Override
        public int compare(Entry<K,V> e1, Entry<K,V> e2) {
            return e2.getValue().compareTo(e1.getValue());
        }
    }
);

return sortedEntries;
}
	
	
}
