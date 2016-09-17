package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.util.List;

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
}
