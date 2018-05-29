int parent(int i);

int left(int i);

int right(int i);

void heapify(int tab[], int i);

void buildMaxHeap(int tab[]);

void heapsort(int tab[]);

int main()
{
    int tabSize = 5;
    int tab[tabSize];

    tab[0] = 5;
    tab[1] = 1;
    tab[2] = 9;
    tab[3] = 0;
    tab[4] = 2;

    heapsort(tab, 5);

    return 0;
}


int parent(int i)
{
    return (i / 2);
}

int left(int i)
{
    if (i != 0)
        return (2 * i);
    else
        return 1;
}

int right(int i)
{
    if (i != 0)
        return ((2 * i) + 1);
    else
        return 2;
}

void heapify(int tab[], int i, int heapSize) {
    int r = right(i);
    int l = left(i);
    int largest = i;

    if (l < heapSize && tab[l] > tab[i]) {
        largest = l;
    }
    if (r < heapSize && tab[r] > tab[largest]) {
        largest = r;
    }
    if (largest != i)
    {
        int temp = tab[i];
        tab[i] = tab[largest];
        tab[largest] = temp;

        heapify(tab, largest, heapSize);
    }
}

void buildMaxHeap(int tab[], int heapSize, int tabSize) {
    heapSize = tabSize;
    for (int i = (tabSize / 2); i >= 0; i--) {
        heapify(tab, i, heapSize);
    }
}

void heapsort(int tab[], int tabSize) {
    int heapSize = tabSize;
    buildMaxHeap(tab, heapSize, tabSize);
    for (int i = (tabSize - 1); i >= 1; i--) {
        int temp = tab[i];
        tab[i] = tab[0];
        tab[0] = temp;

        heapSize--;
        heapify(tab, 0, heapSize);
    }
}


