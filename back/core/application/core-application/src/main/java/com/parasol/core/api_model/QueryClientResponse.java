package com.parasol.core.api_model;

import com.parasol.core.entity.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@SuperBuilder
@NoArgsConstructor
public class QueryClientResponse extends Client {

}
