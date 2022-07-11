```java

//항상 controller를 거쳐서 view로 들어가야한다.

@WebServlet(name = "mvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String viewPath = "/WEB-INF/views/new-form.jsp";
        //controller에서 view로 이동할떄 사용
         RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
         dispatcher.forward(request, response); //서블릿에서 jsp로넘어가 호출

    }
}

JSP
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
```

/WEB-INF
> 이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다. 항상 컨트롤러 통해서만 JSP를 호출!

redirect vs forward
> 리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청, 
> 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경됩니다. 
> 반면에 포워드는 서버내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다.
