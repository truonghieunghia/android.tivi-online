package groupbase.vn.thn.tivionline.data.json;

import java.util.ArrayList;

import groupbase.vn.thn.baselibs.util.JsonAnnotation;
import groupbase.vn.thn.tivionline.data.entry.TiviEntry;

/**
 * Created by nghiath on 5/7/15.
 */
public class TiviListJson {

    @JsonAnnotation(FieldName = "status", FieldType = Integer.class)
    public int status;

    @JsonAnnotation(FieldName = "data", FieldType = TiviEntry.class, isList = true, isObject = true)
    public ArrayList<TiviEntry> listTiviEntry = new ArrayList<>();

    @JsonAnnotation(FieldName = "message", FieldType = String.class)
    public String message;

    @JsonAnnotation(FieldName = "total", FieldType = String.class)
    public String total;
}
