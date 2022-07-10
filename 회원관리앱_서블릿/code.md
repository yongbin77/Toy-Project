```java

@Getter
@Setter
public class Member {
    //Id는회원 저장소에 저장이 되면 발급이 된다.
    private Long id;
    private String username;
    private int age;
    //기본 생성자
    public Member(){

    }

    //이름과 나이를 갖는 생성자
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}


@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

        private MemberRepository memberRepository = MemberRepository.getInstance();

        @Override
        protected void service(HttpServletRequest request, HttpServletResponse
                response)
                throws ServletException, IOException {
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            List<Member> members = memberRepository.findAll();
            PrintWriter w = response.getWriter();
            w.write("<html>");
            w.write("<head>");
            w.write(" <meta charset=\"UTF-8\">");
            w.write(" <title>Title</title>");
            w.write("</head>");
            w.write("<body>");
            w.write("<a href=\"/index.html\">메인</a>");
            w.write("<table>");
            w.write(" <thead>");
            w.write(" <th>id</th>");
            w.write(" <th>username</th>");
            w.write(" <th>age</th>");
            w.write(" </thead>");
            w.write(" <tbody>");

            for (Member member : members) {
                w.write(" <tr>");
                w.write(" <td>" + member.getId() + "</td>");
                w.write(" <td>" + member.getUsername() + "</td>");
                w.write(" <td>" + member.getAge() + "</td>");
                w.write(" </tr>");
            }
            w.write(" </tbody>");
            w.write("</table>");
            w.write("</body>");
            w.write("</html>");
        }
    }

public class MemberRepository {
    //static으로만 했기떄문에 MemberReposiotory가 아무리 많아도 딱 하나만 생성

    private static Map<Long,Member> store = new HashMap<>();

    //아이디가 하나씩 증가하는 시퀀스
    private static long sequence = 0L;

    //싱글톤 만듦
    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){

    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }
    public Member findbyId(Long id){
        return store.get(id);
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
         store.clear();
    }
}

```


