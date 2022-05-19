package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.LoginInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class BankLoginRequest extends LoginInfo {
}
