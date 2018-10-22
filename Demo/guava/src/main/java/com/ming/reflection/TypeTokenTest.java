package com.ming.reflection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;

import java.util.ArrayList;


/**
 *
 */
public class TypeTokenTest {
    public static void main(String[] args) {
        TypeToken typeToken = TypeToken.of(String.class);
        TypeToken stringListToken = new TypeToken<ArrayList<String>>() {};


    }
}
