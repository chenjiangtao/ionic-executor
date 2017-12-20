package com.smspai.ionic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Component
public class ExecuteShellComand {
    ///slf4j logger
    private final static Logger logger = LoggerFactory.getLogger(ExecuteShellComand.class);

    public String executeCommands(String[] commands) throws IOException, InterruptedException {
        return executeCommands(commands, "/Users/jiangtao/myapp/");
    }

    public String executeCommands(String[] commands, String execDir) throws IOException, InterruptedException {
        logger.info("CMD--->>>>>>" + Arrays.deepToString(commands));
        //Run macro on target
        ProcessBuilder pb = new ProcessBuilder(commands);
        //set run directory
        pb.directory(new File(execDir));
        pb.redirectErrorStream(true);
        Process process = pb.start();
        //Read output
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null, previous = null;

        while ((line = br.readLine()) != null)
            if (!line.equals(previous)) {
                previous = line;
                out.append(line).append("<br>\n");
                System.out.println(line);
            }
        //Check result
        if (process.waitFor() == 0) {
            logger.info("Success!");
//            System.exit(0);
        }
        //Abnormal termination: Log command parameters and output and throw ExecutionException
        logger.info(out.toString());
//        System.exit(1);
        return out.toString();
    }

}