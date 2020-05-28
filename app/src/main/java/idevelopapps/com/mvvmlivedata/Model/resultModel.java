package idevelopapps.com.mvvmlivedata.Model;

public class resultModel {
    String id;
    String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public resultModel() {
    }

    public resultModel(String id, String value) {
        this.id = id;
        this.value = value;
    }
}
