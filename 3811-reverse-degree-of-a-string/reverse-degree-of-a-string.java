class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            
            int revAlphabetPos = 26 - (s.charAt(i) - 'a');
            
            
            int stringPos = i + 1;
            
            totalSum += revAlphabetPos * stringPos;
        }
        
        return totalSum;
    }
}