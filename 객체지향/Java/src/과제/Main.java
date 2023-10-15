package 과제;

public class Main {
	static DbLinkedList list = new DbLinkedList();
	//DbLinkedList Class을 list에 정의
	public static void insertNode() { //insertNode 출력 함수
		list.insertNode("insertNode0");
		list.insertNode("insertNode1");
		list.insertNode("insertNode2");
		list.insertNode("insertNode3");
		list.insertNode("insertNode4");
		System.out.println("insertNode(\"insertNode0~4\")");
		list.displayLinkedList();
	}
	public static void insertR() { //insertR 출력 함수
		list.insertR(5, "lastData");
		list.insertR(0, "secondData");
		System.out.println("insertR(4, \"lastData\"), insertR(0, \"secondData\")");
		list.displayLinkedList();
	}
	public static void insertL() {//insertL 출력 함수
		list.insertL(0, "firstData");
		list.insertL(7, "lastPrevData");
		System.out.println("insertL(0, \"firstData\"), insertL(7, \"lastPrevData\")");
		list.displayLinkedList();
	}
	public static void removeNode() {//removeNode 출력 함수
		list.removeNode("insertNode2");
		list.removeNode("insertNode3");
		System.out.println("removeNode(\"insertNode2\"), removeNode(\"insertNode3\")");
		list.displayLinkedList();
	}
	public static void removeL() {//removeL 출력 함수
		list.removeL(5);
		list.removeL(1);
		System.out.println("removeL(5), removeL(1)");
		list.displayLinkedList();
	}
	public static void removeR() {//removeR 출력 함수
		list.removeR(3);
		list.removeR(0);
		System.out.println("removeR(3), removeR(0)");
		list.displayLinkedList();
	}
	public static void main(String[] args) {
		insertNode();
		insertR();
		insertL();
		removeNode();
		removeL();
		removeR();
	}

}
