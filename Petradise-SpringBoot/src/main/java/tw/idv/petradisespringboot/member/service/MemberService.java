package tw.idv.petradisespringboot.member.service;

import tw.idv.petradisespringboot.member.dto.MemberDTO;
import tw.idv.petradisespringboot.member.dto.SignUpDTO;
import tw.idv.petradisespringboot.member.dto.UpdateDTO;
import tw.idv.petradisespringboot.member.vo.AddressInfo;
import tw.idv.petradisespringboot.member.vo.MemberAccess;

import java.util.List;

public interface MemberService {
    MemberDTO login(String account, String password);

    MemberDTO signUp(String host, SignUpDTO dto);

    MemberDTO getById(Integer id);

    MemberDTO update(UpdateDTO dto);

    void changePassword(Integer id, String oldPassword, String newPassword);

    void verifyEmail(String token);

    // 註冊時地址縣市/地區下拉選單
    List<AddressInfo> getAddressInfo() throws Exception;

    List<MemberDTO> getAll();

    MemberDTO changeAccess(Integer id, MemberAccess access);
}
