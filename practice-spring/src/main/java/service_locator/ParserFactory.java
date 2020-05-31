package service_locator;

/**
 * Created by dmygaenko on 13/09/2016.
 */
public interface ParserFactory {

    public Parser getParser(ParserType parserType);
}
