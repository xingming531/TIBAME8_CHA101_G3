package tw.idv.petradisespringboot.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.member.dto.*;
import tw.idv.petradisespringboot.member.service.MemberService;
import tw.idv.petradisespringboot.member.vo.AddressInfo;

import java.util.List;

@RestController
@RequestMapping("/members")
class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping("/update")
    ResponseEntity<MemberDTO> update(@RequestBody UpdateDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @PostMapping("/sign-up")
    ResponseEntity<MemberDTO> signUp(@RequestHeader String host, @RequestBody SignUpDTO dto) {
        return new ResponseEntity<>(service.signUp(host, dto), HttpStatus.CREATED);
    }

    @GetMapping("/id={id}")
    ResponseEntity<MemberDTO> one(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/login")
    ResponseEntity<ObjectNode> login(@RequestBody LoginDTO dto) {
        var member = service.login(dto.getAccount(), dto.getPassword());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("id", member.getId());
        return ResponseEntity.ok(node);
    }

    @GetMapping("/districts")
    ResponseEntity<List<AddressInfo>> districts() throws Exception {
        return ResponseEntity.ok(service.getAddressInfo());
    }

    @PostMapping("/change-password")
    ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO dto) {
        service.changePassword(dto.getId(), dto.getOldPassword(), dto.getNewPassword());
        return ResponseEntity.ok("密碼修改成功");
    }

    @PutMapping("/change-access")
    ResponseEntity<MemberDTO> changeAccess(@RequestBody ChangeAccessDTO dto) {
        return ResponseEntity.ok(service.changeAccess(dto.getId(), dto.getAccess()));
    }

    @GetMapping("/verify-email")
    ResponseEntity<String> verifyEmail(@RequestParam String token) {
        service.verifyEmail(token);
        return ResponseEntity
                .ok("驗證成功");
    }
}