package lesson6;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     *
     *  Time complexity: O(n^2)
     * Space complexity: O(n)
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        List<Integer> answer = new ArrayList<>();
        if (list.isEmpty())
            return answer;

        int[] ends = new int[list.size()];
        int[] parent = new int[list.size()];
        int last = 0;

        for (int i = 0; i < list.size(); i++) {
            ends[i] = 1;
            parent[i] = -1;

            for (int k = 0; k < i; k++) {
                if (list.get(k) < list.get(i) && ends[k] + 1 > ends[i]) {
                    ends[i] = ends[k] + 1;
                    parent[i] = k;

                    if (ends[i] > ends[last])
                        last = i;
                }
            }
        }

        while (last != -1) {
            answer.add(list.get(last));
            last = parent[last];
        }

        Collections.reverse(answer);
        return answer;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     *
     *  Time complexity: O(Width * Length)
     * Space complexity: O(Width)
     */
    public static int shortestPathOnField(String inputName) {
        try {
            List<Integer> ansList = new ArrayList<>();
            ansList.add(0);
            boolean isFirst = true;

            BufferedReader bf = new BufferedReader(new FileReader(inputName));
            String str;
            while ((str = bf.readLine()) != null) {
                List<Integer> line = new ArrayList<>();
                Arrays.stream(str.split(" ")).forEach(it -> line.add(Integer.parseInt(it)));

                if (isFirst) {
                    for (int i = 1; i < line.size(); i++) {
                        ansList.add(i, line.get(i) + ansList.get(i - 1));
                    }
                    isFirst = false;
                }
                else {
                    List<Integer> list2 = new ArrayList<>();
                    list2.add(line.get(0) + ansList.get(0));

                    for (int i = 1; i < line.size(); i++) {
                        list2.add(line.get(i) + Math.min(Math.min(ansList.get(i - 1), ansList.get(i)), list2.get(i - 1)));
                    }
                    ansList = list2;
                }
            }

            bf.close();

            return ansList.get(ansList.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotImplementedError();
        }
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
