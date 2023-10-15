package ����;

public class CircularLinkedList { //���� ���߸���Ʈ
    Node listHead; //list ����-�Ӹ�
    Node listTail; //list ������-����
    
    private static class Node {
        Object nodeData; //list�Է� data�� Object�� �ڷ��� ���� ���� �Է¹���
        Node nextNode; //nextNode, A����� ������� ����
        Node prevNode; //������� ����
        public Node(Object nodeData) { //������ new Node()�� �Է¹޴� �μ��� Node��ü�� ����
            this.nodeData = nodeData;
            this.nextNode = null;
            this.prevNode = null;
        }
 
    }
    public void displayLinkedList() { //list ��� - ppt����
		if(listHead!=null) { //�������� �������
			Node last = new Node(1);
	    	System.out.print("list : {");
	    	for(Node x = listHead; x.nextNode!=listHead; x=x.nextNode) { 
	    		//�����̹Ƿ� ���� ���� listHead �� ������ �ݺ�
				System.out.print(x.nodeData+", "); //A,B,C���� B���� ����ϸ�
				last = x.nextNode; //last�� B.next�� C�� ��. for�� ���ǿ��� C.next�� ����̹Ƿ� last�� ����Ʈ�� ������ ��
			}
			System.out.println(last.nodeData +"}"); //������ �� ���
		}
		else if(listHead==null) System.out.println("��� �ִ� ����Ʈ�Դϴ�."); //�������� ���� ���
	}
    public void insertNode(Object data) { //list ������ ������ �߰� - ppt����
        Node inNode = new Node(data);
        
        if (listHead == null) listHead = inNode; //�������� ������� �����Է¹��� ���� ������
        else { // �̹� �������� ������� ������ ��������.
        	listTail.nextNode = inNode; //�Է¹��� ���� ���������� ����
        	inNode.prevNode = listTail; //���� ������ ���� �Է¹��� �� prev�� ����
        }
        listTail = inNode; //���ι��� ���� listHead�� �� listTail�� �ǹǷ� if else�� �ٱ��� ġȯ
        listHead.prevNode = listTail; // �����̹Ƿ� listHaed�� listTail�� ����
        listTail.nextNode = listHead; // listTail�� listHead�� ���������� ���� ����Ʈ
    }
    public void removeAll() { //insert ���� �����ϰ� �ϱ� ���� removeAll listHead�� Tail�� ����
    	listHead=null; listTail=null;
    	System.out.printf("\n");
    }
    public void removeNode(Object data) { //ppt����
    	Node remove = null; 
        Node before = null;  

        for (remove = listHead; remove != null; before = remove, remove = remove.nextNode) {
        	if (remove.nodeData == data) {
	        	if (remove == listHead) {
	        		if (listTail == listHead)  { //����Ʈ�� 1���� ��� 
	        			listTail = null;
	        			listHead = null;
	        			break; //�������� ���ֵ� ������. �������� ���� �� �� null, ����ӵ��� ���� �������� break;
	        		}
	                listHead = remove.nextNode; //data�� listHead������ �������� ������ �� Head�� ���� ������ ����
	        		listHead.prevNode = listTail; // Head�� ������ ����
	        		listTail.nextNode = listHead; // ������ Head�� ����
	        		break;
	        	}
	            else if (remove == listTail) { //���� ���� ������ ���
	            	listTail = before; //������ ����Ʈ �� data�� ���� ������ ����
	                listTail.nextNode = listHead; 
	                listHead.prevNode = listTail;
	                break;
	            }
	            else { //���� ���� Head�� Tail ������ ������ ���
	            	Node set = remove.nextNode; // A,B,C ���� B������ set=C
	            	set.prevNode = remove.prevNode; // C.prev = B.prev(A), (A<-B<-C) -> (A<-C);
		        	before.nextNode = set; // A.next = C; (A->B->C) -> (A->C)
		            break;
	            }
	        	
        	}
        }
    }
    public void ShowNextNode(Object data){ //���� ����Ʈ�� ������ ���� �Լ�
    	Node next = null;
    	for (next = listHead; next.prevNode.nodeData != data; next = next.nextNode); 
    	//A,B,C���� A�� C�� ����Ǿ������� �����ִ� �Լ� data==C�� ��� Head==A, A.prev == C, �ٷ�ã��
    	System.out.printf(data+"�� ���� ��� = "+next.nodeData+"\n"); // ������ ã�� ����� nodeData�� ���
    }
    public void ShowPrevNode(Object data){ //ShowNextNode�� �ݴ���
    	Node prev = null;
    	for (prev = listTail; prev.nextNode.nodeData != data; prev = prev.prevNode);
    	//A,B,C���� data==A�� A�� �������� C�� ã�°���, Tail==C, C.next==A, �ٷ�ã��
    	System.out.printf(data+"�� ���� ��� = "+prev.nodeData+"\n");//C.next�� A�̹Ƿ� C�� ���
    }
    
	static CircularLinkedList list = new CircularLinkedList(); //���� ����Ʈ Ŭ������ list
	
    public static void insert() { //list����
    	list.insertNode("FirstInsert");
    	list.insertNode("SecondInsert");
    	list.insertNode("ThirdInsert");
    }
    public static void main(String[] args) {
    	insert();
    	list.displayLinkedList();
    	list.removeNode("FirstInsert");
    	list.displayLinkedList();
    	list.removeAll();
    	
    	insert();
    	list.displayLinkedList();
    	list.removeNode("SecondInsert");
    	list.displayLinkedList();
    	list.removeAll();
    	
    	insert();
    	list.displayLinkedList();
    	list.removeNode("ThirdInsert");
    	list.displayLinkedList();
    	list.removeAll();
    	
    	insert();
    	list.displayLinkedList();
    	list.ShowPrevNode("FirstInsert");
    	list.ShowNextNode("ThirdInsert");
    }
}