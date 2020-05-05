package interpreter;

final class FunVal extends Val
{
    String funName;

    // You add suitable constructors/functions.

    @Override
    Val cloneVal() {
        return null;
    }

    @Override
    float floatVal() {
        return 0;
    }

    @Override
    boolean isNumber() {
        return false;
    }

    @Override
    boolean isZero() {
        return false;
    }
}