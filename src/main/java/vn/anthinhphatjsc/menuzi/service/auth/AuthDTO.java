package vn.anthinhphatjsc.menuzi.service.auth;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthDTO implements Serializable {
    private String token;
    private String name;
}
