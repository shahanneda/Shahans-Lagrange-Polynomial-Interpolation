public class Vector2 {
    public float x;
    public float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Vector2 vec = (Vector2)obj;
        return vec.x == x && vec.y == y;
    }
}
