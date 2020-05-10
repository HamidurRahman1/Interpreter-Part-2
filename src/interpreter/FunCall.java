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
        FunDef funDef = Parser.funMap.get(func.id);

        List<String> params = new LinkedList<>();

        while (funDef.header.parameterList instanceof NonEmptyParameterList)
        {
            params.add(((NonEmptyParameterList)funDef.header.parameterList).id);
            funDef.header.parameterList = ((NonEmptyParameterList) funDef.header.parameterList).parameterList;
        }

        while (expList instanceof NonEmptyExpList)
        {
            Val v = ((NonEmptyExpList) expList).exp.Eval(state);
            System.out.println(v);
            if(((NonEmptyExpList) expList).expList instanceof EmptyExpList) break;
            else expList = ((NonEmptyExpList) expList).expList;
        }

        int x = Integer.parseInt(((NonEmptyExpList) expList).exp.Eval(state).toString());

        return new IntVal(x);
    }
}
