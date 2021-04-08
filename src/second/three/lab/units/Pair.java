package second.three.lab.units;

public class Pair{
    Long key;
    Object value;
    public Pair(long key, Object value){
        this.key = key;
        this.value = value;
    }

    public Long getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{key=%s,value=%s}".formatted(key, value);
    }
}
