package ����;

public class CircularStack { 
    Node listHead; 
    Node listTail; 
    
    private static class Node { //CircularLinkedList�� ����
        Object nodeData; 
        Node nextNode; 
        Node prevNode; 
        public Node(Object nodeData) { //CircularLinkedList�� ����
            this.nodeData = nodeData;
            this.nextNode = null;
            this.prevNode = null;
        }
 
    }
    public void displayLinkedList() { //CircularLinkedList�� ����
		if(listHead!=null) { 
			Node last = new Node(1);
	    	System.out.print("list : {");
	    	for(Node x = listHead; x.nextNode!=listHead; x=x.nextNode) { 
				System.out.print(x.nodeData+", "); 
				last = x.nextNode;
			}
			System.out.println(last.nodeData +"}"); 
		}
		else if(listHead==null) System.out.println("��� �ִ� ����Ʈ�Դϴ�."); 
	}
    public void pushNode(Object data) { //CircularLinkedList�� ����
        Node inNode = new Node(data);
        
        if (listHead == null) listHead = inNode;
        else { 
        	listTail.nextNode = inNode; 
        	inNode.prevNode = listTail; 
        }
        listTail = inNode; 
        listHead.prevNode = listTail; 
        listTail.nextNode = listHead; 
    }
    public void popNode() { 
    	Node pop = listTail; //A,B,C ���� pop=C;
    	if(listTail==listHead) { //Stack���� ���� 1���� ������ ��� ������ �Ӹ��� ����
    		listHead=null; 
    		listTail=null;
    	}
    	else{
    		listTail=pop.prevNode;// C.prev == B�� listTail
        	listTail.nextNode=listHead;//B.next = A(listHead)
        	listHead.prevNode=listTail; //A.prev=B(listTail) ���߸���Ʈ
    	}
    }
    //�Ʒ� �� �Լ��� �������� �� �� ����.
    public void listTailNext(){ //listTail �ڿ� �ִ� ��� ã�� - Head�� ����Ǿ� ������ ���� ����
    	Node next = listTail;
    	System.out.printf(next.nodeData+"�� ���� ��� = "+next.nextNode.nodeData+"\n"); 
    }
    public void listHeadPrev(){ //listHead �տ� �ִ� ��� ã�� - Tail�� ����Ǿ� ������ ���� ����
    	Node prev = listHead;
    	System.out.printf(prev.nodeData+"�� ���� ��� = "+prev.prevNode.nodeData+"\n");
    }
    
	static CircularStack stack = new CircularStack(); 
	
    public static void push() { //���� �� push
    	stack.pushNode((int)(Math.random()*10)); 
    	stack.pushNode((int)(Math.random()*10)); 
    	stack.pushNode((int)(Math.random()*10));
    }
    public static void main(String[] args) {
    	push();
    	stack.displayLinkedList();
    	
    	stack.popNode();
    	stack.displayLinkedList();
    	
    	push();
    	stack.displayLinkedList();
    	
    	for(int i=0;i<5;i++) stack.popNode();
    	stack.displayLinkedList();
    	
    	push();
    	stack.displayLinkedList();
    	stack.listTailNext();
    	stack.listHeadPrev();
    }
}


