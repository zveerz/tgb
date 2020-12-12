package org.makeriga.tgbot.helpers;

import java.util.Map;

public class MembersHelper {
    public static void ParseMembers(Map<String, String> doorToMemberMappings, Map<String, String> memberAlternativeNames) throws Throwable {
        String setting = System.getProperty("users_mappings");
        if (setting == null)
            return;
        
        // DOOR_NAME=REAL_NAME,ALT_NAME_1,ALT_NAME_2
        // Rihards S.=@Skrubis,rihards
        String users[] = setting.split(";");
        for (String item : users) {
            String parts[] = item.split("="); {
                String allNames[] = parts[1].split(",");
                doorToMemberMappings.put(parts[0], allNames[0]);
                for (String name : allNames) {
                    String alt = name.toLowerCase();
                    if (alt.equals(allNames[0]))
                        continue;
                    assert !memberAlternativeNames.containsKey(alt);
                    memberAlternativeNames.put(name.toLowerCase(), allNames[0]);
                }
            }
        }
        
        // @Xxx to @xxx and xxx
        for (String realName : doorToMemberMappings.values()) {
            if (!realName.startsWith("@")) {
                String alt = realName.toLowerCase();
                memberAlternativeNames.put(alt, realName);
                continue;
            }
            
            String alt = realName.toLowerCase();
            if (!memberAlternativeNames.containsKey(alt))
                memberAlternativeNames.put(alt, realName);
            
            alt = alt.substring(1);
            if (!memberAlternativeNames.containsKey(alt))
                memberAlternativeNames.put(alt, realName);
        }
        
        // andis m. => @Aa
        // andis m  => @Aa
        for (String alt : doorToMemberMappings.keySet()) {
            String a = alt.toLowerCase();
            if (!memberAlternativeNames.containsKey(a))
                memberAlternativeNames.put(alt.toLowerCase(), doorToMemberMappings.get(alt));
            
            a = alt.substring(0, alt.length()-1);
            if (memberAlternativeNames.containsKey(a.toLowerCase()))
                memberAlternativeNames.put(alt.substring(0, alt.length()-1).toLowerCase(), doorToMemberMappings.get(alt));
        }
    }
}
