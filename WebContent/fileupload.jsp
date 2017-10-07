<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%> 
<%@page import="java.util.Enumeration"%> 
<%@page import="com.oreilly.servlet.*"%> 
<%@page import="com.oreilly.servlet.multipart.*"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta http-equiv="refresh" content="0; url=http://localhost:8888/Mission99/dashboard.jsp">
<title>Insert title here</title>
</head>
<body>
<% 
 request.setCharacterEncoding("utf-8");
    String dir = "C:/upload";
   
    int max =100*1024*1024; // 업로드 파일의 최대 크기 지정 
    String name=""; 
    String subject=""; 
    String filename1=""; 
     
    try{ 
       MultipartRequest m = new MultipartRequest(request, dir, max, "utf-8", new DefaultFileRenamePolicy());          
      name = m.getParameter("name");

     
      System.out.println(name); 
      subject = m.getParameter("subject");  

         
      Enumeration files=m.getFileNames();         
      String file1 =(String)files.nextElement(); 
      filename1=m.getFilesystemName(file1);
        
      String ofile1 =  m.getOriginalFileName("file1");
      
      

       
    }catch(Exception e){ 
        e.printStackTrace(new PrintWriter(out)); 
    } 
%> 
	</body>
</html>