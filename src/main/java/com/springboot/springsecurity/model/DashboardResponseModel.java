package com.springboot.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardResponseModel {
    private String displayMessage;
    private String authenticationName;
    //	private String authenticationAuthorities;
//	private String authenticationCredentials;
//	private String authenticationDetails;
    private Object authenticationPricipal;

    public DashboardResponseModel(String displayMessage, Object authenticationPricipal) {
        super();
        this.displayMessage = displayMessage;
        this.authenticationPricipal = authenticationPricipal;
    }

    public DashboardResponseModel(String displayMessage) {
        super();
        this.displayMessage = displayMessage;
    }

}
