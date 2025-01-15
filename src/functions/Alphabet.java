package functions;

public enum Alphabet {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;

    //Checks if a character is part of Alphabet
    public static boolean contains(char c) {
        for (Alphabet letter : Alphabet.values()) {
            if (letter.name().equals(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
}
