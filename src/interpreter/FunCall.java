package interpreter;

import java.util.*;

final class FunCall extends FunExp
{
    Id func;  // identifier "func" may be a variable or a user-defined function name

    FunCall(Id i, ExpList e)
    {
        func = i;
        expList = e;
    }

    String getFunOp()
    {
        return func.id;
    }

    Val Eval(HashMap<String,Val> state)
    {
        System.out.println(Parser.funMap.size());

        FunDef funDef = Parser.funMap.get(func.id);

        System.out.println(funDef.header.funName);


        return null;
    }
}
