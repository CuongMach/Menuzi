package vn.anthinhphatjsc.menuzi.service.modules.admin.tables;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.core.BaseDTO;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long storeId;
    private Long brandId;
    private String code;
    private String name;
}
