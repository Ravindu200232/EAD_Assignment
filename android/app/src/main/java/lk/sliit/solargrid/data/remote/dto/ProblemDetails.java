package lk.sliit.solargrid.data.remote.dto;

import java.util.List;
import java.util.Map;

public class ProblemDetails {

    public String type;

    /** Short headline, for example "Validation failed". */
    public String title;

    public Integer status;

    /** The sentence written for the user, when the API adds one. */
    public String detail;

    /** Field name, then the messages for that field. */
    public Map<String, List<String>> errors;
}
