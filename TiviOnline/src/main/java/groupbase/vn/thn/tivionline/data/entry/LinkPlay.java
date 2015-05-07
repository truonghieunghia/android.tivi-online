package groupbase.vn.thn.tivionline.data.entry;

import groupbase.vn.thn.baselibs.util.JsonAnnotation;

/**
 * Created by nghiath on 5/7/15.
 */
public class LinkPlay {
    @JsonAnnotation(FieldName = "mp3u8_link", FieldType = String.class)
    public String mp3u8_link;
    @JsonAnnotation(FieldName = "resolution", FieldType = String.class)
    public String resolution;
}
