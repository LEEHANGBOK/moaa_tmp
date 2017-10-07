package drives.google;


import java.io.IOException;
import java.util.Arrays;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;

public class test {
	
	public static Drive getDriveService() throws IOException{
		//인증을 통한 accesstoken 넣으면
		String accessToken = "ya29.GlvZBFbowMsu6xpMwwRSTrqMyMFSPxwrzDeWC3ki0TjJC_u7YKVekBQ9_AJmnBH0nN6XmO5CXTfigjbHIpL30cLHRe_lnMyvcFfjN8YNGZnPyGqZ4m8BgsnShFsJ";
		Credential credential = new GoogleCredential().setAccessToken(accessToken);
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
		       System.out.println("File ID: " + file.getId());

		      return file;
		    } catch (IOException e) {
		      System.out.println("An error occurred: " + e);
		      return null;
		    }
		  }

	public static void main(String[] args) throws IOException {
		Drive service = getDriveService();
		//폴더 생성 코
//		File fileMetadata = new File();
//		fileMetadata.setTitle("test");
//		fileMetadata.setMimeType("application/vnd.google-apps.folder");
//
//		File file = service.files().insert(fileMetadata)
//		    .setFields("id")
//		    .execute();
//		System.out.println("Folder ID: " + file.getId());
//		String id = file.getId();
		insertFile(service, "test2", "","","image/png", "/Users/ikhwan/desktop/test.png" );
	}
}
