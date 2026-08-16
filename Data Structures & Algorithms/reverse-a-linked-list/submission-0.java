/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode newPrev = null;
        ListNode curr = head;

        if (head == null) { return null; }

        ListNode oldNext = head.next;
        while (true) {
            curr.next = newPrev;

            if (oldNext == null) { break; }

            newPrev = curr;
            curr = oldNext;
            oldNext = oldNext.next;
        }

        return curr;
    }
}
