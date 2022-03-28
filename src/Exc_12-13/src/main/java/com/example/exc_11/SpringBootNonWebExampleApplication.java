package com.example.exc_11;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Scanner;


@SpringBootApplication
public class SpringBootNonWebExampleApplication implements CommandLineRunner {
    String input = null;
    String output = null;

    @Value("${program.student.name}")
    String name;

    @Value("${program.student.last_name}")
    String last_name;

    @Value("${program.student.group}")
    String group;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNonWebExampleApplication.class, args);
    }

    // доступ к аргументам командной строки
    @Override
    public void run(String... args) throws Exception {

        System.out.println(args[0].split("=")[0].indexOf("input")!=-1);
        System.out.println(args[0].split("=")[0]);
        if (args[0].split("=")[0].indexOf("input")!=-1) {
            input = args[0].split("=")[1];
            output=args[1].split("=")[1];
        } else {
            output = args[0].split("=")[1];
            if(args.length>1 && args[1].split("=")[0]=="input"){
                input = args[1].split("=")[1];
            }else{
                File outputFile = new File(output);
                if(outputFile.exists())
                    outputFile.delete();
                Path outputFilePath = Paths.get(outputFile.getAbsolutePath());
                Files.write(outputFilePath, Collections.singleton("null"), StandardCharsets.UTF_8);

                return;
            }
        };
        System.out.println(input+" "+output);

        Path inputPath = Paths.get(new File(input).getAbsolutePath());
        String result="";
        String[] fileContent= Files.readAllLines(inputPath).toArray(new String[0]);
        for(int i =0;i<fileContent.length;i++)
            result+=fileContent[i]+"\n";


        File outputFile = new File(new File(input).getCanonicalPath().replace(input,"")+"\\"+output);
        System.out.println(new File(input).getCanonicalPath()+"\\"+output);
        if(outputFile.exists())
            outputFile.delete();

        outputFile.createNewFile();
        Path outputFilePath = Paths.get(outputFile.getAbsolutePath());
        Files.write(outputFilePath, Collections.singleton(getHash(result)), StandardCharsets.UTF_8);
    }

    @PostConstruct
    public void init() {
        System.out.println("Bean is ready");

        System.out.println("");
        System.out.println("___");
        System.out.println(name);
        System.out.println(last_name);
        System.out.println(group);
        System.out.println("___");
        System.out.println("");
    }

    @PreDestroy
    public void end() {
        new File(input).delete();
        System.out.println("END");
    }

    public String getHash(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(str.getBytes());
        String stringHash = new String(messageDigest.digest());
        return stringHash;
    }

}