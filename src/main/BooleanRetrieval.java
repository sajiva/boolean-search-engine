package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BooleanRetrieval {

    private HashMap<String, Set<Integer>> invIndex;
    private int[][] docs;
    private HashSet<String> vocab;
    private HashMap<Integer, String> map;  // int -> word
    private HashMap<String, Integer> i_map; // inv word -> int map

    public BooleanRetrieval() {
        // Initialize variables and Format the data using a pre-processing class and set up variables
        invIndex = new HashMap<>();
        DatasetFormatter formater = new DatasetFormatter();
        formater.textCorpusFormatter("./all.txt");
        docs = formater.getDocs();
        vocab = formater.getVocab();
        map = formater.getVocabMap();
        i_map = formater.getInvMap();
    }

    void createPostingList() {
        //Initialze the inverted index with a SortedSet (so that the later additions become easy!)
        for (String s : vocab) {
            invIndex.put(s, new TreeSet<>());
        }
        //for each doc
        for (int i = 0; i < docs.length; i++) {
            //for each word of that doc
            for (int j = 0; j < docs[i].length; j++) {
                //Get the actual word in position j of doc i
                String w = map.get(docs[i][j]);

				/*
				Get the existing posting list for this word w and add the new doc in the list. 
				Keep in mind doc indices start from 1, we need to add 1 to the doc index , i
				 */
                invIndex.get(w).add(i + 1);

            }
        }
    }

    public Set<Integer> getPostingList(String word) {
        Set<Integer> plist = new TreeSet<>();

        if (invIndex.containsKey(word))
            plist = invIndex.get(word);

        return plist;
    }

    private Set<Integer> intersection(Set<Integer> a, Set<Integer> b) {
		/*
		First convert the posting lists from sorted set to something we 
		can iterate easily using an index. I choose to use ArrayList<Integer>.
		Once can also use other enumerable.
		 */
        Set<Integer> result = new TreeSet<>();
        ArrayList<Integer> PostingList_a = new ArrayList<>(a);
        ArrayList<Integer> PostingList_b = new ArrayList<>(b);

        //Set indices to iterate two lists. I use i, j
        int i = 0;
        int j = 0;

        while (i != PostingList_a.size() && j != PostingList_b.size()) {
            int x = PostingList_a.get(i);
            int y = PostingList_b.get(j);
            //Implement the intersection algorithm here
            if (x == y) {
                result.add(x);
                i++;
                j++;
            } else if (x < y) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    Set<Integer> evaluateANDQuery(String a, String b) {
        return intersection(getPostingList(a), getPostingList(b));
    }

    private Set<Integer> union(Set<Integer> a, Set<Integer> b) {
		/*
		 * IMP note: you are required to implement OR and cannot use Java Collections methods directly, e.g., .addAll whcih solves union in 1 line!
		 * TO-DO: Figure out how to perform union extending the posting list intersection method discussed in class?
		 */
        ArrayList<Integer> PostingList_a = new ArrayList<>(a);
        ArrayList<Integer> PostingList_b = new ArrayList<>(b);
        Set<Integer> result = new TreeSet<>();
        // Implement Union here
        int i = 0;
        int j = 0;

        while (i != PostingList_a.size() && j != PostingList_b.size()) {
            int x = PostingList_a.get(i);
            int y = PostingList_b.get(j);

            if (x == y) {
                result.add(x);
                i++;
                j++;
            } else if (x < y) {
                result.add(x);
                i++;
            } else {
                result.add(y);
                j++;
            }
        }

        while (i < PostingList_a.size()) {
            result.add(PostingList_a.get(i));
            i++;
        }

        while (j < PostingList_b.size()) {
            result.add(PostingList_b.get(j));
            j++;
        }
        return result;
    }

    Set<Integer> evaluateORQuery(String a, String b) {
        return union(getPostingList(a), getPostingList(b));
    }

    private Set<Integer> not(Set<Integer> a) {
        Set<Integer> result = new TreeSet<>();
		/*
		 Hint:
		 NOT is very simple. I traverse the sorted posting list between i and i+1 index
		 and add the other (NOT) terms in this posting list between these two pointers
		 First convert the posting lists from sorted set to something we 
		 can iterate easily using an index. I choose to use ArrayList<Integer>.
		 Once can also use other enumerable.
		 */

        ArrayList<Integer> PostingList_a = new ArrayList<>(a);
        int total_docs = docs.length;
        // TO-DO: Implement the not method using above idea or anything you find better!
        int i = 1;
        int j = 0;


        while (i <= total_docs) {
            int k = (j == PostingList_a.size()) ? total_docs : PostingList_a.get(j);

            while (i < k) {
                result.add(i);
                i++;
            }
            j++;
            i++;
        }

        return result;
    }

    Set<Integer> evaluateNOTQuery(String a) {
        return not(getPostingList(a));
    }

    Set<Integer> evaluateAND_NOTQuery(String a, String b) {
        return intersection(getPostingList(a), evaluateNOTQuery(b));
    }

    private void writeToFile(String filename, String query, String result) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(query + " -> " + result);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing to output file.");
        }
    }

    public void evaluateQuery(String[] args) {
        String errorMsg = "Error: Incorrect parameters!\n" +
                "Correct usage: BooleanRetrieval query_type query_string output_file_path";

        if (args.length < 3) {
            System.err.println(errorMsg);
            return;
        }

        String query_type = args[0].toUpperCase();
        String query;
        String result = "";
        String filename;
        String[] words = new String[2];

        switch (query_type) {
            case "PLIST":
                query = args[1].toLowerCase();
                result = getPostingList(query).toString();
                filename = args[2];
                break;

            case "AND":
            case "OR":
            case "AND_NOT":
                if (args.length < 5) {
                    System.err.println(errorMsg);
                    return;
                }
                if (!args[2].toUpperCase().equals(query_type)) {
                    System.err.println("Error: Query does not match the query type.");
                    return;
                }

                words[0] = args[1].toLowerCase();
                words[1] = args[3].toLowerCase();
                query = words[0] + " " + query_type + " " + words[1];

                switch (query_type) {
                    case "AND":
                        result = evaluateANDQuery(words[0], words[1]).toString();

                        break;
                    case "OR":
                        result = evaluateORQuery(words[0], words[1]).toString();

                        break;
                    case "AND_NOT":
                        result = evaluateAND_NOTQuery(words[0], words[1]).toString();

                        break;
                }

                filename = args[4];
                break;

            default:
                System.err.println("Error: Query type should be one of the 4 values: PLIST, AND, OR, AND_NOT.");
                return;
        }

        writeToFile(filename, query, result);
    }

    public static void main(String[] args) throws Exception {
        //Initialize parameters
        BooleanRetrieval model = new BooleanRetrieval();

        //Generate posting lists
        model.createPostingList();

        model.evaluateQuery(args);

    }

}