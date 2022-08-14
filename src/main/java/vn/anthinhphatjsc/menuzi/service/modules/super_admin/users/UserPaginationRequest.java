package vn.anthinhphatjsc.menuzi.service.modules.super_admin.users;

import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.core.PaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPaginationRequest extends PaginationRequest {
    private String searchName;
    private String searchUsername;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchName != null && !searchName.isEmpty()) {
            list.add(new Filter("name", QueryOperator.LIKE, searchName, null));
        }
        if (searchUsername != null && !searchUsername.isEmpty()) {
            list.add(new Filter("username", QueryOperator.LIKE, searchUsername, null));
        }
        return list;
    }
}
