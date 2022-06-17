# 주사위 (1~6의 숫자표현이 되어있는) 이미지 파일 6개를 복사하여 웹 브라우저에 출력하는 Project


```java

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //ctrl+shift+o 자동 import
public class TwoDice {
	@RequestMapping("/rollDice") 
	public void main(HttpServletResponse response) throws IOException{
		int idx1 = (int)(Math.random()*6)+1;
		int idx2 = (int)(Math.random()*6)+1;
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<img src = 'resources/img/dice"+idx1+".jpg'>");
		out.println("<img src = 'resources/img/dice"+idx2+".jpg'>");
		out.println("</body>");
		out.println("</html>");
	}
}
//새로고침할떄 자동으로 숫자가 바뀐 주사위화면이 출력됨 



```
![출력1](https://user-images.githubusercontent.com/99226598/174201882-f93bd50e-2da1-4fb8-8c3e-38210dc76b10.png)
![출력2](https://user-images.githubusercontent.com/99226598/174201850-ca0a28d9-a190-4c6d-a9f0-054be9e70af0.png)

# 내가 모르던 개념 

## 왜 throws IOException 을 추가할까?



