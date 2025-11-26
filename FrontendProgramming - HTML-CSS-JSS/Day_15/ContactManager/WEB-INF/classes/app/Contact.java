package app;
public class Contact {
  private String name,email,phone;
  public Contact(String n,String e,String p){name=n;email=e;phone=p;}
  public String getName(){return name;}
  public String getEmail(){return email;}
  public String getPhone(){return phone;}
}