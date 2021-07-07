package cn.eatboooo.study;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/6 23:48
 */
public class Demo02_heap {
    public static class maxHeap {
        int size;
        int[] heap;
        int limit;

        public maxHeap(int limit) {
            this.limit = limit;
            size = 0;
            heap = new int[limit];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int i) {
            if (isFull()) {
                return;
            }
            heap[size] = i;
            heapInset(heap, size++);
        }

        public int pop() throws Exception {
            if (isEmpty()) {
                throw new Exception("null");
            }
            int ans = heap[0];
            // 这里要先交换，一开始到时候妹想到，其实放 heapify 里面交换也可以
            swap(heap, 0, --size);
            heapify(heap, 0, size);
            return ans;
        }

        private void heapify(int[] heap, int i, int size) {
            // 和左右孩子比
            int left = (i << 1) | 1;
            // 判断是否越界
            while (left < size) {
                // 判断是否有右孩子,同时获取较大下标
                // 这个第一时间没有想到 需要巩固
                int la = (left + 1 < size) && heap[left + 1] > heap[left] ? left + 1 : left;
                la = heap[i] > heap[la] ? i : la;
                // 一个优化 一开始忘了
                if (la == i) {
                    break;
                }
                swap(heap, la, i);
                // 这个一开始忘了，需要巩固
                i = la;
                left = (left << 1) | 1;
            }
        }

        private void heapInset(int[] heap, int i) {
            //              0
            //        1           2
            //     3     4     5     6
            //   7  8  9  10
            // 画个完全二叉树
            // 不可以使用 (i - 1) >> 1，,当 i 为 0 的时候，这个会出现负数的情况
            // 下标 size 对应的父节点 （size -1）/2
            // int f = ((i - 1)/2);
            // 到最后 肯定是 f = 0 i = 0，所以一定会跳出循环
            while (heap[((i - 1)/2)] < heap[i]) {
                swap(heap,((i - 1)/2), i);
                i = ((i - 1)/2);
            }
        }


        public static void swap(int[] arr, int l, int r) {
            int i = arr[l];
            arr[l] = arr[r];
            arr[r] = i;
        }

    }

    public static void main(String[] args) throws Exception {
        maxHeap maxHeap = new maxHeap(5);
        maxHeap.push(1);
        maxHeap.push(2);
        maxHeap.push(6);
        maxHeap.push(4);
        maxHeap.push(5);
        while (!maxHeap.isEmpty()) {
            int pop = maxHeap.pop();
            System.out.println("pop = " + pop);
        }
    }
}
