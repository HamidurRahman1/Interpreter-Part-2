package interpreter;

import java.util.*;

final class Id extends Exp
{
    String id;

    Id(String s)
    {
        id = s;
    }

    void printParseTree(String indent)
    {
        super.printParseTree(indent);
        String indent1 = indent+" ";
        IO.displayln(indent1 + indent1.length() + " " + id);
    }

    Val Eval(HashMap<String, Val> state)
    {
        if(state.containsKey(id)) return state.get(id).cloneVal();

        if(Parser.funMap.get(id) == null) return null;
        else return new FunVal(id);
    }
}
