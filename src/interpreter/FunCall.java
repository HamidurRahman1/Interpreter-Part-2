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

        if(funDef == null)
        {
            String fun = state.get(func.id).toString();
            if(state.containsKey(func.id))
            {
                FunDef funDef1 = Parser.funMap.get(fun);
                if(funDef1 != null)
                {
                    return funDef1.exp.Eval(state);
                }
            }
            return null;
        }

        ExpList nonEmptyList = expList;
        ParameterList parameterList = funDef.header.parameterList;

        while (parameterList != null && nonEmptyList != null)
        {
            if(parameterList.getClass() == NonEmptyParameterList.class && nonEmptyList.getClass() == NonEmptyExpList.class)
            {
                Val val = ((NonEmptyExpList) nonEmptyList).exp.Eval(state);

                if(val instanceof FunVal)
                {
                    state.put(((NonEmptyParameterList) parameterList).id, ((NonEmptyExpList) nonEmptyList).exp.Eval(state));
                    parameterList = ((NonEmptyParameterList) parameterList).parameterList;
                    nonEmptyList = ((NonEmptyExpList) nonEmptyList).expList;
                }
                else
                {
                    state.put(((NonEmptyParameterList) parameterList).id, val);
                    parameterList = ((NonEmptyParameterList) parameterList).parameterList;
                    nonEmptyList = ((NonEmptyExpList) nonEmptyList).expList;
                }
            }
            else break;
        }

        return funDef.exp.Eval(state);
    }
}
