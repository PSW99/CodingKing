import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer = {};
        String[] st = {"code","date","maximum","remain"};
        
        HashMap <String,Integer> map = new HashMap();
        
        for(int i=0;i<4;i++){
            map.put(st[i],i);
        }
        
        int n1 = map.get(ext); //ext 인덱스 값
        answer = extract(data,n1,val_ext); // ext기준 val_ext보다 낮은 2차원배열 리턴 메소드
        
        int n2 = map.get(sort_by); //sort_by 인덱스 값
        sorting(answer,n2); //sort_by 기준 정렬 메소드
        
        return answer;
    }
    
    public int[][] extract(int[][] arr,int ext,int val){
        
        List <Integer> list = new ArrayList();
        for(int i =0; i<arr.length; i++){
            if(arr[i][ext] < val){
                for(int j = 0; j < 4; j++){
                    list.add(arr[i][j]);
                }
            }
        }
        
        int array [][] = new int[list.size() / 4][4];
        
        for(int i =0; i<array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                array[i][j] = list.remove(0);
            } 
        }
        
        return array;
    }
    
    public void sorting(int[][] arr,int sort_by){
        Arrays.sort(arr, (o1, o2) -> {
            return o1[sort_by] - o2[sort_by]; // 오름차순 정렬
        });
    }
}