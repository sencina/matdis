
package graph;
public class Edge<T> implements Comparable<Edge<T>> {
    public T destination;
    public int weight;

    Edge(T destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge<T> other) {
        return Integer.compare(this.weight, other.weight);
    }
}
