void countingSort(int tab[], int tabSorted[], int k, int l) {
    int capacity = k + 1;
    int before[capacity];
    for (int i = 0; i <= k; i++) {
        before[i] = 0;
    }
    for (int j = 0; j < l; j++) {
        before[tab[j]]++;
    }
    for (int i = 1; i <= k; i++) {
        before[i] = before[i] + before[(i - 1)];
    }

    for (int j = (l - 1); j > 0; j--) {
        tabSorted[before[tab[j]]] = tab[j];
        before[tab[j]]--;
    }
}