package org.mywork.stitchbe.dto;

import lombok.Data;

@Data
public class AddAdminRequest {
    private long admin_id;
    private String password;
}
