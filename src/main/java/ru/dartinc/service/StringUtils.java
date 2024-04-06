package ru.dartinc.service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class StringUtils {
    public static Set<String> fromString(String innerText){
        if(!innerText.isBlank() && !innerText.isEmpty()) {
            String[] tempArr = innerText.split(";");
            return Arrays.stream(tempArr).collect(Collectors.toSet());
        }
        return null;
    }
}
