package org.mywork.stitchbe.dto;

import lombok.Data;

@Data
public class AddMemberRequest {
    private String email;
    private String password;
}
