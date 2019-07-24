package com.baizhi;

import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Hashtable;
import java.util.SortedSet;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoeasy {
    @Test
    public void test1(){
        GoEasy goeasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-35ead28a4ef84f7f89ffc7c60192d02c");
        goeasy.publish("test1","Hello Everyone!");
    }
    @Test
    public void test2(){
        SortedSet<Integer> set = new TreeSet<>();
    }

}
