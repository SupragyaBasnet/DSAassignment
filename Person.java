public class Person {
  public String Name;
  public int Age;
  public String PhoneNumber;
  public String fname;
  public String lname;

  public String greeting(){
    String greeting = "Hello my name is " + this.Name + ". I am " + this.Age + " years old. ";
    return greeting;
  }
  public String BirthYear(int currentyear){
    int BirthYear= currentyear - this.Age;
    String Totalyear = "I was born in" + BirthYear;
    return Totalyear;    
  }
  public String fullname(){
    String fullname = this.fname + this.lname; 
    return fullname;
  }

  


  public static void main(String[] args) {
    Person person1 = new Person();
    person1.Name="Supragya";
    person1.Age=20;
    person1.PhoneNumber="98273892823687";
    person1.fname="Shreeja";
    person1.lname="basnet";
    
    System.out.println(person1.greeting());
    System.out.println(person1.BirthYear (2024));
   
    
    System.out.println(person1.fullname());
    

  }
}
