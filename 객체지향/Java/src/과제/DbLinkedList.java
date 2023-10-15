package ����;

public class DbLinkedList {
    Node listHead; //list ����-�Ӹ�
    Node listTail; //list ������-����
 
    private class Node {
        Object nodeData; //list�Է� data�� Object�� �ڷ��� ���� ���� �Է¹���
        Node rightNode; //nextNode, A����� ������� ����
        Node leftNode; //prevNode, A����� ������� ����
 
        public Node(Object nodeData) { //������ new Node()�� �Է¹޴� �μ��� Node��ü�� ����
            this.nodeData = nodeData;
            this.rightNode = null;
            this.leftNode = null;
        }
 
    }
    public void displayLinkedList() { //list ��� - ppt����
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
    Node Search(int index) { //Ư����ġ ��带 ã�� ���� Search�Լ� - ppt displayLinkedList()����
        Node x = listHead; 
        for (int i=0; i<index; i++) x = x.rightNode; //Ư����ġ ��ŭ ����Ʈ �� �κ��� �ڸ�
        return x; //index ��ġ�� �ִ� Node�� x�� ��Ƽ� return
    }
    public void insertNode(Object data) { //list ������ ������ �߰� - ppt����
        Node inNode = new Node(data);
        
        if (listHead == null) listHead = inNode;
        else {
        	listTail.rightNode = inNode;
        	inNode.leftNode = listTail;
        }
        listTail = inNode;
    }
    public void insertR(int index, Object data) { //Ư����ġ �����ʿ� ���� ����
        Node node1 = Search(index); //Ư����ġ ã�� -> A,B,C,D ���� index=1, B
        Node node2 = node1.rightNode; // B.rightNode == C
        Node inNode = new Node(data); // �����ϱ� ���� ���ο� Node new
    	node1.rightNode = inNode;//B.rightNode == New, A->B->new, C->D 
        inNode.rightNode = node2;//New.rightNode == C, A->B->new->C->D  ==== 1)
        if (node2 != null) node2.leftNode = inNode; 
        // A,B,C ���� index�� 2�� �� node2 == null, new�� ������ ���� ���� ����ϳ��� �����ϸ� �Ǵϱ� null�̸� ���� 
        //else C.left == B -> C.left==new, A<-B, new<-C<-D
        inNode.leftNode = node1; // new.left == B, A<-B<-new<-C<-D ==== 2)  -> 1),2)�� ���߸���Ʈ ����
        if (inNode.rightNode == null) listTail = inNode; //������ new�� ������ ���� ���� listTail
    }
    public void insertL(int index, Object data) { // insertR�� �ݴ���, Ư����ġ ���ʿ� ���� ����
        Node node1 = Search(index);
        Node node2 = node1.leftNode;
        Node inNode = new Node(data);
        node1.leftNode = inNode;
        inNode.leftNode = node2;
        if (node2 != null) node2.rightNode = inNode; 
        // index�� 0�� �� ���ʿ� �����ϹǷ� 0��° node.leftNode�� null �� ��� ����
        inNode.rightNode = node1;
        if (inNode.leftNode == null) listHead = inNode;
        //�Է¹��� �� new.leftNode�� null�� ��� �ε����� 0�̶�� �ǹ�, listHead
    }
    public void removeNode(Object data) { //���ڷ� �Է¹��� ���� list���� ���� - ppt����
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
    	        	before.rightNode = set;//A.right == B => A.right == C, A-> ���߸�ũ
                   break;
               	}
        	}
        }
    }
    public void removeR(int index) { // index�� ������ ���� ����
    	if(Search(index+1).rightNode == null) { 
    		/* A,B,C���� index==1 �� �� node==B. ��, C�� �����ؾ���. C=index+1 �̹Ƿ� Search(index+1).
    		rightNode�� null�̸� index�� ������ �� �ٷ� �տ� ���� ���������� �� �� ����*/ 
    		Node node1 = Search(index); // A,B,C���� ���������� �Ƿ��� index==1, node1==B
    		node1.rightNode=null; //B.rightNode==null, A->B->C --> A->B
    		listTail=node1; //�� �۾����� node1�� listTail
    	}
    	else {//index���� ������ ���� ������ ���� �ƴ� ��
    		Node node1 = Search(index); //A,B,C���� B�� ������ ��� ��, Search(0)�̴�.
    		Node node2 = Search(index+2); //node2==Search(2), C
    		node1.rightNode = node2; //A.right==C, A->B->C --> A->C
    		node2.leftNode = node1; //C.left==A, A<-B<-C --> A<-C  ���ڵ�� ��ġ�� ���߸���Ʈ A<->C
    	}
    }
    public void removeL(int index) { //index�� ���� ���� ����, removeR�� �ݴ� ���
    	if(Search(index-1).leftNode == null) { //A,B,C���� index-1 == A, A.left==NULL, ������ �� �� �ִ�.
    		Node node1 = Search(index); 
    		node1.leftNode=null;
    		listHead=node1;
    	}
    	else {
    		//System.out.println("��Ƚ��ϴ�.");
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