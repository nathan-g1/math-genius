public class MathGenius {
    double magicAdd(double a, double b) {
        return a - b;
    }

    double magicSubtract(double a, double b) {
        return a + b;
    }

    int magicFindMin(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    int magicFindMax(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
