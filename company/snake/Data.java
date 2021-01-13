package com.company.snake;

import javax.swing.*;
import java.net.URL;

//数据中心
public class Data {

    //绝对路径 ctrl+alt+o 补全包
    public static  URL headerURL= Data.class.getResource("statics/material/header.png");
    public static ImageIcon header=new ImageIcon(headerURL);
    public static  URL downURL= Data.class.getResource("statics/material/down.png");
    public static ImageIcon down=new ImageIcon(downURL);
    public static  URL leftURL= Data.class.getResource("statics/material/left.png");
    public static ImageIcon left=new ImageIcon(leftURL);
    public static  URL rightURL= Data.class.getResource("statics/material/right.png");
    public static ImageIcon right=new ImageIcon(rightURL);
    public static  URL upURL= Data.class.getResource("statics/material/up.png");
    public static ImageIcon up=new ImageIcon(upURL);
    public static  URL bodyURL= Data.class.getResource("statics/material/body.png");
    public static ImageIcon body=new ImageIcon(bodyURL);
    public static  URL foodURL= Data.class.getResource("statics/material/food.png");
    public static ImageIcon food=new ImageIcon(foodURL);
}
