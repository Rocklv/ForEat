<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>GetFilePath</title>
</head>
<body>
<%
    String imgLogo="img/food.jpg";
    if(request.getAttribute("filePath")!=null)
        imgLogo = (String) request.getAttribute("filePath");
%>
    <input id="foodLogo" name="foodLogo" type="file" class="foodData" value="<%=request.getAttribute("filePath")%>" />
    <img id="imgLogo" src="<%=imgLogo%>" alt="foodLogo" />
</body>
</html>
