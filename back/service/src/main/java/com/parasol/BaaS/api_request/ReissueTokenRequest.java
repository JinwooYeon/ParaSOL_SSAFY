package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.RefreshToken;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReissueTokenRequest extends RefreshToken {
    private String id;
}
