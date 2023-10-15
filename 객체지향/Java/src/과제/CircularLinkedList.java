package 과제;

public class CircularLinkedList { //원형 이중리스트
    Node listHead; //list 시작-머리
    Node listTail; //list 마지막-꼬리
    
    private static class Node {
        Object nodeData; //list입력 data를 Object로 자료형 구분 없이 입력받음
        Node nextNode; //nextNode, A노드의 다음노드 정의
        Node prevNode; //이전노드 정의
        public Node(Object nodeData) { //생성자 new Node()로 입력받는 인수를 Node객체로 정의
            this.nodeData = nodeData;
            this.nextNode = null;
            this.prevNode = null;
        }
 
    }
    public void displayLinkedList() { //list 출력 - ppt참고
		if(listHead!=null) { //시작점이 있을경우
			Node last = new Node(1);
	    	System.out.print("list : {");
	    	for(Node x = listHead; x.nextNode!=listHead; x=x.nextNode) { 
	    		//원형이므로 다음 값이 listHead 일 때까지 반복
				System.out.print(x.nodeData+", "); //A,B,C에서 B까지 출력하면
				last = x.nextNode; //last는 B.next인 C가 됨. for문 조건에서 C.next가 헤드이므로 last는 리스트의 마지막 값
			}
			System.out.println(last.nodeData +"}"); //마지막 값 출력
		}
		else if(listHead==null) System.out.println("비어 있는 리스트입니다."); //시작점이 없을 경우
	}
    public void insertNode(Object data) { //list 마지막 값으로 추가 - ppt참고
        Node inNode = new Node(data);
        
        if (listHead == null) listHead = inNode; //시작점이 없을경우 새로입력받은 값을 시작점
        else { // 이미 시작점이 있을경우 꼬리에 물려야함.
        	listTail.nextNode = inNode; //입력받은 값을 마지막으로 설정
        	inNode.prevNode = listTail; //기존 마지막 값을 입력받은 값 prev로 설정
        }
        listTail = inNode; //새로받은 값이 listHead일 때 listTail도 되므로 if else문 바깥에 치환
        listHead.prevNode = listTail; // 원형이므로 listHaed와 listTail을 연결
        listTail.nextNode = listHead; // listTail도 listHead와 연결함으로 이중 리스트
    }
    public void removeAll() { //insert 값을 동일하게 하기 위한 removeAll listHead와 Tail을 없앰
    	listHead=null; listTail=null;
    	System.out.printf("\n");
    }
    public void removeNode(Object data) { //ppt참고
    	Node remove = null; 
        Node before = null;  

        for (remove = listHead; remove != null; before = remove, remove = remove.nextNode) {
        	if (remove.nodeData == data) {
	        	if (remove == listHead) {
	        		if (listTail == listHead)  { //리스트가 1개일 경우 
	        			listTail = null;
	        			listHead = null;
	        			break; //시작점만 없애도 되지만. 안정성을 위해 둘 다 null, 연산속도를 위한 빠른종료 break;
	        		}
	                listHead = remove.nextNode; //data가 listHead이지만 다음값이 존재할 때 Head를 다음 값으로 지정
	        		listHead.prevNode = listTail; // Head와 꼬리를 연결
	        		listTail.nextNode = listHead; // 꼬리와 Head를 연결
	        		break;
	        	}
	            else if (remove == listTail) { //삭제 값이 꼬리일 경우
	            	listTail = before; //꼬리를 리스트 내 data의 이전 값으로 설정
	                listTail.nextNode = listHead; 
	                listHead.prevNode = listTail;
	                break;
	            }
	            else { //삭제 값이 Head와 Tail 사이의 원소일 경우
	            	Node set = remove.nextNode; // A,B,C 에서 B삭제시 set=C
	            	set.prevNode = remove.prevNode; // C.prev = B.prev(A), (A<-B<-C) -> (A<-C);
		        	before.nextNode = set; // A.next = C; (A->B->C) -> (A->C)
		            break;
	            }
	        	
        	}
        }
    }
    public void ShowNextNode(Object data){ //원형 리스트의 증명을 위한 함수
    	Node next = null;
    	for (next = listHead; next.prevNode.nodeData != data; next = next.nextNode); 
    	//A,B,C에서 A와 C도 연결되어있음을 보여주는 함수 data==C일 경우 Head==A, A.prev == C, 바로찾음
    	System.out.printf(data+"의 다음 노드 = "+next.nodeData+"\n"); // 위에서 찾은 결과의 nodeData를 출력
    }
    public void ShowPrevNode(Object data){ //ShowNextNode의 반대경우
    	Node prev = null;
    	for (prev = listTail; prev.nextNode.nodeData != data; prev = prev.prevNode);
    	//A,B,C에서 data==A로 A의 이전값은 C를 찾는과정, Tail==C, C.next==A, 바로찾음
    	System.out.printf(data+"의 이전 노드 = "+prev.nodeData+"\n");//C.next가 A이므로 C를 출력
    }
    
	static CircularLinkedList list = new CircularLinkedList(); //원형 리스트 클래스의 list
	
    public static void insert() { //list삽입
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