package com.study.codetop.first;

import com.study.common.entity.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 *  给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Lc143_RerankList {
    public void reorderList(ListNode head) {
        Map<Integer, ListNode> indexMap = new HashMap<>();
        int index = 0;
        ListNode current = head;
        while (current != null) {
            index ++;
            indexMap.put(index, current);
            current = current.next;
        }

        ListNode headHolder = new ListNode(-1);
        for (int i = 1; i <= index; i ++) {
            int key = (i % 2 == 1) ? (i + 1) / 2 : (index - (i - 1) / 2);
            headHolder.next = indexMap.get(key);;
            headHolder = headHolder.next;
        }
        headHolder.next = null;
    }
}
