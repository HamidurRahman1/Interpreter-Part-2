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

        ExpList nonElist = expList;
        ParameterList parameterList = funDef.header.parameterList;

        while (parameterList != null && nonElist != null)
        {
            if(parameterList.getClass() == NonEmptyParameterList.class && nonElist.getClass() == NonEmptyExpList.class)
            {
                state.put(((NonEmptyParameterList) parameterList).id, ((NonEmptyExpList) nonElist).exp.Eval(state));
                parameterList = ((NonEmptyParameterList) parameterList).parameterList;
                nonElist = ((NonEmptyExpList) nonElist).expList;
            }
            else break;
        }

        return funDef.exp.Eval(state);
    }
}
