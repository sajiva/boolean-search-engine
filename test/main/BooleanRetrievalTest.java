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

    @Test
    public void testAndQuery1() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(113, 158));

        Assert.assertEquals(result, model.evaluateANDQuery("mouse", "wifi"));
    }

    @Test
    public void testAndQuery2() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(80, 86, 348, 1029));

        Assert.assertEquals(result, model.evaluateANDQuery("mouse", "scrolling"));
    }

    @Test
    public void testAndQuery3() {
        Set<Integer> result = new TreeSet<>();

        Assert.assertEquals(result, model.evaluateANDQuery("errors", "report"));
    }

    @Test
    public void testOrQuery1() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(19, 67, 122, 227, 313, 342, 377, 507, 659, 825, 846));

        Assert.assertEquals(result, model.evaluateORQuery("youtube", "reported"));
    }

    @Test
    public void testOrQuery2() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(115, 251, 471, 508, 674, 784, 821, 1068, 1080, 1111, 1158, 1245));

        Assert.assertEquals(result, model.evaluateORQuery("errors", "report"));
    }

    @Test
    public void testOrQuery3() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(3, 89, 147, 192, 235, 262, 336, 342, 558, 766, 864, 882, 1120, 1244));

        Assert.assertEquals(result, model.evaluateORQuery("hell", "movie"));
    }

    @Test
    public void testOrQuery4() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(46, 66, 113, 158, 227, 313, 342, 377, 507, 746, 846, 1248));

        Assert.assertEquals(result, model.evaluateORQuery("youtube", "wifi"));
    }

    @Test
    public void testOrQuery5() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(3, 12, 208, 254, 299, 359, 392, 410, 508, 686, 688, 933, 1066));

        Assert.assertEquals(result, model.evaluateORQuery("address", "info"));
    }
}