package Common;


import Common.Node.Customer;
import Common.Node.Node;
import Utils.RandomController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {
    public Node start;
    public Node end;
    private int id;
    private List<Node> route;
    private int weight;
    private int timeCost;

    public Route(List<Node> route, Node start, Node end) {
        this.route = route;
        this.start = start;
        this.end = end;
        this.weight = 0;
        for (Node node : route) {
            this.weight += ((Customer) node).need;
            this.timeCost += node.duration;
        }
    }

    public Route(Route route) {
        this.route = new ArrayList<>(route.getRoute());
        this.start = route.start;
        this.end = route.end;
        this.weight = route.getWeight();
        this.timeCost = route.getTimeCost();
        this.id = route.getId();
    }

    public static void reverseSubList(List<Node> route, int fromIndex, int toIndex) {
        for (int i = 0; i <= (toIndex - fromIndex) / 2; i++) {
            Node node = route.get(fromIndex + i);
            route.set(fromIndex + i, route.get(toIndex - i));
            route.set(toIndex - i, node);
        }
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public int getTimeCost() {
        return timeCost;
    }

    public Node getNode(int pos) {
        if (pos < 0) {
            return this.start;
        } else if (pos < this.route.size()) {
            return this.route.get(pos);
        } else return this.end;
    }

    public int length() {
        return route.size();
    }

    public List<Node> getRoute() {
        return new ArrayList<>(route);
    }

    public void subNode(int pos, Node node) {
        Node oldNode = this.route.get(pos);
        this.route.set(pos, node);
        update(-((Customer) oldNode).need + ((Customer) node).need,
                -oldNode.duration + node.duration);
    }

    public void conNode(int st, int ed) {
        List<Node> subList = this.route.subList(0, st + 1);
        subList.addAll(this.route.subList(ed, this.route.size()));
        this.route = subList;
    }

    public void addNode(int pos, Node node) {
        this.route.add(pos, node);
        update(((Customer) node).need, node.duration);
    }

    public void rmNode(int pos) {
        Node node = this.route.get(pos);
        this.route.remove(pos);
        update(-((Customer) node).need, -node.duration);
    }

    public void rmNode(Node node) {
        this.route.remove(node);
    }

    public void innerShift10(int prev, int next) {
        Node node = this.route.get(prev);
        this.addNode(next + 1, node);
        if (next < prev) { // 新节点插入到编号prev前
            this.rmNode(prev + 1);
        } else {
            this.rmNode(prev);
        }
    }

    public void shift10(Route other, int prev, int next) {
        Node node = this.route.get(prev);
        this.rmNode(prev);
        other.addNode(next + 1, node);
    }

    public void swap11(Route other, int prev, int next) {
        // prev->this.route, next->other
        Node tmp = this.route.get(prev);
        this.subNode(prev, other.getNode(next));
        other.subNode(next, tmp);
    }

    public void innerShift20(int prev, int next) {
        Customer node1 = (Customer) this.getNode(prev);
        Customer node2 = (Customer) this.getNode(prev + 1);
        while (prev < next - 1) {
            route.set(prev, route.get(prev + 2));
            prev++;
        }
        while (prev > next + 1) {
            route.set(prev + 1, route.get(prev - 1));
            prev--;
        }
        route.set(prev, node1);
        route.set(prev + 1, node2);
    }

    public void shift20(Route other, int prev, int next) {
        Customer node1 = (Customer) this.getNode(prev);
        Customer node2 = (Customer) this.getNode(prev + 1);
        for (int i = prev; i < this.length() - 2; i++) {
            this.route.set(i, route.get(i + 2));
        }
        route.remove(route.size() - 1);
        route.remove(route.size() - 1);
        int weightChg = node1.need + node2.need;
        int timeChg = node1.duration + node2.duration;
        update(-weightChg, -timeChg);
        other.route.add(null);
        other.route.add(null);
        for (int i = other.route.size() - 1; i > next + 2; i--) {
            other.route.set(i, other.route.get(i - 2));
        }
        other.route.set(next + 1, node1);
        other.route.set(next + 2, node2);
        other.update(weightChg, timeChg);
    }

    /**
     * 反转子路径
     *
     * @param start 开始
     * @param end   结束
     */
    public void twoOpt(int start, int end) {
        reverseSubList(route, start, end);
    }

    public void swap22(Route other, int prev, int next) {
        swap11(other, prev, next);
        swap11(other, prev + 1, next + 1);
    }

    public void swap21(Route other, int prev, int next) {
        Node node = route.get(prev + 1);
        swap11(other, prev, next);
        other.addNode(next + 1, node);
        route.remove(prev + 1);
    }

    public void twoOptStar1(Route other, int prev, int next) {
//      weightChg&timeChg
        int weightChg = 0, timeChg = 0;
        for (int i = prev + 1; i < route.size(); i++) {
            weightChg += ((Customer) route.get(i)).need;
            timeChg += route.get(i).duration;
        }
        for (int i = 0; i <= next; i++) {
            weightChg -= ((Customer) other.route.get(i)).need;
            timeChg -= other.route.get(i).duration;
        }

        int initMainSize = route.size();
        int initSideSize = other.route.size();
        Node[] tmp = new Node[route.size() - prev - 1];
        for (int i = 0; i < route.size() - 1 - prev; i++) {
            tmp[route.size() - prev - 2 - i] = route.get(prev + 1 + i);
        }
//      适配容量
        int mainCap = next + 2 + prev;
        while (route.size() < mainCap) {
            route.add(null);
        }
        while (route.size() > mainCap) {
            route.remove(route.size() - 1);
        }
        for (int ptr = 0; ptr <= next; ptr++) {
            route.set(prev + 1 + ptr, other.getNode(next - ptr));
        }
//      适配容量
        int sideCap = initMainSize - 1 - prev + other.route.size() - 1 - next;
        if (other.route.size() < sideCap) {
            while (other.route.size() < sideCap) other.route.add(null);
            for (int i = 0; i < other.route.size() - 1 - next; i++) {
                other.route.set(other.route.size() - 1 - i, other.getNode(other.route.size() - 1 - i - sideCap + initSideSize));
            }
        } else if (other.route.size() > sideCap) {
            for (int i = 0; i < other.route.size() - 1 - next; i++) {
                other.route.set(next + 1 + i - other.route.size() + sideCap, other.getNode(next + 1 + i));
            }
            while (other.route.size() > sideCap) other.route.remove(other.route.size() - 1);
        }
        for (int i = 0; i < initMainSize - 1 - prev; i++) {
            other.route.set(i, tmp[i]);
        }
        update(-weightChg, -timeChg);
        other.update(weightChg, timeChg);
    }

    public void twoOptStar2(Route other, int prev, int next) {
//      weightChg&timeChg
        int weightChg = 0, timeChg = 0;
        for (int i = prev + 1; i < route.size(); i++) {
            weightChg += ((Customer) route.get(i)).need;
            timeChg += route.get(i).duration;
        }
        for (int i = next + 1; i < other.route.size(); i++) {
            weightChg -= ((Customer) other.route.get(i)).need;
            timeChg -= other.route.get(i).duration;
        }

        int initMainSize = route.size();
        int initSideSize = other.route.size();
        Node[] tmp = new Node[route.size() - prev - 1];
        for (int i = 0; i < route.size() - 1 - prev; i++) {
            tmp[route.size() - prev - 2 - i] = route.get(prev + 1 + i);
        }
//      适配容量
        int mainCap = prev + initSideSize - next;
        while (route.size() < mainCap) {
            route.add(null);
        }
        while (route.size() > mainCap) {
            route.remove(route.size() - 1);
        }
        for (int ptr = 0; ptr < initSideSize - 1 - next; ptr++) {
            route.set(prev + 1 + ptr, other.getNode(next + 1 + ptr));
        }
//      适配容量
        int sideCap = next + initMainSize - prev;
        if (other.route.size() < sideCap) {
            while (other.route.size() < sideCap) other.route.add(null);
        } else if (other.route.size() > sideCap) {
            while (other.route.size() > sideCap) other.route.remove(other.route.size() - 1);
        }
        Collections.reverse(other.route);
        for (int i = 0; i < initMainSize - 1 - prev; i++) {
            other.route.set(i, tmp[i]);
        }

        update(-weightChg, -timeChg);
        other.update(weightChg, timeChg);
    }

    public void update(int weightChg, int timeChg) {
        this.weight += weightChg;
        this.timeCost += timeChg;
    }

    /**
     * only for test use
     */
    public void shuffle() {
        RandomController.shuffle(this.route);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", route=" + route +
                ", start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                ", timeCost=" + timeCost +
                '}';
    }
}
