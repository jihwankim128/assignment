package 과제;

public class DbStack {
    Node listHead;
    Node listTail; 
 
    private class Node {//DoubleLinkedList와 동일
        Object nodeData; 
        Node rightNode;
        Node leftNode;
 
        public Node(Object nodeData) { //DoubleLinkedList와 동일
            this.nodeData = nodeData;
            this.rightNode = null;
            this.leftNode = null;
        }
 
    }
    public void displayLinkedList() { //DoubleLinkedList와 동일
    	if(listHead!=null) {
	    	Node last = new Node(1);
	    	System.out.print("list : {");
			for(Node x = listHead; x.rightNode!=null; x=x.rightNode) {
				System.out.print(x.nodeData+", ");
				last = x.rightNode;
			}
			System.out.printf(last.nodeData+"}\n\n");
    	}
    	else 
    		System.out.printf("비어 있는 리스트입니다.");
    }
    public void pushNode(Object data) { //DoubleLinkedList와 동일
        Node inNode = new Node(data);
        
        if (listHead == null) listHead = inNode;
        else {
        	listTail.rightNode = inNode;
        	inNode.leftNode = listTail;
        }
        listTail = inNode;
    }
    public void popNode() { //Stack구조에서의 pop
    	Node pop = listTail; //A<->B<->C 에서 pop=C;
    	if(listTail==listHead) { //Stack에서 값이 1개만 남았을 경우 꼬리와 머리를 지움
    		listHead=null; 
    		listTail=null;
    	}
    	else{
    		listTail=pop.leftNode;//listTail = C.left == B, A->B(listTail)->C
        	listTail.rightNode=null;//B.right=null, A->B
    	}
    }
	public static void main(String[] args) {
		DbStack stack=new DbStack();
		stack.pushNode(1);
		stack.pushNode(2);
		stack.pushNode(3);
		stack.displayLinkedList();
		
		stack.popNode();
		stack.displayLinkedList();
		
		stack.popNode();
		stack.displayLinkedList();
		
		stack.popNode();
		stack.displayLinkedList();
	}
}
