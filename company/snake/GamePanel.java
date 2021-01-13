package com.company.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//游戏面板
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    //定义snake的数据结构
    int length;
    int[] snakeX = new int[600];//25*25
    int[] snakeY = new int[500];
    String direction = "R";//默认方向向右
    //游戏当前的状态：开始，停止，默认不开始
    boolean oneStart = false;
    //游戏失败的状态
    boolean twoFail=false;

    //食物的坐标
    int foodx;
    int foody;
    Random random= new Random();
    //成绩
    int score;

    //构造器
    public GamePanel() {
        init();
        //获得焦点和键盘监听
        //聚焦
        this.setFocusable(true);
        //键盘
        this.addKeyListener(this);
        //游戏一开始就启动
        timer.start();
    }
    //定时器 单位毫秒1000=1s
    Timer timer=new Timer(100,this);
    public void init() {
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;//脑袋坐标
        snakeX[1] = 75;
        snakeY[1] = 100;//第一个身体坐标
        snakeX[2] = 50;
        snakeY[2] = 100;//第二个身体坐标
        direction = "R";
        //食物的随机850/25,600/25
        foodx=25+25*random.nextInt(34);
        foody=75+25*random.nextInt(24);
        score=0;
    }

    //绘制面板
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏防止闪烁
        //绘制静态面板
        this.setBackground(Color.BLACK);
        //画头部广告栏
        Data.header.paintIcon(this, g, 25, 11);
        //画矩形默认游戏页面 snake25*25
        g.fillRect(25, 75, 850, 600);
        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("长度"+length, 750, 35);
        g.drawString("分数"+score, 750, 50);
        // 画食物
        Data.food.paintIcon(this,g,foodx,foody);
        //画snake固定头部默认向右 通过方向来判断
        if (direction.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (direction.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);}
            else if (direction.equals("U")) {
                Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
            }else if (direction.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            //第一个身体坐标
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
//        Data.body.paintIcon(this,g,snakeX[1],snakeY[1]);
//        Data.body.paintIcon(this,g,snakeX[2],snakeY[2]);
        //游戏状态
        if (oneStart == false) {
//            填写文字
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
        }
        if (twoFail){
            //默认true，填写文字
            g.setColor(Color.pink);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("over按下空格开始游戏", 300, 300);
        }
    }
    //事件监听通过固定的事件来刷新，1秒10次
    @Override
    public void actionPerformed(ActionEvent e) {
        if (oneStart&&twoFail==false){
            // 游戏开始状态snake动起来,
           // 移动
            for (int i=length-1;i>0;i--){
                snakeX[i]=snakeX[i-1];//向前移动一位
                snakeY[i]=snakeY[i-1];//向前移动一位可不动
            }
            //走向
            if(direction.equals("R")){
                snakeX[0]=snakeX[0]+25;//头部移动一位
                //边界判断
                if ( snakeX[0]>850){ snakeX[0]=25; }
            }else if (direction.equals("L")){
                snakeX[0]=snakeX[0]-25;
                if ( snakeX[0]<25){ snakeX[0]=850; }
            }else if (direction.equals("U")){
                snakeY[0]=snakeY[0]-25;
                if ( snakeY[0]<75){ snakeY[0]=650; }
            }else if (direction.equals("D")){
                snakeY[0]=snakeY[0]+25;
                if ( snakeY[0]>650){ snakeY[0]=75; }
            }
            // 吃食物
            if (snakeX[0]==foodx && snakeY[0]==foody){
                length++;
                score+=10;
                // 再次随机食物
                foodx=25+25*random.nextInt(34);
                foody=75+25*random.nextInt(24);
            }
            // 失败判定，撞到自己up键
            for (int i = 1; i <length ; i++) {
                if (snakeX[i]==snakeX[0]&&snakeY[i]==snakeY[0]){
                    twoFail=true;
                }
            }

            repaint();
        }
        timer.start();//定时器开启

    }


    //键盘监听器
    @Override
    public void keyPressed(KeyEvent e) {
        //获得键盘按键是哪一个
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (twoFail){
               //游戏失败重新开始
               twoFail=false;
               init();
            }else{
                oneStart = !oneStart;//如果是开始游戏空格键取反开始
            }

            repaint();//重新绘制刷新
        }
        if (keyCode== KeyEvent.VK_UP){
            direction="U";
        }else if (keyCode==KeyEvent.VK_DOWN){
            direction="D";
            }else if (keyCode==KeyEvent.VK_LEFT){
            direction="L";
        }else  if (keyCode==KeyEvent.VK_RIGHT){
            direction="R";
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }
}