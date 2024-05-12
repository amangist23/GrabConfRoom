package com.lowleveldesign.crms.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public static final Map<String, List<String>> ROLE_AUTHORITIES_MAPPING;
    static {
        ROLE_AUTHORITIES_MAPPING = new HashMap<>();
        ROLE_AUTHORITIES_MAPPING.put("SUPER_ADMIN", new ArrayList<>(){
            {
                add(Authority.ADD_USER.name());
                add(Authority.DELETE_USER.name());
                add(Authority.ADD_BUILDING.name());
                add(Authority.DELETE_BUILDING.name());
                add(Authority.READ_USER_DETAILS.name());
                add(Authority.ADD_BOOKING.name());
                add(Authority.CANCEL_BOOKING.name());
                add(Authority.GET_BUILDING_DETAILS.name());
            }
        });
        ROLE_AUTHORITIES_MAPPING.put("ADMIN", new ArrayList<>(){
            {
                add(Authority.ADD_BUILDING.name());
                add(Authority.DELETE_BUILDING.name());
                add(Authority.READ_USER_DETAILS.name());
                add(Authority.ADD_BOOKING.name());
                add(Authority.CANCEL_BOOKING.name());
                add(Authority.GET_BUILDING_DETAILS.name());
            }
        });
        ROLE_AUTHORITIES_MAPPING.put("USER", new ArrayList<>(){
            {
                add(Authority.ADD_BOOKING.name());
                add(Authority.CANCEL_BOOKING.name());
                add(Authority.GET_BUILDING_DETAILS.name());
            }
        });
    }
}
