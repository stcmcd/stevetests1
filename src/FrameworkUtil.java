package com.lgim.clientreportingrebates;

import com.lgim.clientreportingrebates.data.PropertiesUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.MDC;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import javax.jms.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;


public class FrameworkUtil {

    XLogger log = XLoggerFactory.getXLogger ( FrameworkUtil.class );

    public long gMsStart;
    public PropertiesUtil props;

    public FrameworkUtil() throws IOException{
        this.props = PropertiesUtil.getInstance();
    }
}
