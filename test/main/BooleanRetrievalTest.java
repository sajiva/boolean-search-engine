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

    @Test
    public void testNotQuery1() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(3, 5, 12, 18, 20, 29, 30, 36, 38, 46, 48, 52, 54, 57, 58, 59, 69, 74, 82,
                84, 96, 97, 98, 101, 102, 105, 107, 116, 123, 124, 130, 131, 133, 134, 135, 142, 155, 162, 172, 185, 189, 194, 195,
                197, 198, 200, 208, 212, 214, 223, 225, 227, 229, 231, 234, 238, 242, 243, 245, 247, 256, 258, 259, 270, 272, 275,
                297, 298, 301, 309, 315, 325, 326, 328, 332, 333, 342, 352, 367, 370, 375, 380, 383, 384, 389, 391, 401, 402, 410,
                413, 414, 415, 418, 433, 435, 439, 441, 443, 449, 450, 451, 452, 453, 458, 459, 462, 463, 467, 469, 474, 482, 489,
                494, 503, 512, 528, 530, 531, 536, 537, 539, 542, 545, 546, 549, 558, 560, 561, 570, 578, 579, 582, 583, 585, 586,
                593, 606, 608, 611, 614, 621, 624, 629, 633, 639, 663, 667, 677, 682, 688, 689, 692, 694, 698, 703, 705, 708, 713,
                718, 728, 732, 736, 743, 745, 752, 754, 761, 766, 774, 782, 785, 799, 801, 803, 807, 812, 817, 819, 827, 837, 842,
                846, 847, 848, 849, 852, 853, 871, 872, 873, 888, 892, 894, 915, 916, 919, 921, 922, 924, 929, 939, 940, 943, 946,
                950, 952, 963, 965, 966, 969, 970, 972, 977, 979, 985, 987, 988, 990, 991, 1003, 1017, 1033, 1034, 1040, 1041, 1050,
                1053, 1058, 1059, 1064, 1070, 1082, 1083, 1085, 1089, 1092, 1093, 1096, 1098, 1099, 1102, 1104, 1105, 1114, 1115,
                1123, 1125, 1140, 1143, 1147, 1148, 1157, 1162, 1165, 1170, 1171, 1174, 1175, 1178, 1181, 1186, 1188, 1192, 1195,
                1201, 1217, 1219, 1221, 1233, 1236, 1241, 1244, 1245, 1246));

        Assert.assertEquals(result, model.evaluateNOTQuery("keyboard"));
    }

    @Test
    public void testAndNotQuery1() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(40, 43, 55, 130, 213, 310, 441, 462, 477, 508, 529, 565, 773, 852, 879, 901, 902, 933, 1060, 1102, 1103, 1157, 1221));

        Assert.assertEquals(result, model.evaluateAND_NOTQuery("scroll", "mouse"));
    }

    @Test
    public void testAndNotQuery2() {
        Set<Integer> result = new TreeSet<>(Arrays.asList(360, 373, 379, 451, 517, 540, 787, 869, 942, 1055, 1146));

        Assert.assertEquals(result, model.evaluateAND_NOTQuery("lenovo", "logitech"));
    }

}