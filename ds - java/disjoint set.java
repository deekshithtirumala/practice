/******************************************************************************

*******************************************************************************/
class DisjointSet{
    List<Integer> rank = new ArrayList<Integer>();
    List<Integer> parent = new ArrayList<Integer>();
    public DisjointSet(int n){
        for(int i=0;i<=n;i++){
            rank.add(0);
            parent.add(i);
        }
    }
    public int findUlparent(int u){
        if(u==parent.get(u)){
            return u;
        }
        
        //recurrsive case
        parent.set(u, findUlparent(parent.get(u)));
        return parent.get(u);
    }
    
    public void unionSet(int u, int v){
        int ultpar_u = findUlparent(u);
        int ultpar_v = findUlparent(v);
        
        if(ultpar_u==ultpar_v){
            return ;
        }
        
        else if(rank.get(ultpar_u)<rank.get(ultpar_v)){
            //adding u to v --> u's parent = v 
            //1      4        4
            //|\     |\      /|\
            //2 3    5 6    1 5 6
            //       | |   /| | |
            //       8 7  2 3 8 7
            //rank of 1's = 1 and rank of 4's = 2
            //ultimate parent of 3's parent is set to ultimate parent 7
            parent.set(ultpar_u, ultpar_v);
        }
        else if(rank.get(ultpar_u)> rank.get(ultpar_v)){
            parent.set(ultpar_v, ultpar_u);
        }
        else{
            //rank are equal rank should be incremented
            parent.set(ultpar_v, ultpar_u);
            rank.set(ultpar_u, rank.get(ultpar_u)+1);
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
