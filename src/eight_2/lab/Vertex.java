package eight_2.lab;

import java.util.Objects;

public class Vertex {
    public int value;
    public int color;
    public int count;

    public Vertex(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return value == vertex.value &&
                color == vertex.color &&
                count == vertex.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, color, count);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "value=" + value +
                ", color=" + color +
                ", count=" + count +
                '}';
    }
}
