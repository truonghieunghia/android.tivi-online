package groupbase.vn.thn.tivionline.data.entry;

import java.util.ArrayList;

import groupbase.vn.thn.baselibs.util.JsonAnnotation;

/**
 * Created by nghiath on 5/7/15.
 */
public class TiviEntry {

    @JsonAnnotation(FieldName = "link_play", FieldType = LinkPlay.class, isList = true, isObject = true)
    public ArrayList<LinkPlay> linkPlays = new ArrayList<>();

    @JsonAnnotation(FieldName = "permission", FieldType = String.class)
    public String permission;

    @JsonAnnotation(FieldName = "id", FieldType = String.class)
    public String id;

    @JsonAnnotation(FieldName = "category_id", FieldType = String.class)
    public String category_id;

    @JsonAnnotation(FieldName = "image", FieldType = String.class)
    public String image;

    @JsonAnnotation(FieldName = "obj_type", FieldType = String.class)
    public String obj_type;

    @JsonAnnotation(FieldName = "intro_text", FieldType = String.class)
    public String intro_text;

    @JsonAnnotation(FieldName = "name", FieldType = String.class)
    public String name;
}
