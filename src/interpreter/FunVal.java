package interpreter;

final class FunVal extends Val
{
    String funName;

    public FunVal(String funName) {
        this.funName = funName;
    }

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

    @Override
    public String toString() {
        return funName;
    }
}