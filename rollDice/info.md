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
![출력1](https://user-images.githubusercontent.com/99226598/174201222-25f2bef5-feb6-4d77-b5df-69a27b97f405.png)
![출력2](https://user-images.githubusercontent.com/99226598/174201234-0f522e0d-aa62-48de-a265-8d4cb5536a7a.png)

```
# 내가 모르던 개념 

## 왜 throws IOException 을 추가할까?



