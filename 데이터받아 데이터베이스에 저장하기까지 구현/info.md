 > 클라이언트 쪽에서 “Hello, World” 문자열 데이터를 Request Body로 전송한 후에 Spring Data JDBC를 이용해서 이 “Hello, World” 문자열을 H2 데이터베이스에 저장해보기

Hello World 샘플 코드에서 구현해야 되는 클래스 또는 인터페이스

- MessageDto(DTO 클래스)
- MessageController
- MessageMapper
- MessageService
- Message(엔티티 클래스)
- MessageRepository
```java
// 클라이언트가 Request Body로 전달하는 “Hello, World” 문자열을 바인딩하는 DTO 클래스
@Getter
public class MessagePostDto {
    @NotBlank
    private String message;

}
@Getter
@Setter
public class MessageResponseDto {
    private long messageId;
    private String message;
}
// 클라이언트에게 결과를 보여줄 DTO클래스 생성



// 클라이언트 요청을 받고 수행하는 Controller 클래스

@RequestMapping("/v1/messages")
@RestController

// MessageService 클래스와 Mapper클래스를 사용하기 위해 생성자 주입 DI를 사용한다.

public class MessageController {
    private final MessageService messageService;
    private final MessageMapper mapper;

    public MessageController(MessageService messageService,
                             MessageMapper mapper) {
        this.messageService = messageService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMessage(
            @Valid @RequestBody MessagePostDto messagePostDto) {
        Message message =
 //요청받은 Requestbody를 모아논 postDto를 mapper를 통해 서비스 계층의 Message로 올림 
 //요청된 메세지를 service메서드로 구현하여 메세지를 만듦  messageService.createMessage(mapper.messageDtoToMessage(messagePostDto));

// 그 결과를 message에서 responseDto로 다시 돌려 반환한다.
return ResponseEntity.ok(mapper.messageToMessageResponseDto(message));
    }
}
//매퍼메서드 생성 mapstruct로 자동생성 
//꼭 componentmodel 선언하는거 까먹지 말기!!
@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message messageDtoToMessage(MessagePostDto messagePostDto);
    MessageResponseDto messageToMessageResponseDto(Message message);
}


// 데이터 액세스 계층에서 데이터베이스와의 연동을 담당하는 Repository인 MessageRepository 인터페이스
// CrudRepository 는 데이터베이스에 CRUD(데이터 생성, 조회, 수정, 삭제) 작업을 진행하기 위해 Spring에서 지원해주는 인터페이스
//<Message, Long>에서 Long은 Message 엔티티 클래스의 멤버 변수 중에 식별자를 의미하는 @Id 라는 애너테이션이 붙어있는 멤버 변수의 데이터 타입


public interface MessageRepository extends CrudRepository<Message, Long> {
}

//MessageRepository 인터페이스는 MessageService 클래스에서 DI를 통해 주입 받은 후, 
//Message 엔티티 클래스에 포함된 데이터를 데이터베이스에 저장하는데 사용
@Service
public class MessageService {
		// (1)
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);  
    }
}

마지막 Message 엔티티클래스 
// Message 라는 클래스 명은 데이터베이스의 테이블 명에 해당
// @Id 애너테이션을 추가한 멤버 변수는 해당 엔티티의 고유 식별자 역할을 하고
//이 식별자는 데이터베이스의 Primary key로 지정한 컬럼에 해당

@Getter
@Setter
public class Message {  
    @Id    
    private long messageId;
    private String message;
}
```
- 포스트맨 요청 결과 잘 구현됨
![hello world](https://user-images.githubusercontent.com/99226598/180449102-b46de609-a1c7-4436-91bd-e27a4077a608.PNG)


- id 키값이 하나씩 오름
![김용빈입니다](https://user-images.githubusercontent.com/99226598/180449195-0d6b0814-0bdb-453d-afa5-6f71c1564ee3.PNG)

- 데이터 베이스에 저장완료

![캡처123](https://user-images.githubusercontent.com/99226598/180449262-f6e921c8-c50b-4b1f-8bbc-ecb79e850974.PNG)



