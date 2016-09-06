package sk.loffay.cache;

/**
 * @author Pavol Loffay
 */
public class StringWrapper {

    private String id;
    private String str;

    public StringWrapper() {}

    public StringWrapper(String id, String str) {
        this.id = id;
        this.str = str;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
