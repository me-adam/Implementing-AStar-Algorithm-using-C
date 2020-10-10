
//@ Mustafa Adam
import java.util.StringTokenizer;
public class AStar{

public static void main(String args[]){
    int nodelevel[]=new int[100];
    int nodelevel2[]=new int[1];
    nodelevel2[0]=1;
    int evaluatedlevel[]=new int[100];
    String edges[]=new String[7];
    String Nodes[]=new String[7];
    char generated[]=new char[100];
    char evaluated[]=new char[100];
    char inmemoryChar[]=new char[100];
    int inmemoryInt[]=new int[100];
    int memindex[]=new int[1];
    memindex[0]=1;
    int level[]=new int[200];
    int gindex[]=new int[1];
    gindex[0]=1;
    int eindex[]=new int[1];
    eindex[0]=1;
    int incre[]=new int[100];
    
    
    int h1[]=new int[7];
    Nodes[0]="A,B,C,D,E";
    Nodes[1]="B,C,A";
    Nodes[2]="C,A,B,D,F";
    Nodes[3]="D,A,C,G";
    Nodes[4]="E,A,G";
    Nodes[5]="F,C,G";
    Nodes[6]="G,D,F,E";
    
    edges[0]="2,6,9,8";
    edges[1]="3,2";
    edges[2]="6,3,6,4";
    edges[3]="9,6,7";
    edges[4]="8,9";
    edges[5]="4,5";
    edges[6]="7,5,9";
    
    h1[0]=12;
    h1[1]=10;
    h1[2]=5;
    h1[3]=6;
    h1[4]=7;
    h1[5]=1;
    h1[6]=0;
    char begin='A';
    char current;
    char goal='G';
    if(checkNode(begin,goal)){
        System.out.println(begin+" is the goal");
        generated[0]=begin;
        evaluated[0]=begin;
        System.exit(0);
    }
    generated[0]=begin;
    evaluated[0]=begin;
    evaluatedlevel[0]=0;
    inmemoryChar[0]='A';
    System.out.println(begin+" been chosen because it has small F(n):12");
    inmemoryInt[0]=1000;
    level[0]=0;
    nodelevel[0]=0;
    String Node;
    int indexto[]=new int[1];
    indexto[0]=0;
    while(true){
        Node=locateNode(Nodes,begin);
        char smallone=calculate(Node,edges,h1,Nodes,generated,gindex,inmemoryChar,inmemoryInt,memindex,evaluated,evaluatedlevel,eindex,level,indexto,nodelevel,nodelevel2);
        evaluated[eindex[0]]=smallone;
        eindex[0]++;
        if(checkNode(smallone,goal)){
            System.out.println(smallone+" is the goal");
            break;
        }
        else
            begin=smallone;
    }//end of while loop
    
}//end of main
/*this method determin the next step of the node and return it back*/
public static char calculate(String Node,String edges[],int h1[],String Nodes[],char generated[],int gindex[],char inmemoryChar[],int inmemoryInt[],int memindex[],char evaluated[],int evaluatedlevel[],int eindex[],int level[], int indexto[], int nodelevel[], int nodelevel2[]){
    StringTokenizer st=new StringTokenizer(Node,",");
    int ind=Integer.parseInt(st.nextToken());
    String ed=edges[ind];
    StringTokenizer st2=new StringTokenizer(ed,",");
    st.nextToken();
    int len=st.countTokens();
    int resul[]=new int[len];
    char resulChar[]=new char[len];
    int level1[]=new int[len];
    for(int i=0;i<len;i++){
        resulChar[i]=st.nextToken().charAt(0);
        String str=locateNode(Nodes,resulChar[i]);
        int ind2=Integer.parseInt(str.charAt(0)+"");
        int le=Integer.parseInt(st2.nextToken());
        resul[i]=h1[ind2]+le+level[indexto[0]];
        level1[i]=le+level[indexto[0]];
        
    }// end of for loop
    
    for(int i=0;i<resulChar.length;i++){
        generated[gindex[0]]=resulChar[i];
        gindex[0]++;
    }//end of for loop

    boolean bo;
    for(int i=0;i<resulChar.length;i++){
        bo=true;
        for(int x=0;x<eindex[0];x++){
            if(resulChar[i]==evaluated[x]){
                bo=false;
                break;
            }//end of if
        }//end of for loop
        
        if(bo){
            inmemoryChar[memindex[0]]=resulChar[i];
            inmemoryInt[memindex[0]]=resul[i];
            level[memindex[0]]=level1[i];
            nodelevel[memindex[0]]=nodelevel2[0];
            memindex[0]++;
        }//end of if
    }//end of for loop
        
     char smallChar=inmemoryChar[0];
     int smallInt=inmemoryInt[0];
     for(int i=0;i<memindex[0];i++){
        if(inmemoryInt[i]<smallInt){
            smallInt=inmemoryInt[i];
            smallChar=inmemoryChar[i];
        }//end of if       
    }//end of for loop
    
    System.out.println("\n Current in the Memory >>>>");
    for(int i=0;i<memindex[0];i++){
        if(inmemoryInt[i]!=1000)
            System.out.println(inmemoryChar[i]+"  "+inmemoryInt[i]);  
    }//end of for loop
    
    System.out.println(">>>>"+smallChar+" been chosen because it has small F(n):"+smallInt);
    
    for(int i=0;i<memindex[0];i++){
        if(inmemoryInt[i]==smallInt&&smallChar==inmemoryChar[i]){
            inmemoryInt[i]=1000;
            indexto[0]=i;
        }//end of if       
    }//end of for loop
    if(nodelevel[indexto[0]]<nodelevel2[0]){
        for(int x=0;x<memindex[0];x++){
             if(inmemoryInt[x]==1000)
             for(int y=0;y<eindex[0];y++)
                 if(inmemoryChar[x]==evaluated[y]){
                    evaluated[y]=' ';
                 }
             
             }//end of for loop
        nodelevel2[0]=nodelevel[indexto[0]];
    }//end of for loop
    
    nodelevel2[0]++;
    return smallChar;
}//end of method

/* this method locate the node and return it back*/
public static String locateNode(String Nodes[], char searched){
    int index=0;
    for(int i=0;i<7;i++){
        StringTokenizer st = new StringTokenizer(Nodes[i],",");
        if(st.nextToken().charAt(0)==searched)
            index=i;
    }

return index+","+Nodes[index];

}//end of locateNode
/*this method check is the current node is the goal node*/
public static boolean checkNode(char curn, char goal){
    if(curn==goal)
    return true;
    else
    return false;
}//end of ckeckNode method
}//end of AStar class