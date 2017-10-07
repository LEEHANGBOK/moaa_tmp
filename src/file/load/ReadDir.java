package file.load;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import drives.box.BoxUp;
import drives.dropbox.DropboxUp;
import drives.google.GoogleUp;

public class ReadDir {
	
	public static String[] Googlelist;
	public static String[] Boxlist;
	public static String[] Droplist;
	public static String[] totallist;
	
	public void zip4j(String direct) {

		try {
			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile(direct+System.getProperty("file.separator")+"uniqlo.zip");
			
			// Build the list of files to be added in the array list
			// Objects of type File have to be added to the ArrayList
			ArrayList filesToAdd = new ArrayList();//압출할 파일 대상들.
			filesToAdd.add(new File("//Users/ikhwan/Downloads/과제1_유니클로.ppsx"));
			
//			filesToAdd.add(new File("c:\\ZipTest\\myvideo.avi"));
//			filesToAdd.add(new File("c:\\ZipTest\\mysong.mp3"));
//			
			// Initiate Zip Parameters which define various properties such
			// as compression method, etc.
			ZipParameters parameters = new ZipParameters();
			
			// set compression method to store compression
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			
			// Set the compression level. This value has to be in between 0 to 9
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			
			// Create a split file by setting splitArchive parameter to true
			// and specifying the splitLength. SplitLenth has to be greater than
			// 65536 bytes
			// Please note: If the zip file already exists, then this method throws an 
			// exception
			zipFile.createZipFile(filesToAdd, parameters, true, 660000);//파일 사이즈 byte단위
			System.out.println(zipFile.getSplitZipFiles());			
			
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	public void read(String dir) {
		String path=dir;
				File dirFile=new File(path);
				File []fileList=dirFile.listFiles();
				int size = fileList.length;
				
				totallist = new String[size];
				
				int drivesize = size/3;
				int flag =0;
				int fluence = 0;
				Googlelist = new String[drivesize];
				Boxlist = new String[drivesize];
				Droplist = new String[drivesize];
				for(File tempFile : fileList) {
				  if(tempFile.isFile()) {
				    String tempPath=tempFile.getParent();
				    String tempFileName=tempFile.getName();
				    System.out.println("Path="+tempPath);
				    System.out.println("FileName="+tempFileName);
//				    totallist[fluence] = tempPath+System.getProperty("file.separator")+tempFileName;
//				    fluence++;
				    switch(flag) {
				    case 0  :System.out.println("G: " + flag +", fluence :" + fluence + ", size : "+ Googlelist.length); 
				    	Googlelist[fluence] = tempPath+System.getProperty("file.separator")+tempFileName;
				    			flag = 1;
				    			break;
				    case 1  :System.out.println("B: " + flag +", fluence :" + fluence + ", size : "+ Boxlist.length); 
				    	Boxlist[fluence] = tempPath+System.getProperty("file.separator")+tempFileName;
	    						flag = 2;
	    						break;
	    					
				    case 2  : System.out.println("D: " + flag +", fluence :" + fluence + ", size : "+ Droplist.length);   
				    	Droplist[fluence] = tempPath+System.getProperty("file.separator")+tempFileName;
				    			flag = 0;
				    			fluence++;
				    			break;
				    	default : System.out.println("finish");
				    			break;
				    }
				    
				  }
				}
	}
	
	
//	public static void actThread(String Drop_access_token , String Box_access_token, String google_access_token) {
//		int flag = 0 ;
//		for (String filepath : totallist) {
//			  switch(flag) {
//			    case 0  : box_up box = new box_up(filepath, Box_access_token);
//			    			box.start();
//			    			flag=1;
//			    			break;
//			    case 1  : drop_up drop = new drop_up(filepath, Drop_access_token);
//			    			drop.start();
//  						flag = 2;
//  						break;
//  					
//			    case 2  : google_up google = new google_up(filepath, google_access_token);
//			    			google.start();
//			    			flag = 0;
//			    			
//			    			break;
//			    	default : System.out.println("finish");
//			    			break;
//		}}
//	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String directory= System.getProperty("user.home")+System.getProperty("file.separator")+"mission_temp";
		boolean result; 
		File f = new File(directory); 
		// 최 하위 디렉토리에 대해서만 생성을 함. 
		// 최 하위 디렉토리의 바루 상위 디렉토리가 존재하지 않을 경우,
		// 디렉토리가 생성되지 못하고, false를 리턴함 
		result = f.mkdir(); 
		System.out.println(result ? "directory is made" : "directory is not made"); 
		// 상위 디렉토리가 존재하지 않을 경우, 상위 디렉토리까지 생성함 
		//result = f.mkdirs(); System.out.println(result == true ? "directory is made" : "directory is not made");
		
		
	

		ReadDir dir = new ReadDir();
		dir.zip4j(directory);
		
	
		dir.read(directory);
		
		String Drop_access_token="kFb_ENWtmyUAAAAAAAAAhsfjcyGuzOLDc5MDHdDGU41xjFGFsUVztfzO_TkF9Wo6";
		String Box_access_token="nxlisAU9KiiXOjRNjFHm8DPtdYDSxlXN";
		String google_access_token="ya29.GlzbBFtyRVlaunQf67PR7C5HRuN3ztdayD1EdRu7AZr6v-9zEWYA9KAo3k07TkVbgvlsbsPiLfJPTaM4gJqzEXxcj3zPdFw0Mb2U_oDzOo87IiG3Fyepjpu_PEsnfA";
		
//		actThread(Drop_access_token,Box_access_token,google_access_token);
		UploadTest upload = new UploadTest();
		
		BoxUp box = new BoxUp(Boxlist, Box_access_token);
		
		DropboxUp drop = new DropboxUp(Droplist, Drop_access_token);
		GoogleUp google = new GoogleUp(Googlelist, google_access_token);
		box.start();
		drop.start();
		google.start();
//		upload.boxupload(Boxlist, Box_access_token);
//		upload.dropupload(Droplist, Drop_access_token);
//		upload.googleupload(Googlelist, google_access_token);
		System.out.println("-------Finish -------");
	}

}
