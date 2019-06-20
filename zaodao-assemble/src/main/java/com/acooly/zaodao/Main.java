package com.acooly.zaodao;

import com.acooly.core.common.boot.Apps;
import org.springframework.boot.SpringApplication;
import com.acooly.core.common.BootApp;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


/**
 * @author qiubo
 */
@BootApp(sysName = "zaodao", httpPort = 8021)
public class Main {
    public static void main(String[] args) {
        Apps.setProfileIfNotExists("sdev");
        new SpringApplication(Main.class).run(args);
    }
}