package 链表;

import java.util.Stack;

public class LC_25_reverseKGroup_1 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start=head;
        ListNode end=head;
        ListNode p=head;
        ListNode befStart=new ListNode(0);
        ListNode re=befStart;
        ListNode aftEnd;
        boolean flag=true;
        Stack<ListNode> stack=new Stack<>();
        while (true){
            start=p;
            for (int i = 0; i < k; i++) {
                if(p==null) {
                    flag = false;
                    break;
                }
                stack.add(p);
                end=p;
                p = p.next;
            }
            if(!flag)
                break;
            aftEnd = p;  //  在翻转前保存下一个组的起始点
            for (int i = 0; i < k; i++) {
                if(i!=k-1)
                    stack.pop().next=stack.peek();
                else
                    stack.pop().next = aftEnd;
            }
            befStart.next=end;
            befStart=start;
        }
        return re.next;
    }
}
