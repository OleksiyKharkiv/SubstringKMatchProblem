import java.io.*;
import java.util.*;

public class SubstringKMatchProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        // Чтение входных данных
        String[] firstLine = br.readLine().split(" ");
        int q = Integer.parseInt(firstLine[1]); // Количество запросов

        String s = br.readLine(); // Входная строка S
        StringBuilder result = new StringBuilder();

        // Обработка запросов
        for (int i = 0; i < q; i++) {
            String[] query = br.readLine().split(" ");
            int l = Integer.parseInt(query[0]); // Левая граница (индексация с 1)
            int r = Integer.parseInt(query[1]); // Правая граница (индексация с 1)
            int k = Integer.parseInt(query[2]); // k-ый символ в подстроке S[l..r]

            // Индексы с 0
            String substring = s.substring(l - 1, r); // Берем подстроку S[l..r] с учётом 0-индексации
            System.out.println(substring);
            // Ищем результат для текущего запроса
            int match = findMatch(substring, k); // Внутри метода индексация будет с 1
            result.append(match).append("\n");
        }

        br.close();

        // Записываем вывод в файл
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        bw.write(result.toString());
        bw.close();
    }

    // Функция для поиска k-го символа в подстроке
    private static int findMatch(String substring, int k) {
        // Создаем списки для индексов символов 'A' и 'B' в подстроке
        List<Integer> aPositions = new ArrayList<>();
        List<Integer> bPositions = new ArrayList<>();

        // Заполняем списки для символов 'A' и 'B' в подстроке
        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) == 'A') {
                aPositions.add(i); // Индексация с 0
            } else {
                bPositions.add(i); // Индексация с 0
            }
        }

        // Определяем символ по индексу k-1
        char targetChar = substring.charAt(k - 1); // Получаем символ на позиции k-1

        if (targetChar == 'A') {
            // Если это 'A', то находим его порядковый номер среди символов 'A'
            int aIndex = aPositions.indexOf(k - 1);
            if (aIndex != -1 && aIndex < bPositions.size()) {
                return bPositions.get(aIndex) + 1; // Возвращаем соответствующую позицию 'B' с добавлением 1 для индексации с 1
            }
        } else if (targetChar == 'B') {
            // Если это 'B', то находим его порядковый номер среди символов 'B'
            int bIndex = bPositions.indexOf(k - 1);
            if (bIndex != -1 && bIndex < aPositions.size()) {
                return aPositions.get(bIndex) + 1; // Возвращаем соответствующую позицию 'A' с добавлением 1 для индексации с 1
            }
        }

        return -1; // Если соответствия не найдено, возвращаем -1
    }

}