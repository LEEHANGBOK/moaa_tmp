<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String saveDir = "C:/upload"; //절대경로 입력 시 해당 디렉토리에 있는 파일들을 페이지 상에 출력, 클릭 시 다운로드 가능
File dir = new File(saveDir);
File[] fileList = dir.listFiles(); %>

           
            <div class="box-header with-border">
            <h3 class="box-title"></h3>
					<div class="btn-group" style = "padding-left:35px; float:left;">
					<form action="fileupload_icon.jsp" method="post"  enctype="multipart/form-data">
					<input type="file" multiple id="file" name="userfile" style="display:inline" >
					<script>
					var curStyle=document.getElementById("file").style.display;
						document.getElementById("file").style.display="none";
					</script>
					<button id="newFile" onclick="check()" class="btn btn-default">올리기</button>	
					<button type="button" class="btn btn-default">내려받기</button>
                    <button type="button" class="btn btn-default">삭제</button>
				 	<script>
				 	function check(){
				 		eventOccur(document.getElementById('file'),'click');
				 	}
				 	
				 	function eventOccur(evEle, evType){
				 		  if (evEle.fireEvent) {
				 		    evEle.fireEvent('on' + evType);
				 		  } else {
				 		    var mouseEvent = document.createEvent('MouseEvents');
				 		    mouseEvent.initEvent(evType, true, false);
				 		    var transCheck = evEle.dispatchEvent(mouseEvent);
				 		    if (!transCheck) {
				 		      console.log("클릭 이벤트 발생 실패!");
				 		    }
				 		  }
				 		}
				 	</script>
				 </form> 					
				</div>
				
				      <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                          <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                          <li><a href="dashboard.jsp">자세히 보기</a></li>
                          <li><a href="dashboard_icon.jsp">아이콘 보기</a></li>
                        </ul>
                      </div>
				 	
          
            </div>
            <!-- /.box-header -->
            <div class="box-body">
               <form>
                 <ul id="checkboxTbl" style="list-style-type: none; margin:0; padding:0;">

                  <%
                  for(File tempFile : fileList){
                     if(tempFile.isFile()) {
                  %>
                 		<li style = "padding-top: 30px; padding-left:30px; padding-right:50px; float:left;">
                 			<input type="checkbox" name="selectfile" value="1" /><p>
                 			<%
                    	String tempFileName=tempFile.getName();
                        int j= tempFileName.indexOf(".");
                    	switch(tempFileName.substring(j+1)){
                    	case "pdf":
                    		%><img src="etc/iconimage/icon_pdf.png" width="120" height="120">
                    		<%
                    	break;
                    	case "jpg":
                    		%>
                    		<img src="etc/iconimage/icon_jpg.png" width="120" height="120">
                    	<%
                    	break;
                    	case "png":
                    		%>
                    		<img src="etc/iconimage/icon_png.png" width="120" height="120">
                    		<%
                    	break;
                    	case "PNG":
                    		%>
                    		<img src="etc/iconimage/icon_png.png" width="120" height="120">
                    	<%
                    	break;
                    	case "html":
                    		%>
                    		<img src="etc/iconimage/icon_html.png" width="120" height="120">
                    	<%
                    	break;
                    	case "css":
                    		%>
                    		<img src="etc/iconimage/icon_css.png" width="120" height="120">
                    	<%
                    	break;
                    	case "xlsx":
                    		%>
                    		<img src="etc/iconimage/icon_excel.png" width="120" height="120">
                    	<%
                    	break;
                    	case "exe":
                    		%>
                    		<img src="etc/iconimage/icon_exe.png" width="120" height="120">
                    		<%
                    	break;
                    	case "mp3":
                    		%>
                    		<img src="etc/iconimage/icon_mp3.png" width="120" height="120">
                    		<%
                    	break;
                    	case "txt":
                    		%>
                    		<img src="etc/iconimage/icon_txt.png" width="120" height="120">
                    	<%
                    	break;
                    	case "pptx":
                    		%>
                    		<img src="etc/iconimage/icon_powerpoint.png" width="120" height="120">
                    	<%
                    	break;
                    	case "avi":
                    		%>
                    		<img src="etc/iconimage/icon_avi.png" width="120" height="120">
                    		<%
                    	break;
                    	case "wmv":
                    		%>
                    		<img src="etc/iconimage/icon_video.png" width="120" height="120">
                    	<%
                    	break;
                    	case "mp4":
                    		%>
                    		<img src="etc/iconimage/icon_mp4.png" width="120" height="120">
                    		<%
                    	break;
                    	case "docx":
                    		%>
                    		<img src="etc/iconimage/icon_word.png" width="120" height="120">
                    		<%
                    	break;
                    	case "war":
                    		%>
                    		<img src="etc/iconimage/icon_zip.png" width="120" height="120">
                    		<%
                    	break;
                    	case "zip":
                    		%>
                    		<img src="etc/iconimage/icon_zip.png" width="120" height="120">
                    		<%
                    	break;
                    	default : 
                    		%>
                    		<img src="etc/iconimage/icon_default.png" width="120" height="120">
                    		<%
                    	}
                    	%><p>
                    	<div align="center">
                    	<%
                        out.write("<a href=\"" + request.getContextPath() + "/FileDownload?filename=" + java.net.URLEncoder.encode(tempFileName, "UTF-8") + "\">" + tempFileName.substring(0,j) + "</a><br>");
                        %></div><p>
                        <div align="center" style="color:  #808080">
                        <%
                     		long length = tempFile.length();
                           double LengthbyUnit = (double)length;
                           int Unit=0;
                           while(LengthbyUnit >1024 && Unit<5){
                              LengthbyUnit = LengthbyUnit/1024;
                              Unit++;
                           }
                           DecimalFormat df = new DecimalFormat("#,##0.00");
                           out.println(df.format(LengthbyUnit));
                           switch(Unit){
                           case 0:
                           out.println("Bytes");
                           break;
                           case 1:
                           out.println("KB");
                           break;
                           case 2:
                           out.println("MB");
                           break;
                           case 3:
                           out.println("GB");
                           break;
                           case 4:
                           out.println("TB");
                           break;
                           }                           
                           %></div>
                 		</li>
                 		<%
                     }
                  }
                 		%>
                 </ul>
                </form>
            </div>
            <!-- ./box-body -->
          </div>