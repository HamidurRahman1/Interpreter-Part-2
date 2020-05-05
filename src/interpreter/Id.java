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

    Val Eval(HashMap<String,Val> state)
    {
        // You implement body code.
        return null;
    }
}
