package study.datajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data //getter, setter
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;
}
