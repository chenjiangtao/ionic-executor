package com.smspai.ionic.controller;

import com.smspai.ionic.ExecuteShellComand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class UserController {
    //slf4j logger
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ExecuteShellComand executeShellComand;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/exec/{action}", method = RequestMethod.GET)
    public Object action(HttpServletRequest request, @PathVariable String action) {
        String cmd = "";
        boolean success = false;
        StringBuffer msg = new StringBuffer();
        logger.info("EXEC ACTION---->>>>" + action);
        String cpApk = "&&cp platforms/android/build/outputs/apk/android-release.apk " +
                "/Users/jiangtao/IdeaProjects/ionicexecutor/target/classes/static&&" +
                "echo 打包并拷贝完成，请回页首下载！";
        String gitInfo = "&&git branch -v";

        if (action.equals("release")) {
            cmd = "ionic cordova build --release android" + cpApk + gitInfo;
        } else if (action.equals("rmall")) {
//            cmd = "rm -rf node_modules plugins platforms";
        } else if (action.equals("add-platform-release")) {
            cmd = "ionic cordova platform rm android&&ionic cordova platform add android&&" +
                    "cp release-signing.properties platforms/android&&ionic cordova build --release android" + cpApk + gitInfo;
        } else if (action.equals("gitpull")) {
            cmd = "git checkout master -f&&git pull";
        } else if (action.equals("gitpull-ymy")) {
            cmd = "git checkout ymy -f&&git pull";
        }

        String cmds[] = cmd.split("&&");
        for (String c : cmds) {
            ExecCmd execCmd = new ExecCmd(c).invoke();
            msg.append(execCmd.getMsg());
            success = execCmd.isSuccess();
        }

        ModelMap map = new ModelMap();
        map.addAttribute("success", success);
        map.addAttribute("message", msg.toString());
        return map;

    }


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
//    @RequestMapping(value = "/exec/cmd", method = RequestMethod.POST)
    public Object exec(HttpServletRequest request, String commands) {
        logger.info("COMMAND---->>>>" + commands);
        String msg;
        boolean success;
        String cmd = "";
        cmd = commands;

        if ((cmd.startsWith("ionic") ||
                cmd.startsWith("cordova") ||
                cmd.startsWith("cnpm") ||
                cmd.startsWith("npm")) &&
                cmd.indexOf("&") < 0) {
            ExecCmd execCmd = new ExecCmd(cmd).invoke();
            success = execCmd.isSuccess();
            msg = execCmd.getMsg();
        } else {
            success = false;
            msg = "请使用ionic,cordova,cnpm,npm命令，并且不使用&&连接符";
        }

        ModelMap map = new ModelMap();
        map.addAttribute("success", success);
        map.addAttribute("message", msg);

        return map;

    }

    private class ExecCmd {
        private String cmd;
        private String msg;
        private boolean success;

        public ExecCmd(String cmd) {
            this.cmd = cmd;
        }

        public String getMsg() {
            return msg;
        }

        public boolean isSuccess() {
            return success;
        }

        public ExecCmd invoke() {
            logger.info("EXEC CMD--->>>>>>>" + cmd);
            try {
                msg = executeShellComand.executeCommands(cmd.split(" "));
                success = true;
            } catch (IOException e) {
                msg = e.getMessage();
                success = false;
                e.printStackTrace();
            } catch (InterruptedException e) {
                msg = e.getMessage();
                success = false;
                e.printStackTrace();
            }
            return this;
        }
    }
}
