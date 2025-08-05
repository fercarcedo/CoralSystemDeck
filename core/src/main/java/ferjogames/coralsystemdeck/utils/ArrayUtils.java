package ferjogames.coralsystemdeck.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by fer on 31/08/16.
 */
public final class ArrayUtils {
    public static List<String> fromString(String arrayAsStr) {
        //Remove starting braces
        arrayAsStr = arrayAsStr.substring(1, arrayAsStr.length() - 1);
        String[] parts = arrayAsStr.split(",");
        List<String> result = new ArrayList<String>();

        //Trim all elements
        for(int i = 0; i < parts.length; i++)
            result.add(parts[i].trim());

        return result;
    }

    public static boolean binarySearchContains(List<String> elements, String element) {
        return Collections.binarySearch(elements, element) >= 0;
    }
}
