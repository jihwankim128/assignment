package 과제;

public class DbLinkedList {
    Node listHead; //list 시작-머리
    Node listTail; //list 마지막-꼬리
 
    private class Node {
        Object nodeData; //list입력 data를 Object로 자료형 구분 없이 입력받음
        Node rightNode; //nextNode, A노드의 다음노드 정의
        Node leftNode; //prevNode, A노드의 이전노드 정의
 
        public Node(Object nodeData) { //생성자 new Node()로 입력받는 인수를 Node객체로 정의
            this.nodeData = nodeData;
            this.rightNode = null;
            this.leftNode = null;
        }
 
    }
    public void displayLinkedList() { //list 출력 - ppt참고
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
    Node Search(int index) { //특정위치 노드를 찾기 위한 Search함수 - ppt displayLinkedList()참고
        Node x = listHead; 
        for (int i=0; i<index; i++) x = x.rightNode; //특정위치 만큼 리스트 앞 부분을 자름
        return x; //index 위치에 있는 Node를 x에 담아서 return
    }
    public void insertNode(Object data) { //list 마지막 값으로 추가 - ppt참고
        Node inNode = new Node(data);
        
        if (listHead == null) listHead = inNode;
        else {
        	listTail.rightNode = inNode;
        	inNode.leftNode = listTail;
        }
        listTail = inNode;
    }
    public void insertR(int index, Object data) { //특정위치 오른쪽에 값을 삽입
        Node node1 = Search(index); //특정위치 찾기 -> A,B,C,D 에서 index=1, B
        Node node2 = node1.rightNode; // B.rightNode == C
        Node inNode = new Node(data); // 삽입하기 위한 새로운 Node new
    	node1.rightNode = inNode;//B.rightNode == New, A->B->new, C->D 
        inNode.rightNode = node2;//New.rightNode == C, A->B->new->C->D  ==== 1)
        if (node2 != null) node2.leftNode = inNode; 
        // A,B,C 에서 index가 2일 때 node2 == null, new가 마지막 값일 때는 노드하나만 연결하면 되니까 null이면 무시 
        //else C.left == B -> C.left==new, A<-B, new<-C<-D
        inNode.leftNode = node1; // new.left == B, A<-B<-new<-C<-D ==== 2)  -> 1),2)로 이중리스트 연결
        if (inNode.rightNode == null) listTail = inNode; //삽입한 new가 마지막 값일 때는 listTail
    }
    public void insertL(int index, Object data) { // insertR의 반대경우, 특정위치 왼쪽에 값을 삽입
        Node node1 = Search(index);
        Node node2 = node1.leftNode;
        Node inNode = new Node(data);
        node1.leftNode = inNode;
        inNode.leftNode = node2;
        if (node2 != null) node2.rightNode = inNode; 
        // index가 0일 때 왼쪽에 삽입하므로 0번째 node.leftNode는 null 이 경우 무시
        inNode.rightNode = node1;
        if (inNode.leftNode == null) listHead = inNode;
        //입력받은 값 new.leftNode가 null일 경우 인덱스가 0이라는 의미, listHead
    }
    public void removeNode(Object data) { //인자로 입력받은 값을 list에서 삭제 - ppt참고
        Node remove = null; 
        Node before = null;  

        for (remove = listHead; remove != null; before = remove, remove = remove.rightNode) {
        	if (remove.nodeData == data) {
        		if (remove == listHead) {
                    if (listTail == listHead)  {
                    	listTail = null; 
                        listHead = null; 
                        break;
                    }
	                listHead = remove.rightNode; 
	                break;
            	}
               	else if (remove == listTail) {
            	   listTail = before; 
                   listTail.rightNode = null;	
                   break;
               	}
               	else{
               		Node set = remove.rightNode;
    	        	set.leftNode = remove.leftNode;//C.left == B => C.left == A, A<-C
    	        	before.rightNode = set;//A.right == B => A.right == C, A-> 이중링크
                   break;
               	}
        	}
        }
    }
    public void removeR(int index) { // index의 오른쪽 값을 삭제
    	if(Search(index+1).rightNode == null) { 
    		/* A,B,C에서 index==1 일 때 node==B. 즉, C를 삭제해야함. C=index+1 이므로 Search(index+1).
    		rightNode가 null이면 index가 마지막 값 바로 앞에 값을 참조했음을 알 수 있음*/ 
    		Node node1 = Search(index); // A,B,C에서 마지막값이 되려면 index==1, node1==B
    		node1.rightNode=null; //B.rightNode==null, A->B->C --> A->B
    		listTail=node1; //위 작업으로 node1은 listTail
    	}
    	else {//index값의 오른쪽 값이 마지막 값이 아닐 때
    		Node node1 = Search(index); //A,B,C에서 B를 삭제할 경우 뿐, Search(0)이다.
    		Node node2 = Search(index+2); //node2==Search(2), C
    		node1.rightNode = node2; //A.right==C, A->B->C --> A->C
    		node2.leftNode = node1; //C.left==A, A<-B<-C --> A<-C  위코드와 합치면 이중리스트 A<->C
    	}
    }
    public void removeL(int index) { //index의 왼쪽 값을 삭제, removeR의 반대 경우
    	if(Search(index-1).leftNode == null) { //A,B,C에서 index-1 == A, A.left==NULL, 공백을 알 수 있다.
    		Node node1 = Search(index); 
    		node1.leftNode=null;
    		listHead=node1;
    	}
    	else {
    		//System.out.println("들렸습니다.");
    		Node node1 = Search(index);
    		//System.out.println(node1.nodeData);
    		Node node2 = Search(index-2);
    		//System.out.println(node2.nodeData);
    		node2.rightNode = node1;
    		//System.out.println(node1.leftNode.nodeData);
    		//System.out.println(node2.rightNode.nodeData);
    		node1.leftNode = node2;
    	}
    }
}