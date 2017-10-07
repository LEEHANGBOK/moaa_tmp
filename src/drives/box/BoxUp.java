package drives.box;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;

public class BoxUp extends Thread{
	
	private String access_Token ;
	private String[] filepath;
	private String[] fileID;
	
	public BoxUp(String[] path, String token) {
		//filepath=path;
		filepath=path;
		fileID= new String[filepath.length];
		access_Token=token;
	}
	
	
	public void run() {
		//인증으로 얻은 accesstoken넣으면
		String access_token = access_Token;

		BoxAPIConnection api = new BoxAPIConnection(access_token);
        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
        System.out.format("Welcome, %s <%s>!\n\n", userInfo.getName(), userInfo.getLogin());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        int num = 0; 
        for(String path : filepath) {
        	if(path!="") {
        	int n = path.lastIndexOf("/");
        	int size = path.length();
        	
        //저장된 파일경
        	String foldername = path.substring(n+1, size);
        //파일 업로드 관련 줄 
        FileInputStream stream;
		try {
			
			stream = new FileInputStream(path);
			BoxItem.Info iteminfo = rootFolder.uploadFile(stream, foldername);
			fileID[num]=iteminfo.getID();
			num++;
	        stream.close();
	        
			//System.out.println(rootFolder.getInfo());
			 System.out.println("Box file is finished at : "+foldername);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        	}}
        try {
        	BufferedWriter a = new BufferedWriter(new FileWriter(System.getProperty("user.home")+System.getProperty("file.separator")+"mission_temp"+System.getProperty("file.separator")+"logfile_Box.txt",true));
        	for( String id : fileID) {
        	a.write(id);
			a.newLine();
				
			}
			a.close();
			System.out.println("Box log file is finished");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
