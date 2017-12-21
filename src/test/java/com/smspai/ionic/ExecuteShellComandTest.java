package com.smspai.ionic;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by 185378641@qq.com on 2017/12/20.
 *
 * @author jiangtao
 */
//@RunWith(SpringRunner.class)
public class ExecuteShellComandTest {
    ExecuteShellComand obj = new ExecuteShellComand();

    @Test
    public void executeCommands() throws Exception {
//        String s = "cd platforms/android && " +
//                "echo 'storeFile=/Users/jiangtao/demo.keystore key.store.password=123456 key.alias=demo.keystore key.alias.password=123456' > release-signing.properties";

        //Build command
        String[] device = "ionic cordova run android --device".split(" ");
//        String[] device = "ionic cordova run android --device".split(" ");

        String dir = "/Users/jiangtao/myapp/";

//        obj.executeCommands(device, dir);
        obj.executeCommands("ionic cordova build --release android".split(" "), dir);
//        obj.executeCommands(s.split(" "), dir);


    }

    @Test
    public void test1() throws Exception {
        String commands = "ls";
        if ((commands.startsWith("ionic") || commands.startsWith("cordova")
                || commands.startsWith("cnpm") || commands.startsWith("npm")
        ) && commands.indexOf("&&") < 0) {
            System.out.println("exec " + commands);
        }

        commands = "ionic";
        if ((commands.startsWith("ionic") || commands.startsWith("cordova")
                || commands.startsWith("cnpm") || commands.startsWith("npm")
        ) && commands.indexOf("&&") < 0) {
            System.out.println("exec " + commands);
        }

        commands = "cordova";
        if ((commands.startsWith("ionic") || commands.startsWith("cordova")
                || commands.startsWith("cnpm") || commands.startsWith("npm")
        ) && commands.indexOf("&&") < 0) {
            System.out.println("exec " + commands);
        }
        commands = "cnpm";
        if ((commands.startsWith("ionic") || commands.startsWith("cordova")
                || commands.startsWith("cnpm") || commands.startsWith("npm")
        ) && commands.indexOf("&&") < 0) {
            System.out.println("exec " + commands);
        }

        commands = "npm";
        if ((commands.startsWith("ionic") || commands.startsWith("cordova")
                || commands.startsWith("cnpm") || commands.startsWith("npm")
        ) && commands.indexOf("&&") < 0) {
            System.out.println("exec " + commands);
        }

        commands = "cnpm & ls";
        if ((commands.startsWith("ionic") || commands.startsWith("cordova")
                || commands.startsWith("cnpm") || commands.startsWith("npm")
        ) && commands.indexOf("&") < 0) {
            System.out.println("exec " + commands);
        } else {
            System.out.println("CAN NOT exec " + commands);
        }

        commands = "alias";
        if ((commands.startsWith("ionic") || commands.startsWith("cordova")
                || commands.startsWith("cnpm") || commands.startsWith("npm")
        ) && commands.indexOf("&") < 0) {
            System.out.println("exec " + commands);
        } else {
            System.out.println("CAN NOT exec " + commands);
        }

        commands = "info";
        if ((commands.startsWith("ionic") || commands.startsWith("cordova")
                || commands.startsWith("cnpm") || commands.startsWith("npm")
        ) && commands.indexOf("&") < 0) {
            System.out.println("exec " + commands);
        } else {
            System.out.println("CAN NOT exec " + commands);
        }

        System.out.println(commands.indexOf("&&") < 0);

    }

    @Test
    public void tset1() {

        String cpApk = "&&cp platforms/android/build/outputs/apk/android-release.apk " +
                "/Users/jiangtao/IdeaProjects/ionicexecutor/src/main/resources/static&&" +
                "echo 打包并拷贝完成，请回页首下载！";
        String gitInfo = "&&git branch -v";

        String cmd = "ionic cordova platform rm android&&ionic cordova platform add android&&" +
                "cp release-signing.properties platforms/android&&ionic cordova build --release android&&" + cpApk + gitInfo;

        String[] cmds = cmd.split("&&");
        System.out.println(Arrays.deepToString(cmds));
    }


}