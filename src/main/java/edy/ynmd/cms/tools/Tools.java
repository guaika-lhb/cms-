package edy.ynmd.cms.tools;

public class Tools {

    public static boolean isNullOrSpace(String str) {
        return null == str || "".equals(str) ? true : false;
    }

}