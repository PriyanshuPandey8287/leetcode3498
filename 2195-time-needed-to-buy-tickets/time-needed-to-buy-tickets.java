class Solution {
    public int timeRequiredToBuy(int[] t, int k) {
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        for(int i=0;i<t.length;i++){
            q.add(t[i]);
        }
        while(q.size()!=0){
            int top = q.poll()-1;
            count++;
            if(k==0 && top==0) return count;
            
            if(top!=0) q.add(top);
            
            k--;
            if(k==-1) k=q.size()-1;
        }
        return count;
    }
}