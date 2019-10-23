package lesson2;

import java.util.HashMap;
import java.util.Map;

class WordTree {
    public class state {
        int parent;

        public boolean isFound() {
            return isFound;
        }

        public boolean isEnd() {
            return isEnd;
        }

        boolean isFound = false;
        boolean isEnd = false;
        Map<Character, Integer> next = new HashMap<>();
    }

    private state[] states;
    private int sn = 1;

    WordTree(int maxSize) {
        states = new state[maxSize + 1];

        states[0] = new state();
        states[0].parent = -1;
    }

    void add(String word) {
        int numInWord = 0;
        int curPos = 0;

        while (states[curPos].next.containsKey(word.charAt(numInWord))) {
            curPos = states[numInWord].next.get(word.charAt(numInWord++));
        }

        while (numInWord < word.length()) {
            states[curPos].next.put(word.charAt(numInWord++), sn);
            states[sn] = new state();
            states[sn].parent = curPos;
            curPos = sn++;
        }
        states[curPos].isEnd = true;
    }

    String returnWord(int pos) {
        StringBuilder sb = new StringBuilder();
        int p = pos;

        while (states[p].parent != -1) {
            for (Map.Entry<Character, Integer> entry : states[states[p].parent].next.entrySet()) {
                if (entry.getValue() == p) {
                    sb.append(entry.getKey());
                }
            }
        }
        return sb.reverse().toString();
    }

    boolean isInTree(char c) {
        return states[0].next.containsKey(c);
    }
    boolean isNext(int step, char c) {
        return states[step].next.containsKey(c);
    }

    int getChar(int pos, char c) {
        return states[pos].next.get(c);
    }
}
