package com.tokyonth.english.source;

public class SourcesConf {

    private static String[] str_zh;
    /*public static String 数字 = "数字";
    public static String 器官 = "数字";
    public static String 动物 = "数字";
    public static String 颜色 = "数字";
    public static String 交通工具 = "数字";
    public static String 水果 = "数字";
    public static String 蔬菜 = "数字";
    public static String 天气 = "数字";
    public static String 化学 = "数字";
    public static String 物理 = "数字";
    public static String 生物 = "数字";*/

    public static String[] WordForZh(String name) {
        switch (name) {
            case "数字":
                str_zh = new String[]{"一", "二", "三", "四", "五", "六","七","八","九","十"};
                break;
            case "器官":
                str_zh = new String[]{"耳朵", "鼻子", "眼睛", "舌头"};
                break;
            case "动物":
                str_zh = new String[]{"猫", "狗", "老虎", "狮子", "大象"};
                break;
            case "颜色":
                str_zh = new String[]{"红色", "黄色", "绿色", "蓝色", "灰色", "紫色"};
                break;
            default:
                str_zh = new String[]{"测试"};
                break;
        }
        return str_zh;
    }
}
