# Interpreter-Part-2

This project is a continuation of Project 3 (Interpreter-Part1). Extend the interpreter by incorporating evaluation of user-defined functions. Specifically, implement Eval functions for two instances of ⟨exp⟩: ⟨id⟩, identifier variables; and (⟨id⟩ ⟨exp list⟩), user-defined function calls. If you are following/using the sample program, they are represented by the classes Id and FunCall.

As pointed out in Project 3 description, implement function-call states by HashMap<String, Val> where Val is the class of values of variables; it maps the parameter variable named by a string to its value. Here are the specifications of java.util.HashMap.

Our project language is a functional language in which functions can take user-defined functions as parameters and return a user-defined function. (It is quite similar to a functional sub-language of Lisp.) For example, consider the following map function:

<pre>

map f list
{
	if (= list nil) then nil
	else (pair (f (first list)) (map f (second list)))
}

</pre>

It recursively applies parameter unary function f to each element of list. Lists of n elements a1, a2, …, an are implemented by nested pair objects tilted to right:
pair(a1, pair(a2, … pair(an, nil) … ))
The nil object represents the empty list. The expression (map square (pair 1 (pair 2 (pair 3 nil)))) evaluates to pair(1, pair(4, pair(9, nil))).

The following is an example of a function that returns a function:

<pre>

smallerFunction f g x
{
	if (< (f x) (g x)) then f else g
}

</pre>

Here f, g are parameter unary functions.

Passing/returning user-defined functions is implemented by including function objects in the domain of values/objects. Each function object simply contains the function name. In the sample program, it is an object of the FunVal class.

The following are the specifications of the Eval functions for the identifier variables and user-defined functions.

First, notice that an identifier in an expression may be a variable or a user-defined function name. For example, in an expression (f x y), f may be a variable or a user-defined function. We adopt the following scoping rule in this regard: If an identifier is used as both a variable and a user-defined function name in the program, it is interpreted as a variable overriding the function name. The following definition of Eval incorporates this rule.

FunVal(id) denotes the function object containing function name id.

<pre>
evaluation of identifiers in ⟨exp⟩

Eval( id, α ) =
   if id has a value v in α then v   // id is a variable name
   else if id is a user-defined function name then FunVal(id)
   else ⊥v

evaluation of function-call expressions

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
   
</pre>

Have the parser construct a map/table of FunDef objects indexed by function names. The formal parameter list and body expression of f-name are extracted from the FunDef object looked up in this map/table. (I've used HashMap<String, FunDef> for this purpose.)

Your interpreter is to read a string of ⟨exp⟩, evaluate it, and display the value or a runtime error message on the terminal.
