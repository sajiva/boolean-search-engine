package main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class BooleanRetrievalTest {

    private BooleanRetrieval model;

    @Test
    public void canaryTest() {
        assertTrue(true);
    }

    @Before
    public void setup() throws Exception {
        model = new BooleanRetrieval();
        model.createPostingList();
    }

    @Test
    public void testPostingListFreeze() {
        Set<Integer> list = new TreeSet<>(Arrays.asList(129, 313, 442, 493, 912));

        Assert.assertEquals(list, model.getPostingList().get("freeze"));
    }

    @Test
    public void testPostingListCtrl() {
        Set<Integer> list = new TreeSet<>(Arrays.asList(23, 47, 138, 232, 498, 882, 993, 1106, 1124, 1218));

        Assert.assertEquals(list, model.getPostingList().get("ctrl"));
    }

    @Test
    public void testPostingListWifi() {
        Set<Integer> list = new TreeSet<>(Arrays.asList(46, 66, 113, 158, 746, 1248));

        Assert.assertEquals(list, model.getPostingList().get("wifi"));
    }

    @Test
    public void testPostingListCpu() {
        Set<Integer> list = new TreeSet<>(Arrays.asList(604, 800, 959, 1156));

        Assert.assertEquals(list, model.getPostingList().get("cpu"));
    }

    @Test
    public void testPostingListTossed() {
        Set<Integer> list = new TreeSet<>(Arrays.asList(725, 730, 916, 983, 1178, 1183));

        Assert.assertEquals(list, model.getPostingList().get("tossed"));
    }

    @Test
    public void testPostingListExcited() {
        Set<Integer> list = new TreeSet<>(Arrays.asList(9, 17, 147, 217, 318, 485, 523, 603, 745, 776, 884, 956, 1025, 1042, 1051, 1103, 1218));

        Assert.assertEquals(list, model.getPostingList().get("excited"));
    }

    @Test
    public void testPostingListGreat() {

        Assert.assertEquals(null, model.getPostingList().get("great"));
    }
}