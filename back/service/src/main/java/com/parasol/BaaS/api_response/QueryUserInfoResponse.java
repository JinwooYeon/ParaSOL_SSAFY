package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.UserInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class QueryUserInfoResponse extends UserInfo {
}
