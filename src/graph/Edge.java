
package graph;
public class Edge<T> implements Comparable<Edge<T>> {
    public T destination;
    public T source;
    public int weight;

    public Edge(T destination, int weight) {
        this.destination = destination;
        this.weight = weight;
        this.source = null;
    }

    public Edge(T source, T destination, int weight) {
        this.destination = destination;
        this.weight = weight;
        this.source = source;
    }

    @Override
    public int compareTo(Edge<T> other) {
        return Integer.compare(this.weight, other.weight);
    }
}
