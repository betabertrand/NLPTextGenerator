import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class textGenerator {


    public static void main(String[] args) {

        String text = null;

        try {
            BufferedReader textFile = new BufferedReader(new FileReader("text.txt"));
            text = textFile.lines().collect(Collectors.joining());
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

        HashMap<String, ArrayList<String>> map = new HashMap<>(wordGram(text));

        String newText = generateNewText(map);

        try {
            PrintWriter outFile = new PrintWriter("newText.txt");
            outFile.println(newText);
            outFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, ArrayList<String>> wordGram(String text) {

        Scanner scanner = new Scanner(text);
        Map<String, ArrayList<String>> wordMap = new HashMap<>();
        String currentWord = scanner.next(); //.replaceAll("[^a-zA-Z ]", "").toLowerCase();;
        String nextWord;
        do {
            nextWord = scanner.next(); //.replaceAll("[^a-zA-Z ]", "").toLowerCase();
            if (!wordMap.containsKey(currentWord)) {
                ArrayList<String> wordList = new ArrayList<>();
                wordList.add(nextWord);
                wordMap.put(currentWord, wordList);
            } else {
                ArrayList<String> wordList = wordMap.get(currentWord);
                wordList.add(nextWord);
                wordMap.put(currentWord, wordList);
            }
            currentWord = nextWord; //.replaceAll("[^a-zA-Z ]", "").toLowerCase();; //Ue this to remove punctiation

        } while (scanner.hasNext());
        {
            currentWord = nextWord;
            if (!wordMap.containsKey(currentWord)) {
                ArrayList<String> wordList = new ArrayList<>();
                wordMap.put(currentWord, wordList);
            } else {
                ArrayList<String> wordList = wordMap.get(currentWord);
                wordMap.put(currentWord, wordList);
            }

        }
        return wordMap;
    }

    public static String generateNewText(Map<String, ArrayList<String>> map) {

        Random random = new Random();
        List<String> keys = new ArrayList<>(map.keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));
        String currWord = randomKey;
        String textResult = "";

        do {
            textResult = textResult + " " + currWord;
            currWord = map.get(currWord).get(random.nextInt(map.get(currWord).size()));

        } while (!map.get(currWord).isEmpty());
        {
            textResult = textResult + " " + currWord;
        }

        return textResult;
    }
}

/*
//Used to print the words and all the words that appear after in in the text.
for (String name : wordMap.keySet()) {
            String key = name.toString();
            ArrayList<String > list = wordMap.get(name);
            String printList = Arrays.toString(list.toArray());
            System.out.println("Key: " + key + " " + printList);
        }
 */
