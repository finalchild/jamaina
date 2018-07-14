package me.finalchild.jamaina.block;

import me.finalchild.jamaina.JamoType;

public final class HangulJamo {

    private HangulJamo() {}

    public static char START_POINT = 'ᄀ';
    public static char INITIAL_CONSONANT_END_POINT = 'ᅟ';
    public static char MEDIAL_VOWEL_START_POINT = 'ᅠ';
    public static char MEDIAL_VOWEL_END_POINT = 'ᆧ';
    public static char FINAL_CONSONANT_START_POINT = 'ᆨ';
    public static char END_POINT = 'ᇿ';

    public static boolean isInHangulJamo(char character) {
        return character >= START_POINT && character <= END_POINT;
    }

    public static JamoType getJamoType(char character) {
        if (!isInHangulJamo(character)) {
            throw new IllegalArgumentException("The character " + character + " is not in Hangul Jamo block.");
        }
        if (character <= INITIAL_CONSONANT_END_POINT || character == 'ᅟ') {
            return JamoType.INITIAL_CONSONANT;
        } else if (character >= MEDIAL_VOWEL_START_POINT && character <= MEDIAL_VOWEL_END_POINT) {
            return JamoType.MEDIAL_VOWEL;
        } else {
            return JamoType.FINAL_CONSONANT;
        }
    }

    public static final class HangulCompatibilityJamoUtil {

        private HangulCompatibilityJamoUtil() {}

        private static final char[] hangulCompatibilityJamoByInitialConsonantValue = {
                'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
        };
        private static final char[] hangulCompatibilityJamoByFinalConsonantValue = {
                '\0', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
        };

        public static char convertToCompatibilityJamo(char character) {
            if (!isInHangulJamo(character)) {
                throw new IllegalArgumentException("The character " + character + " is not in Hangul Jamo block.");
            }
            switch (getJamoType(character)) {
                case INITIAL_CONSONANT:
                    if (character > 'ᄒ') {
                        throw new IllegalArgumentException("The character " + character + " is not convertible to Hangul Compatibility Jamo.");
                    }
                    return hangulCompatibilityJamoByInitialConsonantValue[character - 'ᄀ'];
                case MEDIAL_VOWEL:
                    if (character > 'ᅵ') {
                        throw new IllegalArgumentException("The character " + character + " is not convertible to Hangul Compatibility Jamo.");
                    }
                    return (char) (character - 'ᅡ' + 'ㅏ');
                case FINAL_CONSONANT:
                    if (character > 'ᇂ') {
                        throw new IllegalArgumentException("The character " + character + " is not convertible to Hangul Compatibility Jamo.");
                    }
                    return hangulCompatibilityJamoByFinalConsonantValue[character - 'ᆨ'];
            }
            return '\0'; // Never happens.
        }

    }

}
