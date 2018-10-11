package lab3;

public class LLStack {

    private Node top;

    public boolean IsEmpty() {
        return (top == null);
    }

    public void push(TNode element) {
        top = new Node(element, top);
    }

    public TNode pop() {
        if (IsEmpty()) {
            System.out.println("Stack is empty");
        }
        TNode element = top.element;
        top = top.next;
        return element;
    }

    public TNode top() {
        return top.element;
    }
}
