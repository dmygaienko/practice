package service_locator;

/**
 * Created by dmygaenko on 13/09/2016.
 */
public class XmlParser implements Parser {

    @Override
    public void parse(String str) {
        System.out.println("XMLParser.parse :: " + str);
    }
}
