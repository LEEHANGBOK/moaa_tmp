<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="box-header with-border">
            <h3 class="box-title"></h3>
					<div class="btn-group" style = "padding-left:35px;" "float:left;">
					<form action="fileupload.jsp" method="post"  enctype="multipart/form-data">
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
			<div class="table-responsive">
               <form>
                 <table id="checkboxTbl" class="table no-margin">
                  <thead>
                  <tr>
                  	<th></th>
                  	<th>종류</th>
                    <th>파일명</th>
                    <th>타입</th>
                    <th>크기</th>
                    <th>최근 수정 날짜</th>
                  </tr>
                  </thead>
                  <tbody>
                  
                  <%
                  for(File tempFile : fileList){
                     if(tempFile.isFile()) {
                  %>
                    <tr>
                    	
                    	<td width="3%"><input type="checkbox" name="selectfile" value="1" /></td> 
                    	<td width="5%"><%
                    	String tempFileName=tempFile.getName();
                        int j= tempFileName.indexOf(".");
                    	switch(tempFileName.substring(j+1)){
                    	case "pdf":
                    		%><img src="etc/iconimage/icon_pdf.png" width="23" height="23">
                    		<%
                    	break;
                    	case "jpg":
                    		%>
                    		<img src="etc/iconimage/icon_jpg.png" width="23" height="23">
                    	<%
                    	break;
                    	case "png":
                    		%>
                    		<img src="etc/iconimage/icon_png.png" width="23" height="23">
                    		<%
                    	break;
                    	case "PNG":
                    		%>
                    		<img src="etc/iconimage/icon_png.png" width="23" height="23">
                    	<%
                    	break;
                    	case "html":
                    		%>
                    		<img src="etc/iconimage/icon_html.png" width="23" height="23">
                    	<%
                    	break;
                    	case "css":
                    		%>
                    		<img src="etc/iconimage/icon_css.png" width="23" height="23">
                    	<%
                    	break;
                    	case "xlsx":
                    		%>
                    		<img src="etc/iconimage/icon_excel.png" width="23" height="23">
                    	<%
                    	break;
                    	case "exe":
                    		%>
                    		<img src="etc/iconimage/icon_exe.png" width="23" height="23">
                    		<%
                    	break;
                    	case "mp3":
                    		%>
                    		<img src="etc/iconimage/icon_mp3.png" width="23" height="23">
                    		<%
                    	break;
                    	case "txt":
                    		%>
                    		<img src="etc/iconimage/icon_txt.png" width="23" height="23">
                    	<%
                    	break;
                    	case "pptx":
                    		%>
                    		<img src="etc/iconimage/icon_powerpoint.png" width="23" height="23">
                    	<%
                    	break;
                    	case "avi":
                    		%>
                    		<img src="etc/iconimage/icon_avi.png" width="23" height="23">
                    		<%
                    	break;
                    	case "wmv":
                    		%>
                    		<img src="etc/iconimage/icon_video.png" width="23" height="23">
                    	<%
                    	break;
                    	case "mp4":
                    		%>
                    		<img src="etc/iconimage/icon_mp4.png" width="23" height="23">
                    		<%
                    	break;
                    	case "docx":
                    		%>
                    		<img src="etc/iconimage/icon_word.png" width="23" height="23">
                    		<%
                    	break;
                    	case "war":
                    		%>
                    		<img src="etc/iconimage/icon_zip.png" width="23" height="23">
                    		<%
                    	break;
                    	case "zip":
                    		%>
                    		<img src="etc/iconimage/icon_zip.png" width="23" height="23">
                    		<%
                    	break;
                    	default : 
                    		%>
                    		<img src="etc/iconimage/icon_default.png" width="23" height="23">
                    		<%
                    	}
                    	%>
                    	</td>                 
                    	<td width="68%"><%
                        out.write("<a href=\"" + request.getContextPath() + "/FileDownload?filename=" + java.net.URLEncoder.encode(tempFileName, "UTF-8") + "\">" + tempFileName.substring(0,j) + "</a><br>");
                        %></td>                        
                        <td width="7%"><%
                        out.println(tempFileName.substring(j+1));%></td>                  
                           <td width="7%"><%
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
                              
                           %></td>


                        <td width="10%"><%   
                              long lastModified = tempFile.lastModified();
                              Date date = new Date(lastModified);
                              SimpleDateFormat sfd = new SimpleDateFormat("yyyy/MM/dd hh:mm");
                              out.println(sfd.format(date));

                         }
                      }
                       %>
                        </td>
                  </tr>
                  </tbody>
                </table>
                </form>
              </div>
            </div>
            <!-- ./box-body -->
          </div>