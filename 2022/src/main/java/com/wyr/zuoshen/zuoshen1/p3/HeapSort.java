package com.wyr.zuoshen.zuoshen1.p3;

public class HeapSort {

    /**
     * 时间复杂度：O(NlogN)
     * 大顶堆和小顶堆对应的都是一颗完全二叉树，所以在数组中的下标肯定是连续的
     * 左孩子下标=父下标*2+1
     * 右孩子下标=父下标*2+2
     * 父节点下标=(左/右孩子下标-1)/2
     *
     * PriorityQueue：底层就是一个堆，构造器不传值，默认小顶堆
     * 当然在构造器中传入一个比较器是可以即用lambda表达式为:(s1,s2)=>s2-s1
     * 我们也可以根据自己的需求定制比较器。如果年龄不一样，按照年龄进行升序，如果年龄一样按照身高进行升序排
     * lambda表达式为：(p1,p2)=> p1.getAge()==p2.getAge() ? p1.getTall()-p2.getTall() : p1.getAge()-p2.getAge()
     * @param args
     */
    public static void main(String[] args) {
        int[] arr=new int[]{5,2,3,1};
        heapSort55(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void heapSort510(int [] arr){
        if(arr==null||arr.length==1)
            return;

        //首先应该初始化为大顶堆
        for(int i=0;i<arr.length;i++){
            heapInsert55(arr,i);
        }

        //大顶堆初始化完毕
        int heapSize=arr.length; //堆大小
        swap(arr,0,--heapSize); //将堆顶的元素移动到最后一个数组元素的位置

        while (heapSize>1){ //注意这是>1
            heapify55(arr, 0,heapSize);
            swap(arr,0,--heapSize);
        }

    }

    //5.10练习
    public static void heapInsert510(int [] arr, int index){ //从下往上的过程
        while (arr[index]>arr[(index-1)/2]){
             swap(arr,index,(index-1)/2);
             index=(index-1)/2;
        }
    }

    public static void heapify510(int [] arr, int index, int heapSize){
         int left=index*2+1; //左孩子的下标
         while (left<heapSize){ //有左孩子
             int largest=left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;//如果右孩子存在且右孩子的值大于左孩子，largest保存较大孩子的下标

             largest=arr[largest]>arr[index]?largest:index; //arr[largest]和arr[index]继续比较，largest保存较大的下标

             if(largest==index)
                 break;

             swap(arr,index,largest);
             index=largest;
             left=index*2+1;
         }
    }




    //5.5练习
    public static void heapSort55(int [] arr){
           //初始化这个大顶堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert55(arr,i);
        }
        int heapSize=arr.length;
        swap(arr,0,--heapSize);
        while (heapSize>1){
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }

    }

    public static void heapInsert55(int[] arr, int index){//从下往上的过程
        while (arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }
    public static void heapify55(int[] arr,int index, int heapSize){//从上往下的过程
           int left=index*2+1;//左孩子的下标
           while (left<heapSize){
               int largest=left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;//如果右孩子存在，切右孩子的值大于左孩子，largest保存右孩子的下标，否则largest保存左孩子的下标

               largest=arr[largest]>arr[index]?largest:index;

               if(largest==index)
                   break;

               swap(arr,index,largest);
               index=largest;
               left=2*index+1;
           }
    }









    //4.30练习
    public static void heapInsert430(int [] arr, int index){
        //父节点的下标为：(index-1)/2
        while (arr[index]>arr[(index-1)/2]){//假设index为0代表根结点，此时这个条件不满足；也就是当子节点大于父节点时这个while才会执行
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }
    public static void heapify430(int []arr, int index, int heapSize){
        int left=index*2+1;//左孩子的下标
        while (left<heapSize){
            int largest=left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;//右孩子存在，且右孩子比左孩子大，将largest保存右孩子的下标；否则，保存左孩子的下标
            largest=arr[index]>arr[largest]?index:largest;
            if(largest==index)//如果largest就是保存的index的下标，就break
                break;
            swap(arr,largest,index);//将父节点设置为最大值
            index=largest;
            left=index+2+1;
        }
    }






    //4.27练习
   /* public static void heapInsert(int []arr,int index){
        while (arr[index]>arr[(index-1)/2]){
            swap(arr, index,(index-1)/2);
            index=(index-1)/2;
        }

    }*/

    //4.27练习
    /*public static void heapify(int[] arr,int index,int heapSize){
        //左孩子
        int left=index*2+1;
        //代表有孩子
        while (left<heapSize){
            //是否有右孩子，largest保存最大孩子的下标
            int largest=left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;

            largest=arr[largest]>arr[index]?largest:index;

            if(largest==index)
                break;

            swap(arr,index,largest);
            index=largest;
            left=index*2+1;
        }

    }*/

    //4.28练习
    public static void heapInsert(int [] arr, int index){//上浮的过程
         while (arr[index]>arr[(index-1)/2]){//子节点，比父节点大
             swap(arr,index,(index-1)/2);
             index=(index-1)/2;
         }
    }

    //4.28练习
    public static void heapify(int[] arr, int index,int heapSize){
        int left=index*2+1;//左孩子下标
        while (left<heapSize){//当有左孩子时
            //largest先保存左右孩子的最大值的下标
            int largest=left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;

            largest=arr[largest]>arr[index]?largest:index;

            if(largest==index){
                break;
            }
            swap(arr,largest,index);
            index=largest;
            left=index*2+1;
        }
    }





    //HeapInsert过程：将堆（大顶堆）对应的数组中的index位置的数组元素，调整到合适的位置
    /*public static void heapInsert(int[] arr,int index){
        while (arr[index]>arr[(index-1)/2]){//while循环的停止条件：1，需要调整的数不大于自己的父节点。2，需要调整的数来到了堆顶
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }
*/

    /**
     * Heapify：堆化的过程
     * arr   数组
     * index  代表父节点，对于这个父节点下的堆进行调整的过程
     * heapSize 堆的大小
     */
    /*public static void heapify(int[] arr,int index ,int heapSize) {
        //左孩子的下标
        int left=index*2+1;
        //代表有孩子
        while (left<heapSize){
            //定义largest保存父节点，左/右（如果有）孩子中最大的那个节点的下标
            //先保存左右(如果有)孩子中最大值的下标
            int largest=left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;

            //在和父节点的值进行比较
            largest=arr[largest]>arr[index]?largest:index;

            //左右孩子都没干过父节点的值，break
            if(largest==index)
                break;

            swap(arr,largest,index);
            index=largest;
            left=index*2+1;
        }
    }*/



    //交换数组中的arr[i]和arr[j]的值
    public static void swap(int arr[] ,int i,int j){
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }
}
