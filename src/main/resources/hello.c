#include <stdio.h>

int example_function(int a, int b, char c);
_Bool example_conditions(int a, _Bool b);

struct Test {
    int field1;
    char field2;
    float field3;
    double field4;
    short field5;
    long field6;
    _Bool field7;
};

enum testEnum {PON, WT, SR, CZW, PT, SOB, ND};

int main()
{
    _Atomic int test = 0;
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

    for(int i =0; i < 10; i++) {
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
    testStruct.field1 = testEnum.PON;
    testStruct.field2 = 'c';
    testStruct.field3 = 1.0;
    testStruct.field4 = 2.2;
    testStruct.field5 = testEnum.ND;
    testStruct.field6 = 100000;
    testStruct.field4 = 2.2;

    return test;
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

_Bool example_conditions(int a, _Bool b) {
    return a && b || 1 || 1 < 2 || 1 > 2 || 1 >= 2 || 1 <= 2 && 1 != 2;
}
