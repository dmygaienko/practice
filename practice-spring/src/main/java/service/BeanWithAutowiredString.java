package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by enda1n on 16.09.2016.
 */
public class BeanWithAutowiredString {

    @Qualifier("my")
    @Resource
    private List<String> resourceStrings;

    @Qualifier("my")
    @Autowired
    private List<String> autowiredStrings;

    @Qualifier("my")
    @Resource
    private String resourceString;

    @Qualifier("my")
    @Autowired
    private String autowiredString;

    private String envPropertiesLine;

    @Value("${test.practice.props}")
    private String propertiesLine;

    @Value("#{'${test.practice.props}'.split(',')}")
    private Set<String> properties;

    public List<String> getResourceStrings() {
        return resourceStrings;
    }

    public List<String> getAutowiredStrings() {
        return autowiredStrings;
    }

    public String getResourceString() {
        return resourceString;
    }

    public String getAutowiredString() {
        return autowiredString;
    }

    public String getPropertiesLine() {
        return propertiesLine;
    }

    public Set<String> getProperties() {
        return properties;
    }

    public String getEnvPropertiesLine() {
        return envPropertiesLine;
    }

    public void setEnvPropertiesLine(String envPropertiesLine) {
        this.envPropertiesLine = envPropertiesLine;
    }

    public void init() {
        System.out.println("init");
    }

    public void clean() {
        System.out.println("clean");
    }
}
