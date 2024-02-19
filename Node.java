import java.lang.reflect.Array;
import java.util.Arrays;

public class Node {
  public String Name;
  public Node next;
  public int Age;


  

  public static Node createList(){
    Node person1 = new Node();
    person1.Name = "Supragya";
    person1.Age=16;

    
    
    Node person2 = new Node();
    person2.Name= "Shreeja";
    person1.next= person2;
    person2.Age=26;

    Node person3 =new Node();
    person3.Name= "Ram";
    person2.next= person3;
    person3.Age=36;

    Node person4 = new Node();
    person4.Name= "Shrar";
    person3.next= person4;
    person4.Age=6;

    Node person5 =new Node();
    person5.Name= "hari";
    person4.next= person5;
    person5.Age=26;

    return person1;
    
  }

  public static void printMax(int[] numbers){
    int largestnumber=1;

    for(int i = 0;i<7;i++){
      if (largestnumber<numbers[i]){
        largestnumber=numbers[i];
      }
      
    }
    System.out.println(largestnumber);

    
  }

  public static void printmin(int[] numbers){
    int smallestnumber=1;
    for(int i = 0;i<7;i++){
      if(smallestnumber>numbers[i]){
      smallestnumber=numbers[i];
    }
  }
  System.out.println(smallestnumber);

  }

  public static void printAll(int[] numbers){
    for(int i = 0;i<numbers.length;i++){
      System.out.println(numbers[i]);
    }
  }

  public static void printsumofall(int[] numbers) {
    int sum =0;
    for(int i =0;i<numbers.length;i++){
      sum=sum+numbers[i]; 
    }
    System.out.println(sum);
    
  }

//   public static void printlargestandsmallest(int[]numbers){
//     int value=1;
//     for(int i = 0;i<7;i++){
//       if(value>numbers[i] ){
//         value=numbers[i];
//       }
      
//   }
//   System.out.println(value);

//   for(int i = 0;i<7;i++){
//     if(value<numbers[i] ){
//       value=numbers[i];
//     }}
//   System.out.println(value);
  
  

// }
public static void printlargestandsmallest(int[]numbers){
  int smallestnumber=numbers[0];
  int largestnumber=numbers[0];
  for(int i = 0;i<numbers.length;i++){
    if (smallestnumber>numbers[i]  ){
      smallestnumber=numbers[i];
    }
    if (largestnumber<numbers[i]  ){
      largestnumber=numbers[i];
      }

    
  }
  System.out.println(smallestnumber);
  System.out.println(largestnumber);


}
public static void printsumofevenindex(int[]numbers){
  int sum = numbers[1];
  // for(int i=1;i<numbers.length;i++){
  //   if(numbers[i]%2==0){
  //   sum+=numbers[i];
  //   } 
  // }

  // for(int i=2;i<numbers.length;i+=2){
  //   sum+=numbers[i];
  // }
  for(int i=3;i<numbers.length;i+=2){
    sum+=numbers[i];
  }
  System.out.println(sum);


}
public static void printcountofoddnumber(int[]numbers){
  int count = 0;
  for(int i= 0;i<numbers.length;i++){
    if(numbers[i]%2!=0){
      count=count+1;
    
  }}
  
  System.out.println(count);
}

public static void printcountofevennumber(int[]numbers){
  int count = 0;
  for(int i= 0;i<numbers.length;i++){
    if(numbers[i]%2==0){
      count=count+1;  
  }
}
  
  System.out.println(count);
}
public static void printcountevenoddnumber(int[]numbers){
  int even=0;
  int odd=0;
  for (int i=0;i<numbers.length;i++){
    if(numbers[i]%2==0){
      even=even+1;
    }
    else{
      odd=odd+1;
    
    }

  }
  System.out.println(even);
  System.out.println(odd);


}




//sorting
public static int findminindex(int[] numbers){
  int smallestnumberindex=0;
  int smallestnumber=numbers[0];
  int i;
  for(i = 0;i<numbers.length;i++){
    if(smallestnumber>numbers[i]){
      smallestnumber=numbers[i];
      smallestnumberindex=i;
    }
    
  }

  System.out.println("+++++++++++++++++++++++++++++");
  printAll(numbers);
  // System.out.println(smallestnumber);
  System.out.println("+++++++++++++++++++++++++++++");

  return smallestnumberindex;

}

public static int[] sort1(int[]numbers){
  int smallestnumberindex=0;
  int tmp;
  for(int i = 0;i<numbers.length;i++){
    // Node.printAll(Arrays.copyOfRange(numbers, 0, 7));
    // System.out.println("\n");
    smallestnumberindex=findminindex(Arrays.copyOfRange(numbers, i, numbers.length));
    System.out.println("***********"+smallestnumberindex);
    tmp= numbers[smallestnumberindex+i];
    numbers[smallestnumberindex+i]=numbers[i];
    numbers[i]=tmp;

  }
  return numbers;

} 


  public int sum(int a, int b){
    int s = a+b;
    return s;
  }

  public static void main(String[] args) {
    Node person1 = Node.createList();

    // int sumofage =0;
    
      // while (person1 != null){
      //   // sumofage += person1.Age;

        
      //   System.out.println(person1.Name);
      //   person1 = person1.next;
        // if (person1.Name=="Shreeja")
        
      //  }
      // System.out.print(sumofage);

       int[] numbers = {7,11,5,6, 3, 2, 4, 0};
      //  System.out.println(Node.findMax(numbers));
      Node.printAll(Node.sort1(numbers));
      
  }
    
     
}
    
    
    // Node person2 = person1.next;
    // Node person3 = person2.next;
    // Node person4 = person3.next;
    // Node person5 = person4.next;
    
    // int c = 5;
    // int d = 3;
  
    // int Supragya = Node.sum(c,d);
    // System.out.println(person1.Name);
    // System.out.println(person2.Name);
    // System.out.println(person3.Name);
    // System.out.println(person4.Name);
    // System.out.println(person5.Name);


