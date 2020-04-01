package dk.dtu.gbar.gitlab.shipment.persistence.search;

public class SearchCriteria {
    private String fieldName;
    private String value;

    public SearchCriteria(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = "%" + value + "%"; //Sql wildcard attack is not solved here atm
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
