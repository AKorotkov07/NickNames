import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BeautifulNicknameCounter {

    private static final AtomicInteger beautifulWordsLength3 = new AtomicInteger(0);
    private static final AtomicInteger beautifulWordsLength4 = new AtomicInteger(0);
    private static final AtomicInteger beautifulWordsLength5 = new AtomicInteger(0);

    public static void main(String[] args) {
        String[] texts = generateTexts(100_000);

        Thread palindromeThread = new Thread(() -> countBeautifulPalindromes(texts));
        Thread singleLetterThread = new Thread(() -> countBeautifulSingleLetters(texts));
        Thread increasingLetterThread = new Thread(() -> countBeautifulIncreasingLetters(texts));

        palindromeThread.start();
        singleLetterThread.start();
        increasingLetterThread.start();

        try {
            palindromeThread.join();
            singleLetterThread.join();
            increasingLetterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Красивых слов с длиной 3: " + beautifulWordsLength3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + beautifulWordsLength4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + beautifulWordsLength5.get() + " шт");
    }

    public static String[] generateTexts(int count) {
        Random random = new Random();
        String[] texts = new String[count];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        return texts;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void countBeautifulPalindromes(String[] texts) {
        for (String text : texts) {
            if (isPalindrome(text)) {
                switch (text.length()) {
                    case 3:
                        beautifulWordsLength3.incrementAndGet();
                        break;
                    case 4:
                        beautifulWordsLength4.incrementAndGet();
                        break;
                    case 5:
                        beautifulWordsLength5.incrementAndGet();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void countBeautifulSingleLetters(String[] texts) {
        for (String text : texts) {
            if (isSingleLetter(text)) {
                switch (text.length()) {
                    case 3:
                        beautifulWordsLength3.incrementAndGet();
                        break;
                    case 4:
                        beautifulWordsLength4.incrementAndGet();
                        break;
                    case 5:
                        beautifulWordsLength5.incrementAndGet();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static void countBeautifulIncreasingLetters(String[] texts) {
        for (String text : texts) {
            if (isIncreasingLetters(text)) {
                switch (text.length()) {
                    case 3:
                        beautifulWordsLength3.incrementAndGet();
                        break;
                    case 4:
                        beautifulWordsLength4.incrementAndGet();
                        break;
                    case 5:
                        beautifulWordsLength5.incrementAndGet();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static boolean isPalindrome(String text) {
        int length = text.length();
        for (int i = 0; i < length / 2; i++) {
            if (text.charAt(i) != text.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSingleLetter(String text) {
        char firstChar = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != firstChar) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIncreasingLetters(String text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i + 1) < text.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
