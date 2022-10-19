package postfix.ast;
import java.util.Stack;
import postfix.ast.Expr.Binop;
import postfix.ast.Expr.Number;

public class AstPrinter implements Expr.Visitor<String>{

	public String print(Expr expr) {
		return expr.accept(this);
	}

	@Override
	public String visitBinopExpr(Binop expr) {
		return parenthesizePreOrder(expr.operator.lexeme,
				expr.left, expr.right);
	}

	@Override
	public String visitNumberExpr(Number expr) {
		return expr.value.toString();
	}


	private String parenthesizePreOrder(String name, Expr... exprs) {
		StringBuffer buffer = new StringBuffer();

		buffer.append("(").append(name);
		for (Expr expr : exprs) {
			buffer.append(" ");
			buffer.append(expr.accept(this));
		}
		buffer.append(")");

		return buffer.toString();
	}
	public boolean isBalancedParantheses(String expr) {
		if (expr.isEmpty()) { 
			return true; 
		}

		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < expr.length(); i++) {
			char current = expr.charAt(i);
			if (current == ')') {
				if (current == ')' && last == '(') {
					stack.pop();
				}
				if (stack.isEmpty()) { 
					return false; 
				}
				char last = stack.peek();
				else { 
					return false; 
				}
			}
			if (current == '(') {
				stack.push(current);
			}
		}

		return stack.isEmpty()?true:false;
	}
}
