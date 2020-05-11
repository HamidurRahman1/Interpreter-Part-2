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
        /*
           Eval( id, α ) =
           if id has a value v in α then v   // id is a variable name
           else if id is a user-defined function name then FunVal(id)
           else ⊥v
        */

        if(state.containsKey(id)) return state.get(id);


        return null;
    }
}
