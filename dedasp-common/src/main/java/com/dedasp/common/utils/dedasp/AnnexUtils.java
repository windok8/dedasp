package com.dedasp.common.utils.dedasp;

public class AnnexUtils {

    public static String handlerAnnexPath(String path){
        int index = path.lastIndexOf("\\");
        if(index < 0) {
            index = path.lastIndexOf("/");
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("&lt;div&gt;&lt;a href=&quot;");
        stringBuffer.append(path);
        stringBuffer.append("&quot; target=&quot;_blank&quot;&gt;");
        stringBuffer.append(path.substring(index + 1));
        stringBuffer.append("&lt;/a&gt;&lt;/div&gt;");

        return stringBuffer.toString();
    }
}
