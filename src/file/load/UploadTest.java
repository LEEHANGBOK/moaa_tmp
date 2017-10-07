package file.load;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;

import drives.dropbox.TestV2;

public class UploadTest {
	public void boxupload(String[] boxlist, String access_token_B) throws IOException {
		//인증으로 얻은 accesstoken넣으면
				String access_token = access_token_B;
				
				BoxAPIConnection api = new BoxAPIConnection(access_token);
		        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
		        System.out.format("Welcome, %s <%s>!\n\n", userInfo.getName(), userInfo.getLogin());
		        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
		        for(String filepath : boxlist) {
		        	if(filepath != "") {
		        	int n = filepath.lastIndexOf("/");
		        	int size = filepath.length();
		        	
		        //저장된 파일경
		        	String foldername = filepath.substring(n+1, size);
		        //파일 업로드 관련 줄 
		        FileInputStream stream = new FileInputStream(filepath);
		        rootFolder.uploadFile(stream, foldername);
		        stream.close();
				//System.out.println(rootFolder.getInfo());
				 System.out.println("Box file is finished at : "+foldername);
		        }}
				for (BoxItem.Info itemInfo : rootFolder) {
					
				    System.out.format("[%s] %s"+"Size: ", itemInfo.getID(), itemInfo.getName());
				    BoxFile boxFile = new BoxFile(api, itemInfo.getID());
				    System.out.println(boxFile.getInfo().getSize());
				}
	}
	
	public void dropupload(String[] droplist, String access_token_D) {
	      
		final String USER_NAME="";
	    final String ACCESS_TOKEN = access_token_D;
	    DbxRequestConfig config2 = null;
	    DbxClientV2 client = null;
	    FullAccount account2 = null;
	    config2 = new DbxRequestConfig("dropbox/java-tutorial",null); //이부분 빨간줄 떴던분 -> null 추가
        client = new DbxClientV2(config2, ACCESS_TOKEN);
        
        try {
            account2 = client.users().getCurrentAccount();
            System.out.println(account2.getName().getDisplayName());
        }
        catch (DbxException dbxe)
        {
            dbxe.printStackTrace();
        }
		   
        String folderName = "/Folder";// + System.currentTimeMillis();
        
        for(String path : droplist) {
        	if(path!="") {
        	
        	int n = path.lastIndexOf("/");
        	int size = path.length();
        	
        //저장된 파일경
        	String foldername = folderName+path.substring(n+1, size);
        	
        	
        try {
            InputStream in = new FileInputStream(path);
            FileMetadata metadata = client.files().uploadBuilder(foldername).uploadAndFinish(in);
            System.out.println("Drop box finished at : "+path);
        }
        catch (FileNotFoundException fne)
        {
            fne.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (DbxException dbxe)
        {
            dbxe.printStackTrace();
        }
        }}}
	
	
	//google
	
	public static Drive getDriveService(String access_token) throws IOException{
		//인증을 통한 accesstoken 넣으면
		String accessToken = access_token;
		Credential credential= new GoogleCredential().setAccessToken(accessToken);
		return new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
			    .setApplicationName("Google-PlusSample/1.0")
			    .build();
	}

	private static File insertFile(Drive service, String title, String description,
		      String parentId, String mimeType, String filename) {
		    // File's metadata.
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
		    java.io.File fileContent = new java.io.File(filename);
		    FileContent mediaContent = new FileContent(mimeType, fileContent);
		    try {
		      File file = service.files().insert(body, mediaContent).execute();

		      // Uncomment the following line to print the File ID.
		       System.out.println("Google File ID: " + file.getId());
		       
		      return file;
		    } catch (IOException e) {
		      System.out.println("An error occurred: " + e);
		      return null;
		    }
		  }

	public void googleupload(String[] googlelist, String access_token_G) throws IOException {
		Drive service = getDriveService(access_token_G);
		for(String filepath : googlelist) {
			if(filepath != "") {
        	int n = filepath.lastIndexOf("/");
        	int s = filepath.lastIndexOf(".");
        Path source = Paths.get(filepath);
        	
        //저장된 파일경
        	String foldername = filepath.substring(n+1, s);
		insertFile(service, foldername, "","",Files.probeContentType(source), filepath );
			}}
	}

}
