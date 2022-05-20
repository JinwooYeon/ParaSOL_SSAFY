package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.BankConnectionInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class BankConnectionRequest extends BankConnectionInfo {
}
