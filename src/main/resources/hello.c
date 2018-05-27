
int example_function(int a, int b, char c);
_Bool example_conditions(int a, _Bool b);

struct Test {
    int field1;
    char field2;
    float field3;
    double field4;
    short field5;
    long field6;
};

enum testEnum {PON, WT, SR, CZW, PT, SOB, ND};

int main(void)
{
    int test = 0;
    test +=2;
    test -=1;
    test *=2;
    test /=2;
    test %=2;
    test = 1 * 2;
    test = 2 / 2;
    test = 2 + 2;
    test = 2 - 2;
    test = 2 & 2;

    for(int i = 0; i < 10; i++) {
        test += test;
        if(test == 10) {
            break;
        }

        if(test < 10) {
            continue;
        }
    }

    switch(test) {
        case 1:
            test += 1;
            break;
        case 2:
            test += 2;
            break;
        default:
            test = test + 1;
    }
    struct Test testStruct;
    testStruct.field1 = 1;
    testStruct.field2 = 'c';
    testStruct.field3 = 1.0f;
    testStruct.field4 = 2.2;
    testStruct.field5 = 22;
    testStruct.field6 = 100000;

    test = example_function(1, 2, 3);
    _Bool testBoolean = example_function_with_conditions(5, 2);

    return;
}

int example_function(int a, int b, int c) {
    int result = 0;

    if(a > b) {
        result = (a-b)*c;
    } else {
        result = (((b-a)))*c;
    }

    return result;
}

_Bool example_function_with_conditions(int a, int b) {
    return a == 1 && b != 2 || 1 < 2 || 1 > 2 || 1 >= 2 || 1 <= 2 && 1 != 2;
}
