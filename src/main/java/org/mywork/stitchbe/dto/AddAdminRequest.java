package org.mywork.stitchbe.dto;

import lombok.Data;

@Data
public class AddAdminRequest {
    private String email;
    private String password;
}
