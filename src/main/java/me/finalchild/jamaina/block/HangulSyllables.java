package me.finalchild.jamaina.block;

import me.finalchild.jamaina.JamoType;

import java.util.Objects;

/**
 * Processes Hangul Syllables, a Unicode block containing precomposed Hangul syllable blocks for Modern Korean.
 */
public final class HangulSyllables {

    public static final char START_POINT = '가';
    public static final char END_POINT = '힣';

    public static boolean isInHangulSyllables(char character) {
        return character >= START_POINT && character <= END_POINT;
    }

    public static byte getJamoValue(char character, JamoType jamoType) {
        Objects.requireNonNull(jamoType, "jamoType shouldn't be null.");
        switch (jamoType) {
            case INITIAL_CONSONANT:
                return getInitialConsonantValue(character);
            case MEDIAL_VOWEL:
                return getMedialVowelValue(character);
            case FINAL_CONSONANT:
                return getFinalConsonantValue(character);
        }
        return 0; // Never happens.
    }

    public static byte getInitialConsonantValue(char character) {
        if (!isInHangulSyllables(character)) {
            throw new IllegalArgumentException("The character " + character + " is not in Hangul Syllable block.");
        }

        return (byte) ((character - START_POINT) / 588);
    }

    public static byte getMedialVowelValue(char character) {
        if (!isInHangulSyllables(character)) {
            throw new IllegalArgumentException("The character " + character + " is not in Hangul Syllable block.");
        }

        return (byte) ((character - START_POINT) % 588 / 28);
    }

    public static byte getFinalConsonantValue(char character) {
        if (!isInHangulSyllables(character)) {
            throw new IllegalArgumentException("The character " + character + " is not in Hangul Syllable block.");
        }

        return (byte) ((character - START_POINT) % 28);
    }

    public static final class HangulCompatibilityJamoUtil {

        private HangulCompatibilityJamoUtil() {}

        private static final char[] hangulCompatibilityJamoByInitialConsonantValue = {
                'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
        };
        private static final char[] hangulCompatibilityJamoByFinalConsonantValue = {
                '\0', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
        };

        public static char getJamo(char character, JamoType jamoType) {
            Objects.requireNonNull(jamoType, "jamoType shouldn't be null.");
            switch (jamoType) {
                case INITIAL_CONSONANT:
                    return getInitialConsonantJamo(character);
                case MEDIAL_VOWEL:
                    return getMedialVowelJamo(character);
                case FINAL_CONSONANT:
                    return getFinalConsonantJamo(character);
            }
            return '\0'; // Never happens.
        }

        public static char getInitialConsonantJamo(char character) {
            return convertInitialConsonantValueToJamo(getInitialConsonantValue(character));
        }

        public static char getMedialVowelJamo(char character) {
            return convertMedialVowelValueToJamo(getMedialVowelValue(character));
        }

        public static char getFinalConsonantJamo(char character) {
            return convertFinalConsonantValueToJamo(getFinalConsonantValue(character));
        }

        public static char convertInitialConsonantValueToJamo(byte initialConsonantValue) {
            if (initialConsonantValue < 0 || initialConsonantValue >= hangulCompatibilityJamoByInitialConsonantValue.length) {
                throw new IllegalArgumentException("Illegal initialConsonantValue.");
            }
            return hangulCompatibilityJamoByInitialConsonantValue[initialConsonantValue];
        }

        public static char convertMedialVowelValueToJamo(byte medialVowelValue) {
            if (medialVowelValue < 0 || medialVowelValue >= 21) {
                throw new IllegalArgumentException("Illegal medialVowelValue.");
            }
            return (char) ('ㅏ' + medialVowelValue);
        }

        public static char convertFinalConsonantValueToJamo(byte finalConsonantValue) {
            if (finalConsonantValue < 0 || finalConsonantValue >= hangulCompatibilityJamoByFinalConsonantValue.length) {
                throw new IllegalArgumentException("Illegal finalConsonantValue.");
            }
            return hangulCompatibilityJamoByFinalConsonantValue[finalConsonantValue];
        }

    }

    public static final class HangulJamoUtil {

        private HangulJamoUtil() {}

        public static char getJamo(char character, JamoType jamoType) {
            Objects.requireNonNull(jamoType, "jamoType shouldn't be null.");
            switch (jamoType) {
                case INITIAL_CONSONANT:
                    return getInitialConsonantJamo(character);
                case MEDIAL_VOWEL:
                    return getMedialVowelJamo(character);
                case FINAL_CONSONANT:
                    return getFinalConsonantJamo(character);
            }
            return 0; // Never happens.
        }

        public static char getInitialConsonantJamo(char character) {
            return convertInitialConsonantValueToJamo(getInitialConsonantValue(character));
        }

        public static char getMedialVowelJamo(char character) {
            return convertMedialVowelValueToJamo(getMedialVowelValue(character));
        }

        public static char getFinalConsonantJamo(char character) {
            return convertFinalConsonantValueToJamo(getFinalConsonantValue(character));
        }

        public static char convertInitialConsonantValueToJamo(byte initialConsonantValue) {
            if (initialConsonantValue < 0 || initialConsonantValue >= 19) {
                throw new IllegalArgumentException("Illegal initialConsonantValue.");
            }
            return (char) ('ᄀ' + initialConsonantValue);
        }

        public static char convertMedialVowelValueToJamo(byte medialVowelValue) {
            if (medialVowelValue < 0 || medialVowelValue >= 21) {
                throw new IllegalArgumentException("Illegal medialVowelValue.");
            }
            return (char) ('ᅡ' + medialVowelValue);
        }

        public static char convertFinalConsonantValueToJamo(byte finalConsonantValue) {
            if (finalConsonantValue < 0 || finalConsonantValue >= 27) {
                throw new IllegalArgumentException("Illegal finalConsonantValue.");
            }
            if (finalConsonantValue == 0) {
                return '\0';
            }
            return (char) ('ᆧ' + finalConsonantValue);
        }

        public static String convertSyllableToJamoes(char character) {
            char initialConsonantJamo = getInitialConsonantJamo(character);
            char medialVowelJamo = getMedialVowelJamo(character);
            char finalConsonantJamo = getFinalConsonantJamo(character);

            if (finalConsonantJamo == '\0') {
                return new String(new char[] { initialConsonantJamo, medialVowelJamo });
            } else {
                return new String(new char[] { initialConsonantJamo, medialVowelJamo, finalConsonantJamo });
            }
        }

    }

}
