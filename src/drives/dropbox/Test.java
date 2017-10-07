package drives.dropbox;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import com.dropbox.core.v1.DbxAccountInfo;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.v1.DbxClientV1;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.v1.DbxWriteMode;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

public class Test {

	private static final String DROP_BOX_APP_KEY = "b0ia3o6jv7sl6en";
	private static final String DROP_BOX_APP_SECRET = "i2znlitfmke8ugz";
	//private static final String ACCESS_TOKEN = "j2zjj8cBlkAAAAAAAAAAD7Pkg01pVvoCcyVBMaPb6pYToow4OJ6m5wXLZ5GMIAKj";
	private static final String USER_ID = "blahblah";
	//DbxClient client = new DbxClient(ACCESS_TOKEN,null,null);
	
	DbxClientV1 dbxClient;

	public DbxClientV1 authDropbox(String dropBoxAppKey, String dropBoxAppSecret)
			throws IOException, DbxException {
		DbxAppInfo dbxAppInfo = new DbxAppInfo(dropBoxAppKey, dropBoxAppSecret);
		DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("USER_ID/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(dbxRequestConfig, dbxAppInfo);
		String authorizeUrl = dbxWebAuthNoRedirect.start();
		System.out.println("1. Authorize: Go to URL and click Allow : " + authorizeUrl);
		System.out.println("2. Auth Code: Copy authorization code and input here ");
		String dropboxAuthCode = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
		DbxAuthFinish authFinish = dbxWebAuthNoRedirect.finish(dropboxAuthCode);
		
		String authAccessToken = authFinish.getAccessToken();
		System.out.println(authAccessToken);
		dbxClient = new DbxClientV1(dbxRequestConfig, authAccessToken);
		System.out.println("Dropbox Account Name: "+ dbxClient.getAccountInfo().displayName);

		return dbxClient;
	}

	/* returns Dropbox size in GB */
	public long getDropboxSize() throws DbxException {
		long dropboxSize = 0;
		DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
		// in GB :)
		dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
		return dropboxSize;
	}

	public void uploadToDropbox(String fileName) throws DbxException,IOException {
		File inputFile = new File(fileName);
		FileInputStream fis = new FileInputStream(inputFile);
		try {
			DbxEntry.File uploadedFile = dbxClient.uploadFile("/" + fileName,DbxWriteMode.add(), inputFile.length(), fis);
			String sharedUrl = dbxClient.createShareableUrl("/" + fileName);
			System.out.println("Uploaded: " + uploadedFile.toString() + " URL "+ sharedUrl);
		} finally {
			fis.close();
		}
	}

	public void createFolder(String folderName) throws DbxException {
		dbxClient.createFolder("/" + folderName);
	}

	public void listDropboxFolders(String folderPath) throws DbxException {
		DbxEntry.WithChildren listing = dbxClient.getMetadataWithChildren(folderPath);
		System.out.println("Files List:");
		for (DbxEntry child : listing.children) {
			System.out.println("	" + child.name + ": " + child.toString());
		}
	}

	public void downloadFromDropbox(String fileName) throws DbxException,IOException {
		FileOutputStream outputStream = new FileOutputStream(fileName);
		try {
			DbxEntry.File downloadedFile = dbxClient.getFile("/" + fileName,null, outputStream);
			System.out.println("Metadata: " + downloadedFile.toString());
		} finally {
			outputStream.close();
		}
	}
	/*
	public void uploadFile(String path, String foldername)
    {
        // Upload "test.txt" to Dropbox
        try {
            InputStream in = new FileInputStream(path);
            FileMetadata metadata = client.files().uploadBuilder(foldername).uploadAndFinish(in);
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
    }
*/
	public static void main(String[] args) throws IOException, DbxException {
		Test test = new Test();
		test.authDropbox(DROP_BOX_APP_KEY, DROP_BOX_APP_SECRET);
		System.out.println("Dropbox Size: " + test.getDropboxSize()+ " GB");
		//test.uploadToDropbox("untitled1.py");
		//javaDropbox.createFolder("tutorial");
		//test.listDropboxFolders("/");
		//test.downloadFromDropbox("America.png");

	}

}
