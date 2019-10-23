package lesson2;

import java.util.HashMap;
import java.util.Map;

/**
 *  Time complexity: O(n * log(size of alphabet)) --> O(n)
 * Space complexity: O(n)
*/

class SuffixAutomation {
    public class state {
        int len, link;
        Map<Character, Integer> next = new HashMap<>();
    }

    private int sz = 1;
    private int last = 0;

    private state[] states;

    SuffixAutomation(int length) {
        states = new state[length * 2];

        states[0] = new state();
        states[0].len = 0;
        states[0].link = -1;
    }

    /**
     *  Online algorithm that adds a new char to the graph in linear time
     *
     */
    void extend(char c) {
        int cur = sz++;

        if (states[cur] == null) {
            states[cur] = new state();
        }
        states[cur].len = states[last].len + 1;

        int p;
        for (p = last; p != -1 && !states[p].next.containsKey(c); p = states[p].link)
            states[p].next.put(c, cur);

        if (p == -1)
            states[cur].link = 0;
        else {
            int q = states[p].next.get(c);

            if (states[p].len + 1 == states[q].len)
                states[cur].link = q;
            else {
                int clone = sz++;

                if (states[clone] == null) {
                    states[clone] = new state();
                }
                states[clone].len = states[p].len + 1;
                states[clone].next.putAll(states[q].next);
                states[clone].link = states[q].link;

                for (; p != -1 && states[p].next.get(c) == q; p = states[p].link) {
                    states[p].next.put(c, clone);
                }

                states[q].link = states[cur].link = clone;
            }
        }
        last = cur;
    }

    /**
     *
     *  Time complexity: O(len(n) + len(m))
     *
     */

    String lcs(String string) {
        int v = 0;
        int len = 0;
        int best = 0;
        int bestp = 0;

        for (int i = 0; i < string.length(); i++) {
            char ati = string.charAt(i);

            while (v != 0 && !states[v].next.containsKey(ati)) {
                v = states[v].link;
                if (v == -1) {
                    v = 0;
                    len = 0;
                    break;
                }
                len = states[v].len;
            }
            if (states[v].next.containsKey(ati)) {
                v = states[v].next.get(ati);
                len++;
            }
            if (len > best) {
                best = len;
                bestp = i;
            }
        }
        return string.substring(bestp - best + 1, bestp + 1);
    }
}
