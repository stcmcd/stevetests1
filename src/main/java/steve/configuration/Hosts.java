package steve.configuration;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class Hosts {

    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Hosts.class);

    private static final String RUN_CONFIG_PROPERTIES = "/runConfig.properties";
    private static final String RUN_DEMO_ENV;
    private static Properties runProps;

    static {
        loadRunConfigProps();
        RUN_DEMO_ENV = getDemoEnv();
    }

    public String getDemoUrl() {
        return RUN_DEMO_ENV;
    }

    private static String getDemoEnv() {
        String env;
        env = runProps.getProperty("demoURL");
        LOG.info("Test Demo environment: " + env);
        return env;
    }

    private static void loadRunConfigProps() {
        runProps = new Properties();
        LOG.debug("Loading the properties as project root ");
        loadProps(runProps);
    }

    private static void loadProps(Properties props) {
        try {
            InputStream is = Hosts.class.getResourceAsStream(RUN_CONFIG_PROPERTIES);
            try {
                props.load(is);
            } finally {
                if(is != null){
                    is.close();
                }
            }
        } catch (IOException e) {
            LOG.error("IOException " + e.getMessage());
        }
    }
}