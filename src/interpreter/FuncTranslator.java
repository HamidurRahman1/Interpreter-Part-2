package interpreter;

/*
    This class is functioning more like a Utility class. It has function that represent the ones needs to be
    evaluated. Each function that are available in source program are available here in interpreted form.
 */

public class FuncTranslator
{
    public static Val square(Val x)
    {
        if(x instanceof IntVal)
        {
            int a = Integer.parseInt(x.toString());
            return new IntVal(a*a);
        }
        else
        {
            float a = Float.parseFloat(x.toString());
            return new FloatVal(a*a);
        }
    }

    public static Val pi()
    {
        return new FloatVal(3.14156f);
    }
}

