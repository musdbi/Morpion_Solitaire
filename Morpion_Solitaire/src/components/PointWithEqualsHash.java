package components;

import java.awt.Point;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class PointWithEqualsHash extends Point {
    public PointWithEqualsHash(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointWithEqualsHash otherPoint = (PointWithEqualsHash) o;
        return x == otherPoint.x && y == otherPoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public static void main(String[] args) {
        Set<PointWithEqualsHash> set = new HashSet<>();

        PointWithEqualsHash p1 = new PointWithEqualsHash(0, 0);
        set.add(p1);
        System.out.println(set);

        PointWithEqualsHash p2 = new PointWithEqualsHash(0, 0);
        set.add(p2);
        System.out.println(set);
    }
}

