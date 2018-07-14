package me.finalchild.jamaina.block;

import me.finalchild.jamaina.JamoType;

import java.util.Objects;

public final class HangulCompatibilityJamo {

    private HangulCompatibilityJamo() {}

    public static char START_POINT = '\u3130';
    public static char MEDIAL_VOWELS_START_POINT = 'ㅏ';
    public static char MEDIAL_VOWELS_END_POINT = 'ㅣ';
    public static char OBSOLETE_CONSONANT_START_POINT = 'ㅥ';
    public static char END_POINT = '\u318f';


    public static boolean isInHangulCompatibilityJamo(char character) {
        return character >= START_POINT && character <= END_POINT;
    }

    public static final class HangulSyllablesUtil {

        private HangulSyllablesUtil() {}

        private static final byte[] initialConsonantValueByOffset = {
                -1, 0, 1, -1, 2, -1, -1, 3, 4, 5, -1, -1, -1, -1, -1, -1,
                -1, 6, 7, 8, -1, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18
        };

        private static final byte[] finalConsonantValueByOffset = {
                -1, 1, 2, 3, 4, 5, 6, 7, -1, 8, 9, 10, 11, 12, 13, 14,
                15, 16, 17, -1, 18, 19, -1, 20, 21, 22, 23, 24, 25, 26, 27
        };

        public static byte toHangulSyllablesJamoValue(char character, JamoType jamoType) {
            Objects.requireNonNull(jamoType, "jamoType shouldn't be null.");
            switch (jamoType) {
                case INITIAL_CONSONANT:
                    return toHangulSyllablesInitialConsonantValue(character);
                case MEDIAL_VOWEL:
                    return toHangulSyllablesMedialVowelValue(character);
                case FINAL_CONSONANT:
                    return toHangulSyllablesFinalConsonantValue(character);
            }
            return 0; // Never happens.
        }

        public static byte toHangulSyllablesInitialConsonantValue(char character) {
            if (character < START_POINT || character >= START_POINT + initialConsonantValueByOffset.length || initialConsonantValueByOffset[character - START_POINT] == -1) {
                throw new IllegalArgumentException("No associated initial consonant value for the character" + character + ".");
            }
            return initialConsonantValueByOffset[character - START_POINT];
        }

        public static byte toHangulSyllablesMedialVowelValue(char character) {
            if (character < MEDIAL_VOWELS_START_POINT || character > MEDIAL_VOWELS_END_POINT) {
                throw new IllegalArgumentException("No associated medial vowel value for the character" + character + ".");
            }
            return (byte) (character - OBSOLETE_CONSONANT_START_POINT);
        }

        public static byte toHangulSyllablesFinalConsonantValue(char character) {
            if (character < START_POINT || character >= START_POINT + finalConsonantValueByOffset.length || finalConsonantValueByOffset[character - START_POINT] == -1) {
                throw new IllegalArgumentException("No associated final consonant value for the character" + character + ".");
            }
            return finalConsonantValueByOffset[character - START_POINT];
        }

    }

}
