<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%-- 코드가 너무 복잡해!! --%>
<%--    <li> id= <%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--    <li> username= <%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
<%--    <li> age= <%=((Member)request.getAttribute("member")).getAge()%></li>--%>

<%-- jsp에서 제공하는 프로퍼티 접근법--%>
    <li> id= ${member.id}</li>
    <li> id= ${member.username}</li>
    <li> id= ${member.age}</li>

</ul>
<a href="/index.html">메인</a>
</body>
</html>

