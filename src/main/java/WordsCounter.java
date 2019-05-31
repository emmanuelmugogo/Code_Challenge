
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WordsCounter {
	
	
	// Main method
	public static void main (String [] args ) {
		
		//File path as it is stored to my computer
		String filePath = "/Users/Emmanuel/Downloads/Interview/Paragraph.txt";  
		 
        System.out.println( "**********************************\nParagraph as it Reads before sorting into words counter \n\n\n" +
        		readFileFromPath( filePath ) + "\n\n\nWords Counter starts here\n**********************************\n\n" );
		
	
		String sentence = readFileFromPath( filePath );	
		sentence = sentence.toUpperCase().replaceAll("[-\"|\"$:?,\t\n\r()\\s+]"," ");
		
		
		//replacing the last period on words.
		
		System.out.println(replaceLastChar(sentence, ".", ""));
		
		
		String[] sentenceArray = sentence.split(" ");
		Map<String, Integer> myMap = new HashMap<String, Integer>();
		String myWord = null;
		
		// putting counters into the Map values and use words as keys.
		for (int i = 0; i < sentenceArray.length; i++) {
			myWord = sentenceArray[i];
			myWord = replaceLastChar(myWord, ".", "");
			myMap.put(myWord, returnCounter(sentence, myWord));
		}
		
		// Sorting a map and put to mySortedMap
		Map<String, Integer> mySorterMap = sortByValue(myMap); 
		  
        // Printing a Sorted map. 
        for (Map.Entry<String, Integer> en : mySorterMap.entrySet()) { 
        	if(en.getKey().length() >= 1) {    // checking the length to remove the white spaces printing.
        		System.out.println(en.getKey() + "\t-\t " + en.getValue());
        	}
             
        } 
			 
	}
	
	//Read file content into String
    static String readFileFromPath(String filePath)
    {
        String content = "";
 
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
 
        return content;
    }
   
    // Counting the occurance of the word
	static int returnCounter(String paragraph, String word) {
		int count = 0;
		String myWord = null;
		String[] myArray = paragraph.split(" ");
		for (int i = 0; i < myArray.length; i++) {
			myWord = myArray[i];
			myWord = replaceLastChar(myWord, ".", "");
			if(word.equals(myWord)) {
				count++;
			}
		}
		return count;
	}
	
	// function to sort hashmap by values 
    static HashMap<String, Integer> sortByValue(Map<String, Integer> myMap) { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(myMap.entrySet()); 
        
  
        // Sorting the list 
        Collections.sort(list, Collections.reverseOrder(new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        })); 
          
        // putting data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }
    
    // Logic to replace last period (.) on a word
    static String replaceLastChar(String stringWord, String toReplace, String replacement) {
    	
    	int ind = stringWord.lastIndexOf(toReplace);
    	if( ind>=0 ) {
    		stringWord = new StringBuilder(stringWord).replace(ind, ind+1,replacement).toString();
    	}
    	
    	return stringWord;
    }

}
