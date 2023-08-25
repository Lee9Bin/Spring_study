<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%-- 그래서 서블릿을 통해 넘어온 request모델을 getAttribute() 메서드를 활용해 꺼낼수 있지만 너무 복잡해진다.--%>
<%--    <li> id= <%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--    <li> username= <%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
<%--    <li> age= <%=((Member)request.getAttribute("member")).getAge()%></li>--%>
<%--그래서!!!!!!!!!!!!!!!!!!!!!!!--%>
<%-- jsp에서 제공하는 프로퍼티 접근법을 통해 편하게 조회 할수 있다.--%>
    <li> id= ${member.id}</li>
    <li> username= ${member.username}</li>
    <li> age= ${member.age}</li>

</ul>
<a href="/index.html">메인</a>
</body>
</html>

