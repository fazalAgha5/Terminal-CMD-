import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Method  {

	private File file;
	private Desktop desktop;


	public void action(String path,String input) {
		String[] breakinput= input.split(" ");
		//for open method
		if(breakinput[0].equalsIgnoreCase("xdg-open")){
			String other="";
			for(int k=9; k<input.length(); k++)
				other+=input.charAt(k);
			this.openFile(path, other);
		}
		//delete the directory
		else if(breakinput[0].equalsIgnoreCase("rm") && breakinput[1].equalsIgnoreCase("-r")){
			String other="";
			for(int k=6; k<input.length(); k++)
				other+=input.charAt(k);
			this.deleteDirectory(path, other);
		}
		//delete the file
		else if(breakinput[0].equalsIgnoreCase("rm")){
			String other="";
			for(int k=3; k<input.length(); k++)
				other+=input.charAt(k);
			this.deleteFile(path,other);
		}
		//create File and Write in it
		else if(breakinput[0].equalsIgnoreCase("cat")&& breakinput[1].equalsIgnoreCase(">")){
			String other="";
			for(int k= 6;k<input.length(); k++)
				other+=input.charAt(k);
			this.createFile(path,other);
		}
		//create File
		else if(breakinput[0].equalsIgnoreCase("touch") ){
			String other="";
			for(int k=6; k<input.length(); k++)
				other+=input.charAt(k);
			this.createEmptyFile(path, other);
		}
		// create the Folder
		else if(breakinput[0].equalsIgnoreCase("mkdir")){
			String other="";
			for(int k=6; k<input.length(); k++)
				other+=input.charAt(k);

			this.createDirectory(path, other);
		}

		//rename the file
		else if(breakinput[0].equalsIgnoreCase("rn")){
			String other="";
			for(int k=3; k<input.length(); k++)
				other+=input.charAt(k);
			this.rename(path, other);
		}

		//Copy a file
		else if(breakinput[0].equalsIgnoreCase("cp")){
			String other="";
			for(int k=3; k<input.length(); k++)
				other+=input.charAt(k);
			this.copeFile(path, other);
		}

		//move a file
		else if(breakinput[0].equalsIgnoreCase("mv")){
			String other="";
			for(int k=3; k<input.length(); k++)
				other+=input.charAt(k);
			this.moveFile(path, other);
		}
		//Find File in Whole pc
		else if(breakinput[0].equalsIgnoreCase("locate") && breakinput[1].equalsIgnoreCase("-l")){
			String other="";
			for(int k=10; k<input.length(); k++)
				other+=input.charAt(k);
			File[] file= new File[2];
			file[0]=new File("/home/fazal");
			file[0]=new File("/media/fazal");
			this.searchFile(file, other);
			if(!found){
				System.out.println("Can't Found");
			}
		}
		//Find File in perticular Path
		else if(breakinput[0].equalsIgnoreCase("locate"))
		{
			String other="";
			for(int k=7; k<input.length(); k++)
				other+=input.charAt(k);
			this.searchFile(new File(path).listFiles(), other);
			if(!found){
				System.out.println("Can't Found");
			}

		}

		else
			System.out.println("commond not found");

	}
	//this ver for check the file is found or not
	private boolean found=false;
	//Find Method
	private void searchFile(File[] files, String name){
		for(File data:files){
			if(data.getName().equalsIgnoreCase(name)){
				System.out.println("Found on "+data.getPath());
				found=true;
				return;
			}

			else if(data.isDirectory())
				this.searchFile(data.listFiles(), name);
		}

	}

	//rename the file
	public boolean rename(String path, String input){
		System.out.println(input);
		String [] splitInput=input.split(",");
		file= new File(path+splitInput[0]);
		File file2= new File(path+splitInput[1]);

		if(file.exists()){
			file.renameTo(file2);
			return true;
		}
		else 
			return false;
	}

	//copy the file
	public void copeFile(String path, String input)
	{
		System.out.println(input);
		String[] breakInputs=input.split(" ");
		path+=breakInputs[0];
		breakInputs[1]+="/"+breakInputs[0];
		file=new File(path);
		File file2=new File(breakInputs[1]);
		try {

			Files.copy(file.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Can't Found Directory");
		}

	}
	//move the file
	public void moveFile(String path, String input)
	{
		String[] breakInputs=input.split(" ");
		path+=breakInputs[0];
		breakInputs[1]+="/"+breakInputs[0];
		file=new File(path);
		File file2=new File(breakInputs[1]);
		try {

			Files.move(file.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Can't Found Directory");
		}

	}

	//Check the input is in directory 
	public String isItInDirectory(String path, String input) {
		file = new File(path);
		File[] storedFiles = file.listFiles();
		try {
			for (File f : storedFiles) {
				if (f.getName().equalsIgnoreCase(input) && f.isDirectory() ) 
					return f.getName();
			}	
		} catch (Exception a) {
			System.out.println(a);
		}
		return null;
	}

	//print every thing that Directory contain
	public void printDirectory(String path){
		File file = new File(path);
		File[] storedFiles = file.listFiles();
		try {
			for (File f : storedFiles) {
				if(f.getName().charAt(0)!='.')
					System.out.println(f.getName());
			}

		} catch (Exception a) {
			System.out.println(a);
		}
	}


	//open a file
	private void openFile(String path,String input) {
		desktop=desktop.getDesktop();
		path=path.concat(input);
		file = new File(path);
		try{
			if(file.exists())
				desktop.open(file);
			else
				System.out.println("Can't found file");
		}catch(Exception a){
			System.out.println("File can't found");
		}
	}

	//delete a file
	private void deleteFile(String path,String input) {
		path=path.concat(input);
		file = new File(path);
		try{
			if(file.exists())
				file.delete();
			else
				System.out.println("Can't Found A file");
		}catch(Exception a){
			System.out.println(a);
		}

	}

	//delete a folder
	private void deleteDirectory(String path, String input){
		file = new File(path.concat("/"+input));
		if(file.exists()){
			this.recursiveDelete(file);
			file.delete();
		}
		else
			System.out.println("directory not found");
	}

	//delete a folder that contain file or sub folders
	private void recursiveDelete(File root){
		try{
			if(root.isFile())
				root.delete();
			else{
				File[] fileList=root.listFiles();
				for(File f: fileList){
					if(f.isDirectory()){
						recursiveDelete(f);
						f.delete();
					}
					else
						f.delete();
				}
			}
		}catch(Exception a){
			System.out.println(a);
		}
	}

	//create a folder
	private void createDirectory(String path, String name){
		try{
			file= new File(path+name);
			file.mkdir();
		}catch(Exception a){
			System.out.println("Creating new Dir"+a);
		}
	}

	//create a empty file
	private void createEmptyFile(String path,String name){
		try{
			file= new File(path+name);
			file.createNewFile();
		}catch(Exception a){
			System.out.println("Creating a new File"+a);
		}
	}

	//create A directory and write in it
	private void createFile(String path,String name){
		Scanner scan=new Scanner(System.in);
		try{
			file= new File(path+name);
			file.createNewFile();
			String input=scan.nextLine();
			PrintWriter write= new PrintWriter(file);
			while(!input.equalsIgnoreCase("exit")){
				write.println(input);
				input=scan.nextLine();
			}
			write.close();
		}catch(Exception a){
			System.out.println("Creating a new File"+a);
		}
	}


}
