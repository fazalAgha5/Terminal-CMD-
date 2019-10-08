import java.io.File;
import java.io.IOException;

public class Stack {
	private String add="/";
	private Link head;
	private Link tail;

	
	Method method= new Method();
	//constructor 
	
	public Stack(){
		this.push(add);
	}

	int counter=0;
	
	public void push(String path){
		counter++;
		Link newNode;
		if(head==null){
			newNode=new Link(path);
			tail=newNode;
		}
		else{
			add=add.concat(path.concat("/"));
			newNode=new Link(add);
			newNode.setNext(head);		
		}
		head=newNode;
	}

	public void input(String input) {
		try{
			String[] inputBreak=input.split(" ");
			//go to root
			if(input.equalsIgnoreCase("cd /") || input.equalsIgnoreCase("cd //")){
				head= null;
				this.push("/");
				this.push("/home");
				this.pop();
			}
			//for pop
			else if(input.equalsIgnoreCase("cd .."))
				this.pop();

			//befor Push  first check the directory
			else if(inputBreak[0].equalsIgnoreCase("cd") ){
				String[] inputs=null;
				try{
					String[] inputs2=input.split("\"");
					String inp = head.getAddress()+inputs2[1];
					File file= new File(inp);
					if(file.exists()){	
							this.push(inputs2[1]);
					}
					else
						System.out.println("Directry not found");
					return;
					}catch(Exception a){
				
				}
				
				try{
					inputs=inputBreak[1].split("/");
				}catch(Exception a){
					System.out.println("Commond not found");
					return;
				}
				for(int k=0; k<inputs.length; k++){
					String inp = head.getAddress()+inputs[k];
					File file= new File(inp);
					if(file.exists()){
						if(!inputs[k].equals(""))	
							this.push(inputs[k]);
					}
					else
						System.out.println("Directry not found");
				}
			}
			
			//print the directory
			else if(inputBreak[0].equalsIgnoreCase("ls") || inputBreak[0].equalsIgnoreCase("dir")){
			System.out.println("---------------------------------------------------------------------------------------");
				method.printDirectory(head.getAddress());
		    System.out.println("---------------------------------------------------------------------------------------");
			}

			//print the 
			else if(inputBreak[0].equalsIgnoreCase("pwd"))
				System.out.println(this.gettop());

			//clear the screen
			else if(inputBreak[0].equalsIgnoreCase("clear"))
				for(int k=0; k<50; k++)
					System.out.println();
			//other Action
			else 
				method.action(head.getAddress(),input);

		}catch(Exception a){
			System.out.println(a.getMessage());
		}

	}


	public Link pop(){
		if(head.getAddress().equalsIgnoreCase(tail.getAddress())){
			System.out.println("path Invalid");
			return null;
		}
		else{
			Link node= head;
			head=node.getNext();
			add=head.getAddress();
			return node;
		}
	}

	public Link  gettop(){
		return head;
	}

	public Link getHead(){
		return this.head;
	}


}
