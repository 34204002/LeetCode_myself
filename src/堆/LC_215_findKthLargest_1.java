package 堆;

import java.util.PriorityQueue;

public class LC_215_findKthLargest_1 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for (int num : nums) {
            if(queue.size()<k){
                queue.add(num);
            }else{
                if(queue.peek()<num){
                    queue.poll();
                    queue.add(num);
                }
            }
        }
        return queue.peek();
    }
}
