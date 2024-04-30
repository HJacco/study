package com.study.newcoder.lesson11;

import com.study.common.entity.TreeNode;

public class TreeMaxDistance {
    static class ReturnInfo{
        public int height;
        public int maxDistance;
        public ReturnInfo(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public int getMaxDistance(TreeNode head) {
        if (null == head) {
            return 0;
        }
        return process(head).maxDistance;
    }

    public ReturnInfo process(TreeNode head) {
        if (null == head) {
            return new ReturnInfo(0, 0);
        }
        ReturnInfo left = process(head.left);
        ReturnInfo right = process(head.right);
        int includeHeadDistance = left.height + right.height + 1;
        int p1 = left.maxDistance;
        int p2 = right.maxDistance;
        int maxDistance = Math.max(Math.max(p1, p2), includeHeadDistance);
        int height = Math.max(left.height, right.height) + 1;
        return new ReturnInfo(maxDistance, height);
    }

}
