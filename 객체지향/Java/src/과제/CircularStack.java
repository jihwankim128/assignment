package 과제;

public class CircularStack { 
    Node listHead; 
    Node listTail; 
    
    private static class Node { //CircularLinkedList와 동일
        Object nodeData; 
        Node nextNode; 
        Node prevNode; 
        public Node(Object nodeData) { //CircularLinkedList와 동일
            this.nodeData = nodeData;
            this.nextNode = null;
            this.prevNode = null;
        }
 
    }
    public void displayLinkedList() { //CircularLinkedList와 동일
		if(listHead!=null) { 
			Node last = new Node(1);
	    	System.out.print("list : {");
	    	for(Node x = listHead; x.nextNode!=listHead; x=x.nextNode) { 
				System.out.print(x.nodeData+", "); 
				last = x.nextNode;
			}
			System.out.println(last.nodeData +"}"); 
		}
		else if(listHead==null) System.out.println("비어 있는 리스트입니다."); 
	}
    public void pushNode(Object data) { //CircularLinkedList와 동일
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
    	Node pop = listTail; //A,B,C 에서 pop=C;
    	if(listTail==listHead) { //Stack에서 값이 1개만 남았을 경우 꼬리와 머리를 지움
    		listHead=null; 
    		listTail=null;
    	}
    	else{
    		listTail=pop.prevNode;// C.prev == B를 listTail
        	listTail.nextNode=listHead;//B.next = A(listHead)
        	listHead.prevNode=listTail; //A.prev=B(listTail) 이중리스트
    	}
    }
    //아래 두 함수로 원형임을 알 수 있음.
    public void listTailNext(){ //listTail 뒤에 있는 노드 찾기 - Head와 연결되어 있으면 원형 한쪽
    	Node next = listTail;
    	System.out.printf(next.nodeData+"의 다음 노드 = "+next.nextNode.nodeData+"\n"); 
    }
    public void listHeadPrev(){ //listHead 앞에 있는 노드 찾기 - Tail과 연결되어 있으면 원형 한쪽
    	Node prev = listHead;
    	System.out.printf(prev.nodeData+"의 이전 노드 = "+prev.prevNode.nodeData+"\n");
    }
    
	static CircularStack stack = new CircularStack(); 
	
    public static void push() { //랜덤 값 push
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


