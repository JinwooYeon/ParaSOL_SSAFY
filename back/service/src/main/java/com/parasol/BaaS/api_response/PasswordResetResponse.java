package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.Password;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PasswordResetResponse extends Password {
}
