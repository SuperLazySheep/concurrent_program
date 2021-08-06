package com.sqq.concurrent.java_7;

/**
 * @author sqq
 * @Date 2021/2/8
 */

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 类名：Boom
 * 包名：com.mg.two
 * 描述： Java版轰炸脚本 适用于QQ 微信
 * 原理 往电脑剪贴复制你想发送的Ctrl+V 粘贴到聊天窗口 最后enter发送
 *
 **/
public class Boom {
    public static void main(String[] args) throws AWTException {
        String sentence = "陈超,你是飞猪,我正在轰炸你，轰炸死你，让你睡大街";// 定义要发送的话
        Robot robot = new Robot();// 创建Robot对象（机器人）
        robot.delay(3000);// 延迟三秒，主要是为了预留出打开窗口的时间，括号内的单位为毫秒
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String[] authors = sentence.split("[,]");
        // 字符串根据,分割
        for (int j = 0; j < 50; j++) {//循环次数
            for (int i = 0; i < authors.length; i++) {
                String sentencet = authors[i];
                Transferable tText = new StringSelection(sentencet);
                clip.setContents(tText, null);
                // 按下了ctrl+v，完成粘贴功能
                robot.keyPress(KeyEvent.VK_CONTROL);// 按下Control键
                robot.keyPress(KeyEvent.VK_V);// 按下V键
                robot.keyRelease(KeyEvent.VK_CONTROL);// 释放ctrl按键
                robot.delay(500);// 延迟一秒再发送
                robot.keyPress(KeyEvent.VK_ENTER);// 回车
            }
        }
    }
}
