package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private List<String> autowiredAllStrings;

    @Resource
    @Qualifier("their")
    private List<String> stringsAsListBean;

    @Autowired
    private Map<String, String> autowiredStringsAsMapBean;

    @Resource(name = "stringsAsMapBean")
    private Map<String, String> resourceStringsAsMapBean;

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

    public List<String> getAutowiredAllStrings() {
        return autowiredAllStrings;
    }

    public List<String> getStringsAsListBean() {
        return stringsAsListBean;
    }

    public Map<String, String> getAutowiredStringsAsMapBean() {
        return autowiredStringsAsMapBean;
    }

    public Map<String, String> getResourceStringsAsMapBean() {
        return resourceStringsAsMapBean;
    }
}
