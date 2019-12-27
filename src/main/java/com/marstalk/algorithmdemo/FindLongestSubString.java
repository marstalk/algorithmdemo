package com.marstalk.algorithmdemo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mars
 * Created on 11/19/2019
 * //TODO 有bug
 */
public class FindLongestSubString {
    public static void main(String[] args) {
        String a = "cccaaabbcc";
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        Character lastChar = null;
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            updateMap(c, characterIntegerHashMap, lastChar);
            lastChar = c;
        }
        System.out.println(characterIntegerHashMap);
        Character max = a.charAt(0);
        int maxLength = 0;
        for (Map.Entry<Character, Integer> entry : characterIntegerHashMap.entrySet()) {
            if (entry.getValue() > maxLength || (entry.getValue() == maxLength && entry.getKey() < max)) {
                max = entry.getKey();
                maxLength = entry.getValue();
            }
        }
        System.out.println(max);
        System.out.println(maxLength);
        for (int i = 0; i < maxLength; i++) {
            System.out.print(max);
        }
    }

    private static void updateMap(Character c, HashMap<Character, Integer> map, Character lastChar) {
        if (lastChar == null || !lastChar.equals(c)) {
            map.put(c, 1);
        } else {
            Integer integer = map.get(c);
            map.put(c, integer == null ? 1 : integer.intValue() + 1);
        }
    }
}
