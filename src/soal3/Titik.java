package soal3;

/** Titik di bidang kartesius. */
public class Titik {
    private double x;
    private double y;

    public Titik() {
        this(0, 0);
    }

    public Titik(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /** Jarak ke titik lain. */
    public double jarakKe(Titik lain) {
        double dx = lain.x - this.x;
        double dy = lain.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
