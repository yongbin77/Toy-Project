package com.codestates.member;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/members",produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {
@PostMapping
 //postMember() 핸들러메서드 = 회원정보를 등록하는 메서드
    public String postMember(@RequestParam("email") String email,
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone){

    System.out.println("# email: " + email);
    System.out.println("# email: " + name);
    System.out.println("# email: " + phone);

    String response =
            "{\"" +
                    "email\":\""+email+"\"," +
                    "\"name\":\""+name+"\",\"" +
                    "phone\":\"" + phone+
                    "\"}";
    return response;

    }
    @GetMapping("/{member-id}")
    //getMember는 특정회원정보를 클라이언트에게 제공하는 핸들러 메서드
    public String getMember(@PathVariable ("member-id") long memberId) {
        System.out.println("# memberId:" + memberId);
    return null;

    }
    @GetMapping
    public String getMembers(){
        System.out.println("# get Members");

        return null;
    }
}
