package com.freelance.pigLatin;

public class PigLatin {

    public String encode(String sentence) {
        StringBuilder pigLatinSentence = new StringBuilder();

        if (!isSentenceValid(sentence))
            return "invalid input";

        Character punct = null;
        String woPunct = sentence;

        if (!Character.isLetterOrDigit(sentence.charAt(sentence.length() - 1))) {
            punct = sentence.charAt(sentence.length() - 1);
            woPunct = sentence.substring(0, sentence.length() - 1);
        }

        String[] words = woPunct.split(" ");

        for (String word : words) {
            pigLatinSentence.append(convertWord(word));
            pigLatinSentence.append(" ");
        }

        pigLatinSentence.deleteCharAt(pigLatinSentence.length() - 1);

        if (punct != null) {
            pigLatinSentence.append(punct);

        }
        return pigLatinSentence.toString();
    }

    public String convertWord(String word) {
        StringBuilder sb = new StringBuilder(word);
        sb.append(word.charAt(0));
        sb.append("ay");
        sb.deleteCharAt(0);

        return sb.toString();

    }

    public boolean isSentenceValid(String sentence) {

        for (int i = 0; i < sentence.length(); i++) {
            if (!(Character.isLetter(sentence.charAt(i)) || sentence.charAt(i) == ' ')) {
                return false;
            }
        }

        return true;
    }

    @Test
    void encodeValidInput() {

    }

}
