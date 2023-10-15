package ����;

public class DbStack {
    Node listHead;
    Node listTail; 
 
    private class Node {//DoubleLinkedList�� ����
        Object nodeData; 
        Node rightNode;
        Node leftNode;
 
        public Node(Object nodeData) { //DoubleLinkedList�� ����
            this.nodeData = nodeData;
            this.rightNode = null;
            this.leftNode = null;
        }
 
    }
    public void displayLinkedList() { //DoubleLinkedList�� ����
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
    		System.out.printf("��� �ִ� ����Ʈ�Դϴ�.");
    }
    public void pushNode(Object data) { //DoubleLinkedList�� ����
        Node inNode = new Node(data);
        
        if (listHead == null) listHead = inNode;
        else {
        	listTail.rightNode = inNode;
        	inNode.leftNode = listTail;
        }
        listTail = inNode;
    }
    public void popNode() { //Stack���������� pop
    	Node pop = listTail; //A<->B<->C ���� pop=C;
    	if(listTail==listHead) { //Stack���� ���� 1���� ������ ��� ������ �Ӹ��� ����
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
