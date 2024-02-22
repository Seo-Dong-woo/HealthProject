package com.sist.vo;

import lombok.Data;
// HttpSession => Principal
@Data
public class SessionInfo {
   private String userId;
   private String userName;
   private String sex;
   private String email;
   private String phone;
   private String address;
}
