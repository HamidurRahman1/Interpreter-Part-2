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
        /*
           Eval( (f E1 ··· En), α ) = r   where   // identifier f may be a variable or a user-defined function name
           let f-val = Eval(f, α)
           if f-val = ⊥v then r = ⊥v
           if f-val is not a function object then r = ⊥v
           let f-val = FunVal(f-name)   // f-name is the function name contained in the function object
           let E be the body expression of f-name
           let ei = Eval(Ei, α), 1 ≤ i ≤ n
           if ei = ⊥v for any i then r = ⊥v
           let β = {⟨x1, e1⟩, …, ⟨xn, en⟩}   // form a new function-call state for f-name
           r = Eval(E, β)   // evaluate body expression E of f-name
         */

        FunDef funDef = Parser.funMap.get(func.id);

        List<String> funParamsId = new LinkedList<>();

        while (funDef.header.parameterList instanceof NonEmptyParameterList)
        {
            funParamsId.add(((NonEmptyParameterList)funDef.header.parameterList).id);
            funDef.header.parameterList = ((NonEmptyParameterList) funDef.header.parameterList).parameterList;
        }

        List<Val> funParamsValue = new LinkedList<>();

        while (expList instanceof NonEmptyExpList)
        {
            funParamsValue.add(((NonEmptyExpList) expList).exp.Eval(state));
            if(((NonEmptyExpList) expList).expList instanceof EmptyExpList) break;
            else expList = ((NonEmptyExpList) expList).expList;
        }

        if(funParamsId.size() == funParamsValue.size())
        {
            for(int i = 0; i < funParamsId.size(); i++)
            {
                state.put(funParamsId.get(i), funParamsValue.get(i));
            }
        }

        return funDef.exp.Eval(state);
    }
}
