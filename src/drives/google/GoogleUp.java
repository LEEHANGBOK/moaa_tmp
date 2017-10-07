package drives.google;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;

public class GoogleUp extends Thread {

	private String access_Token ;
	private String[] filepath;
	private Drive service;
	
	public GoogleUp(String[] path, String token) {
		filepath=path;
		access_Token=token;
		
		Credential credential= new GoogleCredential().setAccessToken(access_Token);
		service= new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
			    .setApplicationName("Google-PlusSample/1.0")
			    .build();
	}
	
	public void run() {
		for(String path : filepath) {
			if(path!="") {
	    	int n = path.lastIndexOf("/");
	    	int s = path.lastIndexOf(".");
	    	Path source = Paths.get(path);
	    	
	    		//저장된 파일경
	    	String foldername = path.substring(n+1, s);
	   
			String title = foldername;
			String description="";
			String mimeType="";
			try {
				mimeType = Files.probeContentType(source);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String parentId="";
			
			File body = new File();
		    body.setTitle(title);
		    body.setDescription(description);
		    body.setMimeType(mimeType);
	
		    // Set the parent folder.
		    if (parentId != null && parentId.length() > 0) {
		      body.setParents(
		          Arrays.asList(new ParentReference().setId(parentId)));
		    }
		    
		    
		    
		    // File's content.
		    java.io.File fileContent = new java.io.File(path);
		    FileContent mediaContent = new FileContent(mimeType, fileContent);
		    try {
		    	BufferedWriter a = new BufferedWriter(new FileWriter(System.getProperty("user.home")+System.getProperty("file.separator")+"mission_temp"+System.getProperty("file.separator")+"logfile_Google.txt",true));
		    	File file = service.files().insert(body, mediaContent).execute();
	
		    	// Uncomment the following line to print the File ID.
			   System.out.println("Google File ID: " + file.getId());
			   a.write(file.getId());
			   a.newLine();
			   a.close();
		       
		    } catch (IOException e) {
		      System.out.println("An error occurred: " + e);
		      
		    }
		    }
		}
	}
}
