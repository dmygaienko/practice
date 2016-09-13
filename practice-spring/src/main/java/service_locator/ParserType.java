package service_locator;

/**
 * Created by dmygaenko on 13/09/2016.
 */
public enum ParserType {

    JSON("jsonParser"), XML("xmlParser");

    private final String value;

    ParserType(String input) {
        this.value = input;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
