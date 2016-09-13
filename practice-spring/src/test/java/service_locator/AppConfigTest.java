package service_locator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dmygaenko on 13/09/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppConfigTest {

    @Autowired
    private ParserService parserService;

    @Test
    public void testParserFactory() {
        parserService.doParse("test1", ParserType.JSON);
        parserService.doParse("test2", ParserType.XML);
    }
}