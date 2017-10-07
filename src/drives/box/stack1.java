package drives.box;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;
import com.box.sdk.BoxFile;

public class stack1 {
	public static void main(String args[]) throws IOException {
		//인증으로 얻은 accesstoken넣으면
		String access_token = "";
		
		BoxAPIConnection api = new BoxAPIConnection(access_token);
        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
        System.out.format("Welcome, %s <%s>!\n\n", userInfo.getName(), userInfo.getLogin());
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        
        //파일 업로드 관련 줄 
//        FileInputStream stream = new FileInputStream("/Users/ikhwan/downloads/KakaoTalk_2017-09-26-15-15-32_Photo_54.jpeg");
//        rootFolder.uploadFile(stream, "KakaoTalk_2017-09-26-15-15-32_Photo_54.jpeg");
//        stream.close();
//		System.out.println(rootFolder.getInfo());
		 
		
		for (BoxItem.Info itemInfo : rootFolder) {
			
		    System.out.format("[%s] %s\n"+"Size: ", itemInfo.getID(), itemInfo.getName());
		    BoxFile boxFile = new BoxFile(api, itemInfo.getID());
		    System.out.println(boxFile.getInfo().getSize());
		}
	}
}
