package service_locator;

/**
 * Created by dmygaenko on 13/09/2016.
 */
public class JsonParser implements Parser{

    @Override
    public void parse(String str) {
        System.out.println("JsonParser.parse::" + str);
    }
}
