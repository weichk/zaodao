package com.acooly.zaodao.common;

import com.acooly.openapi.framework.common.utils.Strings;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurableConstants {
    private static Log logger = LogFactory.getLog(ConfigurableConstants.class);
    protected static Properties p = new Properties();

    protected static void init(String propertyFileName) {
        InputStream in = null;
        try {
            in = ConfigurableConstants.class.getClassLoader().getResourceAsStream(propertyFileName);
            if (in != null) {
                p.load(in);
            }
            logger.info("load:"+propertyFileName);
        } catch (IOException e) {
            logger.error("load " + propertyFileName + " into Constants error!");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("close " + propertyFileName + " error!");
                }
            }
        }
    }

    /**
     * 支持profile
     *
     * @param propertyFileName
     */
    protected static void initWithProfile(String propertyFileName) {
        String activeProfile = System.getProperty("spring.profiles.active");
        String configFile = propertyFileName;
        if (Strings.isNotBlank(activeProfile)) {
            configFile = Strings.substringBeforeLast(configFile, ".") + "-" + activeProfile + "."
                    + Strings.substringAfterLast(configFile, ".");
        }
        init(configFile);
    }

    protected static String getProperty(String key, String defaultValue) {
        return p.getProperty(key, defaultValue);
    }


}
