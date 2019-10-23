package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     *
     *  Time complexity: O(len(n) + len(m))
     * Space complexity: O(len(n) + len(m))
     */


    static public String longestCommonSubstring(String first, String second) {
        try {
            SuffixAutomation sa = new SuffixAutomation(second.length());
            for (int i = 0; i < second.length(); i++) {
                sa.extend(second.charAt(i));
            }

            return sa.lcs(first);

        } catch (Exception e) {
            throw new NotImplementedError();
        }
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     *
     *   NOT DONE
     */
//    private static class Pair<I extends Number, I1 extends Number> {
//        char a;
//        int b;
//
//        Pair(Character a, int b) {
//            this.a = a;
//            this.b = b;
//        }
//
//        int getFirst() {
//            return a;
//        }
//
//        int getSecond() {
//            return b;
//        }
//    }
//
//    private static void findWord(Set<String> answer, int height, int width, WordTree tree, char[] letters,
//                                 int x, int y, int pos, Set<Pair<Number, Number>> history) {
//        history.add(new Pair<Number, Number>(letters[x + width * y], pos));
//    }

    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
//        try {
//            HashSet<String> answer = new HashSet<>();
//
//            int treeMaxSize = 0;
//
//            for (String word : words)
//                treeMaxSize += word.length();
//
//            WordTree tree = new WordTree(treeMaxSize);
//
//            for (String word : words)
//                tree.add(word);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputName)));
//            StringBuilder sb = new StringBuilder();
//
//            String in;
//            int height = 0;
//            while ((in = br.readLine()) != null) {
//                sb.append(in.replace(" ", ""));
//                height++;
//            }
//            char[] letters = sb.toString().toCharArray();
//
//            if (height == 0) {
//                return answer;
//            }
//
//            int width = letters.length / height;
//
//            for (int y = 0; y < height; y++) {
//                for (int x = 0; x < width; x++) {
//                    if (tree.isInTree(letters[x + width * y])) {
//                        findWord(answer, height, width, tree, letters, x, y,
//                                tree.getChar(0, letters[x + width * y]), new HashSet<>());
//                    }
//                }
//            }
//
//            return answer;
//        } catch (Exception e) {
            throw new NotImplementedError();
//        }
    }
}
