package vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands;

import vn.anthinhphatjsc.menuzi.service.core.BaseDTO;
import lombok.*;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandDTO extends BaseDTO implements Serializable {
    private Long id;
    private String name;
    private String image;
}
