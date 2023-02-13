/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;
class DisjointSet{
    List<Integer> parent = new ArrayList<Integer>();
    List<Integer> rank = new ArrayList<Integer>();
    public DisjointSet(int n){
        for(int i=0;i<n;i++){
            rank.add(0);
            parent.add(i);
        }
    }
    public int findUlParent(int u){
        //base case :
        if (u==parent.get(u)){
            return u;
        }
        
        //recurrsive case :
        parent.set(u, findUlParent(u));
        return parent.get(u);
    }
    public void unionSet(int u, int v){
        //if ultimate parent not equal to target ultimate
        int ulpar_u = findUlParent(u);
        int ulpar_v = findUlParent(v);
        
        if (ulpar_v==ulpar_u) return;
        
        else if(rank.get(ulpar_u)<rank.get(ulpar_v)){
            parent.set(u, ulpar_v);
        }
        else if(rank.get(ulpar_v)<rank.get(ulpar_u)){
            parent.set(v, ulpar_u);
        }
        else{
            //ranks are equal then chaning v's parent as ulpar_u and rank+=1
            int rankU = rank.get(u);
            rank.set(u, rankU+1);
            parent.set(v, ulpar_u);
            
        }
    }
}
public class Main
{
	public static void main(String[] args) {
		DisjointSet dis = new DisjointSet(7);
		dis.unionSet(1, 2);
		dis.unionSet(2, 3);
		dis.unionSet(4, 5);
		dis.unionSet(6, 7);
		dis.unionSet(4, 6);
		dis.unionSet(3, 4);
		
		System.out.println("Om");
		//System.out.println(dis.findUlParent(1)+" "+ dis.findUlParent(4));
	}
}
