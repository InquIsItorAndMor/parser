<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery-1.6.4.min.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/workObject.js" ></script>
        <title>Home</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>This is the homepage!</p>
        <input type="button" value="Start" onclick='testtest(${sj});' />
        <p>${sj}<script type="text/javascript">console.log(${sj});</script></p>
    </body>
</html>
