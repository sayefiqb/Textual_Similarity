# Textual_Similarity
Retrieve and rank top 49 documents from a list of 40,000 documents using vector space model and cosine similarity.

Step-1
The program reads a text file consisting of tweet id and tweet text. First and foremost, the program removes all the stop words from the file and then stems all the redundant and common words. The program also writes the output of preprocessing and stemming into a text file.

Step-2
Next the LoadFile program loads the preprocessedData.txt file and breaks each line from the text file into an id and text portion. The text portion is further divided into tokens and stored in a hash map. The whole preprocessedData.txt is iterated and distinct words are stored in a hash map whose value points to another hash map where the document and frequency of the word in that document is stored. The following data structure has been implemented.

The document frequency is found from the data structure just by finding the size of the hashmap for each term.

The TermFrequency class in the project loads a text file, stores the id as the key and the value points to another second hash map which has words(term) as the key and their frequency as the value of the second hash map.

The tf method in TermFrequency class normalizes the second hash map by finding the max term in the document and then dividing the frequency of terms in that document with the maximum term frequency for that id.

Step-3
The tf and idf are found using the above methods and then cosine similarity is used to find the term an tfidf value. The hash map of the term frequency is multiplied with df value for that term. As a result we get a hash map with key as the tweet id and value as another hash map which stores words as key and their tfidf score as the value.

To find similarity, VSR is used. Cosine similarity is used to find the vector for each tweet. The length of words in each tweet is found and cos=(d1.q)/|d1|.|q|. The higher the number the more similar the document is to the query. 

Step-4 

The LoadFileTest class is used to measure cosine similarity and rank all the similar documents. The similar documents are retrieved for each query in a descending order. A threshold cosine vale of 0.05 is used to filter the relevant document. The output file of the measurement is stored in a text file called Result.txt.

Step-5

This is the step where the relevant documents are retrieved. The output from Result.txt file is broken into separate text files and fed into the Evaluation class to be compared for match in respect to document id against the relevant document text file provided.
The precision and recall for the output of Result.txt file for each query is found by passing it to the evaluation method.

The startup class provides a command line user interface to use the information retrieval system.


User Guide Commands:
In the command prompt-
Type ‘Q1’ through ‘Q49’ to run test on queries against the relevant file to test for relevance and recall measure.

Type ‘T’ to run a similarity test for all the tweets in the data against the queries and retrieve only those that are more than 0.05 relevant.
