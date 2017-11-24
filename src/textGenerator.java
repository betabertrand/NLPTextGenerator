import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

        String word = generateNewText(map);



    }

    public static Map<String, ArrayList<String>> wordGram(String text) {

        Scanner scanner = new Scanner(text);
        Map<String, ArrayList<String>> wordMap = new HashMap<>();
        String currentWord  = scanner.next(); //.replaceAll("[^a-zA-Z ]", "").toLowerCase();;
        String nextWord;
        while (scanner.hasNext()) {
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
            currentWord = nextWord;//.replaceAll("[^a-zA-Z ]", "").toLowerCase();;

            if (!scanner.hasNext()) {
                currentWord = nextWord;
                if (!wordMap.containsKey(currentWord)) {
                    ArrayList<String> wordList = new ArrayList<>();
                    wordMap.put(currentWord, wordList);
                } else {
                    ArrayList<String> wordList = wordMap.get(currentWord);
                    wordMap.put(currentWord, wordList);
                }
            }
        }




        for (String name : wordMap.keySet()) {
            String key = name.toString();
            ArrayList<String > list = wordMap.get(name);
            String printList = Arrays.toString(list.toArray());
            System.out.println("Key: " + key + " " + printList);
        }

        return wordMap;
    }

    public static String generateNewText(Map<String, ArrayList<String>> map) {

        Random random = new Random();
        List<String> keys = new ArrayList<>(map.keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));
        //System.out.println(randomKey);
        String currWord = randomKey;
        String textResult = "";

        do {
            textResult = textResult + " " + currWord;
            currWord = map.get(currWord).get(random.nextInt(map.get(currWord).size()));

        } while (!map.get(currWord).isEmpty()); {
            textResult = textResult + " " + currWord;
        }

        System.out.println(textResult);

        return null;
    }


}

/*
for (String name : wordMap.keySet()) {
            String key = name.toString();
            ArrayList<String > list = wordMap.get(name);
            String printList = Arrays.toString(list.toArray());
            System.out.println("Key: " + key + " " + printList);
        }
 */
