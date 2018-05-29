int binarySearch(int tab[], int p, int n, int x) {
    if (p <= n) {
        int q = (p + n) / 2;
        if (tab[q] == x) {
            return q;
        } else if (tab[q] > x) {
            binarySearch(tab, p, q - 1, x);
        } else {
            binarySearch(tab, q + 1, n, x);
        }
    }
    return -1;
}